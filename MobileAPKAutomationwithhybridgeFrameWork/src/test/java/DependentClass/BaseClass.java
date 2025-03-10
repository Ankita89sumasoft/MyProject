package DependentClass;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseClass
{
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    public static AndroidDriver driver=null;
    AppiumDriverLocalService service;
    String nodejspath="C:\\Program Files\\nodejs\\node.exe";
    String appiumserverpath="C:\\Users\\pooja.sapkal\\AppData\\Local\\Programs\\Appium Server GUI\\resources\\app\\node_modules\\appium\\build\\lib\\main.js";
    public void startServer()
    {
        service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(nodejspath)).withAppiumJS(new File(appiumserverpath))
                .withIPAddress("127.0.0.1").withArgument(GeneralServerFlag.BASEPATH,"/wd/hub").usingPort(4723));

        System.out.println("Service get stared............");
        service.start();
    }
    public void setUp() throws IOException, InterruptedException {
        try
        {
            String command="emulator -avd emulatorName";
            Runtime.getRuntime().exec(command);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        /*String andriodpath="C:\\Users\\pooja.sapkal\\AppData\\Local\\Android\\Sdk\\emulator";
        String avdname="Pixel_XL_API_30_latest";

        String command=andriodpath +  " -avd " + avdname;

        try
        {
            Runtime.getRuntime().exec(command);
            System.out.println("Emulator started successfully....");
        }
        catch (Exception exception)
        {
            System.out.println("Exception is:="+exception);
        }*/

        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:Android", 11);
        desiredCapabilities.setCapability("appium:deviceName", "Android");
        desiredCapabilities.setCapability("appium:app", "D:\\ALLAPKs\\app.apk");
        desiredCapabilities.setCapability("appium:fullReset", false);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
        desiredCapabilities.setCapability("appium:autoGrantPermissions", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

       driver = new AndroidDriver(remoteUrl, desiredCapabilities);
       Thread.sleep(1000);


    }
    public void sampleTest() {
    }
    public void tearDown() {
        driver.quit();
    }

}
