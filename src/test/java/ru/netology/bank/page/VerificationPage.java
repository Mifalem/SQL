package ru.netology.bank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bank.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;



public class VerificationPage {

    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement buttonVerify = $("[data-test-id=action-verify]");
    private final SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public void verificationPageVisible() {
        codeField.shouldBe(visible);
    }

    public void errorMessageVerify(String text) {
        errorNotification.shouldHave(Condition.exactText(text)).shouldBe(visible);
    }
    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
    public void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        buttonVerify.click();
    }

}
