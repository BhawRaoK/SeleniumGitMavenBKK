package mavenProject.MavenProject;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Hello world!
 *
 */
public class App 
{
	WebDriver driver;
	//final String appURL = "http://naukri.com";
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );        
        App obj = new App();
        obj.testCase1();
        
    }
    
    public void testCase1(){
    	App obj = new App();
        obj.launchApplication("firefox");
        
        obj.launchURL("http://the-internet.herokuapp.com/dropdown");
        obj.getValuesFromDropDown();
        obj.toRefreshPage();
        //obj.closeApplication();
        	
    	
    }
    
    public void toRefreshPage(){
    	WebElement we = driver.findElement(By.name("qp"));
    	we.sendKeys("asdfas");
    	
    	
    	
    	//we.sendKeys("\uE035");
    	driver.navigate().refresh();
    }
    
    public void getValuesFromDropDown(){
    	WebElement dd= driver.findElement(By.cssSelector("#dropdown"));
    	
    	Select sdd= new Select(dd);
    	sdd.selectByValue("1");
    	sdd.selectByIndex(1);
    	sdd.selectByVisibleText("Option 2");
    	
    }
    
    public void handlesWindows(){
    	String  parentHandle = driver.getWindowHandle();
    	driver.getWindowHandles();
    	System.out.println(parentHandle);
    	
    	Set<String> allHandles = driver.getWindowHandles();
    	
    	for(String had : allHandles){
    		
    		driver.switchTo().window(had);
    		System.out.println(driver.getTitle());
    	}
    	driver.switchTo().window(parentHandle);
    	this.getLinksFromPage();
    	
    }
    
    public void getLinksFromPage(){
    	List<WebElement> lis= driver.findElements(By.tagName("a"));
    	for(WebElement we : lis){
    		System.out.println(we.getText());
    	}
    	
    }
    
    public void launchURL(String appURL){
    	
    	driver.get(appURL);
    	driver.manage().window().maximize();
    	String url = driver.getCurrentUrl();
    	String actualTitle = driver.getTitle();
    	
    	if (url.contains("naukri")){
    		if(actualTitle.startsWith("Jobs")){
    			System.out.println("testCase passed");
    		}
    		else{
    			System.out.println("Failed");
    		}
    	}
    	else{
    		System.out.println("failed");
    	}
    }
    public void launchApplication(String browserName){
    	
    	if(browserName.equalsIgnoreCase("firefox")){
    		driver = new FirefoxDriver();
    		this.implcitWaitExample();
    	}
    	else if(browserName.equalsIgnoreCase("chrome")){
    		
    		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
    		driver = new ChromeDriver();
    		//this.implcitWaitExample();
    	}
    	
    	else if(browserName.equalsIgnoreCase("ie")){
    		System.setProperty("webdriver.ie.driver", "C:\\Users\\admin\\Downloads\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
    		driver = new InternetExplorerDriver();
    		//this.implcitWaitExample();
    	}
    }
    
    
    public void implcitWaitExample(){
    	
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    
    public void explicitWaitExample(String title){
    
    	WebDriverWait wdw = new WebDriverWait(driver,10);
    	
    	wdw.until(ExpectedConditions.titleContains(title));
    	
    	
    }
    
        
    public void closeApplication(){
    	driver.close();
    }
}
