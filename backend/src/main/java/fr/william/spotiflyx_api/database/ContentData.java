package fr.william.spotiflyx_api.database;

import org.bson.Document;

import java.util.Date;
import java.util.List;

public class ContentData {

    private int id;
    private String api_id; // Spotify API ID or YouTube API ID
    private String title;
    private String artist;
    private String image_url;
    private List<String> likedBy;
    private ContentType contentType;

    public ContentData(int id, String api_id, String title, String artist, String image_url, Document likedBy, ContentType contentType) {
        this.id = id;
        this.api_id = api_id;
        this.title = title;
        this.artist = artist;
        this.image_url = image_url;
        this.likedBy = likedBy.getList("likedBy", String.class);
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
