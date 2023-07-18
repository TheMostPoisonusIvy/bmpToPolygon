package src;

import java.util.TreeSet;

public class PixelCluster {
    TreeSet<Pixel> cluster;
    // Koordinaten des umgebenden rechtecks
    int northWest, northEast, southWest, southEast;
    Point2D[] cornerPointCoordinates;

    private static class Point2D {
        int x, y;

        public Point2D(int xs, int ys) {
            this.x = xs;
            this.y = ys;
        }
    }

    public static class FloatingPoint2D {
        float x, y;

        public FloatingPoint2D() {

        }

        public FloatingPoint2D(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public PixelCluster(TreeSet<Pixel> c) {
        this.cluster = c;
        this.cornerPointCoordinates = new Point2D[4];
    }

    public void findCornerOfSurroundingRectangleCoordinates() {
        // initialisieren der Koordinaten
        Point2D nW, nE, sW, sE, lokal;
        nW = new Point2D(cluster.first().getX(), cluster.first().getY());
        nE = new Point2D(cluster.first().getX(), cluster.first().getY());
        sW = new Point2D(cluster.first().getX(), cluster.first().getY());
        sE = new Point2D(cluster.first().getX(), cluster.first().getY());

        for (Pixel p : cluster) {
            lokal = new Point2D(p.getX(), p.getY());
            // x-Koordinaten
            if (lokal.x < nW.x) {
                nW.x = lokal.x;
            }
            if (lokal.x > nE.x) {
                nE.x = lokal.x;
            }
            if (lokal.x < sW.x) {
                sW.x = lokal.x;
            }
            if (lokal.x > sE.x) {
                sE.x = lokal.x;
            }

            // y-Koordinaten
            if (lokal.y < nW.y) {
                nW.y = lokal.y;
            }
            if (lokal.y < nE.y) {
                nE.y = lokal.y;
            }
            if (lokal.y > sW.y) {
                sW.y = lokal.y;
            }
            if (lokal.y > sE.y) {
                sE.y = lokal.y;
            }
        }
        this.cornerPointCoordinates[0] = nW;
        this.cornerPointCoordinates[1] = nE;
        this.cornerPointCoordinates[2] = sW;
        this.cornerPointCoordinates[3] = sE;
    }

    public void findCornerPoints(float pixelSize) {
        // startend vom ersten Pixel im TreeSet
        // wenn möglich, gehen in positive x-Richtung bei gleichem y
        // falls kein Pixel in x pos mehr vorhanden ist
        System.out.println(" ");
        for (Pixel p : cluster) {
            System.out.println(p.getX() + " / " + p.getY());
        }
    }

    public class CornerPoint {
        int x, y;
        Boolean realCorner;

        public CornerPoint(int itsAnX, int itsAnY, Boolean itsARealCorner) {
            this.x = itsAnX;
            this.y = itsAnY;
            this.realCorner = itsARealCorner;
        }
    }
}
