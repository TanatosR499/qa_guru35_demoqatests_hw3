import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaTests {

    @BeforeAll
    static void setUp(){
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormSuccessTest(){
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue("Tatyana");
        $("#lastName").setValue("Chigorina");
        $("#userEmail").setValue("tatiana.thegirloftatius@gmail.com");
        $(By.xpath("//input[@value='Female']")).parent().click();
        $("#userNumber").setValue("9182773477");
        //choose date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1996");
        $(By.xpath("//div[text()='13']")).click();
        //choose Subject
        $("#subjectsInput").setValue("Ph");
        $(By.xpath("//div[text()='Physics']")).click();
        //choose Hobbies
        $(By.xpath("//label[text()='Sports']")).parent().click();
        //upload File
        $("#uploadPicture").uploadFromClasspath("garden.png");
        //choose adress
        $("#currentAddress").setValue("Пермь, ул. Сочинская д6, кв.83");
        //choose state
        $("#state").parent().click();
        $(By.xpath("//div[text()='NCR']")).click();
        $("#city").parent().click();
        $(By.xpath("//div[text()='Delhi']")).click();
        //submit
        $("#submit").submit();
        //check result table
        $(By.xpath("//td[text()='Student Name']/following-sibling::td"))
                .shouldHave(text("Tatyana" + " " + "Chigorina"));
        $(By.xpath("//td[text()='Student Email']/following-sibling::td")).shouldHave(text("tatiana.thegirloftatius@gmail.com"));
        $(By.xpath("//td[text()='Gender']/following-sibling::td")).shouldHave(text("Female"));
        $(By.xpath("//td[text()='Mobile']/following-sibling::td")).shouldHave(text("9182773477"));
        $(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).shouldHave(text("13 July,1996"));
        $(By.xpath("//td[text()='Subjects']/following-sibling::td")).shouldHave(text("Physics"));
        $(By.xpath("//td[text()='Hobbies']/following-sibling::td")).shouldHave(text("Sports"));
        $(By.xpath("//td[text()='Address']/following-sibling::td")).shouldHave(text("Пермь, ул. Сочинская д6, кв.83"));
        $(By.xpath("//td[text()='State and City']/following-sibling::td")).shouldHave(text("NCR Delhi"));
    }
}
