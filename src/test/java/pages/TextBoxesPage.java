package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxesPage {
    public static String relativeTextBoxPageUrl = "/text-box";

    SelenideElement userName = $("#userName");
    SelenideElement userEmail = $("#userEmail");
    SelenideElement currentAddress = $("#currentAddress");
    SelenideElement permanentAddress = $("#permanentAddress");
    SelenideElement output = $("#output");
    SelenideElement outputName = output.$("#name");
    SelenideElement outputEmail = output.$("#email");
    SelenideElement outputCurrentAddress = output.$("#currentAddress");
    SelenideElement outputPermanentAddress = output.$("#permanentAddress");

    public TextBoxesPage setUserName(String value) {
        userName.setValue(value);
        return this;
    }

    public TextBoxesPage setUserEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public TextBoxesPage setCurrentAddress(String value) {
        currentAddress.setValue(value);
        return this;
    }

    public TextBoxesPage setPermanentAddress(String value) {
        permanentAddress.setValue(value);
        return this;
    }

    public void sendData() {
        $("#submit").click();
    }

    public void checkOutput(String expectedName, String expectedEmail,
                            String expectedCurrentAddress, String expectedPermanentAddress) {
        outputName.shouldHave(text(expectedName));
        outputEmail.shouldHave(text(expectedEmail));
        outputCurrentAddress.shouldHave(text(expectedCurrentAddress));
        outputPermanentAddress.shouldHave(text(expectedPermanentAddress));
    }
}
