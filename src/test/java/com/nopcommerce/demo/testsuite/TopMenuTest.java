package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.TopMenuPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopMenuTest extends BaseTest {
    TopMenuPage topMenu =new TopMenuPage();
    @Test
    public void verifyPageNavigation(){
        topMenu.selectMenu("Gift Cards");
        Assert.assertEquals(topMenu.getHeadingText(),"Gift Cards","User is not navigated successfully");
    }

}