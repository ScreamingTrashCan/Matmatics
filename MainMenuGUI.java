import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class MainMenuGUI extends JPanel {
    private Font saira;
    private Font sairaBold;

    public MainMenuGUI()
    {
        setPreferredSize(new DimensionUIResource(700, 500));
        setLayout(new BorderLayout());

        this.setBackground(Color.BLACK);

        registerFonts();

        JLabel titleLabel = new JLabel("Welcome to Matmatics!");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(sairaBold);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);

        // Button List
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));

        JButton calcButton = new JButton("Calculator");
        JButton baseButton = new JButton("Base Translator");
        JButton approxButton = new JButton("Curve Approximator");
        JButton newtonsButton = new JButton("Newtons Method");
        JButton quitButton = new JButton("Quit");
        buttonPanel.add(calcButton);
        buttonPanel.add(baseButton);
        buttonPanel.add(approxButton);
        buttonPanel.add(newtonsButton);
        buttonPanel.add(quitButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    private void registerFonts()
    {
        try
        {
            saira = Font.createFont(Font.TRUETYPE_FONT, new File("Media/Fonts/Saira.ttf")).deriveFont(Font.BOLD, 36f);
            sairaBold = Font.createFont(Font.TRUETYPE_FONT, new File("Media/Fonts/Saira-Bold.ttf")).deriveFont(Font.BOLD, 42f);
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
