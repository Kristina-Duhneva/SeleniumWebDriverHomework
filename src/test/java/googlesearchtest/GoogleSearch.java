package googlesearchtest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class GoogleSearch {
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup(){

        driver= staticBrowser(BrowserTypes.FIREFOX);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://google.com");

        WebElement agreeButton= driver.findElement(By.xpath("//button[@id=\"L2AGLb\"]"));
        agreeButton.click();

    }
    @AfterAll
    public static void classTearDown(){
        driver.close();
    }
    @BeforeEach
    public void testSetup(){
        driver.get("https://google.com");
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
    public void resultFound_when_search_telerikAcademy(){
        String searchText="Telerik Academy Alpha";

        WebElement searchField=driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(searchText);

        WebElement searchButton=driver.findElement(By.xpath("(//center/input[@type='submit'][@name='btnK'])[1]"));
        searchButton.submit();

        WebElement firstResult=driver.findElement(By.xpath("(//a/h3)[1]"));
        Assertions.assertEquals("IT Career Start in 6 Months - Telerik Academy Alpha",firstResult.getText(),"Search result not found");

    }
}
