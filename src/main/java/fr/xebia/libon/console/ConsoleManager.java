package fr.xebia.libon.console;


import fr.xebia.libon.console.api.IConsoleManager;
import fr.xebia.libon.exceptions.InternalException;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by romainn on 13/01/2015.
 */
public class ConsoleManager implements IConsoleManager {

    @Override
    public String initGame() {
        return prompt("Give the game configuration (width x height mines-number): ");
    }

    @Override
    public String readCoordinate() {
        return prompt("Give a coordinate (x, y): ");
    }

    @Override
    public void showError(String error) {
        System.err.println(error);
    }

    @Override
    public void showInfo(String info) {
        System.out.println(info);
    }


    private String prompt(String prompt) {
        Console console = System.console();
        String in;
        if (console == null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(prompt);
            System.out.flush();
            try {
                in = reader.readLine();
            } catch (IOException e) {
                throw new InternalException("The system failed to read something, exit");

            }
        } else {
            in = console.readLine(prompt);
        }
        return in;
    }

}
