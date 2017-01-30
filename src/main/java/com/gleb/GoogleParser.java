package com.gleb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 05.06.15.
 */
public class GoogleParser {

    private WebDriver webDriver;
    private static final String GOOGLE_PAGE = "https://www.google.com.ua";
    private static final String GOOGLE_TEXT_INPUT = ".//input[@name='q']"; //search string to text input
    private static final String GOOGLE_PARSE_PAGE = ".//*[@class='r']/a |" + //parse page on the main links
                                                    ".//*[@class='ads-ad']/h3/a[2] |" + //parse page to sponsored links
                                                    ".//*[@id='imagebox_bigimages']/div[1]/a |" + //search link called "Pictures on request ..."
                                                    ".//*[@class='_I2']/a"; //search link called "In The News"

    private final String keyToSearch; //search request

    public GoogleParser(String keyToSearch) {
        this.keyToSearch = keyToSearch;
    }

    /**
     * This is the main method in GoogleParser
     */
    public void start() {
        webDriver = new FirefoxDriver();
        webDriver.get(GOOGLE_PAGE);

        WebElement webElement = webDriver.findElement(By.xpath(GOOGLE_TEXT_INPUT));
        webElement.sendKeys(keyToSearch);
        webElement.submit();

        new WebDriverWait(webDriver, 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().toLowerCase().startsWith(keyToSearch.toLowerCase()); //waiting for Google search page loading
            }
        });

        List<String> links = getLinks(webDriver.findElements(By.xpath(GOOGLE_PARSE_PAGE)));
        List<String> titles = new ArrayList<>();
        for (String link : links) {
            webDriver.navigate().to(link);
            titles.add(" * " + webDriver.getTitle()); //waiting for the page is loaded and outputs page name
            webDriver.navigate().back();
        }

        webDriver.quit();

        for (String s : titles) {
            System.out.println(s);
        }
    }

    /**
     * @param webElements all tags with links
     * @return list links
     */
    private List<String> getLinks(List<WebElement> webElements) {
        List<String> links = new ArrayList<>();
        for (WebElement element : webElements) {
            links.add(element.getAttribute("href"));
        }
        return links;
    }

}
