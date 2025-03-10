package StepDefination;

import Utilities.EndUser;
import Utilities.Routes;
import Utilities.UserPoJo;
import com.github.javafaker.Educator;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SwaggerUser {
    Faker faker;
    UserPoJo userdata;
    public Logger log;
    @Given("set up the required data for the petstore swagger page")
    public void set_up_the_required_data_for_the_petstore_swagger_page() {
        userdata=new UserPoJo();
        faker=new Faker();

        userdata.setId(faker.idNumber().hashCode());
        userdata.setUsername(faker.name().username());
        userdata.setFirstname(faker.name().firstName());
        userdata.setLastname(faker.name().lastName());
        userdata.setEmail(faker.internet().safeEmailAddress());
        userdata.setPassword(faker.internet().password(5,10));
        userdata.setPhonenumber(faker.phoneNumber().phoneNumber());
        log=LogManager.getLogger(this.getClass());
    }
    @When("verify the post API on petstore swagger page")
    public void verify_the_post_api_on_petstore_swagger_page() {
        log.info("******************** Post API test for user module begging ******************");
       Response response= EndUser.createUser(userdata);
       response.then().log().all();

       Assert.assertEquals(response.getStatusCode(),200);
        log.info("******************** Post API test for user module Stop ******************");
    }
    @Then("Verify the get API on petstore swagger page")
    public void verify_the_get_api_on_petstore_swagger_page() {
        log.info("******************** get API test for user module begging ******************");
        Response response=EndUser.readUser(this.userdata.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        log.info("******************** get API test for user module Stop ******************");
    }
    @When("Verify the update API on petstored swagger page")
    public void verify_the_update_api_on_petstored_swagger_page() {
        log.info("******************** put API test for user module begging ******************");
        //Before update data
        userdata.setFirstname(faker.name().firstName());
        userdata.setLastname(faker.name().lastName());
        userdata.setEmail(faker.internet().safeEmailAddress());

        Response response=EndUser.updateUser(userdata,this.userdata.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //after response
        Response afterresponse=EndUser.readUser(this.userdata.getUsername());
        afterresponse.then().log().all();

        Assert.assertEquals(afterresponse.getStatusCode(),200);
        log.info("******************** put API test for user module Stop ******************");
    }
    @Then("Verify the delete API on petstored swagger page")
    public void verify_the_delete_api_on_petstored_swagger_page() {
        log.info("******************** delete API test for user module begging ******************");
        Response response=EndUser.deleteUser(this.userdata.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        log.info("******************** delete API test for user module Stop ******************");
    }
    @Given("set up the required data for the pet module in petstore swagger page")
    public void set_up_the_required_data_for_the_pet_module_in_petstore_swagger_page() {
        userdata=new UserPoJo();
        faker=new Faker();
        userdata.setName(faker.name().username());
        userdata.setId(faker.idNumber().hashCode());
        userdata.setUsername(faker.name().username());
        userdata.setFirstname(faker.name().firstName());
        userdata.setLastname(faker.name().lastName());
        userdata.setEmail(faker.internet().safeEmailAddress());
        userdata.setPassword(faker.internet().password(5,10));
    }
    @When("Verify the Post API of pet module on petstore swagger page")
    public void verify_the_post_api_of_pet_module_on_petstore_swagger_page() {
        Response response=EndUser.PostPet(userdata);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Then("Verify the get API of pet module on petstore swagger page")
    public void verify_the_get_api_of_pet_module_on_petstore_swagger_page() {
        Response response=EndUser.getpet(this.userdata.getName());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @When("verify the put API of pet module on petstore swagger page")
    public void verify_the_put_api_of_pet_module_on_petstore_swagger_page() {
        userdata.setFirstname(faker.name().firstName());
        userdata.setLastname(faker.name().lastName());
        userdata.setEmail(faker.internet().safeEmailAddress());

        Response response=EndUser.updatePet(userdata,this.userdata.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);

        Response againrespone=EndUser.updatePet(userdata,this.userdata.getUsername());
        Assert.assertEquals(againrespone.getStatusCode(),200);
    }
    @Then("Verify the delete API of pet module on petstore swagger page")
    public void verify_the_delete_api_of_pet_module_on_petstore_swagger_page() {
       Response response= EndUser.deletePet(this.userdata.getFirstname());
       response.then().log().all();

       Assert.assertEquals(response.getStatusCode(),200);
    }

    @When("I test the travel agent get API")
    public void i_test_the_travel_agent_get_api() {
        Response response=given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");
                 XmlPath xmlPath=new XmlPath(response.asString());
                 List<String> traveldatacount=xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
                 Assert.assertEquals(traveldatacount.size(),10);
                 boolean status=false;
                 System.out.println("All Travel information names are:");
                 List<String>name=xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
                 for (String travelinformationname:name)
                 {
                     if (travelinformationname.equals("Developer"))
                     {
                         status=true;
                         break;
                     }
                     Assert.assertEquals(status,true);
                 }

                 Assert.assertEquals(response.getStatusCode(),200);
                 Assert.assertEquals(response.header("Content-Type"),"application/xml; charset=utf-8");

                 String pageverify=response.xmlPath().get("TravelerinformationResponse.page").toString();
                 Assert.assertEquals(pageverify,"1");

                 String name1=response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
                 Assert.assertEquals(name1,"Developer");

                 String email=response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].email").toString();
                 Assert.assertEquals(email,"Developer12@gmail.com");

                 String address=response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[1].adderes").toString();
                 Assert.assertEquals(address,"USA");

                 String createdby=response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[2].createdat").toString();
                 Assert.assertEquals(createdby,"0001-01-01T00:00:00");
    }
    @When("verify the single upload file on spring boot website")
    public void verify_the_single_upload_file_on_spring_boot_website() {
        File myfile=new File("C:\\Users\\pooja.sapkal\\Desktop\\RESTAPIAutomationPractics\\Demo1.txt");

        given()
                .multiPart("file",myfile)
                .contentType("multipart/form-date")
                .when()
                    .post("http://localhost:8080/uploadFile")
                .then()
                    .statusCode(200)
                     .body("fileName",equalTo("Demo1.txt"))
                        .log().all();
    }
    @Then("Verify the multiple upload file on spring boot website")
    public void verify_the_multiple_upload_file_on_spring_boot_website() {
        File myfile1=new File("C:\\Users\\pooja.sapkal\\Desktop\\RESTAPIAutomationPractics\\Demo1.txt");
        File myfile2=new File("C:\\Users\\pooja.sapkal\\Desktop\\RESTAPIAutomationPractics\\Demo2.txt");
        given()
                .multiPart("files",myfile1)
                .multiPart("files",myfile2)
                .contentType("multipart/form-date")
       .when()
                    .post("http://localhost:8080/uploadMultipleFiles")
       .then()
                        .statusCode(200)
                        .body("[0].fileName",equalTo("Demo1.txt"))
                        .body("[1].fileName",equalTo("Demo2.txt"))
                        .log().all();
    }
    @And("verify the file is downloaded or not")
    public void verify_the_file_is_downloaded_or_not() {
        given()
                .when()
                    .get("http://localhost:8080/downloadFile/Demo1.txt")
                .then()
                .statusCode(200)
                .log().all();
    }


}

