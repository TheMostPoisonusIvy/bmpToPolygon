package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.text.NumberFormat;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class menu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    Font f = new Font("SansSerif", Font.BOLD, 20);
    JFileChooser jfc;
    JButton openJFC, confirmation;
    bitmapToPicksel bTP;
    JLabel pixelSize;
    float pixelGroesse;
    JTextField pixelSizeInput;

    // constructor
    public menu() {
        setLayout(new GridBagLayout());
        setBackground(Color.GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        openJFC = setButton("Open bmp-file to read");
        add(openJFC, c);
        openJFC.addActionListener(e -> openFileChooser());
        pixelSize = new JLabel("Pixelgröße in Metern (mit Dezimalpunkt)");
        pixelSize.setVisible(true);
        pixelSize.setFont(f);
        pixelSize.setOpaque(false);
        c.gridx = 0;
        c.gridy = 1;
        add(pixelSize, c);

        pixelSizeInput = new JTextField();
        pixelSizeInput.setColumns(10);
        pixelSizeInput.setEditable(true);

        c.gridx = 1;
        c.gridy = 1;
        add(pixelSizeInput, c);

        confirmation = new JButton("OK");
        c.gridx = 2;
        c.gridy = 2;
        add(confirmation, c);
        confirmation.addActionListener(e -> confirmation());
        // TODO: Add Actionlistener

    }

    // open the file chooser dialog
    public void openFileChooser() {
        jfc = new JFileChooser("Z:\\workspace Eclipse\\workspace\\PVL-Nachteilsausgleich\\bin");
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
            bmpToPolygon.bmpPath = jfc.getSelectedFile().getAbsolutePath();
        }

    }

    public void confirmDoubleInput() {
        try {
            String f = pixelSizeInput.getText();
            try {
                pixelGroesse = Float.parseFloat(f);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(bmpToPolygon.frame,
                        "Die eingegebene Zahl wurde nicht erkannt. Bitte versuchen Sie es erneut",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(bmpToPolygon.frame,
                    "Das sollte nicht passieren. Bitte versuchen Sie es erneut",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void confirmation() {
        if (bmpToPolygon.bmpPath != null) {
            confirmDoubleInput();
            bTP = new bitmapToPicksel(bmpToPolygon.bmpPath,
                    pixelGroesse);
        } else {
            JOptionPane.showMessageDialog(bmpToPolygon.frame, "Bitte wählen Sie eine Datei aus",
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
}
