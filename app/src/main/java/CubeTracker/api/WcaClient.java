package CubeTracker.api;

import java.io.IOException;
import okhttp3.*;
import com.google.gson.*;

import CubeTracker.models.*;

public class WcaClient {
  private static final OkHttpClient client = new OkHttpClient();
  private static final Gson gson = new Gson();
  private static final String BASE_URL = "https://raw.githubusercontent.com/robiningelbrecht/wca-rest-api/master/api";

  public Person getPersonById(String wcaID) throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/persons/" + wcaID + ".json")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Person not found: " + wcaID);
      }
      return gson.fromJson(response.body().string(), Person.class);
    }
  }

  public Rank[] getRankings(String eventId, boolean isSingle, int limit) throws IOException {
    String rankType = isSingle ? "single" : "average";
    String url = BASE_URL + "/rank/world/" + rankType + "/" + eventId + ".json";

    Request request = new Request.Builder().url(url).build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Failed to fetch rankings");
      }
      RankResponse rankResponse = gson.fromJson(response.body().string(), RankResponse.class);
      Rank[] allRanks = rankResponse.getItems();

      int count = Math.min(limit, allRanks.length);
      Rank[] result = new Rank[count];
      System.arraycopy(allRanks, 0, result, 0, count);
      return result;
    }
  }

  public Event[] getEvents() throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/events.json")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Failed to fetch events");
      }
      EventResponse eventResponse = gson.fromJson(response.body().string(), EventResponse.class);
      return eventResponse.getItems();
    }
  }

  public Continent[] getContinents() throws IOException {
    Request request = new Request.Builder()
        .url(BASE_URL + "/continents.json")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful()) {
        throw new IOException("Failed to fetch continents");
      }
      ContinentResponse continentResponse = gson.fromJson(response.body().string(), ContinentResponse.class);
      return continentResponse.getItems();
    }
  }
}
