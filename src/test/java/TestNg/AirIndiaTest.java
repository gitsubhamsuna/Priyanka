package TestNg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AirIndiaTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.airindia.com/");
		driver.findElement(By.id("From")).sendKeys("BLR",Keys.TAB);
		driver.findElement(By.id("To")).sendKeys("BBI",Keys.TAB);
		driver.findElement(By.cssSelector("input[id='mat-input-3']+input")).click();
		List<WebElement> ele = driver.findElements(By.xpath("(//div[@class='ngb-dp-month ng-star-inserted']/div)[position() >2]"));
//		List<WebElement> ele = driver.findElements(By.xpath("(//div[@class='ngb-dp-month ng-star-inserted']/div)"));
		boolean flag=true;
		String month="September 2024";
		int from_date=21;
		int to_date=22;
		while(flag) {
			try {
				for(WebElement e:ele) {
					if(e.getText().contains(month)) {
						driver.findElement(By.xpath("//div[contains(text(),'"+e.getText()+"')]/following-sibling::ngb-datepicker-month//span[contains(text(),'"+from_date+"')]")).click();
						flag=false;
					}
					else {
						driver.findElement(By.cssSelector("[title='Next month']")).click();
					}
				}
				
			}catch(Exception e) {
				continue;
			}
		}
		driver.findElement(By.cssSelector("input[id='mat-input-14']+input")).click();
		Thread.sleep(2000);
		while(flag) {
			try {
				for(WebElement e:ele) {
					if(e.getText().contains(month)) {
						driver.findElement(By.xpath("//div[contains(text(),'"+e.getText()+"')]/following-sibling::ngb-datepicker-month//span[contains(text(),'"+to_date+"')]")).click();
						
						flag=false;
					}
					else {
						driver.findElement(By.cssSelector("[title='Next month']")).click();
					}
				}
			}catch(Exception e) {
				continue;
			}
		}
//		driver.quit();
	}
}
