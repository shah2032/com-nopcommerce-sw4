package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class ElectronicTest extends BaseTest {
    TopMenuPage topMenu=new TopMenuPage();
    ComputerPage computerPage=new ComputerPage();
    ShoppingCartPage shoppingCartPage=new ShoppingCartPage();
    BillingPage billingPage=new BillingPage();
    ElectronicPage electronicPage=new ElectronicPage();
    RegisterPage registerPage=new RegisterPage();
    LoginPage loginPage=new LoginPage();
    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {
        //1.1 Mouse Hover on “Electronics” Tab
        computerPage.mouseHover(electronicPage.electronicLink);

        //1.2 Mouse Hover on “Cell phones” and click
        topMenu.selectMenu("Cell phones");

        //1.3 Verify the text “Cell phones”
        Thread.sleep(1000);
        Assert.assertEquals(topMenu.getHeadingText(),"Cell phones","Navigation is not successful");
    }
    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse Hover on “Electronics” Tab
        computerPage.mouseHover(electronicPage.electronicLink);

        //2.2 Mouse Hover on “Cell phones” and click
        topMenu.selectMenu("Cell phones");

        //2.3 Verify the text “Cell phones”
        Thread.sleep(1000);
        Assert.assertEquals(topMenu.getHeadingText(),"Cell phones","Navigation is not successful");

        //2.4 Click on List View Tab
        electronicPage.selectListView();
        Thread.sleep(2000);

        //Click on product name “Nokia Lumia 1020” link
        electronicPage.selectPhone();

        //2.6 Verify the text “Nokia Lumia 1020”
        Assert.assertEquals(electronicPage.getHeading(),"Nokia Lumia 1020","navigation not successful");

        //Verify the price “$349.00”
        Assert.assertEquals(electronicPage.getPrice(),"$349.00","Price is not correct");

        //2.8 Change quantity to 2
        electronicPage.changeQuantity("2");
        Thread.sleep(1000);

        //2.9 Click on “ADD TO CART” tab
        computerPage.addToCart();
        Thread.sleep(1000);

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button.
        Assert.assertEquals(computerPage.getSuccessMessage(),"The product has been added to your shopping cart","Product is not added successfully");
        computerPage.closeMessage();

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        computerPage.mouseHover(computerPage.shoppingCartLink);
        computerPage.clickOnGoToCart();

        //2.12 Verify the message "Shopping cart"
        Assert.assertEquals(topMenu.getHeadingText(),"Shopping cart","Shopping cart not displayed successfully");
        Thread.sleep(2000);

        //2.13 Verify the quantity is 2
        Assert.assertEquals(electronicPage.getQuantity(),"2","Quantity is not correct");

        //2.14 Verify the Total $698.00
        Assert.assertEquals(shoppingCartPage.getPrice(),"$698.00","Price is not updated");

        //2.15 click on checkbox “I agree with the terms of service”
        shoppingCartPage.termsAndCondition();

        //2.16 Click on “CHECKOUT”
        shoppingCartPage.goCheckout();
        Thread.sleep(1000);

        //2.17 Verify the Text “Welcome, Please Sign In!”
        Assert.assertEquals(topMenu.getHeadingText(),"Welcome, Please Sign In!","Checkout navigation not succeed");

        //2.18 Click on “REGISTER” tab
        shoppingCartPage.clickOnRegister();

        //2.19 Verify the text “Register”
        Assert.assertEquals(topMenu.getHeadingText(),"Register","Navigation not successful");

        //2.20 Fill the mandatory fields
        registerPage.enterFirstname("has");
        registerPage.enterLastname("shah");
        String email= "random-" + UUID.randomUUID().toString() + "@example.com";
        registerPage.enterEmail(email);
        registerPage.enterPassword("abc123");
        registerPage.enterConfirmPassword("abc123");

        //Click on “REGISTER” tab
        registerPage.clickOnRegister();

        //2.22 Verify the message “Your registration completed”
        Assert.assertEquals(registerPage.getRegisterMessage(),"Your registration completed","Registration is not done");

        //2.23 Click on “CONTINUE” tab
        registerPage.clickOnContinue();

        //2.24 Verify the text “Shopping card”
        Assert.assertEquals(topMenu.getHeadingText(),"Shopping cart","Shopping cart not displayed successfully");

        //2.27 Fill the Mandatory fields
        registerPage.clickOnLoginLink();
        loginPage.enterEmailId(email);
        loginPage.enterPassword("abc123");
        loginPage.clickOnLoginButton();
        shoppingCartPage.termsAndCondition();
        shoppingCartPage.goCheckout();
        billingPage.selectCountry("United Kingdom");
        billingPage.enterCity("Liverpool");
        billingPage.enterAddress("20, London road");
        billingPage.enterPostcode("L150HR");
        billingPage.enterPhoneNumber("7896541230");

        //2.28 Click on “CONTINUE”
        billingPage.clickContinue();

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        billingPage.selectShipping2();
        Thread.sleep(1000);

        //2.30 Click on “CONTINUE”
        billingPage.clickOnContinue();

        //2.31 Select Radio Button “Credit Card”
        billingPage.clickCreditCard();
        Thread.sleep(1000);


        billingPage.clickPaymentContinue();
        billingPage.selectPaymentMethod();

        //2.32 Select “Visa” From Select credit card dropdown
        billingPage.selectCard(0);

        //2.33 Fill all the details
        billingPage.enterCardHolderName("Ram Raja");
        billingPage.enterCardNumber("5105105105105100");
        billingPage.selectExpiryMonth(1);
        billingPage.selectExpiryYear(2);
        billingPage.enterCVV("769");

        //2.34 Click on “CONTINUE”
        billingPage.clickOnPaymentContinue();

        //2.35 Verify “Payment Method” is “Credit Card”
        Assert.assertEquals(billingPage.getPaymentText(),"Payment Method: Credit Card","Payment method not right");

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        Assert.assertEquals(billingPage.getShippingText(),"Shipping Method: 2nd Day Air","Shipping method not correct");

        //2.37 Verify Total is “$698.00”
        Assert.assertEquals(billingPage.getPriceText(),"$698.00","Price not correct");

        //2.38 Click on “CONFIRM”
        billingPage.clickConfirm();
        Thread.sleep(1000);

        //2.39 Verify the Text “Thank You”
        Assert.assertEquals(billingPage.getHeadText(),"Thank you","Not confirmed");

        //2.40 Verify the message “Your order has been successfully processed!”
        Assert.assertEquals(billingPage.getSuccessText(),"Your order has been successfully processed!","Order Not done Successfully");

        //2.41 Click on “CONTINUE”
        billingPage.clickOnContinueButton();

        //2.42 Verify the text “Welcome to our store”
        Assert.assertEquals(topMenu.getHeadingText(),"Welcome to our store","Message not displayed");

        //2.43 Click on “Logout” link
        loginPage.clickOnLogout();

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/","Log out not successful");
    }
}
