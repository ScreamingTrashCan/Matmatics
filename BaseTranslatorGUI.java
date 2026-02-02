import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class BaseTranslatorGUI extends JPanel {
    private Font titleFont = new Font("Verdana", Font.BOLD, 48);
    
    public BaseTranslatorGUI()
    {
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(40, 40, 40));
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1920, 75));
        headerPanel.setBackground(new Color(30, 30, 30));
        this.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Base Translator");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel);
    }
}
