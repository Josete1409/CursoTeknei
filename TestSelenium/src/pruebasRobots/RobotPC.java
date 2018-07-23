package pruebasRobots;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Sleeper;

public class RobotPC {

	private Properties properties;

	public void ejecutarRobot(String[] args) throws Exception {
		// the location of chromedriver on your machine
		Properties properties = new Properties();
		properties.load(new FileReader(args[0]));
		String buscarNum = properties.getProperty("numero");
		//String email = properties.getProperty("email");

		// the location of chromedriver on your machine
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// This is the location where you have installed Google Chrome on your machine
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.pccomponentes.com/buscar/?query=" + args[1]);

		WebElement producto = driver.findElement(
				By.xpath("//body[@id='resultados-busqueda']/div[@id='contenedor-principal']/div[@id='main']/"
						+ "div[@class='container m-t-1 base']/div[@class='row']/div[@class='col-xs-12 col-xl-9']/div[@class='white-card']/div[@id='articleListContent']"
						+ "/div[@class='row page-0']/div[1]/article[1]/div[1]/a[1]"));
		producto.click();

//		WebElement stock = driver.findElement(By.xpath("//div[@id='btnsWishAddBuy']//button[@id='notify-me']"));
//		if (stock.findElement(By.id("notify-me")).equals(stock)) {
//
//			avisarStock(driver, email);
//			
//		} else {
//			
//			anadirCarrito(driver, buscarNum);
//
//		}
		
		anadirCarrito(driver, buscarNum);
		
	}
	
	private void avisarStock(WebDriver driver, String email) {
		// TODO Auto-generated method stub
	
		WebElement btnAvisame = driver.findElement(By.xpath("//div[@id='btnsWishAddBuy']//button[@id='notify-me']"));
		btnAvisame.click();
		
		WebElement textEmail = driver.findElement(By.xpath("//input[@id='Notify_email']"));
		textEmail.clear();
		textEmail.sendKeys(email);
		
		WebElement btnEnviar = driver.findElement(By.xpath("//button[@type='submit']"));
		btnEnviar.click();
		
	}

	private void anadirCarrito(WebDriver driver, String buscarNum) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement numero = driver.findElement(
				By.xpath("//button[@type='button']//i[contains(@class,'pccom-icon')][contains(text(),'H')]"));
		int num = Integer.parseInt(buscarNum);

//		WebElement cantidad = driver.findElement(By.xpath("//input[@id='article-quantity']"));
//		cantidad.clear();
//		cantidad.sendKeys(buscarNum);

		for (int i = 0; i < num; i++) {
			numero.click();
		}
		Thread.sleep(5000);
		WebElement carrito = driver.findElement(By.xpath("//button[@id='add-cart']"));
		carrito.click();

//		WebElement buscador = driver.findElement(By.xpath("//input[@id='query']"));
//		buscador.click();
		//
//		buscador.sendKeys(args[0]);
	}

}
