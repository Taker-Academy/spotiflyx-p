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
import java.util.stream.Collectors;

public class GetContentsRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        JSONObject response = new JSONObject();

        JSONArray youtubeContent = new JSONArray();
        JSONArray spotifyContent = new JSONArray();

        List<ContentData> contentData = Main.MARIA_DB_SERVICE.getAllContent();
        List<ContentData> youtubeContentData = contentData.stream().filter(content -> content.getContentType() == ContentType.YOUTUBE).limit(10).collect(Collectors.toList());
        youtubeContentData.sort((content1, content2) -> content2.getCreatedAt().compareTo(content1.getCreatedAt()));

        List<ContentData> spotifyContentData = contentData.stream().filter(content -> content.getContentType() == ContentType.SPOTIFY).limit(10).collect(Collectors.toList());
        spotifyContentData.sort((content1, content2) -> content2.getCreatedAt().compareTo(content1.getCreatedAt()));

        for (ContentData content : youtubeContentData)
            youtubeContent.put(content.toJsonObject());
        for (ContentData content : spotifyContentData)
            spotifyContent.put(content.toJsonObject());
        response.put("youtube", youtubeContent);
        response.put("spotify", spotifyContent);
        ctx.json(response.toString());
    }
}
