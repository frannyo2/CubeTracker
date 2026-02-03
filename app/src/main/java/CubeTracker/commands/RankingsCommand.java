package CubeTracker.commands;

import java.io.IOException;
import java.util.Scanner;

import CubeTracker.OutputFormatter;
import CubeTracker.api.WcaClient;
import CubeTracker.models.Person;
import CubeTracker.models.Rank;

public class RankingsCommand implements Command {
    private final WcaClient client;

    public RankingsCommand(WcaClient client) {
        this.client = client;
    }

    @Override
    public String getName() {
        return "View world rankings";
    }

    @Override
    public String getMenuKey() {
        return "2";
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println();
        System.out.println("[1] Single times");
        System.out.println("[2] Average times");
        System.out.print("Select ranking type: ");
        String typeChoice = scanner.nextLine().trim();

        boolean isSingle;
        if (typeChoice.equals("1")) {
            isSingle = true;
        } else if (typeChoice.equals("2")) {
            isSingle = false;
        } else {
            OutputFormatter.printError("Invalid choice. Please select 1 or 2.");
            return;
        }

        System.out.print("How many cubers? (1-100): ");
        String limitStr = scanner.nextLine().trim();
        int limit;
        try {
            limit = Integer.parseInt(limitStr);
            if (limit < 1 || limit > 100) {
                OutputFormatter.printError("Please enter a number between 1 and 100.");
                return;
            }
        } catch (NumberFormatException e) {
            OutputFormatter.printError("Invalid number.");
            return;
        }

        try {
            Rank[] ranks = client.getRankings("333", isSingle, limit);
            String[] names = new String[ranks.length];

            System.out.println("Fetching cuber names...");
            for (int i = 0; i < ranks.length; i++) {
                try {
                    Person person = client.getPersonById(ranks[i].getPersonId());
                    names[i] = person.getName();
                } catch (IOException e) {
                    names[i] = ranks[i].getPersonId();
                }
            }

            OutputFormatter.printRankingsWithNames(ranks, names, limit, isSingle, "333");
        } catch (IOException e) {
            OutputFormatter.printError(e.getMessage());
        }
    }
}
