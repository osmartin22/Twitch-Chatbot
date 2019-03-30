package ozmar.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ozmar.commands.interfaces.TwitchStockCommandInterface;
import ozmar.utils.StringHelper;
import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TwitchStockCommand implements TwitchStockCommandInterface {

    private final WebDriver driver;

    public TwitchStockCommand() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
    }

    /**
     * Checks the website and gets the stock info
     * NOTE: website has no api so everything must be done through web scraping
     *
     * @param event User data anc command info
     * @return String
     */
    @Nullable
    @Override
    public String getStock(@Nonnull CommandEvent event) {
        String stockSymbol = StringHelper.getFirstWord(event.getCommand().trim().toLowerCase());
        String url = "https://twitchstocks.com/stock/";
        String result;
        if (stockSymbol.isEmpty()) {
            result = "Try again using !checkstocks <StockSymbol>";
        } else {
            try {
                driver.get(url + stockSymbol);
                String xPath = "/html/body/app-root/app-nav/mat-sidenav-container/mat-sidenav-content/app-stock-home/div/mat-grid-list/div/mat-grid-tile[1]/figure/mat-card";

                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

                WebElement price = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[1]");
                WebElement name = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-header[1]/div/mat-card-title");
                WebElement diff = ((ChromeDriver) driver).findElementByXPath(xPath + "/mat-card-content[1]/mat-card-title/div[2]");
                result = price.getText() + " " + name.getText() + " " + diff.getText();
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }
}
