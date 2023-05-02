package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.BillingPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.ShoppingCartPage;
import com.nopcommerce.demo.pages.TopMenuPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ComputerTest extends BaseTest {
    TopMenuPage topMenu=new TopMenuPage();
    ComputerPage computerPage=new ComputerPage();
    ShoppingCartPage shoppingCartPage=new ShoppingCartPage();
    BillingPage billingPage=new BillingPage();
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {

        //2.1 Click on Computer Menu.
        computerPage.mouseHover(computerPage.computerLink);

        //2.2 Click on Desktop
        topMenu.selectMenu("Desktops");
        Thread.sleep(1000);

        //2.3 1.3 Select Sort By position "Name: Z to A"
        List<String> expectedList=computerPage.getList();
        expectedList.sort(Comparator.reverseOrder());
        computerPage.selectFromDropDown("Name: Z to A");
        Thread.sleep(1000);
        List<String>actualList=computerPage.getList();
        Assert.assertEquals(expectedList,actualList,"Products not displayed Z to A order");

    }
    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //2.1 Click on Computer Menu.
        computerPage.mouseHover(computerPage.computerLink);

        //2.2 Click on Desktop
        topMenu.selectMenu("Desktops");

        //2.3 Select Sort By position "Name: A to Z"
        computerPage.selectFromDropDown("Name: A to Z");
        Thread.sleep(1000);

        //2.4 Click on "Add To Cart"
        computerPage.addToCart();
        Thread.sleep(1000);

        //2.5 Verify the Text "Build your own computer"
        Assert.assertEquals("Build your own computer",topMenu.getHeadingText(),"Not right product");

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        computerPage.selectProcessor("1");

        //2.7.Select "8GB [+$60.00]" using Select class.
        computerPage.selectRAM("8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        computerPage.selectHddRadio();

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        computerPage.selectOs();

        // 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander[+$5.00]"
        computerPage.selectCheckBox();
        Thread.sleep(2000);


        //2.11 Verify the price "$1,475.00"
        waitUntilVisibilityOfElementLocated(By.xpath("//span[@id='price-value-1']"), 5);
        Assert.assertEquals(computerPage.getPrice(),"$1,475.00","Options not selected correctly");
        Thread.sleep(1000);

        //2.12 Click on "ADD TO CARD" Button.
        computerPage.addToCart();
        Thread.sleep(1000);

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button.
        Assert.assertEquals(computerPage.getSuccessMessage(),"The product has been added to your shopping cart","Product is not added successfully");
        computerPage.closeMessage();

        //2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        computerPage.mouseHover(computerPage.shoppingCartLink);
        computerPage.clickOnGoToCart();

        //2.15 Verify the message "Shopping cart"
        Assert.assertEquals(topMenu.getHeadingText(),"Shopping cart","Shopping cart not displayed successfully");

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        shoppingCartPage.changeQuantity("2");
        shoppingCartPage.updateCart();
        Thread.sleep(2000);

        //2.17 Verify the Total"$2,950.00"
        Assert.assertEquals(shoppingCartPage.getPrice(),"$2,950.00","Cart not updated");

        //2.18 click on checkbox “I agree with the terms of service”
        shoppingCartPage.termsAndCondition();

        //2.19 Click on “CHECKOUT”
        shoppingCartPage.goCheckout();
        Thread.sleep(1000);

        //2.20 Verify the Text “Welcome, Please Sign In!”
        Assert.assertEquals(topMenu.getHeadingText(),"Welcome, Please Sign In!","Checkout navigation not succeed");

        //2.21Click on “CHECKOUT AS GUEST” Tab
        shoppingCartPage.clickCheckoutAsGuest();

        //2.22 Fill the all mandatory field
        billingPage.enterFirstname("has");
        billingPage.enterLastname("Shah");
        billingPage.enterEmail("hasabcd@gmail.com");
        billingPage.selectCountry("United Kingdom");
        billingPage.enterCity("Liverpool");
        billingPage.enterAddress("23, Liverpool road");
        billingPage.enterPostcode("L150HR");
        billingPage.enterPhoneNumber("7896541560");

        //2.23 Click on “CONTINUE”
        billingPage.clickContinue();

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        billingPage.selectShipping();
        Thread.sleep(1000);

        //2.25 Click on “CONTINUE”
        billingPage.clickOnContinue();

        //2.26 Select Radio Button “Credit Card”
        billingPage.clickCreditCard();
        Thread.sleep(1000);
        billingPage.clickPaymentContinue();

        //2.27 Select “Master card” From Select credit card dropdown
        billingPage.selectPaymentMethod();
        billingPage.selectCard(1);

        //2.28 Fill all the details
        billingPage.enterCardHolderName("John Mario");
        billingPage.enterCardNumber("5105105105105100");
        billingPage.selectExpiryMonth(1);
        billingPage.selectExpiryYear(2);
        billingPage.enterCVV("769");

        //2.29 Click on “CONTINUE”
        billingPage.clickOnPaymentContinue();

        //2.30 Verify “Payment Method” is “Credit Card”
        Assert.assertEquals(billingPage.getPaymentText(),"Payment Method: Credit Card","Payment method not right");

        //2.32 Verify “Shipping Method” is “Next Day Air”
        Assert.assertEquals(billingPage.getShippingText(),"Shipping Method: Next Day Air","Shipping method not correct");

        //2.33 Verify Total is “$2,950.00”
        Assert.assertEquals(billingPage.getPriceText(),"$2,950.00","Price not correct");

        //2.34 Click on “CONFIRM”
        billingPage.clickConfirm();
        Thread.sleep(1000);

        //2.35 Verify the Text “Thank You”
        Assert.assertEquals(billingPage.getHeadText(),"Thank you","Not confirmed");

        //2.36 Verify the message “Your order has been successfully processed!”
        Assert.assertEquals(billingPage.getSuccessText(),"Your order has been successfully processed!","Order Not done Successfully");

        //2.37 Click on “CONTINUE”
        billingPage.clickOnContinueButton();

        //2.37 Verify the text “Welcome to our store”
        Assert.assertEquals(topMenu.getHeadingText(),"Welcome to our store","Message not displayed");
    }

}





