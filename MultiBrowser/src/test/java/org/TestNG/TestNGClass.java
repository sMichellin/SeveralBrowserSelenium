package org.TestNG;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.*;




public class TestNGClass
{
    private WebDriver driver;
    private String URL = "https://www.calculator.net/";
    @Parameters("browser")


    @BeforeTest
    public void launchapp(String browser)
    {
        if (browser.equalsIgnoreCase("firefox"))
        {
            System.out.println(" Executing on FireFox");

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);


            System.setProperty("webdriver.firefox.driver","D:\\Selenium\\driver\\geckodriver-v0.33.0-win-aarch64\\geckodriver.exe");

            driver = new FirefoxDriver(firefoxOptions);
           // driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("chrome"))
        {
            System.out.println(" Executing on CHROME");
            //System.out.println("Executing on IE");
           System.setProperty("webdriver.chrome.driver","D:\\Selenium\\driver\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);


            driver = new ChromeDriver(chromeOptions);
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("MS Edge") || browser.equalsIgnoreCase("MicrosoftEdge"))
        {
            System.out.println("Executing MS Edge");

           //EdgeOptions options = new EdgeOptions();
           // options.addArguments("--headless=new");

            System.setProperty("webdriver.ie.driver","D:\\Selenium\\driver\\IEDriverServer_x64_4.8.1\\IEDriverServer.exe");
            // Настройка параметров для Internet Explorer
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.ignoreZoomSettings();
            options.introduceFlakinessByIgnoringSecurityDomains();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
           // options.setCapability("seleniumProtocol", "WebDriver");
            options.withEdgeExecutablePath("C:\\Program Files\\Internet Explorer\\iexplore.exe");
  driver = new InternetExplorerDriver(options);


/*
            // Установите путь к драйверу Microsoft Edge
            System.setProperty("webdriver.edge.driver", "D:\\Selenium\\driver\\edgedriver_win64\\msedgedriver.exe");

            // Создаем объект EdgeOptions
            EdgeOptions options = new EdgeOptions();
            // Включаем режим совместимости с Selenium WebDriver
       options.setCapability("seleniumProtocol", "WebDriver");

            // Инициализируем драйвер Microsoft Edge
           driver = new EdgeDriver(options);

            // Открываем сайт https://www.calculator.net/
            */


            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }



        else
        {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
    }
    @Test
    public void calculatepercent()
    {
// Click on Math Calculators
     //  driver.findElement(By.xpath("/html/body/div[4]/div/div[1]/div[3]/div[2]/a")).click();
driver.findElement(By.linkText("Math Calculators")).click();

       // Click on Percent Calculators


   //    driver.findElement(By.xpath("/html/body/div[3]/div[1]/table[2]/tbody/tr/td/div[3]/a")).click();
driver.findElement(By.linkText("Percentage Calculator")).click();

        // Enter value 10 in the first number of the percent Calculator
        driver.findElement(By.id("cpar1")).sendKeys("10");
// Enter value 50 in the second number of the percent Calculator
        driver.findElement(By.id("cpar2")).sendKeys("50");
// Click Calculate Button
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/table[1]/tbody/tr[2]/td/input[2]")).click();
// Get the Result Text based on its xpath
                String result = driver.findElement(By.xpath("/html/body/div[3]/div[1]/p[2]/font/b")).getText();
// Print a Log In message to the screen
                                System.out.println(" The Result is " + result);
        if(result.equals("5"))
        {
            System.out.println(" The Result is Pass");
        }
        else
        {
            System.out.println(" The Result is Fail");
        }
    }
    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}