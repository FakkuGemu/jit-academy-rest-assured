package comments;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class CommentService implements ICommentService{
    private static final String USERS_ENDPOINT = "http://localhost:3000/comments";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            setContentType(ContentType.JSON).
            build();

    @Override
    public Response getComment(String commentId) {
        return given().
                spec(requestSpec).
                pathParam("commentId", commentId).
                when().
                get("{commentId}");
    }

    @Override
    public Response getCommentList() {
        return given().
                spec(requestSpec).
                when().
                get();
    }

    @Override
    public Response addComment(Comment comment) {
        return given().
                spec(requestSpec).
                body(comment).
                when().
                post();
    }

    @Override
    public Response editComment(String commentId, Comment comment) {
        return given().
                spec(requestSpec).
                queryParam("comment_id", commentId).
                body(comment).
                when().
                post("{comment_id}");
    }

    @Override
    public Response deleteComment(String commentId) {
        return given().
                spec(requestSpec).
                queryParam("comment_id", commentId).
                when().
                delete("{comment_id}");
    }
}
