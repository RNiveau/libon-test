package fr.xebia.libon.domain;

/**
 * Created by romainn on 13/01/2015.
 */
public class Square {

    private Integer x;

    private Integer y;

    private Integer value;

    private boolean discover;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public boolean isDiscover() {
        return discover;
    }

    public void setDiscover(boolean discover) {
        this.discover = discover;
    }
}
