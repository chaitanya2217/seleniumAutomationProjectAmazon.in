package MiniProjects;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.rmi.AccessException;
import java.time.Duration;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.google.common.io.Files;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsStatic;

public class amazonAutomation {
	 static WebDriver driver ;
	 static File f_obj1,f_obj2,f_obj3;
	 
public static void verifyTitle() {
		String actualttl, expectedttl;
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		System.out.println("Verify HomePage Title");
		
		actualttl = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		expectedttl = driver.getTitle();
		if(actualttl.equals(expectedttl))
			System.out.println("Expected & Actual title are match : PASS");
		else 
			System.out.println("Expected & Actual title are not match : Failed");
		driver.quit();
				
		/*expectedttl = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Boolean verifyttl = driver.getTitle().equalsIgnoreCase(expectedttl);
		assertTrue(verifyttl);
		*/
}

public static void dropdownOfSearchFilter() throws IOException
{		
		WebElement verify,verify1;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("Verify DropDown Button of search bar");
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@class=\"nav-search-facade\"]")).click();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		verify = driver.findElement(By.cssSelector("option[value=\"search-alias=appliances\"]"));   //dropdown values
		verify1 = driver.findElement(By.cssSelector("option[value=\"search-alias=baby\"]")); 		 //dropdown values
		if(verify.isDisplayed() || verify1.isDisplayed())
			System.out.println("DropDowm Menu Will be Displayed");
		else {
			System.out.println("DropDowm Menu not Displayed");
			}
		f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f_obj3,new File ("D:\\eclipse_Installation\\output Sreenshots\\dropDownSearch.png"));
		System.out.println("Screenshot captured ");
		
		driver.quit();
}
public static void AddToCart() throws InterruptedException, IOException {
	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		System.out.println("Add one item in cart and verify.");
		//scenario 1 
		driver.get("https://www.amazon.in/");
		System.out.println("Home Page Title : "+driver.getTitle());
	
		//Search box Scenario
		WebElement lable= driver.findElement(By.id("twotabsearchtextbox"));
		lable.sendKeys("apple watch");
		
		driver.findElement(By.id("nav-search-submit-button")).click();   //parent tab
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,650)");
		Thread.sleep(2000);
		
		//select product & view product
		driver.findElement(By.xpath("(//*[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[2]")).click();
		
		// Get handles of all windows
		
		Set<String> allWindowHandles = driver.getWindowHandles();
		String currentWindowHandle = driver.getWindowHandle();

		// Switch to new window
			for (String handle : allWindowHandles) 
			{
			    if (!handle.equals(currentWindowHandle)) 
			    {
			        driver.switchTo().window(handle);
			        
			        System.out.println("Switch window handle to new Window");
			        JavascriptExecutor js2 = (JavascriptExecutor)driver;   //scroll page
			        js2.executeScript("window.scrollBy(0,350)");
					
			        System.out.println("Product open new window title : "+driver.getTitle());
			        
			        // Now in control new window, perform actions
			        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			        driver.findElement(By.xpath("(//*[@value='Add to Cart'])[2]")).click();   //child tab
			       
			        System.out.println("add product in cart");
			        driver.findElement(By.xpath("(//*[@id='attach-sidesheet-view-cart-button'])")).click();
			        // driver.switchTo().window(currentWindowHandle);
			       
			        WebElement subtotal = driver.findElement(By.xpath("(//*[@class='a-button a-button-dropdown quantity'])"));
			        if(subtotal.isDisplayed())
				        {
				        	System.out.println("Product added in cart succesfull");
				        	//driver.close();
				        	Thread.sleep(2000);
				        }
			        //driver.switchTo().window(currentWindowHandle);
			        f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\addToCart.png"));
					System.out.println("Screenshot captured ");
					driver.quit();
			    }
			}		
		}
