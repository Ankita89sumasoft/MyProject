package StepDefination;

import DependentClass.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.MalformedURLException;


public class Login extends BaseClass {
    @When("appium server started")
    public void appium_server_started() {
        startServer();
       // startTest();
    }
    @Given("Lunch mobile apk on android emulator")
    public void lunch_mobile_apk_on_android_emulator() throws IOException, InterruptedException {
        setUp();
        Thread.sleep(25000);
    }
    @When("Login dusmile apk")
    public void login_dusmile_apk() throws InterruptedException {
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.EditText[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[1]")).sendKeys("TMNikunj");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.widget.ScrollView/android.widget.EditText[2]")).sendKeys("Sumasoft!123");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign In\"]")).click();
        Thread.sleep(25000);

    }
}
