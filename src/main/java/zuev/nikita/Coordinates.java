package zuev.nikita;

public class Coordinates implements Comparable<Coordinates> {
    Coordinates(long x, Double y) {
        this.x = x;
        this.y = y;
    }

    private long x; //Максимальное значение поля: 923
    private Double y; //Поле не может быть null

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
        double value = (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) - Math.sqrt(Math.pow(o.getX(), 2) + Math.pow(o.getY(), 2)));
        if (value < 0) {
            return -1;
        } else if (value == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}