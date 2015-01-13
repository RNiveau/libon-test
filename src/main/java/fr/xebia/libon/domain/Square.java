package fr.xebia.libon.domain;

/**
 * Created by romainn on 13/01/2015.
 */
public class Square {

    private Integer x;

    private Integer y;

    private Integer value;

    private boolean discover;

    public Square(Integer x, Integer y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Square(Integer x, Integer y, Integer value, boolean discover) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.discover = discover;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isDiscover() {
        return discover;
    }

    public void setDiscover(boolean discover) {
        this.discover = discover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;

        Square square = (Square) o;

        if (x != null ? !x.equals(square.x) : square.x != null) return false;
        if (y != null ? !y.equals(square.y) : square.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
