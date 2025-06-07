package posts;

import io.restassured.response.Response;

public interface IPostService {
    Response getPost(String postId);

    Response getPostList();

    Response addPost(Post post);

    Response editPost(String postId, Post post);

    Response deletePost(String postId);
}
