package fr.xebia.libon.engine.api;

import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;

import java.util.List;

/**
 * Created by romainn on 13/01/2015.
 */
public interface IMineSweeperEngine {

    Grid createGrid(String initConfig);

    /**
     * Discover a square<br/>
     * If square has no adjacent bomb, discover adjacent squares
     * @param grid
     * @param coordinate
     * @return
     */
    Square discoverSquare(Grid grid, String coordinate);

    List<String> encodeGrid(Grid grid);
}
