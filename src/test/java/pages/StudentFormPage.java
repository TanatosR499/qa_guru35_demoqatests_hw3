package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.components.CalendarComponent;
import pages.components.TableComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class StudentFormPage implements Page {
    public static String studentPageRelativeUrl = "/automation-practice-form";

    CalendarComponent calendar = new CalendarComponent();
    TableComponent table = new TableComponent();

    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement userEmailInput = $("#userEmail");

    public SelenideElement getUserNumberInput() {
        return userNumberInput;
    }

    SelenideElement userNumberInput = $("#userNumber");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement fileInput = $("#uploadPicture");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement stateInput = $("#state");
    SelenideElement cityInput = $("#city");
    SelenideElement genderWrapper = $("#genterWrapper");
    List<SelenideElement> subjectsItems = $$(".subjects-auto-complete__option");


    public StudentFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public StudentFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public StudentFormPage setUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public StudentFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public StudentFormPage setGender(String gender) {
        $(By.xpath(format("//input[@value='%s']", gender))).parent().click();
        return this;
    }

    public StudentFormPage chooseSubject(String subject) {
        $(byTagAndText("div", subject)).click();
        return this;
    }

    public StudentFormPage chooseSubjectByLetters(String letters) {
        subjectsInput.setValue(letters);
        if (!subjectsItems.isEmpty()) {
            subjectsItems.get(0).click();
        } else chooseSubject("Physics");
        return this;
    }

    public StudentFormPage chooseDateOfBirth(String number, String month, String year) {
        dateOfBirthInput.click();
        calendar.selectMonth(month);
        calendar.selectYear(year);
        calendar.chooseNumber(number);
        return this;
    }

    public StudentFormPage chooseHobbies(String hobby) {
        $(byTagAndText("label", hobby)).parent().click();
        return this;
    }

    public StudentFormPage uploadFile(String fileName) {
        fileInput.uploadFromClasspath(fileName);
        return this;
    }

    public StudentFormPage setAdress(String fullAdress) {
        addressInput.setValue(fullAdress);
        return this;
    }

    public StudentFormPage chooseState(String state) {
        stateInput.parent().click();
        $(byTagAndText("div", state)).click();
        return this;
    }

    public StudentFormPage chooseCity(String city) {
        cityInput.parent().click();
        $(byTagAndText("div", city)).click();
        return this;
    }

    public void confirmApplication() {
        $("#submit").submit();
    }

    public StudentFormPage checkStudentName(String expectedStudentName) {
        table.checkValueEquals("Student Name", expectedStudentName);
        return this;
    }

    public StudentFormPage checkUserEmail(String expectedUserEmail) {
        table.checkValueEquals("Student Email", expectedUserEmail);
        return this;
    }

    public StudentFormPage checkGender(String expectedGender) {
        table.checkValueEquals("Gender", expectedGender);
        return this;
    }

    public StudentFormPage checkMobile(String expectedMobile) {
        table.checkValueEquals("Mobile", expectedMobile);
        return this;
    }

    public StudentFormPage checkDateOfBirth(String expectedDateOfBirth) {
        table.checkValueEquals("Date of Birth", expectedDateOfBirth);
        return this;
    }

    public StudentFormPage checkSubjects(String expectedSubjects) {
        table.checkValueEquals("Subjects", expectedSubjects);
        return this;
    }

    public StudentFormPage checkHobbies(String expectedHobbies) {
        table.checkValueEquals("Hobbies", expectedHobbies);
        return this;
    }

    public StudentFormPage checkPicture(String expectedPicture) {
        table.checkValueEquals("Picture", expectedPicture);
        return this;
    }

    public StudentFormPage checkAddress(String expectedAddress) {
        table.checkValueEquals("Address", expectedAddress);
        return this;
    }

    public StudentFormPage checkStateCity(String expectedStateCity) {
        table.checkValueEquals("State and City", expectedStateCity);
        return this;
    }

    public void checkRequiredInputHasColor(SelenideElement input, String color) {
        input.shouldHave(cssValue("border-color", color));
    }

    public void checkRequiredInputsHasColor(String rgbColor) {
        checkRequiredInputHasColor(firstNameInput, rgbColor);
        checkRequiredInputHasColor(lastNameInput, rgbColor);
        checkRequiredInputHasColor(userNumberInput, rgbColor);
    }

    public void checkRequiredGenderLabelHasColor(String rgbColor) {
        genderWrapper.$$("label")
                .asFixedIterable()
                .stream()
                .forEach(el -> el.shouldHave(cssValue("color", rgbColor)));
    }

    @Override
    public void removeAdvertisement() {
        Page.super.removeAdvertisement();
    }
}
