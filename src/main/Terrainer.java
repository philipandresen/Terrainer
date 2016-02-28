package main;

import gameObjects.TemporaryViewController;


public class Terrainer {
	

	private static boolean running;
	private static Window mainWindow = new Window(1024,768,"Terrainer");
	private static TemporaryViewController TVC = new TemporaryViewController();

	public static void main(String[] args) {
		
		running=true;
		while (running){
			run();
		}
	}
	
	public static void run(){
		long timeStamp = System.nanoTime();
		double targetTickRate = 60.0; //How many times per second to run "tick"
		double tickTimeNS = 1000000000 / targetTickRate;
		double deltaT = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		long now;
		while (running){
			now = System.nanoTime();
			deltaT += (now - timeStamp) / tickTimeNS;
			timeStamp = now;
			while(deltaT >= 1){
				tick();
				deltaT--;
			}
			render();
			frames++;
			
			//print frame rate to the console
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames);
				mainWindow.setDebugStr("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	public static void tick() {
		TVC.tick();
		
	}
	
	public static void render() {
		mainWindow.render();
	}
	
	public static void abort() {
		running=false;
		System.exit(0);
	}

}
