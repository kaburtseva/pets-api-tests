package services;


import io.restassured.response.Response;
import models.User;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class UserService extends BaseService {

    @Step("create new users with info ${0}")
    public Response createUserWithArray(Object[] userInfo) {
        return setUp().when()
                .body(userInfo)
                .post("user/createWithArray");

    }

    @Step("create new users based on list ${0}")
    public Response createUserWithList(Object[] userInfo) {
        return setUp().when()
                .body(userInfo)
                .post("user/createWithArray");

    }

    @Step("create new user ${0}")
    public Response createUser(User userInfo) {
        return setUp().when()
                .body(userInfo)
                .post("user");

    }

    public User getUserById(String userName) {
        return setUp().when()
                .get(String.format("user/%s", userName)).then().log().ifError().statusCode(200).extract()
                .body().jsonPath().getObject("", User.class);

    }

    public Response updateUser(User userInfo, String username) {
        return setUp().when()
                .body(userInfo)
                .put(String.format("user/%s", username));

    }

   public Response deleteUser(String username) {
        return setUp().when()
                .delete(String.format("user/%s", username));

    }

   public Response loginWithUser(String username, String password) {
        return setUp().when()
                .get(String.format("user/login?username=%s&password=%s", username, password));

    }

    public Response logout() {
        return setUp().when()
                .get("user/logout");

    }


}
