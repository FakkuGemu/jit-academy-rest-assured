package posts;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class PostService implements IPostService{
    private static final String USERS_ENDPOINT = "http://localhost:3000/posts";

    RequestSpecification requestSpec = new RequestSpecBuilder().
            setBaseUri(USERS_ENDPOINT).
            setContentType(ContentType.JSON).
            build();

    public Response getUserList(Integer pageId){
        return given()
                .spec(requestSpec).
                queryParam("page", pageId).
                when().
                get();
    }
    public Response getUser(Integer userId){
        return given().
                spec(requestSpec).
                pathParam("user_id", userId).
                when().
                get("{user_id}");
    }

    @Override
    public Response getPost(String postId) {
        return given().
                spec(requestSpec).
                pathParam("post_id", postId).
                when().
                get("{post_id}");
    }

    @Override
    public Response getPostList() {
        return given().
                spec(requestSpec).
                when().
                get();
    }

    @Override
    public Response addPost(Post post) {
        return given().
                spec(requestSpec).
                body(post).
                when().
                post();
    }

    @Override
    public Response editPost(String postId, Post post) {
        return given().
                spec(requestSpec).
                pathParam("post_id", postId).
                body(post).
                when().
                post("{postId}");
    }

    @Override
    public Response deletePost(String postId) {
        return given().
                spec(requestSpec).
                pathParam("post_id", postId).
                when().
                delete("{postId}");
    }
}
