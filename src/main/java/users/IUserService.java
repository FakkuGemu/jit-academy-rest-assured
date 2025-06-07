package users;

import io.restassured.response.Response;

public interface IUserService{
    Response getUserList(Integer pageId);
    Response getUser(Integer userId);
}
