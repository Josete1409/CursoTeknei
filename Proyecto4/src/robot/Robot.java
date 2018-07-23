package robot;

import java.io.FileReader;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Robot {

//	private static final String CHROME_BIN = "C:\\Users\\Teknei\\Desktop\\Selenium\\GoogleChromePortable\\App\\Chrome-bin\\chrome.exe";
//	// private static final String PROFILE_CHROME =
//	// "C:\\Users\\Teknei\\Desktop\\cosas\\GoogleChromePortable\\Data\\profile";
	private static Properties properties;

	/*
	 * public static void main(String[] args) throws Exception { // TODO
	 * Auto-generated method stub
	 * 
	 * ChromeOptions options = new ChromeOptions(); options.setBinary(CHROME_BIN);
	 * //options.addArguments("--user-data-dir="+PROFILE_CHROME); //a�adir opciones
	 * al driver como puede ser un profile RemoteWebDriver driver = new
	 * RemoteWebDriver(new URL("http://127.0.0.1:9515"), options);
	 * //driver.get("https://www.pccomponentes.com/");
	 * 
	 * //WebElement elemento =
	 * driver.findElement(By.xpath("//input[@name='query']"));
	 * 
	 * //elemento.sendKeys(args[0]);
	 * 
	 * properties = new Properties(); properties.load(new FileReader(args[0]));
	 * 
	 * int numProd = Integer.parseInt(properties.getProperty("numproducto"));
	 * 
	 * driver.get("https://www.pccomponentes.com/buscar/?query="+args[1]);
	 * 
	 * WebElement elemento = driver.findElement(By.xpath("//a[@data-loop='1']"));
	 * 
	 * //elemento.click(); driver.executeScript("arguments[0].click();", elemento);
	 * 
	 * WebElement añadir=
	 * driver.findElement(By.xpath("//input[@id='article-quantity']"));
	 * añadir.clear(); añadir.sendKeys(""+numProd);
	 * 
	 * WebElement Acarrito=
	 * driver.findElement(By.xpath("//button[@id='add-cart']"));
	 * 
	 * driver.executeScript("arguments[0].click();", Acarrito);
	 * 
	 * WebElement vCarrito= driver.findElement(By.
	 * xpath("//a[@class='c-user-menu__link js-user-cart js-user-menu-link qa-cart-button']"
	 * ));
	 * 
	 * Thread.sleep(5000); vCarrito.click();
	 * 
	 * System.out.println("Fin del programa"); }
	 */

	public void ejecutarRobot(String[] args) throws Exception {
		// the location of chromedriver on your machine
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// This is the location where you have installed Google Chrome on your machine
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

		WebDriver driver = new ChromeDriver();

		properties = new Properties();
		properties.load(new FileReader(args[0]));

		int numProd = Integer.parseInt(properties.getProperty("numproducto"));

		driver.get("https://www.pccomponentes.com/buscar/?query=" + args[1]);

		WebElement elemento = driver.findElement(By.xpath("//a[@data-loop='1']"));

		// elemento.click();
		((RemoteWebDriver) driver).executeScript("arguments[0].click();", elemento);

		WebElement añadir = driver.findElement(By.xpath("//input[@id='article-quantity']"));
		añadir.clear();
		añadir.sendKeys("" + numProd);

		WebElement Acarrito = driver.findElement(By.xpath("//button[@id='add-cart']"));

		((RemoteWebDriver) driver).executeScript("arguments[0].click();", Acarrito);

		WebElement vCarrito = driver
				.findElement(By.xpath("//a[@class='c-user-menu__link js-user-cart js-user-menu-link qa-cart-button']"));

		Thread.sleep(5000);
		vCarrito.click();

		System.out.println("Fin del programa");
	}
}
