package src;

public class picksel {
    int xKoordinate, yKoordinate, wert;
    int xKoordinateWoher, yKoordinateWoher;
    Boolean durchsucht;

    public picksel() {
    }

    public picksel(int x, int y, int w) {
        this.xKoordinate = x;
        this.yKoordinate = y;
        this.wert = w;
        this.durchsucht = false;
    }
}
