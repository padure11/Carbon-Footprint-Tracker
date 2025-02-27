package Ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DBManager.JdbcConnector;

public class LeaderBoardScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel tableModel;
    private JTable leaderboardTable;

    public LeaderBoardScreen() {
        setBackground(new Color(253, 247, 244));
        setSize(980, 502);
        setLayout(null);

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER);
        titleLabel.setBounds(240, 10, 450, 28);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 51, 102));
        add(titleLabel);

        String[] columnNames = {"Rank", "Username", "Carbon Footprint"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        leaderboardTable = new JTable(tableModel);
        leaderboardTable.setFillsViewportHeight(true);
        leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 14));
        leaderboardTable.setRowHeight(30);
        
        JTableHeader header = leaderboardTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(142, 180, 134));  
        header.setForeground(Color.WHITE); 
        
        TableColumnModel columnModel = leaderboardTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); 
        columnModel.getColumn(1).setPreferredWidth(200); 
        columnModel.getColumn(2).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        scrollPane.setBounds(202, 62, 545, 327);
        add(scrollPane);

        loadLeaderboard();
    }

    private void loadLeaderboard() {
        tableModel.setRowCount(0);  
        
        Map<String, Double> leaderboard = JdbcConnector.GetLeaderBoard();

        if (leaderboard == null || leaderboard.isEmpty()) {
            JLabel noDataLabel = new JLabel("No data available", SwingConstants.CENTER);
            noDataLabel.setBounds(0, 28, 450, 272);
            noDataLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            add(noDataLabel);
            return;
        }

        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(leaderboard.entrySet());
        sortedList.sort(Map.Entry.comparingByValue());  

        int rank = 1;
        for (Map.Entry<String, Double> entry : sortedList) {
            Object[] row = {rank, entry.getKey(), entry.getValue()};
            tableModel.addRow(row);
            rank++;
        }

        for (int i = 0; i < leaderboard.size(); i++) {
            if (i < 3) {
                leaderboardTable.setRowSelectionInterval(i, i);
                leaderboardTable.setSelectionBackground(new Color(0, 204, 255)); 
                leaderboardTable.setSelectionForeground(Color.WHITE);
            }
        }
    }

    public void updateLeaderboard() {
        try {
            loadLeaderboard(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
