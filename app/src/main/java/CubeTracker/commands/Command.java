package CubeTracker.commands;

import java.util.Scanner;

public interface Command {
    String getName();
    String getMenuKey();
    void execute(Scanner scanner);
}
