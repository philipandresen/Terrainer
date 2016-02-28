package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/*
 * The Window Object handles all rendering tasks
 * This includes the game view scaling and GUI rendering
 * The render methods of other objects 
 * will be ultimately called through this class, so the
 * Terrainer class only needs to trigger this render method.
 * The reason for this is that the window is handling all the scaling
 * and transformation for the views.
 */

public class Window extends Canvas{
	
	private static final long serialVersionUID = -3002141684976649138L;
	
	public static int windowX = 0, windowY = 0;
	public static int windowWidth, windowHeight;
	public static double viewX=0, viewY=0, viewWidth=1024, viewHeight=768, viewAngle = 0;
	public static double viewWScale, viewHScale;
	public static AffineTransform viewTform = new AffineTransform();
	private static String debugStr = "N/A";
	private static KeyboardInputDetector KID = new KeyboardInputDetector();
	
	public Window(int width, int height, String title){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		windowWidth=width;
		windowHeight=height;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		setBackground(Color.black);
		frame.setVisible(true);
		
		requestFocusInWindow();
		addKeyListener(KID);
	}
	
	public static KeyboardInputDetector getKeyboardInput() {
		return KID;
	}
	
	private Graphics2D transformView(Graphics2D g2) {
		/*
			Sets the graphics transformation for easy drawing of the world.
		Transforms the world view to an arbitrary angle/scale/displacement.
		The transform parameters are set with "changeTransformView" and 
		"SetTransformView".
		*/
		
		viewTform.setToIdentity();
		double scaleFactorWVW = windowWidth/viewWidth; //window to view scaling (wid)
		double scaleFactorWVH = windowHeight/viewHeight; //window to view scaling (Heit)
		
		viewTform.scale(scaleFactorWVW, scaleFactorWVH);
		viewTform.translate(-viewX+viewWidth/2,-viewY+viewHeight/2);
		viewTform.rotate(viewAngle, viewX, viewY);
		g2.setTransform(viewTform);
		return g2;
	}
	
	private Graphics2D unTransformView(Graphics2D g2) {
		//Resets the graphics transformation for easy drawing of a GUI/HUD
		viewTform.setToIdentity();
		g2.setTransform(viewTform);
		return g2;
	}
	
	public static void addTransformView(double x, double y, double w, double h, double a){
		//Changes the user's view in the world relative to what it is now.
		//a = angle in degrees
		viewX+=x;
		viewY+=y;
		viewWidth+=w;
		viewHeight+=h;
		viewAngle+=Math.toRadians(a);
	}

	public static void setTransformView(double x, double y, double w, double h, double a){
		//Sets the user's view in the world (absolutely).
		//a = angle in degrees
		viewX=x;
		viewY=y;
		viewWidth=w;
		viewHeight=h;
		viewAngle=Math.toRadians(a);
	}
	public static void setTransformView(double x, double y){
		//Sets the user's view in the world (absolutely).
		//a = angle in degrees
		viewX=x;
		viewY=y;
	}

	public void setDebugStr(String str){
		debugStr = str;
	}
	
	public void render() {
		
		BufferStrategy buffStrat = getBufferStrategy(); //Check to see if we have a buff strat
		if (buffStrat == null) { //if not
			createBufferStrategy(2); //Create one (2/3 = double/triple buffer)
			return;
		}
		
		Graphics graphWorld = buffStrat.getDrawGraphics();
		Graphics2D graph2DWorld = (Graphics2D) graphWorld;
		
		graph2DWorld.setColor(Color.black);
		graph2DWorld.fillRect(0, 0, windowWidth, windowHeight);
		
		//setTransformView(512,512);
		//addTransformView(0.0,0.0,-0.1,-0.075,0.01);//for testing
		
		graph2DWorld = transformView(graph2DWorld);
		////////////////////////////////////////////////////////
		//Draw world items here (transformed)
		
		for (int i=0; i<=100; i++){
			graph2DWorld.setColor(Color.GRAY);
			graph2DWorld.drawLine(16*i, 0, 16*i, 1600);
		}
		for (int j=0; j<=100; j++){
			graph2DWorld.setColor(Color.GRAY);
			graph2DWorld.drawLine(0, 16*j, 1600, 16*j);
		}
		
		graph2DWorld.setColor(Color.red);
		graph2DWorld.fillRect(50, 50, 100, 150); 
		graph2DWorld.setColor(Color.green);
		graph2DWorld.fillRect(250, 50, 300, 350); 
		
		graph2DWorld.setColor(Color.white);
		graph2DWorld.fillRect((int)(viewX-5), (int)(viewY-5), 5, 5);
		
		//Draw world items above
		////////////////////////////////////////////////////////
		graph2DWorld = unTransformView(graph2DWorld);
		
		//Draw GUI items below
		
		graph2DWorld.setColor(Color.getHSBColor(0, 0, (float)0.1)); 
		graph2DWorld.fillRect(0, 0, windowWidth, 20);
		graph2DWorld.setColor(Color.white);
		graph2DWorld.drawString(debugStr, 20, 14);

		//Draw GUI items above
		
		buffStrat.show();
		graph2DWorld.dispose();
		
	}

}
