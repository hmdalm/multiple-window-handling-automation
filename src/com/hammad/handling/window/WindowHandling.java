package com.hammad.handling.window;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {
	private static WebDriver driver = null;

	@BeforeClass
	public void launch_Website_guru99() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.navigate().to("https://demo.guru99.com/popup.php");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void click() throws Exception {
		driver.findElement(By.xpath("/html/body/p/a")).click();
		String MainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext())
			;
		{
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid"))
						.sendKeys("hmd123@gmail.com");

				driver.findElement(By.name("btnLogin")).click();
				Thread.sleep(3000);
				driver.close();
			}

		}
		driver.switchTo().window(MainWindow);

	}

	@AfterClass
	public void closed_window() throws Exception {
		Thread.sleep(3000);
		driver.close();
	}

}
