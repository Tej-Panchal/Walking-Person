/*
 * Tejkumar Panchal
 * CSC-130-05
 * Final project, one page game
 * This is one page game. In this there is one cartoon character who can move in 
 * all 4 directions and there are 2 objects rose and chicken. which can tells 
 * you message when you press enter and space. And it only appear when you are near to the object
 * and it will disappear when you are out of range.
 */

package Main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import Data.Vector2D;
import Data.spriteInfo;
import Data.Coordinates;
import Data.BoundingBox;
import logic.Control;
import timer.stopWatchX;


public class Main{
	// Fields (Static) below...
	public static Color c = new Color(80,240,120);
	public static boolean isImageDrawn = false;
	public static stopWatchX timer = new stopWatchX(300);
	
	public static ArrayList<spriteInfo> sprites = new ArrayList<>();
	public static int currentSpriteIndex = 0;
	public static HashMap<String, String> map = new HashMap<>();
	public static String trigger = "";
	public static String trigger2 = "";
	private static Coordinates cordinate = new Coordinates(500,200);
	private static Coordinates msgCordinate = new Coordinates(130,100);
	private static String imgName = "stand";
	private static ArrayList<String> directionImages = new ArrayList<String>(Arrays.asList("left","right","up","down"));
	private static ArrayList<BoundingBox> bBoxes = new ArrayList<>();
	private static BoundingBox rose = new BoundingBox(new Coordinates(0,0), new Coordinates(210,130));
	private static BoundingBox nearRose = new BoundingBox(new Coordinates(0,0), new Coordinates(310,230));
	private static BoundingBox chicken = new BoundingBox(new Coordinates(690,290), new Coordinates(1140, 550));
	private static BoundingBox nearChicken = new BoundingBox(new Coordinates(590,190), new Coordinates(1140, 550));
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	
	
	// End Static fields...
	
	public static void main(String[] args) {
		bBoxes.add(new BoundingBox(new Coordinates(0,0), new Coordinates(1280,20))); // top fence
		bBoxes.add(new BoundingBox(new Coordinates(930,0), new Coordinates(1280,550))); // right fence
		bBoxes.add(new BoundingBox(new Coordinates(20,450), new Coordinates(1130,610))); // Bottom fence
		bBoxes.add(new BoundingBox(new Coordinates(0,0), new Coordinates(60,500))); // left fence
		bBoxes.add(rose); // rose box
		bBoxes.add(chicken);
		//bBoxes.add(new BoundingBox(new Coordinates(130,600), new Coordinates(1050,110)));
		//bBoxes.add(new BoundingBox(new Coordinates(820,460), new Coordinates(1100,600)));
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		// TODO: Code your starting conditions here...NOT DRAW CALLS HERE! (no addSprite or drawString)
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		// TODO: This is where you can code! (Starting code below is just to show you how it works)
		ctrl.addSpriteToFrontBuffer(0, 0, "bg");
		ctrl.addSpriteToFrontBuffer(cordinate.x, cordinate.y, imgName);
		ctrl.drawString(msgCordinate.x, msgCordinate.y, trigger ,c);
		ctrl.drawString(130, 60, String.format("%d", cordinate.x) ,c);	
		ctrl.drawString(190, 60, String.format("%d", cordinate.y) ,c);
		// Add a tester sprite to render list by tag (Remove later! Test only!)
		
		//ctrl.drawString(20, 150, "This is a test graphics string!", Color.WHITE);		// Test drawing text on screen where you want (Remove later! Test only!)
	}
	
	public static void TakeAStep(Direction d)
	{
		
		Coordinates newCord = new Coordinates(cordinate);
		
		switch(d)
		{
		case UP:
			newCord.y = newCord.y - 10;			
			break;
		case DOWN:
			newCord.y = newCord.y + 10;
			break;
		case LEFT:
			newCord.x = newCord.x - 10;			
			break;
		case RIGHT:
			newCord.x = newCord.x + 10;
			break;			
		}
		
		//check for collision
		if(IsCollision(newCord) == false)
		{			
			//If valid move then take the step
			cordinate = newCord;
			imgName = directionImages.get(d.ordinal());
			//after 2 seconds change back to stand
		}
		
		
	}
	
	public static boolean IsPointInRectangle(Coordinates newCord, BoundingBox bBox)
	{
		if(newCord.x > bBox.TopLeft.x && newCord.x < bBox.BottomRight.x && newCord.y < bBox.BottomRight.y && newCord.y > bBox.TopLeft.y) 
		{
			return true;
		}
		return false;
	}
	
	public static boolean IsCollision(Coordinates newCord)
	{
		for (int i = 0; i < bBoxes.size(); i++) {
			BoundingBox bBox = bBoxes.get(i);
			
			if(IsPointInRectangle(newCord, bBox)) 
			{
				return true;
			}
        }
		
		return false;
	}
	
	public static void PrintMsg()
	{
		if(IsNearRose())
		{
			Main.trigger = "I am bestfriend of tej, rose. You need to talk with the chicken, press Enter or space!";
			msgCordinate.x = 130;
			msgCordinate.y = 100;
		}
		else if(IsNearChicken())
		{
			Main.trigger = "I am chicken, i gave him food(egs). He is very good man.";
			msgCordinate.x = 690;
			msgCordinate.y = 450;
		}
		else
		{
			Main.trigger = "";
		}
	}
	
	public static boolean IsNearRose()
	{
		if(IsPointInRectangle(cordinate, nearRose)) 
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean IsNearChicken()
	{		
		if(IsPointInRectangle(cordinate, nearChicken)) 
		{
			return true;
		}
		
		return false;
	}
	
	

	// Additional Static methods below...(if needed)
}