public static void deleteCartItems() throws InterruptedException, IOException {
	
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	
	System.out.println("delete/Remove Items added in Cart ");
	//scenario 1 
	driver.get("https://www.amazon.in/");
	System.out.println("Home Page Title : "+driver.getTitle());

	//Search box Scenario
	WebElement lable= driver.findElement(By.id("twotabsearchtextbox"));
	lable.sendKeys("Samsung s24+");
	
	driver.findElement(By.id("nav-search-submit-button")).click();   //parent tab
	
	JavascriptExecutor js1 = (JavascriptExecutor)driver;
	js1.executeScript("window.scrollBy(0,650)");
	Thread.sleep(2000);
	
	//select product & view product
	driver.findElement(By.xpath("(//*[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[2]")).click();
	
	// Get handles of all windows
	
	Set<String> allWindowHandles = driver.getWindowHandles();
	String currentWindowHandle = driver.getWindowHandle();

	// Switch to new window
		for (String handle : allWindowHandles) 
		{
		    if (!handle.equals(currentWindowHandle)) 
		    {
		        driver.switchTo().window(handle);
		        
		        System.out.println("Switch window handle to new Window");
		        JavascriptExecutor js2 = (JavascriptExecutor)driver;   //scroll page
		        js2.executeScript("window.scrollBy(0,350)");
				
		        System.out.println("Product open new window title : "+driver.getTitle());
		        
		        // Now in control new window, perform actions
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		        driver.findElement(By.xpath("(//*[@value='Add to Cart'])[2]")).click();   //child tab
		       
		        System.out.println("add product in cart");
		        driver.findElement(By.xpath("(//*[@id='attach-sidesheet-view-cart-button'])")).click();
		        // driver.switchTo().window(currentWindowHandle);
		       
		        WebElement subtotal = driver.findElement(By.xpath("(//*[@class='a-button a-button-dropdown quantity'])"));
		        if(subtotal.isDisplayed())
			        {
			        	System.out.println("Product added in cart succesfull");
			        	//driver.close();			        	
			        }	       
		       
		        Actions a = new Actions(driver);
		        WebElement deletebtn = driver.findElement(By.xpath("(//*[@class=\"a-color-link\"])[1]"));
		        a.moveToElement(deletebtn).click().build().perform();
		        
		    	WebElement empty = driver.findElement(By.xpath("//*[@class=\"a-spacing-mini a-spacing-top-base\"]"));
		    	  		    			    	   
		    	   if(empty.isDisplayed())
		        	 	System.out.println("item removed sucessfull : Pass");
		    	   else
		    		   System.out.println("item not removed : failed");
		      
    	    f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\deleteCartItems.png"));
			System.out.println("Screenshot captured ");	   
			driver.quit();
		    }
		}		
	}

