package fr.xebia.libon.engine;

import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;
import fr.xebia.libon.engine.api.IMineSweeperEngine;
import fr.xebia.libon.exceptions.InternalException;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by romainn on 13/01/2015.
 */
public class MineSweeperEngine implements IMineSweeperEngine {

    @Override
    public Grid createGrid(String initConfig) {

        if (initConfig == null)
            throw new InternalException("Bad game configuration, game exits");
        Pattern pattern = Pattern.compile("^ *(\\d+) *x *(\\d+) +(\\d+) *$");
        Matcher matcher = pattern.matcher(initConfig);
        if (!matcher.matches())
            throw new InternalException("Bad game configuration, game exits");

        int width = Integer.parseInt(matcher.group(1));
        int height = Integer.parseInt(matcher.group(2));
        int minesNumber = Integer.parseInt(matcher.group(3));

        checkValidConfiguration(width, height, minesNumber);

        Grid grid = new Grid(width, height);
        for (int i = 0; i < minesNumber; i++) {
            Square bomb = generateBomb(width, height);
            while (isBomb(grid, bomb))
                bomb = generateBomb(width, height);
            grid.getBombs().add(bomb);
        }
        return grid;
    }

    private boolean isBomb(Grid grid, Square bomb) {
        return grid.getBombs().stream()
                .filter(square -> square.equals(bomb))
                .findFirst()
                .isPresent();
    }

    private Square generateBomb(int width, int height) {
        Random random = new Random();
        int mineWidth = random.nextInt(width);
        int mineHeight = random.nextInt(height);
        return new Square(mineWidth, mineHeight, -1);
    }

    private void checkValidConfiguration(int width, int height, int minesNumber) {
        if (width == 0)
            throw new InternalException("Bad game configuration, width must be greater than 0 game exits");
        if (height == 0)
            throw new InternalException("Bad game configuration, height must be greater than 0 game exits");
        if (minesNumber == 0 || (minesNumber > (width * height - 1)))
            throw new InternalException("Bad game configuration, mines number must be greater than 0 and lesser than squares number game exits");
    }

    @Override
    public Square discoverSquare(Grid grid, String coordinate) {
        return null;
    }

}
