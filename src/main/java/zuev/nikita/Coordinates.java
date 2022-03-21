package zuev.nikita;

/**
 * Координаты организации.
 */
public class Coordinates implements Comparable<Coordinates> {
    Coordinates(long x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Максимальное значение поля: 923
     */
    private long x;
    /**
     * Поле не может быть null
     */
    private Double y;

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        return Double.compare(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)), Math.sqrt(Math.pow(o.getX(), 2) + Math.pow(o.getY(), 2)));
    }
}