package fr.william.spotiflyx_api.routes.content;

import com.google.gson.JsonObject;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.api.spotify.SpotifyAPI;
import fr.william.spotiflyx_api.api.youtube.YouTubeAPI;
import fr.william.spotiflyx_api.database.ContentType;
import io.javalin.http.Context;
import org.bson.Document;
import org.json.JSONObject;

import java.util.List;

public class FindContentRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        Document content = Document.parse(ctx.body());

        if (!content.containsKey("contentType")) {
            ctx.status(400).result("Missing contentType");
            return;
        }

        ContentType contentType = ContentType.valueOf(content.getString("contentType"));

        if (!content.containsKey("query")) {
            ctx.status(400).result("Missing query");
            return;
        }
        if (contentType == ContentType.SPOTIFY) {
            /*JSONObject result = SpotifyAPI.getMusics(content.getString("query"), 10);
            if (result == null) {
                ctx.status(404).result("No content found");
                return;
            }
            ctx.json(result);*/
        } else if (contentType == ContentType.YOUTUBE) {
            /*List<JSONObject> result = YouTubeAPI.getVideos(content.getString("query"), 10);
            if (result == null) {
                ctx.status(404).result("No content found");
                return;
            }
            ctx.json(result);*/
        }
    }
}