public static void headerFunctions() throws InterruptedException, IOException {
		//header functions testing
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
				
		System.out.println("Verify Header Functions added on the website");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		/*driver.findElement(By.xpath("//*[@href=\"/fresh?ref_=nav_cs_fresh\"]")).click();
		String expectedttl1 = "Amazon.in: Amazon Fresh";
		String actualttl1 = driver.getTitle();
		if(actualttl1.equalsIgnoreCase(expectedttl1)) 
		System.out.println("Fresh: headerfuntion will be opend"); else System.out.println("Fresh: headerfuntion not opend");
		
		driver.navigate().back();
		*/driver.findElement(By.xpath("//*[@href=\"/minitv?ref_=nav_avod_desktop_topnav\"]")).click();
		String expectedttl2 = "Amazon miniTV - Watch Free Web Series, Movies, Short Films & K-Dramas Online";
		String actualttl2 = driver.getTitle();
		if(actualttl2.equalsIgnoreCase(expectedttl2)) 
		System.out.println("AmezonMiniTV: headerfuntion will be opend"); else System.out.println("AmezonMiniTV: headerfuntion not opend");
		
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@href=\"/b/32702023031?node=32702023031&ld=AZINSOANavDesktop_T3&ref_=nav_cs_sell_T3\"]")).click();
		String expectedttl3 = "Amazon.in: : Start Selling on Amazon.in";
		String actualttl3 = driver.getTitle();
		if(actualttl3.equalsIgnoreCase(expectedttl3)) 
		System.out.println("Sell: headerfuntion will be opend"); else System.out.println("Sell: headerfuntion not opend");
		driver.navigate().back();
				
		driver.findElement(By.xpath("//*[@href=\"/gp/bestsellers/?ref_=nav_cs_bestsellers\"]")).click();
		String expectedttl4 = "Amazon.in Bestsellers: The most popular items on Amazon";
		String actualttl4 = driver.getTitle();
		if(actualttl4.equalsIgnoreCase(expectedttl4)) 
		System.out.println("Bestsellers: headerfuntion will be opend"); else System.out.println("Bestsellers: headerfuntion not opend");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@href=\"/mobile-phones/b/?ie=UTF8&node=1389401031&ref_=nav_cs_mobiles\"]")).click();
		String expectedttl5 = "Mobile Phones: Buy New Mobiles Online at Best Prices in India | Buy Cell Phones Online - Amazon.in";
		String actualttl5 = driver.getTitle();
		if(actualttl5.equalsIgnoreCase(expectedttl5)) 
		System.out.println("Mobiles: headerfuntion will be opend"); else System.out.println("Mobiles: headerfuntion not opend");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@href=\"/deals?ref_=nav_cs_gb\"]")).click();
		String expectedttl6 = "Amazon.in - Deals";
		String actualttl6 = driver.getTitle();
		if(actualttl6.equalsIgnoreCase(expectedttl6)) 
		System.out.println("Todays Deals: headerfuntion will be opend"); else System.out.println("Todays Deals: headerfuntion not opend");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@href=\"/gp/browse.html?node=6648217031&ref_=nav_cs_fashion\"]")).click();
		String expectedttl7 = "Amazon Fashion: Clothing, Footwear and Accessories online for Men, Women and Kids";
		String actualttl7 = driver.getTitle();
		if(actualttl7.equalsIgnoreCase(expectedttl7)) 
		System.out.println("Fashion: headerfuntion will be opend"); else System.out.println("Fashion: headerfuntion not opend");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@href=\"/electronics/b/?ie=UTF8&node=976419031&ref_=nav_cs_electronics\"]")).click();
		String expectedttl8 = "Electronics Store: Buy Electronics products Online at Best Prices in India at Amazon.in";
		String actualttl8 = driver.getTitle();
		if(actualttl8.equalsIgnoreCase(expectedttl8)) 
		System.out.println("Electronics: headerfuntion will be opend"); else System.out.println("Electronics: headerfuntion not opend");
		driver.navigate().back();
		
		driver.findElement(By.xpath("//*[@href=\"/prime?ref_=nav_cs_primelink_nonmember\"]")).click();
		String expectedttl9 = "Amazon.in: Amazon Prime - 30 Day FREE Trial";
		String actualttl9= driver.getTitle();
		if(actualttl9.equalsIgnoreCase(expectedttl9)) 
		System.out.println("Prime: headerfuntion will be opend"); else System.out.println("Prime: headerfuntion not opend");
		
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@href=\"/gp/new-releases/?ref_=nav_cs_newreleases\"]")).click();
		String expectedttl10 = "Amazon.in Hot New Releases: The bestselling new and future releases on Amazon";
		String actualttl10 = driver.getTitle();
		if(actualttl10.equalsIgnoreCase(expectedttl10)) 
		System.out.println("New Realese: headerfuntion will be opend"); else System.out.println("New Realese: headerfuntion not opend");
		
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@href=\"/Home-Kitchen/b/?ie=UTF8&node=976442031&ref_=nav_cs_home\"]")).click();
		String expectedttl11 = "Home Store: Buy Home & Kitchen products online at best prices in India - Amazon.in";
		String actualttl11 = driver.getTitle();
		if(actualttl11.equalsIgnoreCase(expectedttl11)) 
		System.out.println("Home & Kitchen: headerfuntion will be opend"); else System.out.println("Home & Kitchen: headerfuntion not opend");
		
		driver.navigate().back();
		driver.findElement(By.xpath("//*[@href=\"/gp/sva/dashboard?ref_=nav_cs_apay\"]")).click();
		String expectedttl12 = "Amazon Pay";
		String actualttl12 = driver.getTitle();
		if(actualttl12.equalsIgnoreCase(expectedttl12)) 
		System.out.println("Amazon Pay: headerfuntion will be opend"); else System.out.println("Amazon Pay: headerfuntion not opend");
		
		driver.findElement(By.xpath("//*[@href=\"/gp/help/customer/display.html?nodeId=200507590&ref_=nav_cs_help\"]")).click();
		String expectedttl13 = "Help - Amazon Customer Service";
		String actualttl13 = driver.getTitle();
		if(actualttl13.equalsIgnoreCase(expectedttl13)) 
		System.out.println("Customer Service: headerfuntion will be opend"); else System.out.println("Customer Service: headerfuntion not opend");
		
		driver.findElement(By.xpath("//*[@href=\"/computers-and-accessories/b/?ie=UTF8&node=976392031&ref_=nav_cs_pc\"]")).click();
		String expectedttl14 = "Computers & Accessories: Buy Computers & Accessories Online at Low Prices in India - Amazon.in";
		String actualttl14 = driver.getTitle();
		if(actualttl14.equalsIgnoreCase(expectedttl14)) 
		System.out.println("Computers: headerfuntion will be opend"); else System.out.println("Computers: headerfuntion not opend");
		
		driver.findElement(By.xpath("//*[@href=\"/Books/b/?ie=UTF8&node=976389031&ref_=nav_cs_books\"]")).click();
		String expectedttl15 = "Book Store Online : Buy Books Online at Best Prices in India | Books Shopping @ Amazon.in";
		String actualttl15 = driver.getTitle();
		if(actualttl7.equalsIgnoreCase(expectedttl7)) 
		System.out.println("Books: headerfuntion will be opend"); else System.out.println("Books: headerfuntion not opend");
		
		driver.findElement(By.xpath("//*[@href=\"/Car-Motorbike-Store/b/?ie=UTF8&node=4772060031&ref_=nav_cs_automotive\"]")).click();
		String expectedttl16 = "Car & Motorbike: Buy Car & Motorbike Online at Best Prices in India-Amazon.in";
		String actualttl16 = driver.getTitle();
		if(actualttl16.equalsIgnoreCase(expectedttl16)) 
		System.out.println("Cars & MotoerBike: headerfuntion will be opend"); else System.out.println("Cars & MotoerBike: headerfuntion not opend");
		
				
		f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\headerFunctionsChecked.png"));
		System.out.println("Screenshot captured ");	   
		
		driver.quit();
}
	
