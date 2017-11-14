package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("testi");
        element = driver.findElement(By.name("password"));
        element.sendKeys("itset");
        element = driver.findElement(By.name("passwordConfrimation"));
        element.sendKeys("itset");
        element = driver.findElement(By.name("signup"));
        sleep(2);
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        sleep(2);
        element.click();
        element = driver.findElement(By.linkText("logout"));
        sleep(2);
        element.click();
        

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
