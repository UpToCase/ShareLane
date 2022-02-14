import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    @Test
    public void zipCodeShouldAcceptFiveDigits() {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isOpened =
                driver.findElement(By.cssSelector("[type=submit]")).isDisplayed();
        Assert.assertTrue(isOpened, "registration for do non been opened");
        driver.quit();
    }

    @Test
    public void zipCodeShouldNotAcceptSixDigits() {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String errorMassage = driver.findElement(By.cssSelector("[class=error_message]")).
                getText();
        Assert.assertEquals(errorMassage,
                "Oops, error on page. ZIP code should have 5 digits",
                "error massages is not been correct");
        driver.quit();
    }

    @Test
    public void zipCodeShouldNotAcceptFourDigits() {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String errorMassage = driver.findElement(By.cssSelector("[class=error_message]")).
                getText();
        Assert.assertEquals(errorMassage,
                "Oops, error on page. ZIP code should have 5 digits",
                "error massages is not been correct");
        driver.quit();
    }

    @Test
    public void successfulSignUp() {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.name("first_name")).sendKeys("qwerty");
        driver.findElement(By.name("last_name")).sendKeys("qwerty");
        driver.findElement(By.name("email")).sendKeys("qwerty@qwerty.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String successfulRegistrationMassage = driver.findElement
                (By.cssSelector("[class=confirmation_message]")).getText();
        Assert.assertEquals(successfulRegistrationMassage, "Account is created!",
                "do not registered");
        driver.quit();
    }

    @Test
    public void logInShareLane() {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://sharelane.com/cgi-bin/main.py");
        driver.findElement(By.cssSelector("[name=email]")).
                sendKeys("ven_padron@224.65.sharelane.com");
        driver.findElement(By.cssSelector("[name=password]")).
                sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        boolean userGreeting = driver.findElement
                (By.className("user")).
                isDisplayed();
        Assert.assertTrue(userGreeting,
                "login in ShareLane failed");

    }
}
