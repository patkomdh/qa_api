package com.jsystems.qa.qagui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static java.lang.Thread.sleep;

public class WindowsTest extends ConfigFrontend {

    @Test
    public void testWindows() {
        String firstPageWindowHandle;
        String secondTestWindowHandle = null;

        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.get(contactUrl);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in a new window")));

        firstPageWindowHandle = driver.getWindowHandle();

        WebElement hyperLinkElement = driver.findElement(By.linkText("Open page in a new window"));

        int hyperlinkYCoordinate = hyperLinkElement.getLocation().getY();
        int hyperlinkXCoordinate = hyperLinkElement.getLocation().getX();

        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");

        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in a new window")));

        hyperLinkElement.click();

        Set<String> testPageWindowHandle = driver.getWindowHandles();

        for (String windowHandle : testPageWindowHandle) {
            if (!firstPageWindowHandle.equals(windowHandle)) {
                secondTestWindowHandle = windowHandle;
            }
        }

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().window(secondTestWindowHandle);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("testpagelink")));

        driver.switchTo().window(secondTestWindowHandle).close();

        driver.switchTo().window(firstPageWindowHandle);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in a new window")));

    }

    @Test
    public void frameTest(){

        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.get(contactUrl);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("testframe")));

        WebElement testframe = driver.findElement(By.name("testframe"));

        driver.switchTo().frame(testframe);

        String expectedFrameText = driver.findElement(By.id("testpagelink")).getText();
        String actualFrameText = "My frame text";

        if(actualFrameText.equals(expectedFrameText)){
            System.out.println("Found my frame text");
        }
        else {
            System.out.println("Did not find my frame text");
        }

        driver.switchTo().parentFrame();
    }

    @Test
    public void pageScroll() {
        String contactUrl = "http://www.testdiary.com/training/selenium/selenium-test-page/";

        driver.get(contactUrl);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in the same window")));

        int hyperlinkYCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getY();

        int hyperlinkXCoordinate = driver.findElement(By.linkText("Open page in the same window")).getLocation().getX();

        JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
        jsexecutor.executeScript("window.scrollBy(" + hyperlinkXCoordinate + "," + hyperlinkYCoordinate + ")", "");

        new WebDriverWait(driver, 100)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in the same window")));

        driver.findElement(By.linkText("Open page in the same window")).click();
    }

    @Test
    void scrollIntoView(){
        driver.get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");

        JavascriptExecutor je = (JavascriptExecutor) driver;

        WebElement element = driver.findElement(By.xpath("//*[@id=\"mCSB_9_container\"]/ul/li[4]/img"));

        je.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    @Test
    public void popupHandler(){
        driver.switchTo().alert();
        driver.findElement(By.id("userID")).sendKeys("userName");
        driver.findElement(By.id("password")).sendKeys("myPassword");
        driver.switchTo().alert().accept();
        driver.switchTo().defaultContent();
    }

    @Test
    public void alert(){
        String firstWIndow;
        firstWIndow = driver.getWindowHandle();

        Alert alert = driver.switchTo().alert();
        alert.accept();
        alert.dismiss();

        driver.switchTo().window(firstWIndow);
    }

    @Test
    public void copyTest() {


//Open a new Firefox Browser

// Maximise the browser window
        driver.manage().window().maximize();

// Save the url of the page into a string object
        String appUrl = "http://www.testdiary.com/";

// Open the application url
        driver.get(appUrl);

// Declare and initialise the variable to store the expected title of
// the web page.
        String expectedTitle = "Test Diary - A software testers guide";

// Fetch the title of the web page and save it into a string variable
        String actualTitle = driver.getTitle();

// Use an if condition to check if both string objects are equal
        if (expectedTitle.equals(actualTitle)) {

            System.out
                    .println("verification successful - The correct Tile is Displayed on the Webpage");
        } else {
            System.out.println("verification unsuccessful");
        }

// Close the browser

    }
}