public static void subHeaders_TodaysDeals() throws InterruptedException, IOException
{
	driver = new ChromeDriver();
	driver.get("https://www.amazon.in/");
	driver.manage().window().maximize();
	
	System.out.println("Verify SubHeader functions of TodaysDeal");
	driver.findElement(By.xpath("//*[@href=\"/deals?ref_=nav_cs_gb\"] ")).click();
		
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.xpath("//*[@href=\"/gp/goldbox/?ie=UTF8&ref_=topnav_storetab_gb\"]")).click();
	String expectedtittle = "Amazon.in - Deals";
	String actualtittle = "Amazon.in - Deals" ;
	if(expectedtittle.equalsIgnoreCase(actualtittle)) {System.out.println("Todays Deal: Subheader will be opend");}
	
	driver.findElement(By.xpath("//*[@href=\"/gp/goldbox/all-deals/?ie=UTF8&ref_=sv_gb_1\"]")).click();
	System.out.println(driver.getTitle());
	String expectedtittle1 = "Amazon.in - Deals";
	String actualtittle1 = "Amazon.in - Deals" ;
	if(expectedtittle1.equalsIgnoreCase(actualtittle1)) {System.out.println("All Deals: Subheader will be opend");}
	
	driver.findElement(By.xpath("//*[@href=\"/gp/goldbox/watching/?ie=UTF8&ref_=sv_gb_2\"]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@href=\"/subscribe-save-products/b/?ie=UTF8&node=5728645031&ref_=sv_gb_3\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/coupons/?_encoding=UTF8&ref_=sv_gb_4\"]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@href=\"/b/?ie=UTF8&node=14284467031&ref_=sv_gb_5\"]")).click();
	driver.navigate().back();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@href=\"/b/?ie=UTF8&node=14639407031&ref_=sv_gb_6\"]")).click();
	
	f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\subheadersTodaysDealsChecked.png"));
	System.out.println("Screenshot captured ");	   
	
	driver.quit();	
}

