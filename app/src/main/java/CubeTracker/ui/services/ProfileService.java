package CubeTracker.ui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import CubeTracker.api.WcaClient;
import CubeTracker.models.Person;

public class ProfileService extends Service<Person> {
    private final WcaClient client;
    private String wcaId;

    public ProfileService(WcaClient client) {
        this.client = client;
    }

    public void setWcaId(String wcaId) {
        this.wcaId = wcaId;
    }

    @Override
    protected Task<Person> createTask() {
        final String id = this.wcaId;
        return new Task<>() {
            @Override
            protected Person call() throws Exception {
                return client.getPersonById(id);
            }
        };
    }
}
