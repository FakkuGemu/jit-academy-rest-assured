import org.junit.jupiter.api.Test;
import posts.Post;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostTest {

    @Test
    public void addPostTest(){
        Post post = new Post().setTitle("TestTitle").setViews(200);

        String addedPostId = RestService.getPostService().addPost(post).
                then().
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                statusCode(201).
                extract().
                path("id");

        RestService.getPostService().getPost(addedPostId).
                then().
                body("views", equalTo(post.getViews())).
                body("title", equalTo(post.getTitle())).
                body("id", equalTo(addedPostId)).
                statusCode(200);
    }
}
