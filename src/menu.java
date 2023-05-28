package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class menu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    Font f = new Font("SansSerif", Font.BOLD, 20);
    JFileChooser jfc;
    JButton openJFC;
    bitmapToPicksel bTP;

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
            bmpToPolygon.bmpPath = jfc.getSelectedFile().getAbsolutePath();
            System.out.println(bmpToPolygon.bmpPath);
        }
        bTP = new bitmapToPicksel(bmpToPolygon.bmpPath);
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
