package src;

import java.util.Vector;

public class pickselToPolygon {
    public bitmapToPicksel bmpToPick;
    public Vector<Vector<picksel>> groupsPerRow;
    int x, y;

    public pickselToPolygon(bitmapToPicksel b) {
        this.bmpToPick = b;
        this.groupsPerRow = new Vector<Vector<picksel>>(1);
        System.out.println("Success");
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

    public void searchForPickselWithSameValueNearTheCurrentOne(picksel p) {

    }
}
