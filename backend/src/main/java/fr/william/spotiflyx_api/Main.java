package fr.william.spotiflyx_api;

import fr.william.spotiflyx_api.database.MariaDBService;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

public class Main {

    public final static MariaDBService MARIA_DB_SERVICE = new MariaDBService();
    public final static UserService USER_SERVICE = new UserService(MARIA_DB_SERVICE);

    public static void main(String[] args) {
        MariaDBService.connect();
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        });

        for (SpotiflyxRoutes route : SpotiflyxRoutes.values())
            route.register(app);

        app.events(event -> event.serverStopping(MariaDBService::close));
        app.start(7070);
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }

}
