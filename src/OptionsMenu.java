package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OptionsMenu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    Font f = new Font("SansSerif", Font.BOLD, 20);
    JFileChooser jfc;
    JButton openJFC, confirmation;
    BitmapToPixelArray bTP;
    JLabel pixelSize;
    float pixelGroesse;
    JTextField pixelSizeInput;
    public JProgressBar calcProgress;

    public void formatC(int x, int y) {
        c.gridx = x;
        c.gridy = y;
    }

    // constructor
    public OptionsMenu() {
        setLayout(new BorderLayout());

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.NORTHWEST;
        setBackground(Color.GRAY);

        openJFC = setButton("Open bmp-file to read");
        container.add(openJFC, c);
        openJFC.addActionListener(e -> openFileChooser());

        formatC(0, 1);
        pixelSize = new JLabel("Pixelgröße in Metern (mit Dezimalpunkt)");
        pixelSize.setVisible(true);
        pixelSize.setFont(f);
        pixelSize.setOpaque(false);
        container.add(pixelSize, c);

        formatC(1, 1);
        pixelSizeInput = new JTextField();
        pixelSizeInput.setColumns(10);
        pixelSizeInput.setEditable(true);
        container.add(pixelSizeInput, c);

        formatC(2, 3);
        confirmation = new JButton("OK");
        container.add(confirmation, c);
        confirmation.addActionListener(e -> confirmation());

        calcProgress = new JProgressBar(0, 100);
        calcProgress.setBounds(0, 0, BitmapToPolygon.width, 100);
        add(container, BorderLayout.NORTH);
        add(calcProgress, BorderLayout.SOUTH);
    }

    // open the file chooser dialog
    public void openFileChooser() {
        jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "Bitmaps (*.bmp)";
            }

            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".bmp");
                }
            }
        });

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Save bmp in central type
            // bmpToPolygon.bmpPath = jfc.getSelectedFile().getName();
            BitmapToPolygon.bmpPath = jfc.getSelectedFile().getAbsolutePath();
        }

    }

    public void confirmDoubleInput() {
        try {
            String f = pixelSizeInput.getText();
            try {
                pixelGroesse = Float.parseFloat(f);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(BitmapToPolygon.frame,
                        "Die eingegebene Zahl wurde nicht erkannt. Bitte versuchen Sie es erneut",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(BitmapToPolygon.frame,
                    "Das sollte nicht passieren. Bitte versuchen Sie es erneut",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void confirmation() {
        if (BitmapToPolygon.bmpPath != null) {
            confirmDoubleInput();
            bTP = new BitmapToPixelArray(BitmapToPolygon.bmpPath,
                    pixelGroesse, calcProgress);

        } else {
            JOptionPane.showMessageDialog(BitmapToPolygon.frame, "Bitte wählen Sie eine Datei aus",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    // helper function to set a button in "style"
    private JButton setButton(String txt) {
        JButton j;
        j = new JButton(txt);
        j.setFont(f);
        j.setOpaque(false);
        j.setContentAreaFilled(false);
        j.setBorderPainted(false);
        return j;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // backGround = backGround.getScaledInstance(getParent().getWidth(),
        // getParent().getHeight(), ABORT);
        super.paintComponent(g);
        // g.drawImage(backGround, 0, 0, null);
    }
}
