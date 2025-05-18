package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxesPage;
import utils.JsUtils;

import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends BaseTest {
    TextBoxesPage page = new TextBoxesPage();
    JsUtils jsUtils = new JsUtils();

    @Test
    void fillTextBoxesOkTest() {
        open(TextBoxesPage.relativeTextBoxPageUrl);
        jsUtils.removeAdvertisement();
        page.setUserName("Tatyana")
                .setUserEmail("tatiana.thegirloftatius@gmail.com")
                .setCurrentAddress("Пермь, ул. Сочинская д6, кв.83")
                .setPermanentAddress("Пермь, ул. Сочинская д5, кв.83")
                .sendData();
        page.checkOutput("Tatyana", "tatiana.thegirloftatius@gmail.com",
                "Пермь, ул. Сочинская д6, кв.83",
                "Пермь, ул. Сочинская д5, кв.83"
        );
    }
}
