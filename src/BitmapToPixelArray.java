package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JProgressBar;

public class BitmapToPixelArray {
    public Pixel[][] pickselBild;
    public int height, width;
    public float pixelSize;
    BufferedImage bfr;
    ImageToPolygon p;
    JProgressBar j;

    public BitmapToPixelArray(String s, float size, JProgressBar jPro) {
        try {
            this.j = jPro;
            this.pixelSize = size;
            System.out.println(size);
            this.bfr = ImageIO.read(new File(s));
            this.height = bfr.getHeight();
            this.width = bfr.getWidth();
            this.pickselBild = new Pixel[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    this.pickselBild[x][y] = new Pixel(x, y, bfr.getRGB(x, y));
                }
            }
            p = new ImageToPolygon(this);
        } catch (IOException e) {
            System.out.println("Could not get img");
        }
    }
}
