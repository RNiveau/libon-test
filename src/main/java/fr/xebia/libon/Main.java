package fr.xebia.libon;

import dagger.ObjectGraph;
import fr.xebia.libon.exceptions.InternalException;
import fr.xebia.libon.minesweeper.MineSweeper;
import fr.xebia.libon.minesweeper.MineSweeperModule;

/**
 * Created by romainn on 13/01/2015.
 */
public class Main {

    public static void main(String[] args) {
        ObjectGraph objectGraph = ObjectGraph.create(MineSweeperModule.class);
        MineSweeper mineSweeper = objectGraph.get(MineSweeper.class);
        try {
            mineSweeper.launchGame();
            mineSweeper.runGame();
        } catch (InternalException exception) {
            System.err.println(exception.getMessage());
            System.exit(1);
        }
    }

}
