package src;

import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class ImageToPolygon {
    public BitmapToPixelArray bmpToPick;
    LinkedList<TreeSet<Pixel>> clusters;
    LinkedList<TreeSet<Pixel>> cornerPointCluster = new LinkedList<TreeSet<Pixel>>();

    public ImageToPolygon(BitmapToPixelArray b) {
        this.bmpToPick = b;
        // Bilden der Gruppen
        manageSearch();
        // Erkennen der Kanten der Gruppen
        for (TreeSet<Pixel> cluster : clusters) {
            this.cornerPointCluster.add(clusterToPolygonCorners(cluster));
        }
        for (TreeSet<Pixel> cluster : cornerPointCluster) {
            System.out.println("");
            for (Pixel p : cluster) {
                System.out.print(p.getX() + " / " + p.getY() + "; ");
            }
        }
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
        safeToPolygon();
        // TODO: Beenden des Programmes
        System.exit(-1);
    }

    public void safeToPolygon() {

    }

    public void orderPolygonCornersToPolygon(TreeSet<Pixel> cornerPointCluster) {
        // Testen in welche Richtung die Kante / Grenze verläuft

    }

    public void getDirectionOfPolygonEdge() {

    }

    public TreeSet<Pixel> clusterToPolygonCorners(TreeSet<Pixel> cluster) {
        TreeSet<Pixel> polygonCorners = new TreeSet<>();
        for (Pixel p : cluster) {
            tryAdd(cluster, polygonCorners, new Pixel(p.getX(), p.getY()));
            tryAdd(cluster, polygonCorners, new Pixel(p.getX() + 1, p.getY()));
            tryAdd(cluster, polygonCorners, new Pixel(p.getX(), p.getY() + 1));
            tryAdd(cluster, polygonCorners, new Pixel(p.getX() + 1, p.getY() + 1));
        }
        return polygonCorners;
    }

    // helper function for clusterToPolygonCorners
    private void tryAdd(TreeSet<Pixel> cluster, TreeSet<Pixel> polygonCorners, Pixel p) {
        int count = 0;
        if (cluster.contains(new Pixel(p.getX(), p.getY())))
            count++;
        if (cluster.contains(new Pixel(p.getX() - 1, p.getY() - 1)))
            count++;
        if (cluster.contains(new Pixel(p.getX() - 1, p.getY())))
            count--;
        if (cluster.contains(new Pixel(p.getX(), p.getY() - 1)))
            count--;
        if (count != 0) {
            polygonCorners.add(p);
        }
    }

    public void manageSearch() {
        this.clusters = new LinkedList<>();
        for (int y = 0; y < bmpToPick.height; y++) {
            for (int x = 0; x < bmpToPick.width; x++) {
                if (bmpToPick.pickselBild[x][y].isChecked()) {
                    continue;
                }
                TreeSet<Pixel> cluster = new TreeSet<>(new PointComparator());
                this.clusters.add(cluster);
                Stack<Pixel> s = new Stack<>();
                int col = bmpToPick.pickselBild[x][y].getColor();
                s.add(bmpToPick.pickselBild[x][y]);
                while (!s.empty()) {
                    Pixel p = s.pop();
                    if (p.isChecked() || col != p.getColor())
                        continue;
                    cluster.add(p);
                    p.check();
                    int px = p.getX();
                    int py = p.getY();
                    // only looking downwards and sideways since we are looping from the top so
                    // there can't be any unreached pixels above
                    if (px > 0)
                        s.add(bmpToPick.pickselBild[px - 1][py]);
                    if (px < bmpToPick.width - 1)
                        s.add(bmpToPick.pickselBild[px + 1][py]);
                    if (py < bmpToPick.height - 1) {
                        if (px > 0)
                            s.add(bmpToPick.pickselBild[px - 1][py + 1]);
                        s.add(bmpToPick.pickselBild[px][py + 1]);
                        if (px < bmpToPick.width - 1)
                            s.add(bmpToPick.pickselBild[px + 1][py + 1]);
                    }
                }
            }
        }
        // do whatever with the clusters
        System.out.println(this.clusters.size());
    }
}
