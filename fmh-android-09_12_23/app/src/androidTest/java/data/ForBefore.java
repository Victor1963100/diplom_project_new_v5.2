package data;

import androidx.test.espresso.NoMatchingViewException;

import steps.AuthorizationSteps;

public class ForBefore {
    public static void validUser() {
        Helper.needWait(6_000); {
            try {
                AuthorizationSteps.isAuthorizationScreen();
            } catch (NoMatchingViewException e) {
                return;
            }
            AuthorizationSteps.clickLoginField("login2");
            AuthorizationSteps.clickPasswordField("password2");
            AuthorizationSteps.clickSignIn();
        }
    }
}
