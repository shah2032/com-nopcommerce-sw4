package com.nopcommerce.demo.pages;

import com.nopcommerce.demo.utility.Utility;
import org.openqa.selenium.By;

public class RegisterPage extends Utility {
    By maleRadio=By.xpath("//input[@id='gender-male']");
    By firstNameLink=By.xpath("//input[@id='FirstName']");
    By lastNameLink=By.xpath("//input[@id='LastName']");
    By emailLink=By.xpath("//input[@id='Email']");
    By passwordLink=By.xpath("//input[@id='Password']");
    By confirmPasswordLink=By.xpath("//input[@id='ConfirmPassword']");
    By registerButton=By.xpath("//button[@id='register-button']");
    By registerMessage=By.xpath("//div[contains(text(),'Your registration completed')]");
    By continueLink=By.xpath("//a[contains(text(),'Continue')]");
    By loginLink = By.linkText("Log in");
    public void selectGender(){
        clickOnElement(maleRadio);
    }
    public void enterFirstname(String name){
        sendTextToElement(firstNameLink,name);
    }
    public void enterLastname(String name){
        sendTextToElement(lastNameLink,name);
    }
    public void enterEmail(String email){
        sendTextToElement(emailLink,email);
    }
    public void enterPassword(String password){
        sendTextToElement(passwordLink,password);
    }
    public void enterConfirmPassword(String password){
        sendTextToElement(confirmPasswordLink,password);
    }
    public void clickOnRegister(){
        clickOnElement(registerButton);
    }
    public String getRegisterMessage(){
        return getTextFromElement(registerMessage);
    }
    public void clickOnContinue(){
        clickOnElement(continueLink);
    }
    public void clickOnLoginLink(){
        clickOnElement(loginLink);
    }
}



