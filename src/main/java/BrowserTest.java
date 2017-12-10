import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BrowserTest {
    private static final String EMAIL = "c34test@gmail.com";
    private static final String PWD = "7Xn3sSlrNGDvCZh";

    public static void main(String[] args) {
        System.out.println("hello");
//        testRegex();

        System.setProperty("webdriver.chrome.driver", "/Users/xwei/Downloads/chromedriver");
//        WebDriver driver = new ChromeDriver();
        try {
//        URL url = new URL("http://localhost:9515");
//        WebDriver driver = new RemoteWebDriver(url, DesiredCapabilities.chrome());
            WebDriver driver = new ChromeDriver();
//            testBrowser(driver);
            testGoogle(driver);
//            testQBSE(driver);
//            testQBSESignIn(driver);
            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testQBSE(WebDriver driver) throws Exception {
        final String url = "https://selfemployed-dev.intuit" +
                ".com/contractor/invite?signup=true&invitation_id=i00b73a0e1648204142bc9b47679194086c&access_code=fc5d144680cf4a22b207031bb0eb5f0e";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(3000);
        WebElement pwd = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath
//                ("//*[@id=\"ius-label-password\"]")));
        ("//*[@id=\"ius-sign-in-url\"]"))).findElement(By.xpath("//*[@id=\"ius-password\"]"));
        pwd.sendKeys("$Test1234");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement confirmPwd = driver.findElement(By.id("ius-confirm-password"));
        confirmPwd.sendKeys("$Test1234");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement createBtn = driver.findElement(By.id("ius-sign-up-submit-btn"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        createBtn.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ius-email-message-text")));
        WebElement signIn = driver.findElement(By.xpath("//*[@id=\"ius-sign-in-url\"]"));
        signIn.click();
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ius-sign-in-submit-btn-text")));
        pwd =driver.findElements(By.xpath("//*[@id=\"ius-password\"]")).get(1);
        pwd.sendKeys("$Test1234");
        WebElement accpetBtn = driver.findElement(By.id("ius-sign-in-submit-btn"));
        accpetBtn.click();

        Thread.sleep(5000);
        driver.quit();
    }

    public static void testQBSESignIn(WebDriver driver) throws Exception {
        final String url = "https://selfemployed-dev.intuit" +
                ".com/contractor/invite?signup=true&invitation_id=i0041ebc2ad6482432397096382ee4c1a4f&access_code" +
                "=57db6339b2174d80a30c1dd5ee924851#";
        driver.get(url);

        Thread.sleep(5000);
//        driver.quit();

    }

    public static void testGoogle(WebDriver driver) throws Exception {
        driver.get("https://accounts.google.com/AccountChooser?service=mail&amp;continue=https://mail.google" +
                ".com/mail/");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement nameInput = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        nameInput.sendKeys(EMAIL);
        //wait 5 secs for userid to be entered
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/content/span")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement pwd = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        pwd.sendKeys(PWD);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content/span")).click();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }


    public static void testBrowser(WebDriver driver) throws Exception {
        driver.get("http://www.cnblogs.com/tankxiao");
        Thread.sleep(5000);
        // 浏览器最大化
        driver.manage().window().maximize();

        driver.navigate().to("http://www.baidu.com");
        // 刷新浏览器
        driver.navigate().refresh();
        // 浏览器后退
        driver.navigate().back();
        // 浏览器前进
        driver.navigate().forward();
        // 浏览器退出
        driver.quit();
    }

    public static void testRegex(){
        String url = "<a href=\"https://selfemployed-stage.intuit" +
                ".com/home?invitation_id=i00a81261be7fc2493c94b0e31346102cfc&amp;" +
                "access_code=9f3cf7e040e4417aa2900743b54f4a41#/contractor/1099\" style=\"color:#ffffff;" +
                "text-decoration:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google" +
                ".com/url?hl=en&amp;q=https://selfemployed-stage.intuit" +
                ".com/home?invitation_id%3Di00a81261be7fc2493c94b0e31346102cfc%26access_code" +
                "%3D9f3cf7e040e4417aa2900743b54f4a41%23/contractor/1099&amp;source=gmail&amp;" +
                "ust=1512685786776000&amp;usg=AFQjCNHzMyhdFWs87Maxuy_ZC8lzjz-T_A\">    Get your 1099 online\n" +
                "</a>";
        String pattern  = "href=\\\"https://selfemployed-stage.intuit.com\\/home\\?(.+)?\\\"\\s+style=(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if(m.find()){
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }

    }
}