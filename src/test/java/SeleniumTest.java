import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ozmar.Stock;

import java.util.ArrayList;
import java.util.List;


public class SeleniumTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterClass
    public static void closing() {
        driver.quit();
    }

    @Test
    public void getElements() {
        driver.get("https://twitchstocks.com/stock/mnmn");
        String xPath = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-stock-home/div/mat-grid-list/div/mat-grid-tile[1]/figure/mat-card";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

        WebElement price = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[1]");
        WebElement name = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-header[1]/div/mat-card-title");
        WebElement diff = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[2]");

        System.out.println(price.getText() + " " + name.getText() + " " + diff.getText());

//        stock.setPrice(price.getText());
//        stock.setStockName(name.getText());
//        stock.setChange24Hour(diff.getText());
    }


    @Test
    public void getAll() {
        driver.get("https://twitchstocks.com/stocks/all");
        String numOfItemsButton = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-all-stocks/div/div/mat-paginator/div/div/div[1]/mat-form-field/div/div[1]/div";
        String select500Items = "/html/body/div[2]/div[2]/div/div/mat-option[6]/span";
        String table = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-all-stocks/div/div/table/tbody/tr";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(numOfItemsButton)))).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(select500Items)))).click();

        // Selected 500 items per page, Currently only one page exists so 450 works for now
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(table), 450));
        List<WebElement> webElements = driver.findElements(By.xpath(table));

        int count = 1;
        int size = webElements.size();
        List<Stock> stockList = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            WebElement webElement = webElements.get(i);
            System.out.print(count++ + " ");

            String[] split = webElement.getText().split(" ", 4);
            Stock stock = new Stock();
            stock.setStockName(split[0]);
            stock.setStockSymbol(split[1]);
            stock.setPrice(split[2]);
            stock.setLastModified(System.currentTimeMillis());
            stockList.add(stock);

            System.out.print("Name: " + stock.getStockName());
            System.out.print(" Symbol: " + stock.getStockSymbol());
            System.out.print(" Price: " + stock.getPrice());
            System.out.print(" Other: " + split[3]);
            System.out.println();
        }

        System.out.println(stockList.size());
    }
}
