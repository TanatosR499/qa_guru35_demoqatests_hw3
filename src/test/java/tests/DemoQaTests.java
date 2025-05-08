package tests;

import org.junit.jupiter.api.Test;
import pages.StudentFormPage;

import static com.codeborne.selenide.Selenide.open;

public class DemoQaTests extends BaseTest {

    StudentFormPage page = new StudentFormPage();

    @Test
    void fillFormSuccessTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        page.removeAdvertisement();
        page.setFirstName("Tatyana")
                .setLastName("Chigorina")
                .setUserEmail("tatiana.thegirloftatius@gmail.com")
                .setUserNumber("9182773477")
                .setGender("Female")
                .chooseDateOfBirth("13", "July", "1996")
                .chooseSubjectByLetters("Phy")
                .chooseHobbies("Sports")
                .uploadFile("garden.png")
                .setAdress("Пермь, ул. Сочинская д6, кв.83")
                .chooseState("NCR")
                .chooseCity("Delhi")
                .confirmApplication();

        //check result table
        page.checkTableValue("Student Name", "Tatyana Chigorina")
                .checkTableValue("Student Email","tatiana.thegirloftatius@gmail.com")
                .checkTableValue("Gender","Female")
                .checkTableValue("Mobile","9182773477")
                .checkTableValue("Subjects","Physics")
                .checkTableValue("Hobbies","Sports")
                .checkTableValue("Picture","garden.png")
                .checkTableValue("Address","Пермь, ул. Сочинская д6, кв.83")
                .checkTableValue("State and City","NCR Delhi")
        ;
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        page.removeAdvertisement();
        page.confirmApplication();
        page.checkRequiredInputsHasColor("rgb(220, 53, 69)");
        page.checkRequiredGenderLabelHasColor("rgba(220, 53, 69, 1)");
        page.setFirstName("Tatyana")
                .setLastName("Chigorina")
                .setUserEmail("tatiana.thegirloftatius@gmail.com")
                .setUserNumber("9182773477")
                .setGender("Female");
        page.checkRequiredInputsHasColor("rgb(40, 167, 69)");
        page.checkRequiredGenderLabelHasColor("rgba(40, 167, 69, 1)");
        page.confirmApplication();

        page.checkTableValue("Student Name", "Tatyana Chigorina")
                .checkTableValue("Student Email","tatiana.thegirloftatius@gmail.com")
                .checkTableValue("Gender","Female")
                .checkTableValue("Mobile","9182773477");
    }

    @Test
    void checkMobileValidationTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        page.removeAdvertisement();
        page.setFirstName("Tatyana")
                .setLastName("Chigorina")
                .setUserEmail("tatiana.thegirloftatius@gmail.com")
                .setUserNumber("91827f3477")
                .setGender("Other")
                .confirmApplication();
        page.checkRequiredInputHasColor(page.getUserNumberInput(), "rgb(220, 53, 69)");
    }
}
