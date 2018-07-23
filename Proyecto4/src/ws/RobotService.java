package ws;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import robot.Robot;

@RestController
public class RobotService {
	@GetMapping(path="/aniadirCarrito")
	@ResponseBody
public ResponseEntity<String> aniadirCarrito(@RequestParam("busqueda") String cadenaBusqueda) throws Exception{
	try {
		Robot robot = new Robot();
		
		robot.ejecutarRobot(new String[] {"Resources\\Clave",cadenaBusqueda});
		return ResponseEntity.ok().body("TODO aniadir al carrito el primer producto que salga por '"+cadenaBusqueda+"");
	}
	
	catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo no fue bien :( " + e);
	}
	
	finally {
	}
}

}
