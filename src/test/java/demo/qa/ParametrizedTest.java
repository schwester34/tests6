package demo.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTest {
     @BeforeAll
    static void beforeAll(){
         Configuration.browserSize = "1920x1080";
     }
     @BeforeEach
    void precondition(){
     open("https://demoqa.com/text-box");
     }
     @AfterEach
     void closeBrowser(){
         Selenide.closeWebDriver();
     }
    @ValueSource(strings = {"Marina", "Romanova"})
    @ParameterizedTest(name = "Заполнение формы text-box {0}")

    void parameterizedTest(String testData){

        //заполнить поля
        $("#userName").setValue(testData);
        $("#userEmail").setValue(testData + "mari@rom.eu");
        $("#currentAddress").setValue("1111222");
        $("#permanentAddress").setValue("abavfd");

        //кликнуть Submit
        $("#submit").click();
        //проверить значение в поле
        $("#output").shouldHave(text(testData))
                .shouldHave(text(testData + "mari@rom.eu"))
                .shouldHave(text("1111222"))
                .shouldHave(text("abavfd"));

    }

    @CsvSource(value = {
            "Marina/ mari@rom.eu/ Address, address Moscow/ Address, address Yekaterinburg",
            "Romanova/ rom@va.eu/ Address 1 Romanova/ Address 2 Romanova"
    },
            delimiter = '/')
    @ParameterizedTest(name = "Заполнение формы text-box {0}")

    void parameterizedCvTest(String testData, String email, String address, String curaddress){

        //заполнить поля
        $("#userName").setValue(testData);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#permanentAddress").setValue(curaddress);

        //кликнуть Submit
        $("#submit").click();
        //проверить значение в поле
        $("#output").shouldHave(text(testData))
                .shouldHave(text(email))
                .shouldHave(text(address))
                .shouldHave(text(curaddress));

    }
}
