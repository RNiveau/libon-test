package fr.xebia.libon.minesweeper;

import fr.xebia.libon.console.api.IConsoleManager;
import fr.xebia.libon.domain.Grid;
import fr.xebia.libon.domain.Square;
import fr.xebia.libon.engine.api.IMineSweeperEngine;

import javax.inject.Inject;

/**
 * Created by romainn on 13/01/2015.
 */
public class MineSweeper {

    private Grid grid;

    private IConsoleManager consoleManager;

    private IMineSweeperEngine mineSweeperEngine;


    @Inject
    public MineSweeper(IConsoleManager consoleManager, IMineSweeperEngine mineSweeperEngine) {
        this.consoleManager = consoleManager;
        this.mineSweeperEngine = mineSweeperEngine;
    }

    public void launchGame() {
        String initConfig = consoleManager.initGame();
        grid = mineSweeperEngine.createGrid(initConfig);
    }


    public void runGame() {
        while (true) {
            String coordinate = consoleManager.readCoordinate();
            Square square = mineSweeperEngine.discoverSquare(grid, coordinate);
            if (square == null) {
                consoleManager.showError("Bad coordinate, try again");
            } else if (Square.BOMB_VALUE.equals(square.getValue())) {
                consoleManager.showInfo("Bomb uncover, game ends");
                break;
            } else {
                mineSweeperEngine.encodeGrid(grid).stream().forEach(line -> consoleManager.showInfo(line));
            }
            if (mineSweeperEngine.gridComplete(grid)) {
                consoleManager.showInfo("All squares are uncovered, you win");
                break;
            }
        }
    }
}
