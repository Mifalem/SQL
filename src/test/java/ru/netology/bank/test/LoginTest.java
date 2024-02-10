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
    void tearDown() {
        cleanAuthCodes();
    }

    @AfterAll
    static void tearDownAll() {
        cleanData();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @Test
    void shouldLogin() {
        var authInfo = DataHelper.getAuthInfoFromDataTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
    @Test
    void shouldBeErrorMessageIfUserDoesNotExist() {
        var authInfo = DataHelper.generateRandomUser();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.errorMessageVerify("Ошибка! \nНеверно указан логин или пароль");
    }
    @Test
    void shouldBeErrorMessageIfExistAndInvalidVerificationCode() {
        var authInfo = DataHelper.getAuthInfoFromDataTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verificationPageVisible();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.errorMessageVerify("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }
}
