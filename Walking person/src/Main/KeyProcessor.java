/* This will handle the "Hot Key" system. */

package Main;

import Main.Main.Direction;
import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(50);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		/* TODO: You can modify values below here! */
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
			
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		case '$':
			Main.PrintMsg();
			break;
		case '=':
			Main.PrintMsg();
			break;
		case 'w':
			Main.TakeAStep(Direction.UP); 
			break;
		case 'a':
			Main.TakeAStep(Direction.LEFT); 
			break;
		case 'd':
			Main.TakeAStep(Direction.RIGHT); 
			break;
		case 's':
			Main.TakeAStep(Direction.DOWN); 
			break;
		}
	}
}