package bingsearchtest;

import googlesearchtest.BrowserTypes;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.time.Duration;

public class BingSearch {
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {

        driver = staticBrowser(BrowserTypes.CHROME);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://bing.com");

        WebElement agreeButton= driver.findElement(By.xpath("//button[@id='bnp_btn_accept'] "));
        agreeButton.click();
    }
    @BeforeEach
    public void testSetup(){
        driver.get("https://bing.com");
    }
    @AfterAll
    public static void classTearDown(){
        driver.close();
    }
    private static WebDriver staticBrowser(BrowserTypes browserTypes) {
        switch (browserTypes) {
            case CHROME:
                return new ChromeDriver();
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                return new ChromeDriver(chromeOptions);
            case FIREFOX:
                return new FirefoxDriver();
            case FIREFOX_HEADLESS:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                return new FirefoxDriver(firefoxOptions);
        }
        return null;
    }

        @Test
  public void  resultFound_when_search_telerikAcademy(){
        String searchText="Telerik Academy Alpha";

        WebElement searchField=driver.findElement(By.xpath("//input[@type='search']"));
        searchField.sendKeys(searchText);

        WebElement searchButton=driver.findElement(By.xpath("//label[@id=\"search_icon\"]"));
        searchButton.click();

        WebElement firstResult=driver.findElement(By.xpath("(//h2/a)[1]"));
        String title = firstResult.getText();

        if (title.equals("IT Career Start in 6 Months - Telerik Academy Alpha")) {
            System.out.println("Test Passed: Title of the first result is correct.");
        } else {
            System.out.println("Test Failed: Title of the first result is incorrect.");
        }

       Assertions.assertEquals("IT Career Start in 6 Months - Telerik Academy Alpha",firstResult.getText(),"Search result not found");

        System.out.println(title);

  }
}
