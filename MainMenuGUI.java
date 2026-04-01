import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI()
    {
        super("Matmatics Main Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new DimensionUIResource(700, 500));
        setLayout(new BorderLayout());

        try {
            Image iconImage = ImageIO.read(MainMenuGUI.class.getResource("/Media/Matti/Matti_Idle.png"));
            setIconImage(iconImage);
        }
        catch (Exception e) {
            System.err.println("Error: Could not load asset.");
        }


        // Title to Matmatics
        JPanel titlePanel = new JPanel(); 
        titlePanel.add(new JLabel("<html><span style='font-size:30px'>Welcome to Matmatics!</span></html>"));
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        add(titlePanel, BorderLayout.NORTH);

        // Button List
        JPanel buttonPanel = new JPanel();
        JButton calcButton = new JButton("Calculator");
        JButton baseButton = new JButton("Base Translator");
        JButton approxButton = new JButton("Curve Approximator");
        JButton newtonsButton = new JButton("Newtons Method");
        buttonPanel.add(calcButton); buttonPanel.add(baseButton);
        buttonPanel.add(approxButton); buttonPanel.add(newtonsButton);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
        
}
