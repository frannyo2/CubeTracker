package CubeTracker;

import java.util.Scanner;

import CubeTracker.api.WcaClient;
import CubeTracker.commands.*;

public class App {

    public static void main(String[] args) {
        WcaClient client = new WcaClient();
        Scanner scanner = new Scanner(System.in);

        MenuRunner menu = new MenuRunner("CubeTracker");
        menu.addCommand(new ProfileCommand(client));
        menu.addCommand(new RankingsCommand(client));
        menu.addCommand(new EventsCommand(client));
        menu.addCommand(new ContinentsCommand(client));

        menu.run(scanner);

        scanner.close();
    }
}
