package CubeTracker.ui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import CubeTracker.api.WcaClient;
import CubeTracker.models.Event;

public class EventsService extends Service<Event[]> {
    private final WcaClient client;

    public EventsService(WcaClient client) {
        this.client = client;
    }

    @Override
    protected Task<Event[]> createTask() {
        return new Task<>() {
            @Override
            protected Event[] call() throws Exception {
                return client.getEvents();
            }
        };
    }
}
