import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.net.URL;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaTests {
    public static String formURL = "https://demoqa.com/automation-practice-form";

    @BeforeAll
    static void setUp(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
    }

    @Test
    void fillFormSuccess(){
        Map<String,String> testData = Map.of("firstName","Tatyana", "lastName","Chigorina",
                "userEmail","tatiana.thegirloftatius@gmail.com",
                "Mobile","9182773477","address","Пермь, ул. Сочинская д6, кв.83");
        open(formURL);
        $("#firstName").setValue(testData.get("firstName"));
        $("#lastName").setValue(testData.get("lastName"));
        $("#userEmail").setValue(testData.get("userEmail"));
        $(By.xpath("//input[@value='Female']")).parent().click();
        $("#userNumber").setValue(testData.get("Mobile"));
        //choose date of birth
        for (int i = 0; i < 10; i++) {
            $("#dateOfBirthInput").sendKeys(Keys.BACK_SPACE);
        }
        $("#dateOfBirthInput").type("3 Jul 1996");
        //choose Subject
        $("#subjectsInput").setValue("Ph");
        $(By.xpath("//div[text()='Physics']")).click();
        //choose Hobbies
        $(By.xpath("//label[text()='Sports']")).parent().click();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("garden.png");
        assert resource != null;
        File file = new File(resource.getFile());
        $("#uploadPicture").uploadFile(file);
        //choose adress
        $("#currentAddress").setValue(testData.get("address"));
        //choose state
        $("#state").parent().click();
        $(By.xpath("//div[text()='NCR']")).click();
        $("#city").parent().click();
        $(By.xpath("//div[text()='Delhi']")).click();
        //submit
        $("#submit").submit();
        //check result table
        $(By.xpath("//td[text()='Student Name']/following-sibling::td"))
                .shouldHave(text(testData.get("firstName") + " " + testData.get("lastName")));
        $(By.xpath("//td[text()='Student Email']/following-sibling::td")).shouldHave(text(testData.get("userEmail")));
        $(By.xpath("//td[text()='Gender']/following-sibling::td")).shouldHave(text("Female"));
        $(By.xpath("//td[text()='Mobile']/following-sibling::td")).shouldHave(text(testData.get("Mobile")));
        $(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).shouldHave(text("13 July,1996"));
        $(By.xpath("//td[text()='Subjects']/following-sibling::td")).shouldHave(text("Physics"));
        $(By.xpath("//td[text()='Hobbies']/following-sibling::td")).shouldHave(text("Sports"));
        $(By.xpath("//td[text()='Address']/following-sibling::td")).shouldHave(text(testData.get("address")));
        $(By.xpath("//td[text()='State and City']/following-sibling::td")).shouldHave(text("NCR Delhi"));
    }
}
