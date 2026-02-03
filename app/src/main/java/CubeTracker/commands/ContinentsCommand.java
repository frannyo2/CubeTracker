package CubeTracker.commands;

import java.io.IOException;
import java.util.Scanner;

import CubeTracker.OutputFormatter;
import CubeTracker.api.WcaClient;
import CubeTracker.models.Continent;

public class ContinentsCommand implements Command {
    private final WcaClient client;

    public ContinentsCommand(WcaClient client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return "List continents";
    }

    @Override
    public String getMenuKey() {
        return "4";
    }

    @Override
    public void execute(Scanner scanner) {
        try {
            Continent[] continents = client.getContinents();
            OutputFormatter.printContinents(continents);
        } catch (IOException e) {
            OutputFormatter.printError(e.getMessage());
        }
    }
}
