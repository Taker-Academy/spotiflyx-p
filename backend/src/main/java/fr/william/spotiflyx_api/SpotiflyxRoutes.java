package fr.william.spotiflyx_api;

import fr.william.spotiflyx_api.routes.*;
import fr.william.spotiflyx_api.routes.content.*;
import io.javalin.Javalin;
import org.eclipse.jetty.http.HttpMethod;

public enum SpotiflyxRoutes {

    LOGIN(HttpMethod.POST, "/auth/login", new LoginRoute()),
    REGISTER(HttpMethod.POST, "/auth/register", new RegisterRoute()),
    CHANGE_PASSWORD(HttpMethod.PUT, "/auth/change-password", new ChangePasswordRoute()),
    DELETE_ACCOUNT(HttpMethod.DELETE, "/auth/delete-account", new DeleteAccountRoute()),
    GET_ACCOUNT(HttpMethod.GET, "/auth/account", new GetAccountRoute()),
    EDIT_USER_PROFILE(HttpMethod.PUT, "/auth/edit-profile", new EditSpotiflyxProfileRoute()),

    GET_FIND_CONTENT(HttpMethod.GET, "/content/find", new FindContentRoute()),
    CREATE_CONTENT(HttpMethod.POST, "/content/create", new CreateContentRoute()),
    GET_CONTENT(HttpMethod.GET, "/content/{id}", new GetContentRoute()),
    GET_CONTENTS(HttpMethod.GET, "/content", new GetContentsRoute()),
    LIKE_CONTENT(HttpMethod.POST, "/content/{id}/like", new LikeContentRoute()),
    UNLIKE_CONTENT(HttpMethod.DELETE, "/content/{id}/like", new UnlikeContentRoute()),
    FAVORITE_CONTENT(HttpMethod.POST, "/content/{id}/favorite", new FavoriteContentRoute()),
    UNFAVORITE_CONTENT(HttpMethod.DELETE, "/content/{id}/favorite", new UnfavoriteContentRoute()),
    ;

    private final HttpMethod method;
    private final String path;
    private final SpotiflyxRoute route;

    SpotiflyxRoutes(HttpMethod method, String path, SpotiflyxRoute route) {
        this.method = method;
        this.path = path;
        this.route = route;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public SpotiflyxRoute getRoute() {
        return route;
    }

    public void register(Javalin app) {
        switch (this.method) {
            case GET:
                app.get(this.path, this.route::handle);
                break;
            case POST:
                app.post(this.path, this.route::handle);
                break;
            case PUT:
                app.put(this.path, this.route::handle);
                break;
            case DELETE:
                app.delete(this.path, this.route::handle);
                break;
            default:
                break;
        }
    }
}
