package gameObjects;

import main.*;
import java.awt.event.KeyEvent;

public class TemporaryViewController {
	
	private static KeyboardInputDetector KID;
	
	public TemporaryViewController() {
		KID = Window.getKeyboardInput();
	}
	
	public static void tick(){
		if (KID.isPressed(KeyEvent.VK_UP)) {
			Window.addTransformView(0,-1,0,0,0);
		}
		if (KID.isPressed(KeyEvent.VK_DOWN)) {
			Window.addTransformView(0,1,0,0,0);
		}
		if (KID.isPressed(KeyEvent.VK_LEFT)) {
			Window.addTransformView(-1,0,0,0,0);
		}
		if (KID.isPressed(KeyEvent.VK_RIGHT)) {
			Window.addTransformView(1,0,0,0,0);
		}
		if (KID.isPressed(KeyEvent.VK_PAGE_UP)) {
			Window.addTransformView(0,0,0,0,0.5);
		}
		if (KID.isPressed(KeyEvent.VK_PAGE_DOWN)) {
			Window.addTransformView(0,0,0,0,-0.5);
		}
		if (KID.isPressed(KeyEvent.VK_ESCAPE)) {
			Terrainer.abort();
		}
	}

}