public static void subfunctions_AmazonPay() throws InterruptedException, IOException
{
	driver = new ChromeDriver();
	driver.get("https://www.amazon.in/");
	driver.manage().window().maximize();
	
	System.out.println("Verify SubHeader functions of AmazonPay");
	driver.findElement(By.xpath("//*[@href=\"/gp/sva/dashboard?ref_=nav_cs_apay\"]")).click();
	
	WebElement transcations = driver.findElement(By.xpath("//*[@href=\"/pay/history?ref_=apay_deskhome_ViewStatement\"]"));
	transcations.click();
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/b?node=21102071031&ref_=apay_deskhome_YourRewards\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/b?node=26163663031&ref_=apay_deskhome_Covid\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/b?ie=UTF8&node=14072630031&ref_=apay_deskhome_APayEMI\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/cpe/managepaymentmethods?ref_=apay_deskhome_SavedCards\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/gp/help/customer/display.html?nodeId=202123450&ref_=apay_deskhome_Help\"]")).click();
	Thread.sleep(1000);
	driver.navigate().back();
	driver.findElement(By.xpath("//*[@href=\"/hfc/mobileRecharge?ref_=apay_deskhome_MobileRecharge\"]")).click();
	Thread.sleep(1000);
	
	f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\subheadersAmazonPayChecked.png"));
	System.out.println("Screenshot captured ");	   
	
	driver.quit();
}

public static void logIn() throws InterruptedException, IOException {
	WebElement loginbtn, loginid, psw, submitcode;
		
	driver = new ChromeDriver();
	driver.get("https://www.amazon.in/");
	driver.manage().window().maximize();
	System.out.println("Verify Login Functionality With valid Data");
	
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	loginbtn = driver.findElement(By.xpath("//div[@class=\"nav-line-1-container\"]"));
	
	Actions a = new Actions(driver);
	a.moveToElement(loginbtn).perform();
	a.moveToElement(driver.findElement(By.xpath("//a[@class=\"nav-action-signin-button\"]"))).click().perform();
		
	loginid = driver.findElement(By.name("email"));
	loginid.click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	loginid.sendKeys("7");Thread.sleep(1000);
	loginid.sendKeys("498927664");
	System.out.println("User Id Entered");
	driver.findElement(By.className("a-button-input")).click();
	
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	psw = driver.findElement(By.id("ap_password"));
	psw.sendKeys("@Chaitanya@#$%");
	System.out.println("Password Enterd" );
	System.out.println("if Submit Code/OTP window will be open then login Succesfull");
	driver.findElement(By.id("auth-signin-button")).click();
	
	submitcode = driver.findElement(By.xpath("//*[@id=\"cvf-submit-otp-button\"]"));
	if(submitcode.isDisplayed() || submitcode.isEnabled()) {
		System.out.println("User Login Succesfull");		
		Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\LoginSuccess.png"));
		System.out.println("Screenshot captured ");	
		}
	else {
		System.out.println("User Login Unsuccesfull");}
	driver.quit();
		
}

