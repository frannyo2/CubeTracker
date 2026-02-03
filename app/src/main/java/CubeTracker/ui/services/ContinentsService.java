package CubeTracker.ui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import CubeTracker.api.WcaClient;
import CubeTracker.models.Continent;

public class ContinentsService extends Service<Continent[]> {
    private final WcaClient client;

    public ContinentsService(WcaClient client) {
        this.client = client;
    }

    @Override
    protected Task<Continent[]> createTask() {
        return new Task<>() {
            @Override
            protected Continent[] call() throws Exception {
                return client.getContinents();
            }
        };
    }
}
