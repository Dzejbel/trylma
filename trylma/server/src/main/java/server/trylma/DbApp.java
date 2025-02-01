package server.trylma;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import server.trylma.components.GameEngine;
import server.trylma.components.ReplayEngine;

@SpringBootApplication
public class DbApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DbApp.class, args);
		GameEngine game = context.getBean(GameEngine.class);
		ReplayEngine replay = context.getBean(ReplayEngine.class);
		
		try {
			int port, maxCapacity; 

			try { port = Integer.parseInt(args[0]); } 
			catch(NumberFormatException e) { throw new IllegalArgumentException("ServerApp.main: invalid port"); }

			try {maxCapacity = Integer.parseInt(args[1]);} 
			catch(NumberFormatException e) { throw new IllegalArgumentException("ServerApp.main: invalid capacity"); }

			// Sprawdzenie poprawności wariantu

			if (!args[2].matches("r|c|o"))
				throw new IllegalArgumentException("SeverApp.main: wrong variant (cmd line arg at idx 2)");

			char variant = args[2].charAt(0);
			new ServerApp(port, maxCapacity, variant, game, replay);
		} 
		catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
		catch (IndexOutOfBoundsException e) { System.out.println("ServerApp.main: invalid number of arguments"); }
		catch (IOException e) { System.out.println(e.getMessage()); }
	}
}
