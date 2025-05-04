package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxesPage;

import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends BaseTest {
    TextBoxesPage page = new TextBoxesPage();

    @Test
    void fillTextBoxesOkTest(){
        open(TextBoxesPage.relativeTextBoxPageUrl);
        removeAdvertisement();
        page.setUserName("Tatyana")
                .setUserEmail("tatiana.thegirloftatius@gmail.com")
                .setCurrentAddress("Пермь, ул. Сочинская д6, кв.83")
                .setPermanentAddress("Пермь, ул. Сочинская д5, кв.83")
                .sendData();
        page.checkOutput("Tatyana","tatiana.thegirloftatius@gmail.com",
                "Пермь, ул. Сочинская д6, кв.83",
                "Пермь, ул. Сочинская д5, кв.83"
        );
    }
}
