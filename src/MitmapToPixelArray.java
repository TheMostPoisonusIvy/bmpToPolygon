package src;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MitmapToPixelArray {
    public Pixel[][] pickselBild;
    public int height, width;
    public float pixelSize;
    BufferedImage bfr;
    ImageToPolygon p;

    public MitmapToPixelArray(String s, float size) {
        try {
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