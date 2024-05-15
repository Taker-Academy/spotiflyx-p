package fr.william.spotiflyx_api.routes.content;

import fr.william.spotiflyx_api.Main;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.api.spotify.SpotifyAPI;
import fr.william.spotiflyx_api.api.youtube.YouTubeAPI;
import fr.william.spotiflyx_api.database.ContentType;
import fr.william.spotiflyx_api.response.Response;
import fr.william.spotiflyx_api.response.SuccessResponse;
import io.javalin.http.Context;
import org.bson.Document;
import org.json.JSONObject;

import java.io.IOException;

public class CreateContentRoute implements SpotiflyxRoute {

    @Override
    public void handle(Context ctx) {
        Document content = Document.parse(ctx.body());

        if (!content.containsKey("contentType") || content.getString("contentType").isEmpty() || !content.containsKey("title") || content.getString("title").isEmpty()) {
            ctx.status(400).result("Missing contentType");
            return;
        }

        ContentType contentType = ContentType.valueOf(content.getString("contentType"));
        String url = content.getString("url");
        String title = content.getString("title");
        String id = null;
        String artist = null;
        String image = null;

        if (contentType == ContentType.SPOTIFY) {
            JSONObject track = SpotifyAPI.searchTrack(title);
            if (track == null) {
                ctx.status(400).result("Invalid track name");
                return;
            }
            image = track.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url");
            artist = track.getJSONArray("artists").getJSONObject(0).getString("name");
            id = track.getString("id");
        } else if (contentType == ContentType.YOUTUBE) {
            JSONObject video = YouTubeAPI.searchVideo(title);
            if (video == null) {
                ctx.status(400).result("Invalid track name");
                return;
            }
            image = video.getString("thumbnail");
            artist = video.getString("author");
            id = video.getString("id");
        }

        if (id == null || artist == null) {
            ctx.status(400).result("Invalid URL : " + (id == null ? "id" : "artist") + " not found");
            return;
        }
        Main.MARIA_DB_SERVICE.createContent(id, title, artist, image, contentType);
        Response response = new SuccessResponse(new Document()
                .append("id", id)
                .append("title", title)
                .append("artist", artist)
                .append("image", image)
                .append("contentType", contentType.toString()));
        ctx.json(response.toJson());
    }
}
