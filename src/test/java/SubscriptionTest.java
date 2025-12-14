import com.google.inject.Inject;
import extensions.junit.UIExtensions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.SubscriptionPage;

import java.util.Arrays;
import java.util.List;

@ExtendWith(UIExtensions.class)
public class SubscriptionTest {
  List<String> subscriptionOptions = Arrays.asList("Basic\uD83D\uDD25 new", "Standard", "Professional");
  String paymentSumm = "500";

  String login = "login";
  String pass = "pass";

  @Inject
  SubscriptionPage subscriptionPage;

  @DisplayName("Сценарий 4")
  @Test
  public void chechSubscriptionPageTest2() {

    subscriptionPage.open();
    subscriptionPage.clickOpenDetailsButton();
    subscriptionPage.checkSubscriptionOptionsIsExist(subscriptionOptions);
    subscriptionPage.clickOpenMoreDetailsButton2();
    subscriptionPage.checkAdditionalParamsIsPresent();
    subscriptionPage.clickOpenMoreDetailsButton2();
    subscriptionPage.checkAdditionalParamsIsNotPresent();
    subscriptionPage.clickButtonOnSubscriptionSelectionSage();
    subscriptionPage.clickEnterToLogin();
    subscriptionPage.logAndPass(login, pass);
    subscriptionPage.checkSummOnPaymentPageIsPresent(paymentSumm);
    subscriptionPage.choseAnotherTypeSubs();
    subscriptionPage.checkSummIsChanged(paymentSumm);
    subscriptionPage.checkSummOnPaymentPageIsPresent(paymentSumm);
    subscriptionPage.choseAnotherTypeSubs();
    subscriptionPage.checkSummIsChanged(paymentSumm);
  }
}