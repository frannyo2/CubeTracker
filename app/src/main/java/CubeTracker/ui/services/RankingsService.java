package CubeTracker.ui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import CubeTracker.api.WcaClient;
import CubeTracker.models.Rank;

public class RankingsService extends Service<Rank[]> {
    private final WcaClient client;
    private String eventId = "333";
    private boolean isSingle = true;
    private int limit = 10;

    public RankingsService(WcaClient client) {
        this.client = client;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setSingle(boolean isSingle) {
        this.isSingle = isSingle;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    protected Task<Rank[]> createTask() {
        final String event = this.eventId;
        final boolean single = this.isSingle;
        final int lim = this.limit;
        return new Task<>() {
            @Override
            protected Rank[] call() throws Exception {
                return client.getRankings(event, single, lim);
            }
        };
    }
}
