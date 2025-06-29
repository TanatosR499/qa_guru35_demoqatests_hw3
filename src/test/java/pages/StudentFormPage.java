package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.TableComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StudentFormPage {
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
        genderWrapper.$(byText(gender)).click();
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

    public StudentFormPage checkTableValue(String label, String expected){
        table.checkValueEquals(label, expected);
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
}
