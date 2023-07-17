package src;

import java.util.Comparator;

public class PointComparator implements Comparator<Pixel> {
    @Override
    public int compare(Pixel p1, Pixel p2) {
        // First, compare X coordinates
        int compareX = Integer.compare(p1.getX(), p2.getX());
        if (compareX != 0) {
            return compareX;
        }
        // If X coordinates are the same, compare Y coordinates
        return Integer.compare(p1.getY(), p2.getY());
    }
}
