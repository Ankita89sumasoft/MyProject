package Utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class EndUser {

    public static Response createUser(UserPoJo payout)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payout)
       .when()
          .post(Routes.Post_URL);

     return response;
    }

    public static Response readUser(String username)
    {
        Response response=given()
                .pathParam("username",username)

           .when()
                .get(Routes.Get_URL);
        return response;
    }

    public static Response updateUser(UserPoJo payout,String username)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payout)
           .when()
                .put(Routes.Update_URL);
        return response;
    }
    public static Response deleteUser(String username)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)

                .when()
                .delete(Routes.delete_URL);
        return response;
    }

    public static Response PostPet(UserPoJo payload)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
   .when()
                .post(Routes.Post_PetURL);
        return response;
    }
    public static Response getpet(String petname)
    {
        Response response=given()
                .pathParam("petId",petname)

                .when()
                .get(Routes.Get_PetURL);

        return response;
    }
    public static Response updatePet(UserPoJo user,String username)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(user)

                .when()
                        .put(Routes.Update_PetURL);
        return response;
    }

    public static Response deletePet(String name)
    {
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",name)
                .when()
                .delete(Routes.delete_URL);
        return response;
    }
}
