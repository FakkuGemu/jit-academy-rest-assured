package comments;

import io.restassured.response.Response;

public interface ICommentService {
    Response getComment(String commentId);

    Response getCommentList();

    Response addComment(Comment comment);

    Response editComment(String commentId, Comment comment);

    Response deleteComment(String commentId);
}
