package CubeTracker.commands;

import java.io.IOException;
import java.util.Scanner;

import CubeTracker.OutputFormatter;
import CubeTracker.api.WcaClient;
import CubeTracker.models.Event;

public class EventsCommand implements Command {
    private final WcaClient client;

    public EventsCommand(WcaClient client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return "List all events";
    }

    @Override
    public String getMenuKey() {
        return "3";
    }

    @Override
    public void execute(Scanner scanner) {
        try {
            Event[] events = client.getEvents();
            OutputFormatter.printEvents(events);
        } catch (IOException e) {
            OutputFormatter.printError(e.getMessage());
        }
    }
}
