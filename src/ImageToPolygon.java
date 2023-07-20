package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

/**
 * The main calculation class to transform the bitmap to transform the Bitmap to
 * the wanted .csv-file
 * 
 * @author szwiletitsch
 * @author TheMostPoisonusIvy
 */

public class ImageToPolygon {
    public BitmapToPixelArray bmpToPick;
    LinkedList<TreeSet<Pixel>> clusters;
    LinkedList<LinkedList<LinkedList<Pixel>>> cornerPointCluster = new LinkedList<>();

    public ImageToPolygon(BitmapToPixelArray b) {
        this.bmpToPick = b;
        // Bilden der Gruppen
        manageSearch();
        // Erkennen der Kanten der Gruppen
        int iterations = 0;
        for (TreeSet<Pixel> cluster : clusters) {
            this.cornerPointCluster.add(clusterToPolygonCorners(cluster));
            iterations++;
            bmpToPick.j.setValue((((100 * iterations / clusters.size())) / 2) + 50);
        }
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
        safeToCSV(cornerPointCluster);
        // TODO: Beenden des Programmes
        // System.exit(200);
    }

    public void safeToCSV(LinkedList<LinkedList<LinkedList<Pixel>>> cornerPointClusters) {
        System.out.println("Finished");
        // Da lastIndexOf buggy war
        for (int i = bmpToPick.pathToFile.length() - 1; i > 0; i--) {
            if (bmpToPick.pathToFile.charAt(i) == '/') {
                System.out.println(i);
            }
        }
        String pathStringWithCSV = new StringBuilder(bmpToPick.pathToFile)
                .delete(bmpToPick.pathToFile.length() - 4, bmpToPick.pathToFile.length()).append(".csv").toString();
        System.out.println(pathStringWithCSV);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(pathStringWithCSV);
        } catch (Exception e) {
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.write("Easting, Northing\n");
        for (LinkedList<LinkedList<Pixel>> polygonGroup : cornerPointClusters) {
            for (LinkedList<Pixel> polygon : polygonGroup) {
                for (Pixel p : polygon) {
                    printWriter.write(p.getX() + "," + p.getY() + ";\n");
                }
                printWriter.write("\n");
            }

        }
        printWriter.close();
    }

    public LinkedList<LinkedList<Pixel>> clusterToPolygonCorners(TreeSet<Pixel> cluster) {
        TreeSet<Pixel> cornerPoints = clusterToPolygonCornerPoints(cluster);
        TreeSet<Pixel> unUsed = clusterToPolygonCornerPoints(cluster);

        // First Polygon in List will be outer Edge
        // Later ones are inner Polygons
        LinkedList<LinkedList<Pixel>> polygons = new LinkedList<>();
        while (unUsed.size() != 0) {
            LinkedList<Pixel> currentPoly = new LinkedList<>();
            polygons.add(currentPoly);
            Pixel startAndEnd = unUsed.first();
            Pixel p = unUsed.first();

            // logic for setting a valid starting direction
            boolean lu = cluster.contains(new Pixel(p.getX() - 1, p.getY() - 1));
            boolean ld = cluster.contains(new Pixel(p.getX() - 1, p.getY()));
            boolean ru = cluster.contains(new Pixel(p.getX(), p.getY() - 1));
            boolean rd = cluster.contains(new Pixel(p.getX(), p.getY()));
            boolean edgeCase = (!lu && ld && ru && !rd) || (lu && !ld && !ru && rd);
            int ix, iy;
            if (edgeCase || (rd && ld)) {
                ix = 0;
                iy = 1;
            } else if (ru && lu) {
                ix = 0;
                iy = -1;
            } else if (ru || lu) {
                ix = 0;
                iy = 1;
            } else {
                ix = 0;
                iy = -1;
            }

            while (!p.equals(startAndEnd) || currentPoly.isEmpty()) {
                if (cornerPoints.contains(p)) {
                    currentPoly.add(p);
                    lu = cluster.contains(new Pixel(p.getX() - 1, p.getY() - 1));
                    ld = cluster.contains(new Pixel(p.getX() - 1, p.getY()));
                    ru = cluster.contains(new Pixel(p.getX(), p.getY() - 1));
                    rd = cluster.contains(new Pixel(p.getX(), p.getY()));
                    edgeCase = (!lu && ld && ru && !rd) || (lu && !ld && !ru && rd);
                    if (ix == 1) {
                        ix = 0;
                        if (edgeCase) {
                            iy = ru ? -1 : 1;
                        } else if (ru && rd) {
                            iy = lu ? 1 : -1;
                        } else {
                            iy = lu ? -1 : 1;
                        }
                    } else if (ix == -1) {
                        ix = 0;
                        if (edgeCase) {
                            iy = ru ? 1 : -1;
                        } else if (lu && ld) {
                            iy = ru ? 1 : -1;
                        } else {
                            iy = ru ? -1 : 1;
                        }
                    } else if (iy == 1) {
                        iy = 0;
                        if (edgeCase) {
                            ix = ru ? -1 : 1;
                        } else if (rd && ld) {
                            ix = ru ? -1 : 1;
                        } else {
                            ix = ru ? 1 : -1;
                        }
                    } else if (iy == -1) {
                        iy = 0;
                        if (edgeCase) {
                            ix = ru ? 1 : -1;
                        } else if (ru && lu) {
                            ix = rd ? -1 : 1;
                        } else {
                            ix = rd ? 1 : -1;
                        }
                    }
                }
                p = new Pixel(p.getX() + ix, p.getY() + iy);
                // System.out.println(p);
            }
            currentPoly.forEach(unUsed::remove);
            // bmpToPick.j.setValue((100 * y / y));
            // rambazamba
        }
        return polygons;
    }

    public TreeSet<Pixel> clusterToPolygonCornerPoints(TreeSet<Pixel> cluster) {
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
            bmpToPick.j.setValue((100 * y / bmpToPick.height) / 2);
        }
        System.out.println("Progress: " + 100 + "%");
        System.out.println(clusters.size() + " clusters detected");
    }
}
