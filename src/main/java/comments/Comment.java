package comments;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment {
    public String getText() {
        return text;
    }

    public String getPostId() {
        return postId;
    }

    public String getId() {
        return id;
    }

    public Comment setId(String id) {
        this.id = id;
        return this;
    }

    public Comment setPostId(String postId) {
        this.postId = postId;
        return this;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    private String text;
    private String postId;
    private String id;



}
