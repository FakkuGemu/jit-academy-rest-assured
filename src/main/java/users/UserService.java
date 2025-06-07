package users;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;


public class UserService implements IUserService{
    private static final String USERS_ENDPOINT = "https://reqres.in/api/users";
    private static final String API_KEY = "reqres-free-v1";

    RequestSpecification requestSpec = new RequestSpecBuilder().
    setBaseUri(USERS_ENDPOINT).
    addHeader("x-api-key",API_KEY).
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
                get("/{user_id}");
    }

}
