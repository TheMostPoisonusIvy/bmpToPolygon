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
        // TODO: Ganz am Ende Duplikate noch einmal aus dem kompletten Vektor entfernen

        // Erkennen der Kanten der Gruppen
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
    }

    public void addVectorToGeneralVector() {

    }

    public void searchForNewPixelGroupMostNorthWestOfCurrentOne() {

    }

    public void findMostNorthWestCornerOfPickselVector() {

    }

    public void manageSearch() {
        // Erstellen lokaler Koordinaten x,y -> dazugehöriger Picksel
        // so lange die Koordinaten des nächsten Searchclusters nicht bei width+1 und
        // height+1
        // neues Searchcluster bei x,y
        // Duplikate innerhalb des Searchclusters entfernen
        // searchcluster zum allgemeinen cluster hinzufügen
        // berechnen des neuen x / y
    }

    public Vector<picksel> newSearchCluster(picksel p) {
        // Erstellen des übergeordneten Suchclusters
        Vector<picksel> v = new Vector<>();
        // Beginn ab übergebenen Pixel

        // Erstellen des Suchclusters
        // Rückgabe des Suchclusters
        return v;
    }

    public Vector<picksel> removeDuplicatesFromSearchCluster(Vector<picksel> v) {
        for (picksel p : v) {
            for (int i = v.indexOf(p); i < v.size(); i++) {
                // wenn suchpixel und vector-element sich gleichen, entferne das überflüssige
                // vector-element
                if (compareTwoPicksel(p, v.elementAt(i))) {
                    v.removeElementAt(i);
                }
            }
        }
        return v;
    }

    /**
     * Methode zum vergleichen zweier Pixel.
     * 
     * @param p erster Pixel
     * @param q zweiter Pixel
     * @return Boolean ob Gleichheit besteht oder nicht
     */
    public Boolean compareTwoPicksel(picksel p, picksel q) {
        if (q != p) {
            return false;
        } else {
            return true;
        }
    }

    public Vector<picksel> searchForPickselWithSameValueNearTheCurrentOne(picksel p) {
        Vector<picksel> v = new Vector<picksel>(1);
        // Vermerke, dass ab diesem Pixel eine Suche gestartet wurde
        p.durchsucht = true;

        v.add(1, p);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // TODO: Edgecasehandeling
                // Wenn der Suchpixel den gleichen Wert wie der aktuelle Pixel hat
                if (bmpToPick.bild[x + i][x + j] == bmpToPick.bild[x][y]) {
                    v.add(bmpToPick.pickselBild[x + i][x + j]);
                    v.elementAt(v.size()).durchsucht = true;
                }
            }
        }
        return v;
    }
}
