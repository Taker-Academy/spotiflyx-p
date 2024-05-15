package fr.william.spotiflyx_api.api.spotify;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SpotifyAPI {

    public static JSONObject searchTrack(String trackName) {
        OkHttpClient client = new OkHttpClient();

        Request request = null;
        try {
            request = new Request.Builder()
                    .url("https://api.spotify.com/v1/search?q=" + trackName + "&type=track&limit=1")
                    .get()
                    .addHeader("Authorization", "Bearer " + TokenProvider.getAccessToken())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONObject jsonObject = new JSONObject(response.body().string());

            if (jsonObject.getJSONObject("tracks").getJSONArray("items").isEmpty()) {
                return null;
            }

            JSONArray items = jsonObject.getJSONObject("tracks").getJSONArray("items");

            if (items.isEmpty())
                return null;

            JSONObject result = items.getJSONObject(0);

            if (!result.has("name"))
                return null;

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
