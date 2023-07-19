package src;

import java.util.Objects;

public class Pixel implements Comparable<Pixel> {
    private final int x;
    private final int y;
    private final int color;
    private boolean checked;

    public Pixel(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.checked = false;
    }

    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = 0;
        this.checked = false;
    }

    public int getColor() {
        return color;
    }

    public void check() {
        this.checked = true;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ": " + color + ")";
    }

    @Override
    public int compareTo(Pixel o) {
        if (this.y == o.y)
            return Integer.compare(this.x, o.x);
        return Integer.compare(this.y, o.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x && y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
