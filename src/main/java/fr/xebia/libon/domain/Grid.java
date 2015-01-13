package fr.xebia.libon.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romainn on 13/01/2015.
 */
public class Grid {

    private List<Square> squares;

    private List<Square> bombs;

    private Integer width;

    private Integer height;

    public Grid(Integer width, Integer height) {
        this.width = width;
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public List<Square> getSquares() {
        if (squares == null)
            squares = new ArrayList<>();
        return squares;
    }

    public List<Square> getBombs() {
        if (bombs == null) {
            bombs = new ArrayList<>();
        }
        return bombs;
    }
}
