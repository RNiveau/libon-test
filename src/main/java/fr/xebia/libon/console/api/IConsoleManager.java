package fr.xebia.libon.console.api;

/**
 * Created by romainn on 13/01/2015.
 */
public interface IConsoleManager {

    String initGame();

    String readCoordinate();

    void showError(String error);

    void showInfo(String info);

}