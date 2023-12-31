package src;

import java.awt.*;
import javax.swing.*;

public class BitmapToPolygon {
    public static double version = 0.05;
    public static JFrame frame;
    public static OptionsMenu mainMenu;
    public static JPanel mainPanel;
    public static int height, width;
    public static String bmpPath = null;

    // Constructor
    public BitmapToPolygon() {
        frame = new JFrame("bmpToPolygon v" + version);
        frame.setSize(800, 400);
        height = frame.getHeight();
        width = frame.getWidth();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(0, 0);
        mainMenu = new OptionsMenu();
        mainPanel = mainMenu;
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        BitmapToPolygon b = new BitmapToPolygon();
    }
}