package com.test.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private static String driverDirectoryMac = System.getProperty("user.dir") + "/src/test/resources/drivers/mac";
    private static String driverDirectoryLinux = System.getProperty("user.dir") + "/src/test/resources/drivers/linux";
    private static String driverDirectoryWindows = System.getProperty("user.dir") + "/src/test/resources/drivers/windows";

    private static DesiredCapabilities defaultedcapabilities(){
        Config deviceConfig;
        if (System.getProperty("testDevice").startsWith("BS")){
            String testDevice = System.getProperty("testDevice");
            deviceConfig = ConfigFactory.parseResources("browserstackdata/" + testDevice + ".json");

            return new DesiredCapabilities(deviceConfig.root().unwrapped());
        }
        else {
            throw new RuntimeException("test device not found");
        }

    }

    public static WebDriver CreateBrowser() {
        String browserType = System.getProperties().getProperty("browser", "chrome");
        switch (browserType) {
            case "firefox":
                if(getOs().startsWith("Mac")){
                    System.setProperty("webdriver.gecko.driver", driverDirectoryMac + "/firefox/geckodriver");
                }
                else if(getOs().startsWith("Linux")){
                    System.setProperty("webdriver.gecko.driver", driverDirectoryLinux + "/geckodriver");
                }
                else if(getOs().startsWith("Windows")){
                    System.setProperty("webdriver.gecko.driver", driverDirectoryWindows + "/geckodriver.exe");
                }
                return CreateFireFoxDriver();
            case "chrome":
                if(getOs().startsWith("Mac")){
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverDirectoryMac + "/chrome/chromedriver");
                }
                else if(getOs().startsWith("Linux")){
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverDirectoryLinux + "/geckodriver");
                }
                else if(getOs().startsWith("Windows")){
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverDirectoryWindows + "/geckodriver.exe");
                }
                return CreateChromeDriver();
            case "browserstack":
                return BrowserStackDriver();
            default: throw new RuntimeException("Browser Type not found");
        }
    }

    private static WebDriver CreateFireFoxDriver() {

        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver");
        FirefoxOptions options  = new FirefoxOptions();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
        FirefoxProfile profile  = new FirefoxProfile();
        profile.setPreference("javascript.enabled", true);
        profile.setAcceptUntrustedCertificates(true);

        options.setProfile(profile);
        options.setAcceptInsecureCerts(true);

        FirefoxDriver driver = new FirefoxDriver(options);
        System.out.println("Browser:" + driver.getCapabilities().getBrowserName().toUpperCase() + " version:" + driver.getCapabilities().getVersion() + " " + "OS:" + System.getProperty("os.name"));

        return driver;
    }

    private static WebDriver CreateChromeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        if(System.getProperty("zapEnabled", "true").equals("true")){
            Proxy proxy = new Proxy();
            proxy.setHttpProxy("localhost:11000");
            options.setCapability("proxy", proxy);
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);

        ChromeDriver driver = new ChromeDriver(options);
        System.out.println("Browser:" + driver.getCapabilities().getBrowserName().toUpperCase() + " version:" + driver.getCapabilities().getVersion() + " " + "OS:" + System.getProperty("os.name"));

        return driver;
    }

    private static WebDriver BrowserStackDriver(){
        String projectName = "TAI";
        String buildName = "Local Build";
        DesiredCapabilities capabilities = defaultedcapabilities();
        capabilities.setCapability("browserstack.debug", "true");
        capabilities.setCapability("browserstack.local", "true");
        capabilities.setCapability("build", buildName);
        capabilities.setCapability("project", projectName);
        Config config = ConfigFactory.parseResources("browserConfig.properties");
        String username = config.getString("username");
        String key = config.getString("automatekey");
        String urlForm = "http://" + username + ":" + key + "@hub-cloud.browserstack.com/wd/hub";

        RemoteWebDriver driver = new RemoteWebDriver(UrlMethod(urlForm), capabilities);
        System.out.println("Browser:" + driver.getCapabilities().getBrowserName().toUpperCase() + " version:" + driver.getCapabilities().getVersion() + " " + "OS:" + System.getProperty("os.name"));

        return driver;
    }

    private static String getOs(){
        return System.getProperty("os.name");
    }

    private static URL UrlMethod(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex.getLocalizedMessage());
        }
    }

}
