package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.components.Calendar;
import pages.components.Table;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class StudentFormPage {
    public static String studentPageRelativeUrl = "/automation-practice-form";

    Calendar calendar = new Calendar();

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
        firstNameInput.click();
        firstNameInput.setValue(value);
        return this;
    }

    public StudentFormPage setLastName(String value) {
        lastNameInput.click();
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
//        genderWrapper.selectRadio(gender);
        return this;
    }

    public StudentFormPage chooseSubject(String subject) {
        $(By.xpath(format("//div[text()='%s']", subject))).click();
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
        $(By.xpath(format("//label[text()='%s']", hobby))).parent().click();
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
        $(By.xpath(format("//div[text()='%s']", state))).click();
        return this;
    }

    public StudentFormPage chooseCity(String city) {
        cityInput.parent().click();
        $(By.xpath(format("//div[text()='%s']", city))).click();
        return this;
    }

    public void confirmApplication() {
        $("#submit").submit();
    }

    public void checksResultTable(String expectedStudentName, String expectedUserEmail, String expectedGender,
                                  String expectedMobile, String expectedDateOfBirth, String expectedSubjects,
                                  String expectedHobby, String expectedFileName, String expectedAdress, String expectedStateCity) {
        Table table = new Table();
        table.getValueByLabel("Student Name").shouldHave(text(expectedStudentName));
        table.getValueByLabel("Gender").shouldHave(text(expectedGender));
        table.getValueByLabel("Mobile").shouldHave(text(expectedMobile));

        if (expectedUserEmail != null) {
            table.getValueByLabel("Student Email").shouldHave(text(expectedUserEmail));
        }
        if (expectedDateOfBirth != null) {
            table.getValueByLabel("Date of Birth").shouldHave(text(expectedDateOfBirth));
        }
        if (expectedSubjects != null) {
            table.getValueByLabel("Subjects").shouldHave(text(expectedSubjects));
        }
        if (expectedHobby != null) {
            table.getValueByLabel("Hobbies").shouldHave(text(expectedHobby));
        }
        if (expectedAdress != null) {
            table.getValueByLabel("Address").shouldHave(text(expectedAdress));
        }
        if (expectedStateCity != null) {
            table.getValueByLabel("State and City").shouldHave(text(expectedStateCity));
        }
        if (expectedFileName != null) {
            table.getValueByLabel("Picture").shouldHave(text(expectedFileName));
        }
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
