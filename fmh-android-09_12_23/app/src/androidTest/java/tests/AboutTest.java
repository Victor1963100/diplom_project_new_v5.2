package tests;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.ForBefore;

import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static steps.MainSteps.goToScreen;
import static steps.MainSteps.waitForMainScreen;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import screen.AboutScreen;
import steps.AboutSteps;
import steps.AuthorizationSteps;

@RunWith(AllureAndroidJUnit4.class)
public class AboutTest {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void authorization() {
        ForBefore.validUser();
    }

    @After
    public void tearDown() throws Exception {
        AuthorizationSteps.logOut();
    }

    @Test
    @DisplayName("#38: Отображение элементов экрана О приложении (Позитивный)")
    public void showAllElementsAndGoBackOfAboutScreen() {
        waitForMainScreen();
        goToScreen("About");
        AboutSteps.displayedAllElementsOfAboutScreen();
        AboutSteps.backToPreviousFromAboutScreen();
        pressBack();

    }

    @Test
    @DisplayName("#39: Работа ссылки Политика конфиденциальности (Позитивный)")
    public void transitionPrivacyPolicy() {
        waitForMainScreen();
        goToScreen("About");
        AboutScreen.privacyPolicy.check(matches(isDisplayed()));
        pressBack();
    }

    @Test
    @DisplayName("#40: Работа ссылки Пользовательское соглашение (Позитивный)")
    public void transitionTermsOfUse() {
        waitForMainScreen();
        goToScreen("About");
        AboutScreen.termsOfUse.check(matches(isDisplayed()));
        pressBack();
    }
}


