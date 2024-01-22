package tests;


import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;

import static steps.QoutesSteps.rollUpFirstQoute;

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
import screen.MainScreen;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.QoutesSteps;

@RunWith(AllureAndroidJUnit4.class)
public class QoutesTest {

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
    @DisplayName("#37: Развернуть и свернуть цитату (Позитивный)")
    public void expandAndRollUpQuotes() {
        Helper.needWait(5_000);
        MainSteps.waitForMainScreen();
        MainScreen.quotesButton.perform(click());
        QoutesSteps.displayedElementsOfQoutesScreen();
        QoutesSteps.expandFirstQoute();
        rollUpFirstQoute();
    }
}
