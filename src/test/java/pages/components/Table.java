package pages.components;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class Table {

    public SelenideElement getValueByLabel(String name) {
        return $(byTagAndText("td", name)).sibling(0);
    }
}