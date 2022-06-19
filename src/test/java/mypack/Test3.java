package mypack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3
{
	public static void main(String[] args) throws Exception
	{
		//Open browser(SWD)
		WebDriverManager.chromedriver().setup();
		RemoteWebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//Launch site(SWD)
		driver.get("http://www.amazon.com"); //SUT
		Thread.sleep(5000);
		//Goto registration(SWD)
		WebElement e=driver.findElement(By.xpath("//span[text()='Hello, Sign in']"));
		Actions a=new Actions(driver);
		a.moveToElement(e).perform();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Start here.")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("customerName")).sendKeys("kalamsirbtach");
		driver.findElement(By.name("email")).sendKeys("2722826818"); //virtual contact
		driver.findElement(By.name("password")).sendKeys("kalamsirindia143");
		driver.findElement(By.name("passwordCheck")).sendKeys("kalamsirindia143");
		driver.findElement(By.id("continue")).click();
		Thread.sleep(50000); //to solve puzzle manually
		//Get OTP received from Amazon(Twilio code)
		String asid="ACc417fc5ea68009fd2301efd7846278a3"; //constant
		String auth="744394a204a62b97c5a500c17a1f5c33"; //constant
		Twilio.init(asid,auth); //connect to twilio cloud
        ResourceSet<Message> ms=Message.reader().read();//collect all messages(sequentially)
        String temp=ms.iterator().next().getBody(); //goto 1st(latest) message body
        String[] pieces=temp.split(" "); //split that body using blank space into pieces
        String otp=pieces[0]; //1st piece is otp number received from Amazon
        //Enter OTP in page element(SWD)
        driver.findElement(By.id("auth-pv-enter-code")).sendKeys(otp);    
	}
}






