package pages.components;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    SelenideElement monthSelector = $(".react-datepicker__month-select");
    SelenideElement yearSelector = $(".react-datepicker__year-select");
    SelenideElement datePicker = $(".react-datepicker__month");

    public void selectMonth(String month) {
        monthSelector.selectOption(month);
    }

    public void selectYear(String year) {
        yearSelector.selectOption(year);
    }

    public void chooseNumber(String num) {
        List<SelenideElement> numbers = $$(byTagAndText("div", num));
        if(numbers.size() == 2){
            numbers.get(1).click();
        } else numbers.get(0).click();
    }
}
