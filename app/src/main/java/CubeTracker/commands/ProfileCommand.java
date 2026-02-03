package CubeTracker.commands;

import java.io.IOException;
import java.util.Scanner;

import CubeTracker.OutputFormatter;
import CubeTracker.api.WcaClient;
import CubeTracker.models.Person;

public class ProfileCommand implements Command {
    private final WcaClient client;

    public ProfileCommand(WcaClient client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return "Look up cuber profile";
    }

    @Override
    public String getMenuKey() {
        return "1";
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.print("Enter WCA ID (e.g., 2015PARK08): ");
        String wcaId = scanner.nextLine().trim().toUpperCase();

        if (wcaId.isEmpty()) {
            OutputFormatter.printError("WCA ID cannot be empty");
            return;
        }

        try {
            Person person = client.getPersonById(wcaId);
            OutputFormatter.printPerson(person);
        } catch (IOException e) {
            OutputFormatter.printError(e.getMessage());
        }
    }
}
