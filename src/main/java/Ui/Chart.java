package Ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import DBManager.JdbcConnector;

public class Chart extends JPanel {

    private int userId;

    public Chart(int userId) {
        this.userId = userId;
        setLayout(new BorderLayout());
        setSize(980, 502);
        setBackground(new Color(245, 245, 245));

        JComboBox<String> timeFrameSelector = new JComboBox<>(new String[]{"Weekly", "Yearly"});
        timeFrameSelector.setFont(new Font("Arial", Font.PLAIN, 16));
        timeFrameSelector.setBounds(110, 10, 150, 35);
        timeFrameSelector.setBackground(new Color(200, 200, 255)); 
        timeFrameSelector.setForeground(new Color(0, 51, 102)); 
        timeFrameSelector.setBorder(BorderFactory.createEmptyBorder()); 

        JPanel chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.setBounds(110, 50, 760, 400);
        chartPanel.setBackground(Color.WHITE); 

        timeFrameSelector.addActionListener(e -> {
            String timeFrame = (String) timeFrameSelector.getSelectedItem();
            JFreeChart chart;
            if (timeFrame.equals("Weekly")) {
                chart = createWeeklyChart(fetchWeeklyEmissionsData());
            } else {
                chart = createYearlyChart(fetchYearlyEmissionsData());
            }
            ChartPanel chartDisplay = new ChartPanel(chart);
            chartPanel.removeAll();
            chartPanel.add(chartDisplay, BorderLayout.CENTER);
            chartPanel.validate();
        });

        JFreeChart defaultChart = createWeeklyChart(fetchWeeklyEmissionsData());
        ChartPanel defaultChartDisplay = new ChartPanel(defaultChart);
        chartPanel.add(defaultChartDisplay, BorderLayout.CENTER);

        add(timeFrameSelector, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

        JButton downloadButton = new JButton("Download Chart as PDF");
        downloadButton.setBounds(690, 10, 180, 35);
        downloadButton.setFont(new Font("Arial", Font.BOLD, 14));
        downloadButton.setBackground(new Color(0, 123, 255));
        downloadButton.setForeground(Color.WHITE);
        downloadButton.setFocusPainted(false); 
        downloadButton.setBorder(BorderFactory.createEmptyBorder()); 
        downloadButton.addActionListener(e -> {
            String timeFrame = (String) timeFrameSelector.getSelectedItem();
            JFreeChart chart = timeFrame.equals("Weekly") ?
                    createWeeklyChart(fetchWeeklyEmissionsData()) :
                    createYearlyChart(fetchYearlyEmissionsData());
            exportChartToPDF(chart, "chart.pdf");
            JOptionPane.showMessageDialog(this, "Chart exported to chart.pdf");
        });

        add(downloadButton);
    }

    private void exportChartToPDF(JFreeChart chart, String filePath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            int width = 500;
            int height = 400;
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            javax.imageio.ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            Image image = Image.getInstance(byteArrayOutputStream.toByteArray());
            image.scaleToFit(width, height);
            document.add(image);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private Map<String, Map<String, Double>> fetchWeeklyEmissionsData() {
        Map<String, Map<String, Double>> emissionsData = new HashMap<>();
        String query = "SELECT DAYNAME(done_date) AS day_of_week, categorie, SUM(carbon_footprint) AS total " +
                       "FROM Activities WHERE id_user = ? " +
                       "AND WEEK(done_date) = WEEK(CURDATE()) " +
                       "GROUP BY day_of_week, categorie " +
                       "ORDER BY FIELD(day_of_week, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');";
        
        try (Connection connection = JdbcConnector.JdbcConnector();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
            for (String day : daysOfWeek) {
                emissionsData.putIfAbsent(day, new HashMap<>());
            }
            
            while (resultSet.next()) {
                String dayOfWeek = resultSet.getString("day_of_week");
                String category = resultSet.getString("categorie");
                double total = resultSet.getDouble("total");
                
                emissionsData.get(dayOfWeek).put(category, total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emissionsData;
    }

    private JFreeChart createWeeklyChart(Map<String, Map<String, Double>> emissionsData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        for (String day : daysOfWeek) {
            Map<String, Double> categoryData = emissionsData.get(day);

            if (categoryData == null) {
                categoryData = new HashMap<>();
            }

            dataset.addValue(categoryData.getOrDefault("Transport", 0.0), "Transport", day);
            dataset.addValue(categoryData.getOrDefault("Energy", 0.0), "Energy", day);
            dataset.addValue(categoryData.getOrDefault("Food", 0.0), "Food", day);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Carbon Emissions (Weekly)",
                "Day of the Week", 
                "Emissions (kg CO2)", 
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(255, 99, 71)); 
        renderer.setSeriesPaint(1, new Color(70, 130, 180));
        renderer.setSeriesPaint(2, new Color(34, 139, 34)); 

        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); 

        chart.setBackgroundPaint(Color.WHITE); 

        return chart;
    }

    private Map<String, Map<String, Double>> fetchYearlyEmissionsData() {
        Map<String, Map<String, Double>> emissionsData = new HashMap<>();
        String query = "SELECT MONTH(done_date) AS month, categorie, SUM(carbon_footprint) AS total " +
                       "FROM Activities WHERE id_user = ? " +
                       "AND YEAR(done_date) = YEAR(CURDATE()) " +
                       "GROUP BY month, categorie " +
                       "ORDER BY month;";
        
        try (Connection connection = JdbcConnector.JdbcConnector();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            for (int i = 1; i <= 12; i++) {
                String monthString = String.format("%02d", i);

                Map<String, Double> monthData = new HashMap<>();
                monthData.put("Transport", 0.0);
                monthData.put("Energy", 0.0);
                monthData.put("Food", 0.0);
                emissionsData.put(monthString, monthData);
            }

            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                String category = resultSet.getString("categorie");
                double total = resultSet.getDouble("total");

                String monthString = String.format("%02d", month);

                Map<String, Double> monthData = emissionsData.get(monthString);
                if (monthData != null) {
                    monthData.put(category, total);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emissionsData;
    }

    private JFreeChart createYearlyChart(Map<String, Map<String, Double>> emissionsData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int month = 1; month <= 12; month++) {
            String monthString = String.format("%02d", month);
            Map<String, Double> categoryData = emissionsData.get(monthString);

            dataset.addValue(categoryData.getOrDefault("Transport", 0.0), "Transport", monthString);
            dataset.addValue(categoryData.getOrDefault("Energy", 0.0), "Energy", monthString);
            dataset.addValue(categoryData.getOrDefault("Food", 0.0), "Food", monthString);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Carbon Emissions (Yearly)",
                "Month",
                "Emissions (kg CO2)",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, new Color(255, 99, 71)); 
        renderer.setSeriesPaint(1, new Color(70, 130, 180)); 
        renderer.setSeriesPaint(2, new Color(34, 139, 34)); 
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); 

        chart.setBackgroundPaint(Color.WHITE);

        return chart;
    }
}
