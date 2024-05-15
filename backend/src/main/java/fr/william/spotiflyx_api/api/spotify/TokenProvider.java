package fr.william.spotiflyx_api.api.spotify;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenProvider {

    private static final String CLIENT_ID = System.getenv("SPOTIFY_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("SPOTIFY_CLIENT_SECRET");
    private static String accessToken;
    private static long expiryTime;

    public static String getAccessToken() throws IOException {
        if (accessToken == null || System.currentTimeMillis() > expiryTime) {
            OkHttpClient client = new OkHttpClient();

            String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
            String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            RequestBody body = new FormBody.Builder()
                    .add("grant_type", "client_credentials")
                    .build();

            Request request = new Request.Builder()
                    .url("https://accounts.spotify.com/api/token")
                    .post(body)
                    .addHeader("Authorization", "Basic " + encodedCredentials)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                JSONObject jsonObject = new JSONObject(response.body().string());
                accessToken = jsonObject.getString("access_token");

                int expiresIn = jsonObject.getInt("expires_in");
                expiryTime = System.currentTimeMillis() + (expiresIn * 1000);
            }
        }

        return accessToken;
    }
}
