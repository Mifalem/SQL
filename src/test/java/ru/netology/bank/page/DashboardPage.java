package ru.netology.bank.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    public DashboardPage() {
        SelenideElement header = $("[data-test-id=dashboard]");
        header.shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }
}
