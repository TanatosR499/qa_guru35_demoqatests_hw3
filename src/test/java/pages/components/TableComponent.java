package pages.components;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {

    public SelenideElement getValueByLabel(String name) {
        return $(byTagAndText("td", name)).sibling(0);
    }

    public void checkValueEquals(String label, String expected) {
        getValueByLabel(label).shouldHave(text(expected));
    }
}