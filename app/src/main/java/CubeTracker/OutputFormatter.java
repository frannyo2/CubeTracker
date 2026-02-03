package CubeTracker;

import CubeTracker.models.Continent;
import CubeTracker.models.Event;
import CubeTracker.models.Person;
import CubeTracker.models.Rank;

public class OutputFormatter {

    public static void printPerson(Person person) {
        System.out.println();
        System.out.println("=== Cuber Profile ===");
        System.out.println("Name: " + person.getName());
        System.out.println("WCA ID: " + person.getId());
        System.out.println("Country: " + person.getCountry());
        System.out.println("Competitions: " + person.getNumberOfCompetitions());

        Person.Medals medals = person.getMedals();
        if (medals != null) {
            System.out.println("Medals: " + medals.getGold() + " gold, "
                    + medals.getSilver() + " silver, "
                    + medals.getBronze() + " bronze ("
                    + medals.getTotal() + " total)");
        }
    }

    public static void printRankings(Rank[] ranks, int limit, boolean isSingle, String eventId) {
        String rankType = isSingle ? "Single" : "Average";
        System.out.println();
        System.out.println("=== " + eventId.toUpperCase() + " " + rankType + " World Rankings ===");

        int count = Math.min(limit, ranks.length);
        for (int i = 0; i < count; i++) {
            Rank rank = ranks[i];
            String timeLabel = isSingle ? "Best" : "Avg";
            System.out.printf("%-4d %-15s %s: %.2f sec%n",
                    i + 1,
                    rank.getPersonId(),
                    timeLabel,
                    rank.getBest());
        }
    }

    public static void printRankingsWithNames(Rank[] ranks, String[] names, int limit, boolean isSingle, String eventId) {
        String rankType = isSingle ? "Single" : "Average";
        System.out.println();
        System.out.println("=== " + eventId.toUpperCase() + " " + rankType + " World Rankings ===");

        int count = Math.min(limit, ranks.length);
        for (int i = 0; i < count; i++) {
            Rank rank = ranks[i];
            String name = (names != null && i < names.length && names[i] != null) ? names[i] : rank.getPersonId();
            String timeLabel = isSingle ? "Best" : "Avg";
            System.out.printf("%-4d %-25s %s: %.2f sec%n",
                    i + 1,
                    name,
                    timeLabel,
                    rank.getBest());
        }
    }

    public static void printEvents(Event[] events) {
        System.out.println();
        System.out.println("=== WCA Events ===");
        for (Event event : events) {
            System.out.printf("%-8s %s%n", event.getId(), event.getName());
        }
        System.out.println("Total: " + events.length + " events");
    }

    public static void printContinents(Continent[] continents) {
        System.out.println();
        System.out.println("=== Continents ===");
        for (Continent continent : continents) {
            System.out.printf("%-20s (%s)%n", continent.getName(), continent.getId());
        }
        System.out.println("Total: " + continents.length + " continents");
    }

    public static void printError(String message) {
        System.out.println("Error: " + message);
    }
}
