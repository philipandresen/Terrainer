package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardInputDetector extends KeyAdapter{
	
	//Keys that are pressed
	private ArrayList<Integer> KeyList = new ArrayList<Integer>(); 
	//Keys we know are pressed and are waiting to release (ignoring keyboard repeat)
	private ArrayList<Integer> IgnoreList = new ArrayList<Integer>(); 
	
	//Invoked when a key has been pressed. (KeyAdapter)
	public void keyPressed(KeyEvent e){ 
		int key = e.getKeyCode();
		addKey(key, KeyList);
	}
	
	//Invoked when a key has been released. (KeyAdapter)
	public void keyReleased(KeyEvent e){ 
		int key = e.getKeyCode();
		removeKey(key, KeyList);
		removeKey(key, IgnoreList);
	}
	
	//add a key to the specified list
	private boolean addKey(int key, ArrayList<Integer> list){ 
		for(int i = 0; i < list.size(); i++){
			//if key is already in list, don't add.
			if (list.get(i) == key){
				return false;
			}
		}

		list.add(key);
		return true;
		
	}
	
	//remove a key from the specified list
	private boolean removeKey(int key, ArrayList<Integer> list){ 
		for(int i = 0; i < list.size(); i++){
			//if key is in list
			if (list.get(i) == key){
				//remove and return true
				list.remove(i);
				return true;
			}
		}
		
		return false;
	}

	//return the list of keys pressed
	public ArrayList<Integer> getKeyList(){
		return KeyList;
	}
	
	//return if key is being held down
	public boolean isPressed(int key){ 
		for(int i = 0; i < KeyList.size(); i++){
			if (KeyList.get(i) == key){
				return true;
			}
		}
		return false;
	}

	//Return true only if this is the first press event detected.
	public boolean justPressed(int key){
		//Ignored?
		for(int i = 0; i < IgnoreList.size(); i++){
			if(IgnoreList.get(i) == key){
				return false;
			}
		}
		
		//Not ignored, pressed?
		if(isPressed(key)){
			//ignore until released
			addKey(key, IgnoreList); 
			return true;
		}
		
		//not recently pressed.
		return false;
	}
		
}
