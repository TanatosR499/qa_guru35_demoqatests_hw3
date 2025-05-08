package pages;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public interface Page {
    default void removeAdvertisement(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
