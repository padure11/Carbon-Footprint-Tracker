package Ui;

import javax.swing.*;
import java.awt.*;

public class DailyChallenge extends JPanel {

    public DailyChallenge() {
        setBackground(new Color(245, 245, 245)); 
        setSize(980, 502);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("What is a Carbon Footprint?", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));  
        titleLabel.setForeground(new Color(0, 51, 102));  
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 20))); 

        JTextArea introText = new JTextArea();
        introText.setText(
                "Carbon footprint is the total amount of greenhouse gases (GHGs) emitted into the atmosphere due to human activities. " +
                "These gases are primarily carbon dioxide (CO2), but can also include methane (CH4) and nitrous oxide (N2O). " +
                "The carbon footprint of an individual or a business is typically measured in terms of the amount of CO2 emitted per year.\n\n" +
                "The activities that contribute most to a person’s carbon footprint include:\n" +
                "- Transportation (driving, flying, etc.)\n" +
                "- Energy use (heating, cooling, electricity consumption)\n" +
                "- Food consumption (meat production has a higher carbon footprint)\n" +
                "- Waste production (landfill waste generates methane)\n\n" +
                "Understanding and reducing your carbon footprint can have a significant impact on mitigating climate change and protecting the environment.");
        introText.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        introText.setLineWrap(true);
        introText.setWrapStyleWord(true);
        introText.setEditable(false);
        introText.setBackground(new Color(255, 255, 255)); 
        introText.setForeground(new Color(0, 51, 102));  
        introText.setCaretPosition(0);
        introText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
        add(new JScrollPane(introText));

        add(Box.createRigidArea(new Dimension(0, 20))); 

        // Tips Label
        JLabel tipsLabel = new JLabel("How to Reduce Your Carbon Footprint", JLabel.CENTER);
        tipsLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tipsLabel.setForeground(new Color(34, 139, 34));  
        add(tipsLabel);

        add(Box.createRigidArea(new Dimension(0, 10))); 

        // Tips Text Area
        JTextArea tipsText = new JTextArea();
        tipsText.setText(
                "1. Use Public Transport or Carpool - Reducing car travel is one of the quickest ways to cut emissions.\n" +
                "2. Switch to Renewable Energy - Consider installing solar panels or choosing renewable energy from your provider.\n" +
                "3. Eat a Plant-Based Diet - Reducing meat consumption lowers the environmental impact of your diet.\n" +
                "4. Reduce, Reuse, Recycle - Decrease the amount of waste you create by buying fewer single-use products and recycling.\n" +
                "5. Conserve Water - Saving water reduces the energy needed to process and heat water.\n" +
                "6. Opt for Energy-Efficient Appliances - Use appliances that consume less energy and reduce your overall footprint.\n" +
                "7. Offset Your Emissions - Participate in programs that help offset your carbon footprint, such as tree planting or renewable energy projects.");
        tipsText.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
        tipsText.setLineWrap(true);
        tipsText.setWrapStyleWord(true);
        tipsText.setEditable(false);
        tipsText.setBackground(new Color(255, 255, 255)); 
        tipsText.setForeground(new Color(0, 51, 102)); 
        tipsText.setCaretPosition(0);
        tipsText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        add(new JScrollPane(tipsText));

        add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(245, 245, 245)); 

        JButton proceedButton = new JButton("Start Tracking Your Carbon Footprint");
        proceedButton.setFont(new Font("Segoe UI", Font.BOLD, 16));  
        proceedButton.setBackground(new Color(0, 123, 255)); 
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false); 
        proceedButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 
        proceedButton.setPreferredSize(new Dimension(250, 40)); 
        proceedButton.addActionListener(e -> {
            System.out.println("Proceeding to main screen...");
        });
        footerPanel.add(proceedButton);
        add(footerPanel);
    }
}
