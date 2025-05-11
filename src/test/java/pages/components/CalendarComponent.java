package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    SelenideElement monthSelector = $(".react-datepicker__month-select");
    SelenideElement yearSelector = $(".react-datepicker__year-select");

    public void selectMonth(String month) {
        monthSelector.selectOption(month);
    }

    public void selectYear(String year) {
        yearSelector.selectOption(year);
    }

    public void chooseNumber(String num) {
        $(byTagAndText("div", num)).click();
    }
}
