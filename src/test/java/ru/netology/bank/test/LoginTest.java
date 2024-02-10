package ru.netology.bank.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.bank.data.DataHelper;
import ru.netology.bank.data.SQLHelper;
import ru.netology.bank.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.bank.data.SQLHelper.cleanAuthCodes;
import static ru.netology.bank.data.SQLHelper.cleanData;

public class LoginTest {
    LoginPage loginPage;

    @AfterEach
    void clearAuthCodes() {
        cleanAuthCodes();
    }

    @AfterAll
    static void clearAll() {
        cleanData();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test

    public void shouldLogin() {

        var authInfo = DataHelper.getAuthInfoFromDataTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
