import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JUnit5ForSoftAssertions {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void checkForJUnit5() {
        String code = "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}";

        //Откройте страницу Selenide в Github
        open("selenide/selenide");

        // Перейдите в раздел Wiki проекта
        $x("//a[@id='wiki-tab']").click();

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions (попробовала найти элемент коллекции)
        $x("//button[contains(text(),'Show 2 more pages…')]").click(); //раскрыть список страниц справа
        $$x("//li[@class='Box-row wiki-more-pages px-2 py-2']").get(1).shouldHave(text("SoftAssertions"));

        //Откройте страницу SoftAssertions
        $x("//a[contains(text(),'SoftAssertions')]").click();

        //Проверьте что внутри есть пример кода для JUnit5
        $x("//h4[contains(text(),'3. Using JUnit5 extend test class:')]").scrollTo(); //подскроллить к блоку с JUNIT5
        $x("//span[text()='ExtendWith']/..").shouldHave(text(code));
    }
}
