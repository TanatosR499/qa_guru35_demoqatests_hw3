package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BaseTest {
    @BeforeAll
    static void setUp(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

     public void removeAdvertisement(){
         executeJavaScript("$('#fixedban').remove()");
         executeJavaScript("$('footer').remove()");
    }
}
