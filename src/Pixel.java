package src;

public class Pixel {
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
}
