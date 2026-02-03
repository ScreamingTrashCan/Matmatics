import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class BaseTranslatorGUI extends JPanel {
    private Font saira;
    private Font sairaBold;
    
    private Font saira;
    private Font sairaBold;
    
    public BaseTranslatorGUI()
    {
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        registerFonts();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(40, 40, 40));
        this.add(mainPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1920, 75));
        headerPanel.setBackground(new Color(30, 30, 30));
        this.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Base Translator");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(sairaBold);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(titleLabel);
    }

    private void registerFonts()
    {
        try
        {
            saira = Font.createFont(Font.TRUETYPE_FONT, new File("Media/Fonts/Saira.ttf")).deriveFont(Font.BOLD, 36f);
            sairaBold = Font.createFont(Font.TRUETYPE_FONT, new File("Media/Fonts/Saira-Bold.ttf")).deriveFont(Font.BOLD, 36f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(saira);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(sairaBold);
        }
        catch (FontFormatException e)
        {
            System.out.println("Font Format Exception: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }
}
