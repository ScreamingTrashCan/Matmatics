import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.*;

public class BaseTranslatorGUI extends JPanel {
    public BaseTranslatorGUI()
    {
        this.setPreferredSize(new Dimension(1920, 1080));
        this.setLayout(new CardLayout());
    }
}