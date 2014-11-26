package net.bucktower.drawcessing;

import processing.core.PApplet;


public class Drawcessing extends PApplet {

	
	//sidebar
	float previousX, previousY, previousWidth;

	//default draw mode
	String drawMode = "None";
	int myHeight = 700;
	
	//setting up the slots
	int[] slot1 = { 0, 0, myHeight/10, 50 };
	int[] slot2 = { myHeight/10, 0, myHeight/10*2, 50 };
	int[] slot3 = { myHeight/10*2, 0, myHeight/10*3, 50 };
	int[] slot4 = { myHeight/10*3, 0, myHeight/10*4, 50 };
	int[] slot5 = { myHeight/10*4, 0, myHeight/10*5, 50 };
	int[] slot6 = { myHeight/10*5, 0, myHeight/10*6, 50 };
	int[] slot7 = { myHeight/10*6, 0, myHeight/10*7, 50 };
	int[] slot8 = { myHeight/10*7, 0, myHeight/10*8, 50 };
	int[] slot9 = { myHeight/10*8, 0, myHeight/10*9, 50 };
	int[] slot10 = { myHeight/10*9, 0, myHeight, 50 };

	//active slot
	int slotActive = 0;
	
	//background
	int backgroundColor = color(244,241,237);
	
	public void setup() {
		SketchObject.setApp(this);
		
		background(backgroundColor);
		size(1280,myHeight);

		previousWidth = 0;

		frameRate(250);

		noStroke();
		createSideBar(50,7,0,false);
		refreshModeDisplay();
	}

	public void draw() {
	}
	
	public void mouseDragged(){
		boolean inBounds = false;

		if(mouseX > 57 && !(mouseX > width-180 && mouseY < 20)){
		  inBounds = true;
		}

		if(inBounds == true){
		  if(drawMode == "Ink"){
		    fill(255,255,0, 15);
		    ellipse(mouseX,mouseY,20,20);
		  }
		  
		  if(drawMode == "Storytime"){
			  
		  }
		  
		  if(drawMode == "Eraser"){
		    fill(backgroundColor);
		    ellipse(mouseX,mouseY,50,50);
		  }
		}
		}

		public void mouseClicked(){

		}

		public void mouseReleased(){
		//println("Mouse released. MX = "+mouseX+"\tsl1[3] = "+slot1[3]+"\tMY = "+mouseY+"\tsl1[2] = "+slot1[2]);
		//1ST BUTTON

		boolean inBounds = false;

		if(mouseX > 57 && !(mouseX > width-180 && mouseY < 20)){
		  inBounds = true;
		}

		if (inBounds){
		  if(drawMode == "Swirl"){
			  
		  }
		}

		//1ST BUTTON
		if(mouseX <= 57){
		  if(mouseX <= slot1[3] && mouseY <= slot1[2]){
		    drawMode = "Ink";
		    fill(0);
		    slotActive = 1;
		  }
		  //2ND BUTTON
		  if(mouseX >= slot2[1] && mouseX <= slot2[3] && mouseY >= slot2[0] && mouseY <= slot2[2]){
		    drawMode = "Eraser";
		    slotActive = 2;
		  }
		  //3RD BUTTON
		  if(mouseX >= slot3[1] && mouseX <= slot3[3] && mouseY >= slot3[0] && mouseY <= slot3[2]){
		    drawMode = "Storytime";
		    slotActive = 3;
		  }
		  //4TH BUTTON
		  if(mouseX >= slot4[1] && mouseX <= slot4[3] && mouseY >= slot4[0] && mouseY <= slot4[2]){
		    drawMode = "Swirl";
		    slotActive = 4;
		  }
		  //5TH BUTTON
		  if(mouseX >= slot5[1] && mouseX <= slot5[3] && mouseY >= slot5[0] && mouseY <= slot5[2]){
		    slotActive = 5;
		  }
		  //6TH BUTTON
		  if(mouseX >= slot6[1] && mouseX <= slot6[3] && mouseY >= slot6[0] && mouseY <= slot6[2]){
		    slotActive = 6;
		  }
		  //7TH BUTTON
		  if(mouseX >= slot7[1] && mouseX <= slot7[3] && mouseY >= slot7[0] && mouseY <= slot7[2]){
		    println(drawMode);
		    slotActive = 7;
		  }
		  if(slotActive != 0){
		    createSideBar(50,7,slotActive,true);
		  }
		  refreshModeDisplay();
		  println("Active tab: " + drawMode);
		}
		}

		void createSideBar(int blueBandWidth, int whiteBandWidth, int slotSelected, boolean onlyWhiteStripe){

		if(onlyWhiteStripe == false){
		  fill(30,109,214);
		    rect(0,0,blueBandWidth,height);
		  for(int barHeight = 1; barHeight < 10; barHeight++){
		    fill(24,87,171);
		    rect(0,height/10*barHeight,blueBandWidth,2);
		    //System.out.println(height/10*barHeight);
		    
		    //automated labeling...fancy :)
		    String tabLabel;
		    boolean isValidTab = true;
		    
		    switch(barHeight){
		      case 1: tabLabel = "Mark";
		              break;
		      case 2: tabLabel = "Erase";
		              break;
		      case 3: tabLabel = "Story";
		              break;
		      case 4: tabLabel = "Swirl";
		              break;
		      case 5: tabLabel = "";
		              break;
		      case 6: tabLabel = "";
		              break;
		      case 7: tabLabel = "";
		              break;
		      default: tabLabel = "ERROR: See log";
		               println("Can't find a label for tab #" + barHeight);
		               isValidTab = false;
		               break;
		    }
		    if(isValidTab){
		      fill(255);
		      text(tabLabel, 5, ((height/10*barHeight) + (height/10*(barHeight-1)))/2); 
		    }
		  }
		}

		fill(255);
		rect(blueBandWidth,0,whiteBandWidth,height);
		  
		if(slotSelected > 0){
		  fill(0);
		  rect(50,height/10*(slotSelected-1),7,height/10);
		}
		}

		void refreshModeDisplay(){
		fill(255);
		rect(width-180, 0, 180, 20);
		fill(0);
		text("Draw Mode: " + drawMode, width-175, 15);
		}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { net.bucktower.drawcessing.Drawcessing.class.getName() });
	}
}