package fr.william.spotiflyx_api.api.youtube;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeAPI {

    private static final String API_KEY = System.getenv("YOUTUBE_API_KEY");

    public static JSONObject searchVideo(String trackName) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + trackName + "&type=video&maxResults=1&key=" + API_KEY)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONObject jsonObject = new JSONObject(response.body().string());

            if (jsonObject.getJSONArray("items").isEmpty()) {
                return null;
            }

            JSONArray items = jsonObject.getJSONArray("items");

            if (items.isEmpty())
                return null;

            JSONObject result = items.getJSONObject(0).getJSONObject("snippet");

            if (!result.has("title"))
                return null;

            // Create a new JSON object to store the required details
            JSONObject videoDetails = new JSONObject();
            videoDetails.put("id", items.getJSONObject(0).getJSONObject("id").getString("videoId"));
            videoDetails.put("author", result.getString("channelTitle"));
            videoDetails.put("thumbnail", result.getJSONObject("thumbnails").getJSONObject("default").getString("url"));

            return videoDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
