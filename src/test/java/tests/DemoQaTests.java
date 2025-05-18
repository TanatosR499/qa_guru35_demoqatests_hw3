package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentFormPage;
import utils.DateUtils;
import utils.JsUtils;
import utils.RandomValues;

import java.util.Date;

import static com.codeborne.selenide.Selenide.open;

public class DemoQaTests extends BaseTest {
    String firstName;
    String lastName;
    String userEmail;
    String mobile;
    String badMobile;
    String gender;
    String subject;
    String hobbies;
    String address;
    String state;
    String city;
    Date birthday;
    String dayOfBirth;
    String birthMonth;
    String birthYear;
    StudentFormPage page = new StudentFormPage();
    RandomValues randomValues = new RandomValues();
    DateUtils dateUtils = new DateUtils();
    JsUtils jsUtils = new JsUtils();

    @BeforeEach
    void initTestData() {
        firstName = randomValues.getRandomUserFirstName();
        lastName = randomValues.getRandomUserLastName();
        userEmail = randomValues.getRandomUserEmail();
        mobile = randomValues.getRandomMobileWithoutRegionCode();
        gender = randomValues.getRandomGender();
        subject = randomValues.getRandomSubject();
        hobbies = randomValues.getRandomHobby();
        address = randomValues.getRandomAddress();
        state = randomValues.getRandomState();
        city = randomValues.getRandomCity(state);
        birthday = randomValues.getRandomBirthDay(18);
        dayOfBirth = dateUtils.getDayFromDate(birthday);
        birthMonth = dateUtils.getMonthFromDate(birthday);
        birthYear = dateUtils.getYearFromDate(birthday);
        badMobile = randomValues.getRandomMobile();
    }

    @Test
    void fillFormSuccessTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        jsUtils.removeAdvertisement();
        page.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserNumber(mobile)
                .setGender(gender)
                .chooseDateOfBirth(dayOfBirth, birthMonth, birthYear)
                .chooseSubjectByLetters(subject)
                .chooseHobbies(hobbies)
                .uploadFile("garden.png")
                .setAdress(address)
                .chooseState(state)
                .chooseCity(city)
                .confirmApplication();

        //check result table
        page.checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", userEmail)
                .checkTableValue("Date of Birth", dayOfBirth + " " + birthMonth + "," + birthYear)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", mobile)
                .checkTableValue("Subjects", subject)
                .checkTableValue("Hobbies", hobbies)
                .checkTableValue("Picture", "garden.png")
                .checkTableValue("Address", address)
                .checkTableValue("State and City", state + " " + city)
        ;
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        jsUtils.removeAdvertisement();
        page.confirmApplication();
        page.checkRequiredInputsHasColor("rgb(220, 53, 69)");
        page.checkRequiredGenderLabelHasColor("rgba(220, 53, 69, 1)");
        page.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserNumber(mobile)
                .setGender(gender);
        page.checkRequiredInputsHasColor("rgb(40, 167, 69)");
        page.checkRequiredGenderLabelHasColor("rgba(40, 167, 69, 1)");
        page.confirmApplication();

        page.checkTableValue("Student Name", firstName + " " + lastName)
                .checkTableValue("Student Email", userEmail)
                .checkTableValue("Gender", gender)
                .checkTableValue("Mobile", mobile);
    }

    @Test
    void checkMobileValidationTest() {
        open(StudentFormPage.studentPageRelativeUrl);
        jsUtils.removeAdvertisement();
        page.setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setUserNumber(badMobile)
                .setGender(gender)
                .confirmApplication();
        page.checkRequiredInputHasColor(page.getUserNumberInput(), "rgb(220, 53, 69)");
    }
}
