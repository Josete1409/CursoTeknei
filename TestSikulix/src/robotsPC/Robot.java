package robotsPC;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class Robot {

	private static final String RUTA_IMAGENES = ("resources\\images");
	
	public static void main(String[] args) throws FileNotFoundException, IOException, FindFailed {
		// TODO Auto-generated method stub

//		Properties properties = new Properties();
//		properties.load(new FileReader(args[0]));
		String busqueda = args[0];
		String buscarNum = args[1];
		
		int num = Integer.parseInt(buscarNum);

		// the location of chromedriver on your machine
		System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// This is the location where you have installed Google Chrome on your machine
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.pccomponentes.com/");
		
		ImagePath.add(RUTA_IMAGENES);
		
		Screen screen = Screen.getPrimaryScreen();
		Match match = screen.find("busqueda.png");
		match.click("busqueda.png");
		screen.type(busqueda);
		
		match = screen.find("disposicionResultados.PNG");
	    Region region = match.offset(20, 60);
	    region.click();

		match = screen.find("mas.png");
		for(int i=0; i<num; i++) {
			screen.click();
		}
		
		match = screen.find("anadir.png");
		match.click("anadir.png");
		
		match = screen.find("carrito.png");
		screen.click("carrito.png");
		
	}

}
