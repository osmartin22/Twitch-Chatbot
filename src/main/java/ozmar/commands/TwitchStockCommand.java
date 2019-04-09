package ozmar.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.MessageSource;
import ozmar.commands.interfaces.TwitchStockCommandInterface;
import ozmar.utils.StringHelper;
import twitch4j_packages.chat.events.CommandEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Locale;

public class TwitchStockCommand implements TwitchStockCommandInterface {

    private final WebDriver driver;
    private final MessageSource source;
    private final Locale defaultLocale;

    public TwitchStockCommand(MessageSource messageSource) {
        this.source = messageSource;
        this.defaultLocale = new Locale("en");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        String webdriver = source.getMessage("cmd.webdriver", null, defaultLocale);
        String webdriverLocation = source.getMessage("cmd.webdriver.location", null, defaultLocale);
        System.setProperty(webdriver, webdriverLocation);
        this.driver = new ChromeDriver(chromeOptions);
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
        String url = source.getMessage("cmd.base.url", null, defaultLocale);
        String result;
        if (stockSymbol.isEmpty()) {
            result = source.getMessage("cmd.stock.error", null, defaultLocale);
        } else {
            try {
                driver.get(url + stockSymbol);
                String xPath = source.getMessage("cmd.stock.xpath", null, defaultLocale);

                WebDriverWait wait = new WebDriverWait(driver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));

                WebElement price = ((ChromeDriver) driver).findElementByXPath(xPath +
                        source.getMessage("cmd.stock.xpath.price", null, defaultLocale));
                WebElement name = ((ChromeDriver) driver).findElementByXPath(xPath +
                        source.getMessage("cmd.stock.xpath.name", null, defaultLocale));
                WebElement diff = ((ChromeDriver) driver).findElementByXPath(xPath +
                        source.getMessage("cmd.stock.xpath.diff", null, defaultLocale));
                result = price.getText() + " " + name.getText() + " " + diff.getText();
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }
}
