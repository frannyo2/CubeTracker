package CubeTracker.api;

import java.io.IOException;
import okhttp3.*;
import com.google.gson.*;

import CubeTracker.models.*;

public class WcaClient {
  private static final OkHttpClient client = new OkHttpClient();
  private static final Gson gson = new Gson();
  private static final String URL = "https://raw.githubusercontent.com/robiningelbrecht/wca-rest-api/master/api";

  public Person personbyID(String wcaID) throws IOException {

    Request request = new Request.Builder()
        .url(URL + "/persons/" + wcaID + ".json")
        .build();

    try (Response response = client.newCall(request).execute()) {
      if (!response.isSuccessful())
        throw new IOException("Unexpected Code" + response);

      Person person = gson.fromJson(response.body().string(), Person.class);
      return person;
    } catch (IOException err) {
      System.out.println("Error Occured: " + err.getMessage());
    }
    return new Person();
  }

  public void listCubers(int num, Boolean isSingle) throws IOException {
    if (isSingle) {
      Request request = new Request.Builder().url(URL + "/rank/world/single/333.json").build();
      Response response = client.newCall(request).execute();
      RankResponse page = gson.fromJson(response.body().string(), RankResponse.class);
      Rank[] ranks = page.getItems();
      System.out.println("3x3 Singles World Rankings-----------------------------------------------------------");
      for (int i = 0; i < num; i++) {
        String wcaID = ranks[i].getPersonId();
        System.out.println(
            "Rank " + (i + 1) + ": " + personbyID(wcaID).getname() + "-------" + "Best time: " + ranks[i].getBest()
                + " seconds");
      }
    } else {
      Request request = new Request.Builder().url(URL + "/rank/world/average/333.json").build();
      Response response = client.newCall(request).execute();
      RankResponse page = gson.fromJson(response.body().string(), RankResponse.class);
      Rank[] ranks = page.getItems();
      System.out.println("3x3 Average World Rankings-----------------------------------------------------------");
      for (int i = 0; i < num; i++) {
        String wcaID = ranks[i].getPersonId();
        System.out.println(
            "Rank " + (i + 1) + ": " + personbyID(wcaID).getname() + "-------" + "Best average: " + ranks[i].getBest()
                + " seconds");
      }
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
