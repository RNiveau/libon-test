package fr.xebia.libon.engine;

import fr.xebia.libon.domain.Coordinate;
import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;
import fr.xebia.libon.exceptions.InternalException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by romainn on 14/01/2015.
 */
public class MineSweeperEngineTest {

    @Test(expected = InternalException.class)
    public void should_not_create_grid_bad_user_entry() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        mineSweeperEngine.createGrid("skjdfsdkjflj");
    }

    @Test(expected = InternalException.class)
    public void should_not_create_grid_bad_dimension() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        mineSweeperEngine.createGrid("0 x 1 1");
    }

    @Test(expected = InternalException.class)
    public void should_not_create_grid_zero_mine() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        mineSweeperEngine.createGrid("1 x 2 0");
    }

    @Test(expected = InternalException.class)
    public void should_not_create_grid_too_many_mines() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        mineSweeperEngine.createGrid("1 x 2 2");
    }

    @Test
    public void should_create_grid() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();

        Grid grid = mineSweeperEngine.createGrid("1 x 2 1");
        Assert.assertEquals(new Integer(1), grid.getWidth());
        Assert.assertEquals(new Integer(2), grid.getHeight());
        Assert.assertEquals(1, grid.getBombs().size());
    }

    @Test
    public void should_not_discover_square() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        Grid grid = new Grid(1, 2);

        // Bad user entry
        Assert.assertNull(mineSweeperEngine.discoverSquare(grid, "sfslid"));

        // Bad coordinates
        Assert.assertNull(mineSweeperEngine.discoverSquare(grid, "0, 5"));
        Assert.assertNull(mineSweeperEngine.discoverSquare(grid, "1, 0"));
    }

    @Test
    public void should_discover_square() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        Grid grid = new Grid(10, 5);
        grid.getBombs().add(new Square(new Coordinate(0, 1), Square.BOMB_VALUE));

        // Square adjacent to bomb, discover 1 square
        Square square = mineSweeperEngine.discoverSquare(grid, "0, 0");
        Assert.assertNotNull(square);
        Assert.assertEquals(new Integer(1), square.getValue());
        Assert.assertTrue(square.isDiscover());
        Assert.assertEquals(1, grid.getSquares().size());

        // Recursive discover
        square = mineSweeperEngine.discoverSquare(grid, "0, 4");
        Assert.assertNotNull(square);
        Assert.assertEquals(new Integer(0), square.getValue());
        Assert.assertTrue(square.isDiscover());
        Assert.assertEquals(49, grid.getSquares().size());

        // Discover a bomb
        square = mineSweeperEngine.discoverSquare(grid, "0, 1");
        Assert.assertNotNull(square);
        Assert.assertEquals(new Integer(Square.BOMB_VALUE), square.getValue());
        Assert.assertEquals(49, grid.getSquares().size());

        // Add a second bomb and clear squares
        grid.getBombs().add(new Square(new Coordinate(1, 0), Square.BOMB_VALUE));
        grid.getSquares().clear();
        square = mineSweeperEngine.discoverSquare(grid, "0, 0");
        Assert.assertNotNull(square);
        Assert.assertEquals(new Integer(2), square.getValue());
        Assert.assertTrue(square.isDiscover());
        Assert.assertEquals(1, grid.getSquares().size());
    }

    @Test
    public void should_encode_grid() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        Grid grid = new Grid(3, 3);
        grid.getBombs().add(new Square(new Coordinate(0, 1), Square.BOMB_VALUE));

        mineSweeperEngine.discoverSquare(grid, "0,0");
        List<String> encode = mineSweeperEngine.encodeGrid(grid);
        Assert.assertEquals("1__", encode.get(0));
        Assert.assertEquals("___", encode.get(1));
        Assert.assertEquals("___", encode.get(2));

        mineSweeperEngine.discoverSquare(grid, "1,2");
        encode = mineSweeperEngine.encodeGrid(grid);
        Assert.assertEquals("100", encode.get(0));
        Assert.assertEquals("_10", encode.get(1));
        Assert.assertEquals("100", encode.get(2));
    }

    @Test
    public void should_not_grid_completed() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        Grid grid = new Grid(3, 3);

        Assert.assertFalse(mineSweeperEngine.gridCompleted(grid));
    }

    @Test
    public void should_grid_completed() {
        MineSweeperEngine mineSweeperEngine = new MineSweeperEngine();
        Grid grid = new Grid(1, 3);
        grid.getBombs().add(new Square(new Coordinate(0, 1), Square.BOMB_VALUE));
        grid.getSquares().add(new Square(new Coordinate(0, 0), 1, true));
        grid.getSquares().add(new Square(new Coordinate(0, 2), 1, true));
        Assert.assertTrue(mineSweeperEngine.gridCompleted(grid));
    }
}
