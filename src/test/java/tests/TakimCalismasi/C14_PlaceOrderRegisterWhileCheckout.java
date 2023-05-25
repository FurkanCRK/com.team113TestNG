package tests.TakimCalismasi;

import com.github.javafaker.Faker;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AutomationPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.security.Key;

public class C14_PlaceOrderRegisterWhileCheckout {

    @Test
    public void test01(){
        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("autoUrl"));
        AutomationPage automationPage = new AutomationPage();
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();

        // 3. Verify that home page is visible successfully
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(automationPage.autoHomePage.isDisplayed(),"anasayfa goruntulenmedi");

        // 4. Add products to cart
        jse.executeScript("arguments[0].scrollIntoView();",automationPage.addCart);
        Actions actions = new Actions(Driver.getDriver());
        automationPage.addCart.click();
        automationPage.conshoping.click();

        // 5. Click 'Cart' button
        automationPage.cartButton.click();

        // 6. Verify that cart page is displayed
        softAssert.assertTrue(automationPage.shoppingPage.isDisplayed(),"cart page gorunmedi");

        // 7. Click Proceed To Checkout
        automationPage.proceedToCheckout.click();

        // 8. Click 'Register / Login' button
        automationPage.registerLogin.click();

        // 9. Fill all details in Signup and create account
        Faker faker = new Faker();

        actions.click(automationPage.name)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().emailAddress())
                .sendKeys(Keys.TAB).sendKeys(Keys.ENTER).perform();

        actions.click(automationPage.radioButton)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB)
                .sendKeys("15")
                .sendKeys(Keys.TAB)
                .sendKeys("Mar")
                .sendKeys(Keys.TAB)
                .sendKeys("1993")
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.SPACE)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.SPACE)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().fullAddress())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys("Canada")
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().state())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().cityName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER).perform();

        // 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
        softAssert.assertTrue(automationPage.accountDisplayed.isDisplayed(),"account yazisi ayni degil");
        automationPage.continButton.click();

        // 11. Verify ' Logged in as username' at top
        softAssert.assertTrue(automationPage.oturumAcildiGoruntulendi.isDisplayed(),"Oturum acildi goruntulendi");

        // 12. Click 'Cart' button
        automationPage.cartButton.click();

        // 13. Click 'Proceed To Checkout' button
        automationPage.proceedToCheckout.click();

        // 14. Verify Address Details and Review Your Order
        softAssert.assertTrue(automationPage.adresDetay.isDisplayed(),"adres detaylari goruntulendi");
        softAssert.assertTrue(automationPage.siparisInceleme.isDisplayed(),"siparis inceleme yanlis");

        // 15. Enter description in comment text area and click 'Place Order'
        automationPage.aciklaBolumu.sendKeys("Hizli gönderirseniz sevinirim");
        automationPage.PlaceOrder.click();

        // 16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
        automationPage.cartIsmi.sendKeys(faker.name().fullName());
        automationPage.cartNumara.sendKeys(faker.business().creditCardNumber());
        automationPage.cvc.sendKeys("123");
        automationPage.cartAy.sendKeys("10");
        automationPage.cartYil.sendKeys("2025");

        // 17. Click 'Pay and Confirm Order' button
        automationPage.payButon.click();

        // 18. Verify success message 'Your order has been placed successfully!'
        Driver.getDriver().navigate().back();

        String expectedIcerik = "Your order has been placed successfully";
        String actualIcerik = automationPage.basariMesaji.getText();
        System.out.println(actualIcerik);

        softAssert.assertEquals(actualIcerik,expectedIcerik,"siparis yazisi ayni degil");

        Driver.getDriver().navigate().forward();

        // 19. Click 'Delete Account' button
        automationPage.DeleteAccountButon.click();

        // 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button

        expectedIcerik = "ACCOUNT DELETED";
        actualIcerik = automationPage.accountDeletedYazisi.getText();

        softAssert.assertEquals(actualIcerik,expectedIcerik,"acoount yazisi ayni degil");

        automationPage.devamButonu.click();

        softAssert.assertAll();

        Driver.closeDriver();
    }
}
