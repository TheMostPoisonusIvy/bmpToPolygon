package src;

import java.util.Vector;

public class pickselToPolygon {
    public bitmapToPicksel bmpToPick;
    public Vector<Vector<Integer>> groupsPerRow;

    public pickselToPolygon(bitmapToPicksel b) {
        this.bmpToPick = b;
        this.groupsPerRow = new Vector<Vector<Integer>>(b.height);
        System.out.println("Success");
        // durchgehen einer jeden Bildzeile
        // finden des Anfangs und Ende einer jeden Pixelgruppe pro Zeile
        // Zusammenfügen der Gruppen über Zeilen hinweg
        // Erkennen der Kanten der Gruppen
        // Abspeichern einer jeden Gruppe, bzw. deren Kanten, als Polygon
    }
}
