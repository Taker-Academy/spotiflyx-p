package fr.william.spotiflyx_api;

import io.javalin.http.Context;

public interface SpotiflyxRoute {

    void handle(Context ctx);

}
