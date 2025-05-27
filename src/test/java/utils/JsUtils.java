package utils;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class JsUtils {
    public static void removeAdvertisement(){
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
