package com.grepiu.www.process.common.tools.crawler.node;

import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 크롤링 기본 노드 - selenium 기반 개발
 * // 프록시 List : http://www.gatherproxy.com/proxylist/country/?c=Republic%20of%20Korea
 * // 프록시 메뉴얼 : https://www.seleniumhq.org/docs/04_webdriver_advanced.jsp
 *
 */
public abstract class BaseNode<T> {

  // 지현 시간
  private final int sleep_second = 2500;
  // 설정 정보
  private final String localChromeDriver = "c:\\workspace\\sw\\selenium\\chromedriver.exe";
  private final String remoteChromeDriver = "http://selenium.grepiu.com/wd/hub";

  // WebDriver
  private WebDriver driver;
  // WebDriver 타입
  private String driverType = "CHROME";

  private final String chromePath = "/usr/bin/chromedriver";
  private final String firefoxPath = "/home/sw/firefox";

  /**
   * 크롬으로 초기화
   */
  public void initChromeLocal(String startUrl) {

//    System.setProperty("webdriver.chrome.driver", chromePath);
    this.driverType = "CHROME";
    System.setProperty("webdriver.chrome.driver", localChromeDriver);
    this.driver = new ChromeDriver();
    this.driver.get(startUrl);


    sleep();
  }

  /**
   *
   * 원격 크롬 driver
   *
   * @param startUrl
   */
  public void initChromeRemote(String startUrl) {
    try {
      this.driverType = "CHROME";
      this.driver = new RemoteWebDriver(new URL(remoteChromeDriver),
          DesiredCapabilities.chrome());
      this.driver.get(startUrl);
    } catch (Exception e) {
      throw new RuntimeException("초기화에 실패 하였습니다.");
    }
  }

  /**
   *
   * 크롬 원격 driver
   *
   * @param startUrl 크롤링 시작 URL
   * @param option ExecuteOption 객체
   */
  public void initChromeRemote(String startUrl, ExecuteOption option) {
    try {
      this.driverType = "CHROME";

      if(option.isProxyUse()) {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(option.getProxyServerIp());
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.PROXY, proxy);
        cap.setBrowserName(BrowserType.CHROME);
        cap.setPlatform(Platform.ANY);
        this.driver = new RemoteWebDriver(new URL(remoteChromeDriver), cap);
      } else {
        this.driver = new RemoteWebDriver(new URL(remoteChromeDriver),
            DesiredCapabilities.chrome());
      }

      this.driver.get(startUrl);
    } catch (Exception e) {
      throw new RuntimeException("초기화에 실패 하였습니다.");
    }
  }

  /**
   *
   * firefox driver
   *
   * @param url
   */
  public void initFirefox(String url) {
    System.setProperty("webdriver.gecko.driver", firefoxPath);
//    System.setProperty("webdriver.gecko.driver", "c:\\workspace\\sw\\selenium\\geckodriver.exe");
    this.driver = new FirefoxDriver();
    this.driver.get(url);

    this.driverType = "FIREFOX";
    sleep();
  }

  /**
   * 시간 지연 처리한다.
   * 네트워크 사항등을 고려하여 변경해야될 필요도 있다.
   * 기본값 : 2초
   */
  public void sleep() {
    try {
      Thread.sleep(sleep_second);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * 크롤링 driver 접속을 끝낸다.
   *
   */
  public void quit() {
    getDriver().quit();
  }

  /**
   *
   * Driver를 가져온다.
   *
   * @return
   */
  public WebDriver getDriver() {
    return this.driver;
  }

  /**
   *
   * Root 엘리먼트에서 Loop 엘리먼트를 가져온다.
   *
   * @param webElement
   * @param by
   * @return
   */
  public List<WebElement> findElements(WebElement webElement, By by) {
    try {
      return webElement.findElements(by);
    } catch (Exception e) {
      return null;
    }

  }

  /**
   *
   * Root엘리먼트에서 특정 엘리먼트를 가져온다.
   *
   * @param webElement
   * @param by
   * @return
   * @throws Exception
   */
  public WebElement findElement(WebElement webElement, By by) throws Exception {
    try {
      return webElement.findElement(by);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 엘리먼트를 클릭한다.
   */
  public void elementClick(WebElement webElement) {
    Actions actions = new Actions(driver);
    actions.moveToElement(webElement).click().perform();
    sleep();
  }


  /**
   * 실 구동 로직 구현
   * <pre>
   *     BaseNode를 상속받아 executeLogic 로직 구현
   *     특정 이벤트처리를 위해 onUpdate 지정하여 사용
   * </pre>
   */
  public abstract List<T> execute(ExecuteOption executeOption);
}
