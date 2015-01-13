package fr.xebia.libon.minesweeper;

import fr.xebia.libon.console.api.IConsoleManager;

import javax.inject.Inject;

/**
 * Created by romainn on 13/01/2015.
 */
public class MineSweeper {

    private IConsoleManager consoleManager;

    @Inject
    public MineSweeper(IConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    public void launchGame() {
        String initConfig = consoleManager.initGame();



    }


}
