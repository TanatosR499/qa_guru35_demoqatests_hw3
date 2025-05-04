package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;

public class Calendar {
    SelenideElement monthSelector = $(".react-datepicker__month-select");
    SelenideElement yearSelector = $(".react-datepicker__year-select");

    public void selectMonth(String month) {
        monthSelector.selectOption(month);
    }

    public void selectYear(String year) {
        yearSelector.selectOption(year);
    }

    public void chooseNumber(String num) {
        $(By.xpath(format("//div[text()='%s']", num))).click();
    }
}
