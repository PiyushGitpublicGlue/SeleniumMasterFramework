package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOrignal;
import org.selenium.pom.utils.CookiesUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTest {


        private ThreadLocal<WebDriver>  driver = new ThreadLocal<>();

        private void setDriver(WebDriver driver){
            this.driver.set(driver);
        }

        protected WebDriver getDriver(){
            return this.driver.get();
        }
        @Parameters("browser")
        @BeforeMethod
        public synchronized void startDriver(@Optional String browser){
            //browser = System.getProperty("browser",browser);
            if(browser==null) browser="CHROME";
            //setDriver(new DriverManagerOrignal().initializeDriver(browser));

            setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
            System.out.println("CURRENT THREAD ID : "+Thread.currentThread().getId() + " , " +
                    "DRIVER : " + getDriver());

        }

        @Parameters("browser")
        @AfterMethod
        public synchronized void quitDriver(@Optional String browser,ITestResult result) throws IOException {
            System.out.println("CURRENT THREAD ID : "+Thread.currentThread().getId() + " , " +
                    "DRIVER : " + getDriver());
            if(result.getStatus()==ITestResult.FAILURE){
                File destFile = new File("scr"+File.separator+browser+File.separator+
                        result.getTestClass().getRealClass().getSimpleName()+"_"+
                        result.getMethod().getMethodName()+".png");
                //takeScreenShot(destFile);
                takeScreenShotUsingAshot(destFile);
            }
            getDriver().quit();
        }

        public void injectCookiesToBrowser(Cookies cookies){
            List<Cookie> seleniumCookies = new CookiesUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
            for(Cookie cookie : seleniumCookies){
                getDriver().manage().addCookie(cookie);
            }
        }

        private void takeScreenShot(File destFile) throws IOException {
            TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
            File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile,destFile);

        }

        private void takeScreenShotUsingAshot(File destFile){
             Screenshot screenshot =new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(getDriver());
             try{
                 ImageIO.write(screenshot.getImage(),"PNG",destFile);
             }catch (IOException e){
                    e.printStackTrace();
             }
        }

    }

