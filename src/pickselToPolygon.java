package src;

import java.util.Vector;

public class pickselToPolygon {
    public bitmapToPicksel bmpToPick;
    public Vector<Vector<picksel>> groupsPerRow;
    int x, y;

    public pickselToPolygon(bitmapToPicksel b) {
        this.bmpToPick = b;
        this.groupsPerRow = new Vector<Vector<picksel>>(1);
        System.out.println(this.bmpToPick.height + " " + this.bmpToPick.width);
        // beginnen beim obersten linken Pixel

        // Bilden einer Gruppe

        // speichern der Gruppe

        // nordwestlichsten Pixel ohne Gruppe suchen

        // Bilden einer Gruppe usw.

        // Erkennen der Kanten der Gruppen
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
    }

    public void addVectorToGeneralVector() {

    }

    public void searchForNewPixelGroupMostNorthWestOfCurrentOne() {

    }

    public void findMostNorthWestCornerOfPickselVector() {

    }

    public void newSearchCluster() {

    }

    public Vector<picksel> removeDuplicatesFromSearchCluster(Vector<picksel> v) {
        for (picksel p : v) {
            for (int i = 0; i < v.size(); i++) {
                // wenn suchpixel und vector-element sich gleichen, entferne das überflüssige
                // vector-element
                if (compareTwoPicksel(p, v.elementAt(i))) {
                    v.removeElementAt(i);
                }
            }
        }
        return v;
    }

    public Boolean compareTwoPicksel(picksel p, picksel q) {
        if (q != p) {
            return false;
        } else {
            return true;
        }
    }

    public Vector<picksel> searchForPickselWithSameValueNearTheCurrentOne(picksel p) {
        Vector<picksel> v = new Vector<picksel>(1);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // TODO: Edgecasehandeling
                // Wenn der Suchpixel den gleichen Wert wie der aktuelle Pixel hat
                if (bmpToPick.bild[x + i][x + j] == bmpToPick.bild[x][y]) {

                }

            }
        }
        return v;
    }
}