public static void searchTextField() throws InterruptedException, IOException, WebDriverException
{
	WebElement searchTxt,searchBtn, noOutput,noOutput1;
		
	driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.amazon.in/");
	
	System.out.println("Verify Search Box Using Valid & Invalid data");
	searchTxt = driver.findElement(By.id("twotabsearchtextbox"));
	searchBtn = driver.findElement(By.id("nav-search-submit-button"));
	
	searchTxt.sendKeys("caavbbdzb");
	System.out.println("Send Invalid Characters");
	searchBtn.click();
	
	noOutput = driver.findElement(By.xpath("(//*[@class=\"sg-col-inner\"])[2]"));
	if(noOutput.isDisplayed())
		System.out.println("No Result Found : Pass");
	
	f_obj1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Files.copy(f_obj1,new File ("D:\\eclipse_Installation\\output Sreenshots\\serachbox1.png"));
	System.out.println("Screenshot captured ");	

	driver.navigate().back();
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("If you're choosing the search option to shop for a product then enter your key words into the search box "
			+ "on Amazon.in. By default we will show you \"Featured\" results, which take into account a variety of factors including particularly, customer actions "
			+ "(such as how frequently an item was searched for, clicked on, added to cart or purchased) and information about the item (such as title, price, and "
			+ "description). We also consider factors such as availability, delivery speed, costs (such as shipping costs), and whether we think the item will be of "
			+ "interest (such as new items).");
	driver.findElement(By.id("nav-search-submit-button")).click();
	System.out.println("Send Paragraph");
	noOutput1 = driver.findElement(By.xpath("(//*[@class=\"sg-col-inner\"])[2]"));
	if(noOutput1.isDisplayed())
		System.out.println("No Result Found : Pass");
	f_obj2=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Files.copy(f_obj2,new File  ("D:\\eclipse_Installation\\output Sreenshots\\serachbox2.png"));
	System.out.println("Screenshot captured ");	
		
	driver.navigate().back();
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("123121215454.4464465");
	driver.findElement(By.id("nav-search-submit-button")).click();
	System.out.println("Send integer values or numbers");
	noOutput1 = driver.findElement(By.xpath("(//*[@class=\"sg-col-inner\"])[2]"));
	if(noOutput1.isDisplayed())
		System.out.println("No Result Found : Pass");
	f_obj3=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Files.copy(f_obj3,new File ("D:\\eclipse_Installation\\output Sreenshots\\serachbox3.png"));
	System.out.println("Screenshot captured ");	

	driver.quit();
}


public static void main(String[] args) throws IOException
{
			
	System.setProperty("webdriver.chrome.driver","D:\\ChormeDriver\\chromedriver-win32\\chromedriver.exe");
	amazonAutomation m1 = new amazonAutomation ();
	
			try {
				m1.verifyTitle();
				System.out.print("PASS");
			}
			catch(Exception ae)
			{
				System.out.println("FAILED!!!");
				driver.quit();
			}			
			try {
				m1.dropdownOfSearchFilter();
				System.out.print("PASS");
			}
			catch(Exception ae)
			{
				System.out.println("FAILED!!!"+ae);
				driver.quit();
			}
			try{ 
				m1.AddToCart(); 
				System.out.println("PASS");
			}
			catch(Exception ae) {
				System.out.println("FAILED!!!");
				driver.quit();
			}	
			try{
				m1.deleteCartItems();
				System.out.print("PASS");
			}
			catch(Exception ae){
				System.out.println("FAILED!!!");
				//driver.quit();
			}
			try{ 
				m1.subHeaders_TodaysDeals(); 
				System.out.print("PASS");
			}
			catch(Exception ae) {
				System.out.println("FAILED!!!");
				driver.quit();
			}
			try{ 
				m1.subfunctions_AmazonPay(); 
				System.out.println("PASS");
			}
			catch(Exception ae) {
				System.out.println("FAILED!!!");
				driver.quit();
			}
			try{ 
				m1.logIn();
				System.out.println("PASS");
			}
			catch(Exception ae) {
				System.out.println("FAILED!!!");
				driver.quit();
			}			
			try{ 
				m1.searchTextField();
				System.out.println("PASS");
			}
			catch(Exception ae) {
				System.out.println("FAILED!!!");
				//driver.quit();
			}			
			try{
				m1.headerFunctions();
				System.out.print("PASS");
			}
			catch(Exception ae){
				System.out.println("FAILED!!!");
				driver.quit();
			}
	}
}
