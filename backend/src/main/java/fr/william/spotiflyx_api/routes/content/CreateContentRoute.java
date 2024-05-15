package fr.william.spotiflyx_api.routes.content;

import fr.william.spotiflyx_api.SpotiflyxRoute;
import io.javalin.http.Context;
import org.bson.Document;

public class CreateContentRoute implements SpotiflyxRoute {

    @Override
    public void handle(Context ctx) {
        Document content = Document.parse(ctx.body());

    }
}
