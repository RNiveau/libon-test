package fr.xebia.libon.minesweeper;

/**
 * Created by romainn on 13/01/2015.
 */

import dagger.Module;
import dagger.Provides;
import fr.xebia.libon.console.ConsoleManager;
import fr.xebia.libon.console.api.IConsoleManager;
import fr.xebia.libon.engine.MineSweeperEngine;
import fr.xebia.libon.engine.api.IMineSweeperEngine;

import javax.inject.Singleton;

@Module(injects = MineSweeper.class)
public class MineSweeperModule {

    @Provides
    @Singleton
    public IConsoleManager provideConsoleManager() {
        return new ConsoleManager();
    }

    @Provides
    @Singleton
    public IMineSweeperEngine provideMineSweeperEngine() {
        return new MineSweeperEngine();
    }

}
