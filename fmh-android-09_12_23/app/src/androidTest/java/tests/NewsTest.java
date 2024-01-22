package tests;


import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;

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
import screen.NewsScreen;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.NewsSteps;

@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

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
    @DisplayName("#17: Переход на экран Панель управления Новостей (Позитивный)")
    public void goToControlPanel() {
        Helper.needWait(5_000);
        MainSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.isControlPanelNews();
        pressBack();
    }

    @Test
    @DisplayName("#18: Сортировка новостей (Позитивный)")
    public void sortingNewsControlPanel() {
        Helper.needWait(2_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Объявление");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Объявление");
        NewsSteps.enterNewsTitle("Объявление");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        pressBack();
    }

    @Test
    @DisplayName("#19: Удаление новости (Позитивный)")
    public void deleteNews() {
        Helper.needWait(2_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Новость");
        NewsScreen.clickButtonDeleteNews();
        pressBack();
    }

    @Test
    @DisplayName("#20: Редактирование новости во вкладке Панель управления (Позитивный)")
    public void editNewsControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Объявление");
        NewsScreen.titleTextInput.perform();
        NewsScreen.clickSaveButton();
        pressBack();
    }

    @Test
    @DisplayName("#21: Смена статуса новости, находящейся в статусе АКТИВНА на статус НЕ АКТИВНА, во вкладке Панель управления (Позитивный)")
    public void statusChangeNews() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Объявление");
        NewsScreen.activeCheckBox();
        NewsScreen.notActiveCheckBox();
        NewsScreen.clickSaveButton();
        pressBack();
    }

    @Test
    @DisplayName("#22: Фильтрация новостей по критерию Активна (Позитивный)")
    public void filterNewsByCriterionActive() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Active");
        pressBack();
    }

    @Test
    @DisplayName("#23: Фильтрация новостей по критерию Не активна (Позитивный)")
    public void filterNewsByCriterionNotActive() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.findDisplayedNews("Not active");
        pressBack();
    }

    @Test
    @DisplayName("#24: Создание новости во вкладке Панель управления (Позитивный)")
    public void addNewsFromControlPanel () {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("12:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Новостная сводка");
        pressBack();
    }

    @Test
    @DisplayName("#25: Поле Категория пустое, при создании новости, во вкладке Панель управления (Негативный)")
    public void fieldCategoryEmptyCreationNewsInControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("15:00");
        NewsSteps.enterNewsDescription("Обсуждение");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Собрание");
        pressBack();
    }

    @Test
    @DisplayName("#26: Поле Заголовок пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldTitleEmptyCreationNewsInControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.enterNewsTitle("");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("16:00");
        NewsSteps.enterNewsDescription("Обсуждение");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
        pressBack();
    }

    @Test
    @DisplayName("#27: Поле Дата публикации пустое, при создании новости во вкладке Панель управления (Негативный)")
    public void fieldDateEmptyCreationNewsInControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
        pressBack();
    }

    @Test
    @DisplayName("#28: Поле Время пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldTimeEmptyCreationNewsInControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
        pressBack();
    }

    @Test
    @DisplayName("#29: Поле Описание пустое при создании новости во вкладке Панель управления (Негативный)")
    public void fieldDescriptionEmptyCreationNewsInControlPanel() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("");
        pressBack();
    }

    @Test
    @DisplayName("#30: Ввод в поле Категория собственного названия категории при создании новости во вкладке Панель управления (Негативный)")
    public void customCategoryName() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("Приглашение на обед");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("13:30");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("Приглашение на обед");
        pressBack();
    }

    @Test
    @DisplayName("#31: Поле Категория состоит из цифр при создании новости во вкладке Панель управления (Негативный)")
    public void fieldCategoryConsistsOfNumbers() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("1234567890");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("1234567890");
        pressBack();
    }

    @Test
    @DisplayName("#32: Поле Категория состоит из спецсимволов при создании новости во вкладке Панель управления (Негативный)")
    public void fieldCategoryConsistsOfSpecialCharacters() {
        Helper.needWait(7_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsScreen.categoryOfNews("@#$^&**");
        NewsSteps.enterNewsTitle("Собрание");
        NewsSteps.enterDateOfNewsPublication("22.01.2024");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("@#$^&**");
        pressBack();
    }

    @Test
    @DisplayName("#33: Поле Дата публикации состоит из даты будущего года при создании новости во вкладке Панель управления (Позитивный)")
    public void fieldDateConsistsOfNextYearCreatingNews() {
        Helper.needWait(7_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsSteps.clickAddNewsFromControlPanel();
        NewsSteps.chooseCategory();
        NewsSteps.enterNewsTitle("Новостная сводка");
        NewsSteps.enterDateOfNewsPublication("22.01.2025");
        NewsSteps.enterTimeOfNewsPublication("17:00");
        NewsSteps.enterNewsDescription("Все новости на сегодня");
        NewsScreen.saveButton.perform(click());
        NewsSteps.findDisplayedNews("22.01.2025");
        pressBack();
    }

    @Test
    @DisplayName("#34: Просмотр новостей во вкладке Новости (Позитивный)")
    public void viewingNews() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.editNewsItemButton();
        pressBack();
    }

    @Test
    @DisplayName("#35: Фильтрация новостей без указания категории во вкладке Новости (Позитивный)")
    public void filteringNewsNoCategoryPositive() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.filterNewsButton();
        pressBack();
    }


    @Test
    @DisplayName("#36: Фильтрация новостей, без указания категории, в определенный период времени (Позитивный)")
    public void filteringNewsCertainPeriodTime() {
        Helper.needWait(5_000);
        MainSteps.clickAllNewsButton();
        NewsSteps.openControlPanelOfNews();
        NewsScreen.dateStartPeriod("15.01.2024");
        NewsScreen.dateEndPeriod("22.01.2024");
        pressBack();
    }
}
