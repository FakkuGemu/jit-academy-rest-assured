import io.restassured.builder.RequestSpecBuilder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UserTest {
    private static RequestSpecification requestSpec;

    static Stream<Arguments> userListData(){
        return Stream.of(
                arguments(1, 6, 12, 2, 1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                arguments(2, 6, 12, 2, 7, "michael.lawson@reqres.in", "Michael", "Lawson", "https://reqres.in/img/faces/7-image.jpg")
        );
    }
    static Stream<Arguments> userData(){
        return Stream.of(
                arguments(1, "george.bluth@reqres.in", "George", "Bluth", "https://reqres.in/img/faces/1-image.jpg"),
                arguments(2, "janet.weaver@reqres.in", "Janet", "Weaver", "https://reqres.in/img/faces/2-image.jpg"),
                arguments(3, "emma.wong@reqres.in", "Emma", "Wong", "https://reqres.in/img/faces/3-image.jpg")
        );
    }

    @BeforeAll
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users").
                addHeader("x-api-key","reqres-free-v1").
                    build();
    }

    @ParameterizedTest()
    @MethodSource("userListData")
    public void getUserListTest(Integer pageId, Integer perPage, Integer total, Integer total_pages, Integer userId, String email, String firstName, String lastName, String avatar){
        given().
                spec(requestSpec).
                queryParam("page",pageId).
                when().

                get().
                then().

                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/userList.json")).
                body("page", equalTo(pageId)).
                body("per_page", equalTo(perPage)).
                body("total", equalTo(total)).
                body("total_pages", equalTo(total_pages)).

                rootPath("data[0]").

                body("id", equalTo(userId)).
                body("email", equalTo(email)).
                body("first_name", equalTo(firstName)).
                body("last_name", equalTo(lastName)).
                body("avatar", equalTo(avatar)).

                contentType("application/json; charset=utf-8").

                time(lessThan(2L), TimeUnit.SECONDS).
                statusCode(200).
                log().all();
    }

    @ParameterizedTest()
    @MethodSource("userData")
    public void getSingleUserTest(Integer userId, String email, String firstName, String lastName, String avatar){
        given().
               spec(requestSpec).
                pathParam("userId", userId).
                when().
                get("/{userId}").
                then().
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/singleUser.json")).

                rootPath("data").

                body("id", equalTo(userId)).
                body("email", equalTo(email)).
                body("first_name", equalTo(firstName)).
                body("last_name", equalTo(lastName)).
                body("avatar", equalTo(avatar)).

                rootPath("support").

                body("url", equalTo("https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral")).
                body("text", equalTo("Tired of writing endless social media content? Let Content Caddy generate it for you.")).

                contentType("application/json; charset=utf-8").

                statusCode(200).
                log().all();
    }

    @Test
    public void getStarWarsPeopleList(){
        when().
                get("https://swapi.py4e.com/api/people/").
                then().
                body("results.findAll { it.height.toInteger() > 180 }.name", containsInAnyOrder("Darth Vader", "Biggs Darklighter", "Obi-Wan Kenobi")).
                body("results.findAll { it.gender == 'female' }.size()", equalTo(2)).
                statusCode(200).
                contentType("application/json").
                log().all();
    }
}
