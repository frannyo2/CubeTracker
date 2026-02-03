package CubeTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CubeTracker.commands.Command;

public class MenuRunner {
    private final List<Command> commands = new ArrayList<>();
    private final String title;

    public MenuRunner(String title) {
        this.title = title;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void run(Scanner scanner) {
        while (true) {
            printMenu();
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                System.out.println("Goodbye!");
                break;
            }

            Command selected = findCommand(input);
            if (selected != null) {
                selected.execute(scanner);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== " + title + " ===");
        for (Command cmd : commands) {
            System.out.println("[" + cmd.getMenuKey() + "] " + cmd.getName());
        }
        System.out.println("[0] Exit");
        System.out.print("Select option: ");
    }

    private Command findCommand(String key) {
        for (Command cmd : commands) {
            if (cmd.getMenuKey().equals(key)) {
                return cmd;
            }
        }
        return null;
    }
}
