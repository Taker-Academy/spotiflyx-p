package fr.william.spotiflyx_api.routes.content;

import fr.william.spotiflyx_api.Main;
import fr.william.spotiflyx_api.SpotiflyxRoute;
import fr.william.spotiflyx_api.database.ContentData;
import io.javalin.http.Context;
import org.bson.Document;


public class GetContentRoute implements SpotiflyxRoute {
    @Override
    public void handle(Context ctx) {
        String id = ctx.pathParam("id");
        ContentData content = Main.MARIA_DB_SERVICE.getContentData(id);
        if (content == null) {
            ctx.status(404).result("Content not found");
            return;
        }
        ctx.json(content);
    }
}
