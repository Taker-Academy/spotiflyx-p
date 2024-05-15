package fr.william.spotiflyx_api.routes.content;

import fr.william.spotiflyx_api.Main;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.database.ContentData;
import fr.william.spotiflyx_api.database.ContentType;
import io.javalin.http.Context;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class LikeContentRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        Document response = new Document();

        JSONArray youtubeContent = new JSONArray();
        JSONArray spotifyContent = new JSONArray();

        List<ContentData> contentData = Main.MARIA_DB_SERVICE.getAllContent();

        for (ContentData content : contentData) {
            if (content.getContentType() == ContentType.YOUTUBE) {
                youtubeContent.put(new JSONObject(content.toJsonObject()));
            } else if (content.getContentType() == ContentType.SPOTIFY) {
                spotifyContent.put(new JSONObject(content.toJsonObject()));
            }
        }
        ctx.json(response.append("youtubeContent", youtubeContent).append("spotifyContent", spotifyContent));
    }
}
