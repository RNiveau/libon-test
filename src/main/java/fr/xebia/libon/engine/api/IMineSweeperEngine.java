package fr.xebia.libon.engine.api;

import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;

/**
 * Created by romainn on 13/01/2015.
 */
public interface IMineSweeperEngine {

    Grid createGrid(String initConfig);

    Square discoverSquare(Grid grid, String coordinate);

}
