package com.olga;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created by User on 29.04.2018.
 * [x] Задание 11. Сделайте сценарий регистрации пользователя
 Сделайте сценарий для регистрации нового пользователя в учебном приложении litecart (не в админке, а в клиентской части магазина).

 Сценарий должен состоять из следующих частей:

 1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
 2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
 3) повторный вход в только что созданную учётную запись,
 4) и ещё раз выход.

 В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.

 Можно оформить сценарий либо как тест, либо как отдельный исполняемый файл.

 Проверки можно никакие не делать, только действия -- заполнение полей, нажатия на кнопки и ссылки. Если сценарий дошёл до конца, то есть созданный пользователь смог выполнить вход и выход -- значит создание прошло успешно.

 В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.
 */
public class UserRegistrationTest extends TestBasis{

    @Test
    public void newUserRegistration() {
        driver.get("http://localhost/litecart/en/");

        List<WebElement> regInLoginForm = driver.findElements(By.cssSelector("#box-account-login a"));
       if (!(regInLoginForm.size()==1) || !(regInLoginForm.get(0).getAttribute("textContent").equals("New customers click here"))) {
           System.out.println("absent or more than 1 link for registration");
           Assert.fail();
       }
        regInLoginForm.get(0).click();
       if (!driver.getCurrentUrl().equals("http://localhost/litecart/en/create_account")) {
           System.out.println("registration link leads to the wrong page");
           Assert.fail();
       }

     WebElement createAccount= driver.findElement(By.cssSelector("#create-account"));
      //First Name
       createAccount.findElement(By.cssSelector("input[name=firstname]")).sendKeys(randomStringGenerator(5));
       //Last Name
        createAccount.findElement(By.cssSelector("input[name=lastname]")).sendKeys(randomStringGenerator(4));
        //Address 1
        createAccount.findElement(By.cssSelector("input[name=address1]")).sendKeys(randomStringGenerator(7)+", "+randomNumberGenerator(2));
       //Postcode
        createAccount.findElement(By.cssSelector("input[name=postcode]")).sendKeys(randomNumberGenerator(5));
       //City
        createAccount.findElement(By.cssSelector("input[name=city]")).sendKeys(randomStringGenerator(7));
       //Country
        Select selectCountry = new Select(createAccount.findElement(By.cssSelector("select[name=country_code]")));
        selectCountry.selectByValue("US");
       //Zone/State/Province
        if(isElementPresent(By.cssSelector("#create-account select[name=zone_code]"))){
            Select selectState = new Select(createAccount.findElement(By.cssSelector("select[name=zone_code]")));
            selectState.selectByIndex(0);
        }
        //Email
        String eMail= randomEmailGenerator(6, "gmail.com");
        createAccount.findElement(By.cssSelector("input[name=email]")).sendKeys(eMail);
       //Phone
        WebElement phone = createAccount.findElement(By.cssSelector("input[name=phone]"));
        phone.sendKeys(Keys.HOME+randomNumberGenerator(8));
       //Desired Password
       String password = randomNumberGenerator(1)+randomStringGenerator(3)+randomNumberGenerator(2)+randomStringGenerator(3);
        createAccount.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
       //Confirm Password
        createAccount.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);
      // Create Account
       createAccount.findElement(By.cssSelector("button[name=create_account]")).click();


        if (!driver.getCurrentUrl().equals("http://localhost/litecart/en/")) {
            System.out.println("after reggistration redirect to the wrong page");
            Assert.fail();
        }
        // Logout first time
        logout(driver.findElements(By.cssSelector("#box-account a")),"Logout");
        //Loggin
        WebElement accountBox = driver.findElement(By.cssSelector("#box-account-login"));
        accountBox.findElement(By.cssSelector("input[name=email]")).sendKeys(eMail);
        accountBox.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        accountBox.findElement(By.cssSelector("button[name=login]")).click();
        // Logout second time
        logout(driver.findElements(By.cssSelector("#box-account a")),"Logout");
    }
}
