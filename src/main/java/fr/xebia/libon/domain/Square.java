package fr.xebia.libon.domain;

/**
 * Created by romainn on 13/01/2015.
 */
public class Square {

    public static final Integer BOMB_VALUE = -1;

    private Coordinate coordinate;

    private Integer value;

    private boolean discover;

    public Square(Coordinate coordinate, Integer value) {
        this.coordinate = coordinate;
        this.value = value;
    }

    public Square(Coordinate coordinate, Integer value, boolean discover) {
        this.coordinate = coordinate;
        this.value = value;
        this.discover = discover;
    }

    public Coordinate getCoordinate() {
        return coordinate;
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

        if (coordinate != null ? !coordinate.equals(square.coordinate) : square.coordinate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return coordinate != null ? coordinate.hashCode() : 0;
    }
}
