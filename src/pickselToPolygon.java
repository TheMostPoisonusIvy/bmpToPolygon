package src;

import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class pickselToPolygon {
    public bitmapToPicksel bmpToPick;

    public pickselToPolygon(bitmapToPicksel b) {
        this.bmpToPick = b;
        manageSearch();
        // beginnen beim obersten linken Pixel

        // Bilden einer Gruppe

        // speichern der Gruppe

        // nordwestlichsten Pixel ohne Gruppe suchen

        // Bilden einer Gruppe usw.
        // TODO: Ganz am Ende Duplikate noch einmal aus dem kompletten Vektor entfernen

        // Erkennen der Kanten der Gruppen
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
    }

    public void manageSearch() {
        LinkedList<TreeSet<Pixel>> clusters = new LinkedList<>();
        for (int y = 0; y < bmpToPick.height; y++) {
            for (int x = 0; x < bmpToPick.width; x++) {
                if (bmpToPick.pickselBild[x][y].isChecked()) {
                    continue;
                }
                TreeSet<Pixel> cluster = new TreeSet<>(new PointComparator());
                clusters.add(cluster);
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
        System.out.println(clusters.size());
    }
}
