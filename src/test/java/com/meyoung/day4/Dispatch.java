package examples;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Dispatch {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("Webdriver.chrome.driver","D:\\IdeaProjects\\test20190718\\driver\\chromedriver.exe");
       // System.setProperty("Webdriver.firefox.marionette","D:\\IdeaProjects\\test20190718\\river\\geckodriver.exe");
       // driver = new FirefoxDriver();
        driver = new ChromeDriver();
        baseUrl = "http://test.ach.haomianjie.com/admin/aLogin.html";
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    //登入系统
    public void test3() throws Exception {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.findElement(By.xpath(".//*[@id='LAY-user-login-username']")).sendKeys("admin");
        driver.findElement(By.xpath(".//*[@id='LAY-user-login-password']")).sendKeys("tengfei123");
        driver.findElement(By.xpath(".//*[@id=\'LAY-user-login\']/div/div[2]/div[3]/button")).click();
        Thread.sleep(3000);
        //登入系统-系统设置-部门管理-部门名称查询
        driver.findElement(By.xpath(".//*[@id='LAY-system-side-menu']/li[2]/a")).click();
        driver.findElement(By.xpath(".//*[@id='LAY-system-side-menu']/li[2]/dl/dd[2]/a")).click();
        driver.get("https://test.ach.haomianjie.com/admin/department/index");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/form/div[1]/div/input")).sendKeys("王牌团1");
        driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/form/div[1]/button")).click();
        Thread.sleep(3000);
        //登入系统-系统设置-部门管理-部门名称-编辑
        driver.findElement(By.xpath(".//*[@id='departmentTreeTable']/div/table/tbody/tr[1]/td[3]/a[1]")).click();
        driver.get("https://test.ach.haomianjie.com/admin/department/departmentUpdate");
        driver.findElement(By.xpath("/html/body/form/div[1]/div/div/input")).sendKeys("王牌团11");
        driver.findElement(By.xpath("/html/body/form/div[2]/div/div/div/div/i")).click();
        driver.findElement(By.xpath(".//*[@id='selecttree_parentDept']/li[2]/a/cite")).click();
        driver.findElement(By.xpath(".//*[@id='departmentDesc']")).sendKeys("请输入部门描述123测试测试");
        //部门管理-部门名称-编辑--保存
        driver.findElement(By.xpath("/html/body/form/div[4]/div/button")).click();
        Thread.sleep(4000);
        //登入系统-系统设置-部门管理-部门名称-删除
        driver.get("https://test.ach.haomianjie.com/admin/department/index");
        driver.findElement(By.xpath(".//*[@id='departmentTreeTable']/div/table/tbody/tr[10]/td[3]/a[2]")).click();
        //部门管理-部门名称-删除--取消
        Thread.sleep(3000);
        driver.findElement(By.xpath(".//*[@id='layui-layer1']/div[3]/a[2]")).click();
        Thread.sleep(3000);

        driver.switchTo().defaultContent();
    }


    public void tearDown() throws Exception {
        //driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }

    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}