package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class menu extends JPanel {
    GridBagConstraints c = new GridBagConstraints();
    Font f = new Font("SansSerif", Font.BOLD, 20);
    JFileChooser jfc;
    JButton openJFC, confirmation;
    bitmapToPicksel bTP;
    JLabel pixelSize;
    JComboBox<String> pixelDropDownMenu;

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
        pixelSize = new JLabel("Select pixelsize");
        pixelSize.setVisible(true);
        pixelSize.setFont(f);
        pixelSize.setOpaque(false);
        c.gridx = 0;
        c.gridy = 1;
        add(pixelSize, c);
        // TODO: Get choices off of bmp-header
        String[] choices = { "5m * 5m", "10m * 10m", "15m * 15m", "20m * 20m" };
        pixelDropDownMenu = new JComboBox<String>(choices);
        pixelDropDownMenu.setVisible(true);
        c.gridx = 1;
        c.gridy = 1;
        add(pixelDropDownMenu, c);

        confirmation = new JButton("OK");
        c.gridx = 2;
        c.gridy = 1;
        add(confirmation, c);
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
