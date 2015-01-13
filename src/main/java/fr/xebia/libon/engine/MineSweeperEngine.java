package fr.xebia.libon.engine;

import fr.xebia.libon.domain.Coordinate;
import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;
import fr.xebia.libon.engine.api.IMineSweeperEngine;
import fr.xebia.libon.exceptions.InternalException;

import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.xebia.libon.domain.Square.BOMB_VALUE;

/**
 * Created by romainn on 13/01/2015.
 */
public class MineSweeperEngine implements IMineSweeperEngine {

    @Override
    public Grid createGrid(String initConfig) {
        Matcher matcher = validateInitConfig(initConfig);

        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));
        int minesNumber = Integer.parseInt(matcher.group(3));

        checkValidConfiguration(width, height, minesNumber);

        Grid grid = new Grid(width, height);
        for (int i = 0; i < minesNumber; i++) {
            Square bomb = generateBomb(width, height);
            while (getBomb(grid, bomb).isPresent())
                bomb = generateBomb(width, height);
            grid.getBombs().add(bomb);
        }
        return grid;
    }

    @Override
    public Square discoverSquare(Grid grid, String strCoordinate) {
        Matcher matcher = validateCoordinate(strCoordinate);
        if (matcher == null)
            return null;
        Coordinate coordinate = new Coordinate(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        if (!checkValidCoordinate(grid, coordinate))
            return null;
        Optional<Square> bomb = getBomb(grid, coordinate);
        return bomb.orElseGet(() -> {
            Optional<Square> square = getSquare(grid, coordinate);
            return square.orElseGet(() -> {
                return new Square(coordinate, bombAroundSquare(grid, coordinate), true);
            });
        });
    }

    private Integer bombAroundSquare(Grid grid, Coordinate coordinate) {
        Integer value = 0;
        if (coordinate.getX() + 1 < grid.getWidth())
            value = getBomb(grid, new Coordinate(coordinate.getX() + 1, coordinate.getY())).isPresent() ? value + 1 : value;
        if (coordinate.getY() + 1 < grid.getHeight())
            value = getBomb(grid, new Coordinate(coordinate.getX(), coordinate.getY() + 1)).isPresent() ? value + 1 : value;
        if (coordinate.getX() - 1 >= 0)
            value = getBomb(grid, new Coordinate(coordinate.getX() - 1, coordinate.getY())).isPresent() ? value + 1 : value;
        if (coordinate.getY() - 1 >= 0)
            value = getBomb(grid, new Coordinate(coordinate.getX(), coordinate.getY() - 1)).isPresent() ? value + 1 : value;
        return value;
    }

    private Optional<Square> getSquare(Grid grid, Coordinate coordinate) {
        return grid.getSquares().stream()
                .filter(sq -> sq.getCoordinate().equals(coordinate))
                .findFirst();
    }

    private boolean checkValidCoordinate(Grid grid, Coordinate coordinate) {
        return coordinate.getX() < grid.getWidth() && coordinate.getY() < grid.getHeight();
    }

    private Matcher validateCoordinate(String coordinate) {
        if (coordinate == null)
            return null;
        Pattern pattern = Pattern.compile("^ *(\\d+) *, *(\\d+) *$");
        Matcher matcher = pattern.matcher(coordinate);
        if (!matcher.matches())
            return null;
        return matcher;
    }


    private Matcher validateInitConfig(String initConfig) {
        if (initConfig == null)
            throw new InternalException("Bad game configuration, game exits");
        Pattern pattern = Pattern.compile("^ *(\\d+) *x *(\\d+) +(\\d+) *$");
        Matcher matcher = pattern.matcher(initConfig);
        if (!matcher.matches())
            throw new InternalException("Bad game configuration, game exits");
        return matcher;
    }

    private Optional<Square> getBomb(Grid grid, Square bomb) {
        return grid.getBombs().stream()
                .filter(square -> square.equals(bomb))
                .findFirst();
    }

    private Optional<Square> getBomb(Grid grid, Coordinate coordinate) {
        return getBomb(grid, new Square(coordinate, BOMB_VALUE));
    }

    private Square generateBomb(int width, int height) {
        Random random = new Random();
        int mineWidth = random.nextInt(width);
        int mineHeight = random.nextInt(height);
        return new Square(new Coordinate(mineWidth, mineHeight), BOMB_VALUE);
    }

    private void checkValidConfiguration(int width, int height, int minesNumber) {
        if (width == 0)
            throw new InternalException("Bad game configuration, width must be greater than 0 game exits");
        if (height == 0)
            throw new InternalException("Bad game configuration, height must be greater than 0 game exits");
        if (minesNumber == 0 || (minesNumber > (width * height - 1)))
            throw new InternalException("Bad game configuration, mines number must be greater than 0 and lesser than squares number game exits");
    }
}
