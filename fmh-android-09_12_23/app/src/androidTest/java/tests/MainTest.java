package tests;


import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import data.ForBefore;
import data.Helper;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import screen.AboutScreen;
import screen.AuthorizationScreen;
import screen.MainScreen;
import steps.AboutSteps;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.NewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

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
    @DisplayName("#12: Разворачивание/сворачивание блока новости")
    public void expandAndRollUpNewsBlock() {
        MainSteps.waitForMainScreen();
        MainSteps.rollUpAllNews();
        MainSteps.expandAllNews();
    }

    @Test
    @DisplayName("#13: Переход в блок Новости с главного экрана")
    public void goToNewsScreenFromMain() {
        Helper.needWait(2_000);
        MainSteps.waitForMainScreen();
        MainSteps.goToScreen("News");
        MainScreen.titleOfBlockNews.check(matches(isDisplayed()));
        NewsSteps.isNewsScreen();
        pressBack();
    }

    @Test
    @DisplayName("#14: Переход в блок О приложении")
    public void goToAboutScreenFromMenu() {
        Helper.needWait(2_000);
        MainSteps.waitForMainScreen();
        MainSteps.goToScreen("About");
        AboutSteps.displayedAllElementsOfAboutScreen();
        pressBack();
    }

    @Test
    @DisplayName("#15: Переход в блок Цитаты")
    public void goToQoutesScreen() {
        Helper.needWait(2_000);
        MainSteps.waitForMainScreen();
        MainScreen.quotesButton.perform(click());
        pressBack();
    }

    @Test
    @DisplayName("#16: Выход из приложения")
    public void logOut(){
        Helper.needWait(2_000);
        MainSteps.waitForMainScreen();
    }
}
