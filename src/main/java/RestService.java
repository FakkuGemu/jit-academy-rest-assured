import comments.CommentService;
import org.checkerframework.checker.units.qual.C;
import posts.PostService;
import users.UserService;

public class RestService {

    public static UserService getUserService(){
        return new UserService();
    }

    public static PostService getPostService(){
        return new PostService();
    }

    public static CommentService getCommentService(){
        return new CommentService();
    }
}
