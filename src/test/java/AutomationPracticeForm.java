import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com/automation-practice-form";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {
        String tablesValueXpath = "//td[text()='%s']/../td[text()='%s']";
        String firstName = "Alexandra";
        String secondName = "M";
        String email = "alex123@abc.com";
        String gender = "Female";
        String mobile = "1234567890";
        String day = "27";
        String month = "August";
        String year = "1989";
        String subject1 = "Computer Science";
        String subject2 = "Civics";
        String hobbies1 = "Reading";
        String hobbies2 = "Music";
        String picture = "10.jpg";
        String address = "Russia, Saint Petersburg, Admiralteyskaya Street 15";
        String state = "Uttar Pradesh";
        String city = "Merrut";

        open("");
        $x("//input[@id='firstName']").setValue(firstName);
        $x("//input[@id='lastName']").setValue(secondName);
        $x("//*[@id='userEmail']").setValue(email);
        $x("//label[@for='gender-radio-2']").click();
        $x("//*[@id='userNumber']").setValue(mobile);
        $x("//*[@id='dateOfBirthInput']").click();
        $x("//option[text()='" + month + "']").click();
        $x("//option[@value='" + year + "']").click();
        $x("//*[@aria-label='Choose Sunday, August " + day + "th, 1989']").click();
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $x("//*[@id='subjectsInput']").click();
        $x("//*[@id='subjectsInput']").setValue("M");
        $x("//*[@id='react-select-2-option-2']").click();
        $x("//*[@id='subjectsInput']").setValue("C");
        $x("//*[@id='react-select-2-option-7']").click();
        $x("//label[@for='hobbies-checkbox-2']").click();
        $x("//label[@for='hobbies-checkbox-3']").click();
        $x("//input[@id='uploadPicture']").uploadFromClasspath(picture);
        $x("//textarea[@id='currentAddress']").setValue(address);
        $x("//*[@id='state']").click();
        $x("//*[text()='" + state + "']").click();
        $x("//div[@class=' css-1wa3eu0-placeholder']").click();
        $x("//*[text()='" + city + "']").click();
        $x("//button[@id='submit']").click();
        // check
        $x("//*[contains(text(),'Thanks for submitting the form')]").shouldHave(text("Thanks for submitting the form"));
        $x(String.format(tablesValueXpath, "Student Name", firstName + " " + secondName)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Student Email", email)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Gender", gender)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Mobile", mobile)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Date of Birth", day + " " + month + "," + year)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Subjects", subject1 + ", " + subject2)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Hobbies", hobbies1 + ", " + hobbies2)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Picture", picture)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "Address", address)).shouldBe(visible);
        $x(String.format(tablesValueXpath, "State and City", state + " " + city)).shouldBe(visible);
    }
}
