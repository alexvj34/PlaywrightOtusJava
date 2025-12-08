package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Path("/subscription")
public class SubscriptionPage extends AbsBasePage {

  public SubscriptionPage(Page page) {
    super(page);
  }

  Locator summField = page.locator("//section[2]//span//text()[1]");

  @Step("В блоке «Варианты подписок» проверить, что отображаются варианты подписки")
  public void checkSubscriptionOptionsIsExist(List<String> expectedOptionsList) {
    Locator locator = page.locator("//*[@id='packages']//h4");
    List<String> allSubscriptions = locator.allTextContents();
    assertEquals(allSubscriptions, expectedOptionsList);
  }

  @Step("Кликнуть на ссылку «Подробнее»")
  public void clickOpenMoreDetailsButton(String name) {
    page.locator(String.format("//h4[text()='%s']/../..//button[text()='Подробнее']", name)).click();
  }

  @Step("Кликнуть на ссылку «Варианты подписки»")
  public void clickOpenDetailsButton() {
    page.locator("//a[text()='варианты подписки']").click();
  }

  @Step("Кликнуть на ссылку «Подробнее»")
  public void clickOpenMoreDetailsButton2() {
    page.locator("(//button[text()='Подробнее'])[1]").click();
  }

  @Step("Проверить, что описание подписки развернулось")
  public void checkAdditionalParamsIsPresent() {
    //assertThat(page.locator("section#packages").getByText("Можно продлить подписку на 3, 6 или 12 месяцев в личном кабинете").first()).isInViewport();
    page.locator("(//div[text()='Можно продлить подписку на 3, 6 или 12 месяцев в личном кабинете'])[1]").isVisible();
  }

  @Step("Клин по кнопке свернуть")
  public void clickCloseMoreDetailsButton(String name) {
    page.locator(String.format("//h4[text()='%s']/../..//button[text()='Свернуть']", name)).click();
  }

  @Step("Проверить, что описание подписки свернулось")
  public void checkAdditionalParamsIsNotPresent() {
    //assertThat(page.locator("section#packages").getByText("Можно продлить подписку на 3, 6 или 12 месяцев в личном кабинете").first()).not().isInViewport();
    page.locator("(//div[text()='Можно продлить подписку на 3, 6 или 12 месяцев в личном кабинете'])[1]").isHidden();
  }

  public void clickButtonOnSubscriptionSelectionSage() {
    page.locator(String.format("//h4[text()='Standard']/../..//button[text()='Купить']")).click();
  }

  @Step("Проверка отображения суммы на страниц оплаты")
  public void checkSummOnPaymentPageIsPresent(String summ) {
    assertThat(page.getByText(summ)).isVisible();
  }

  public void choseAnotherTypeSubs() {
    page.locator("//*[@id=\"radio-base-trial\"]").click();  //тут name дбвляется в url
  }

  @Step("Проверка суммы")
  public void checkSummIsChanged(String summ) {
    assertThat(page.getByText(summ)).not().isVisible();
  }

  @Step("Нажать на странице логина/регистрации 'Вход'")
  public void clickEnterToLogin() {
    page.getByText("Вход").click();
  }

  @Step("Ввод логина пароля на странице входа")
  public void logAndPass(String login, String pass) {
    page.locator("//input[@name='email']").fill(login);
    page.locator("//input[@type='password']").fill(pass);
    page.locator("//button//div[text()='Войти']").click();
  }
}