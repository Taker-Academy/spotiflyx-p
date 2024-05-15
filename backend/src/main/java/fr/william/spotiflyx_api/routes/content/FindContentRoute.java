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
    }
}
