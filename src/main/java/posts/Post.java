package posts;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    public String getId() {
        return id;
    }

    public int getViews() {
        return views;
    }

    public String getTitle() {
        return title;
    }

    public Post setViews(int views) {
        this.views = views;
        return this;
    }

    public Post setId(String id) {
        this.id = id;
        return this;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    private String title;
    private int views;
    private String id;
}
