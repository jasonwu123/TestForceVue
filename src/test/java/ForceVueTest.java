
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by ruxit on 5/18/16.
 */
public class ForceVueTest {
	WebDriver driver;
	String testUrl;
	Process pr;

	@BeforeClass
	public void startServer() throws InterruptedException, IOException {
		Runtime rt = Runtime.getRuntime();
        //start local dev server
        //startServer.sh takes in the force-vue director
        String[] env = new String[1];
        env[0] = "~/Documents/GoodStart/force-vue";
		pr = rt.exec("src/test/serverstart/startServer.sh");
//		Thread.sleep(20000);
		Thread.sleep(200);

	}

	@AfterClass
	public void stopServer() {
		pr.destroy();
		System.out.println(pr.exitValue());
	}

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("test-type");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		testUrl = "http://www.alexckramer.com/force-vue/#!/home";
		testUrl = "http://localhost:8080";
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testPerson() throws InterruptedException {
		try {
			driver.get(testUrl);
			WebElement ele = driver.findElement(By.cssSelector(".randPersonBtn"));
			ele.click();
			Thread.sleep(2000);
			ele = driver.findElement(By.cssSelector(".mdl-tabs__tab"));
			ele.click();
			Select select = new Select(ele);
			List<WebElement> list = select.getOptions();
			for(int i=0; i<list.size(); i++){
				System.out.println(list.get(i).getText());
				if(list.get(i).getText().contains("PLANET")){
					list.get(i).click();
					break;
				}
			}
			Thread.sleep(2000);
			ele = driver.findElement(By.cssSelector(".randplanetBtn"));
			ele.click();
			Thread.sleep(2000);
		} catch(Exception e){
			System.out.println(e);
		}


	}

}
