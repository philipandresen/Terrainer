package collisions;

import java.util.ArrayList;


public class QuadtreeNode {
	QuadtreeNode children[];
	double x, y, width, height, minSize;
	int maxContained;
	ArrayList<QuadtreeObject> myObjectList;
	
	QuadtreeNode(ArrayList<QuadtreeObject> objects, double newX, double newY, double newWidth, double newHeight, int newMaxSize, double newMinSize) {
		x=newX;
		y=newY;
		width=newWidth;
		height=newHeight;
		maxContained = newMaxSize;
		minSize = newMinSize;
		myObjectList = objects;
		
		//When contructed, take the list of objects given and determine if they
		//are too numerous. If they are not, fine. If they are, split them into
		//Quadrants and distribute them to children.
		//But don't split if you are already smaller than a certain size.
		
		if ((objects.size()<maxContained) || ((width+height)/2 <= minSize)){
			myObjectList = objects;
		} 
		else {
			//If we need to split, this is how...
			
			
		}
		
		
	}
	
	public ArrayList<QuadtreeObject> getObjectList(){
		
		return null;
	}
}
