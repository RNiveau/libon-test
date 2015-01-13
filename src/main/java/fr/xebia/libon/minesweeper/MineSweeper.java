package fr.xebia.libon.minesweeper;

import fr.xebia.libon.console.api.IConsoleManager;
import fr.xebia.libon.domain.Grid;
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
        grid = grid;

    }


}
