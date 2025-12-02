package CubeTracker.api;

import java.io.IOException;
import okhttp3.*;
import com.google.gson.*;

import CubeTracker.models.*;

public class WcaClient {
  private static final OkHttpClient client = new OkHttpClient();
  private static final Gson gson = new Gson();
  private static final String URL = "https://raw.githubusercontent.com/robiningelbrecht/wca-rest-api/master/api";

  public void personData(String wcaID) throws IOException {

    Request request = new Request.Builder()
        .url(URL + "/persons/" + wcaID + ".json")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful())
        throw new IOException("Unexpected Code" + response);

      // Headers responseHeaders = response.headers();
      // for (int i = 0; i < responseHeaders.size(); i++) {
      // System.out.println(responseHeaders.name(i) + ": " +
      // responseHeaders.value(i));
      // }

      Person person = gson.fromJson(response.body().string(), Person.class);
      System.out.println(person.getCountry() + "   " + person.getid() + "   " + person.getname());
    } catch (IOException err) {
      System.out.println("Error Occured: " + err.getMessage());
    }

  }

  public void listContinents() throws IOException {
    Request request = new Request.Builder().url(URL + "/continents.json").build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful())
        throw new IOException("Unexpected Code" + response);

      ContinentResponse continents = gson.fromJson(response.body().string(), ContinentResponse.class);
      System.out.println(continents.getPagination() + "   " + continents.getTotal() + "   " + continents.getItems()[1]);
    } catch (IOException err) {
      System.out.println("Error: " + err.getMessage());
    }
  }
}
