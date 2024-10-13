import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJUnit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Get website title")
    @Test
    public void getTitle(){
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        String title = driver.getTitle();
        System.out.println(title);
        //String titleExpected = "guest-registration-form";
        //Assertions.assertTrue(title.contains("Guest-Registration-Form"));
        //Assertions.assertTrue(titleActual.contains(titleExpected));
        //Assertions.assertEquals(titleExpected,titleActual);
    }
    @DisplayName("Submit user form")
    @Test
    public void submitForm(){
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        List<WebElement> formControls = driver.findElements(By.className("input-text"));
        formControls.get(0).sendKeys("Ishrat");
        formControls.get(1).sendKeys("ishrat@test.com");
        formControls.get(2).sendKeys("1234@1234a");
        formControls.get(3).sendKeys("jahan");

        WebElement calendarElem=driver.findElement(By.className("ur-flatpickr-field"));
        calendarElem.click();
        calendarElem.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        calendarElem.sendKeys("2024-08-13");
        calendarElem.sendKeys(Keys.ENTER);

        WebElement dropdownElem = driver.findElement(By.className("ur-field-item"));
        dropdownElem.click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();

        Select select=new Select( driver.findElement(By.id("country_1665629257")));
        select.selectByVisibleText("Bangladesh");






       Utils.scroll(driver,700);
        driver.findElement(By.id("privacy_policy_1665633140_field")).click();
        driver.findElement(By.className("ur-submit-button")).click();


    }

    //@AfterAll

    public void closeBrowser(){
        driver.quit();
        //driver.close();
    }
}
