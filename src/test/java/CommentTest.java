import comments.Comment;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CommentTest {
    @Test
    public void addCommentTest(){
        Comment comment = new Comment().setPostId("242").setText("Kto slucha w 2420?");

        String postCommentId = RestService.getCommentService().addComment(comment).
                then().
                body("text", equalTo(comment.getText())).
                body("postId", equalTo(comment.getPostId())).
                statusCode(201).
                extract().
                path("id");

        RestService.getCommentService().getComment(postCommentId).
                then().
                body("text", equalTo(comment.getText())).
                body("postId", equalTo(comment.getPostId())).
                body("id", equalTo(postCommentId)).
                statusCode(200);
    }
}
