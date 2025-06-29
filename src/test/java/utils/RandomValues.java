package utils;

import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;

public class RandomValues {

    Faker ruFaker = new Faker(new Locale("ru"));
    Faker engFaker = new Faker(new Locale("en-GB"));

    public String getRandomUserFirstName() {
        return ruFaker.name().firstName();
    }

    public String getRandomUserLastName() {
        return ruFaker.name().lastName();
    }

    public String getRandomUserEmail() {
        return engFaker.internet().emailAddress();
    }

    public String getRandomGender() {
        return ruFaker.options().option("Male", "Female", "Other");
    }

    public String getRandomMobile() {
        return ruFaker.phoneNumber().phoneNumber();
    }

    public String getRandomMobileWithoutRegionCode() {
        return getRandomMobile().replaceAll("[^0-9]", "").substring(1);
    }

    public String getRandomAddress() {
        return ruFaker.address().fullAddress().replaceAll("#", "");
    }

    public String getRandomHobby() {
        return ruFaker.options().option("Sports", "Reading", "Music");
    }

    public String getRandomState() {
        return ruFaker.options().option(
                "NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomSubject() {
        return ruFaker.options().option(
                "Chemistry", "Maths", "Physics", "Arts", "English",
                "Biology", "History", "Economics", "Computer Science");
    }

    public String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> ruFaker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> ruFaker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> ruFaker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> ruFaker.options().option("Jaipur", "Jaiselmer");
            default -> getRandomCity(getRandomState());
        };
    }

    public Date getRandomBirthDay(int minAge) {
        return ruFaker.date().birthday(minAge, 80);
    }
}
