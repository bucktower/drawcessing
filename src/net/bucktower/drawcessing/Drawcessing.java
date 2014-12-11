package net.bucktower.drawcessing;

import processing.core.PApplet;


public class Drawcessing extends PApplet {

	
	//sidebar
	float previousX, previousY, previousWidth;

	//default draw mode
	String drawMode = "None";
	int myHeight = 700;
	
	//cross-draw-mode variables
	int activeColor = color(0);
	int activeSize = 0;
	int maxSize = 80;
	
	//ink
	int inkSize = 20;
	
	//eraser
	int eraserSize = 50;
	
	//rect
	int rectSize = 20;
	
	//smile
	//ImageEditor smile;
	
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
	
	//tr toolbox
	int numRows = 3;
	int rowMargin = 5;
	int rowHeight = 15;
	int toolBoxWidth = 175;
	
	//sidebar
	int sideBandWidth = 50;
	int borderBandWidth = 7;
	
	public void setup() {
		SketchObject.setApp(this);
		
		//ImageEditor smile = new ImageEditor("net/bucktower/drawcessing/data/smiley.png");
		
		background(backgroundColor);
		size(1280,myHeight);

		previousWidth = 0;

		frameRate(250);

		noStroke();
		createSideBar(sideBandWidth,borderBandWidth,0,false);
		refreshModeDisplay();
	}

	public void draw() {
	}
	
	public void mouseDragged(){
			if(mouseX-activeSize > sideBandWidth+borderBandWidth && !(mouseX+activeSize > width-toolBoxWidth && mouseY-activeSize < (rowMargin*2+rowHeight)*numRows)){
			  if(drawMode == "Ink"){
				    fill(activeColor);
				    ellipse(mouseX,mouseY,inkSize,inkSize);
				  }
				  
				  if(drawMode == "Eraser"){
				    fill(backgroundColor);
				    ellipse(mouseX,mouseY,eraserSize,eraserSize);
				  }
				  
				  if(drawMode == "Storytime"){
					  
				  }
			}else if(mouseX > width-toolBoxWidth && mouseX < width-rowMargin-(toolBoxWidth-rowMargin)/16 && mouseY > rowMargin*2+rowHeight*2 && mouseY < rowMargin*2+rowHeight*2+15){
				//changing the size
				updateSizeScale();
			}
			  
			
		}
	
		public void mouseClicked(){
			//changing the size
			if(mouseX > width-toolBoxWidth && mouseX < width-rowMargin-(toolBoxWidth-rowMargin)/16 && mouseY > rowMargin*2+rowHeight*2 && mouseY < rowMargin*2+rowHeight*2+15){
				updateSizeScale();
			}
		}
	
	public void mouseReleased(){
		//println("Mouse released. MX = "+mouseX+"\tsl1[3] = "+slot1[3]+"\tMY = "+mouseY+"\tsl1[2] = "+slot1[2]);
		//1ST BUTTON
	
		boolean inBounds = false;
	
		if(mouseX-activeSize > sideBandWidth+borderBandWidth && !(mouseX+activeSize > width-toolBoxWidth && mouseY-activeSize < (rowMargin*2+rowHeight)*numRows)){
		  inBounds = true;
		}
	
		if (inBounds){
		  if(drawMode == "Smile"){
			  //smile.drawAt(mouseX, mouseY);
		  }else if(drawMode == "Rect"){
			  rect(mouseX - rectSize/2, mouseY - rectSize/2, rectSize, rectSize);
		  }
		}
	
			//1ST BUTTON
			if(mouseX <= 57){
			  if(mouseX <= slot1[3] && mouseY <= slot1[2]){
			    drawMode = "Ink";
			    fill(0);
			    slotActive = 1;
			    activeSize = inkSize;
			  }
			  //2ND BUTTON
			  if(mouseX >= slot2[1] && mouseX <= slot2[3] && mouseY >= slot2[0] && mouseY <= slot2[2]){
			    drawMode = "Eraser";
			    slotActive = 2;
			    activeSize = eraserSize;
			  }
			  //3RD BUTTON
			  if(mouseX >= slot3[1] && mouseX <= slot3[3] && mouseY >= slot3[0] && mouseY <= slot3[2]){
			    drawMode = "Rect";
			    slotActive = 3;
			    activeSize = rectSize;
			  }
			  //4TH BUTTON
			  if(mouseX >= slot4[1] && mouseX <= slot4[3] && mouseY >= slot4[0] && mouseY <= slot4[2]){
			    drawMode = "Smile";
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
		      case 1: tabLabel = "Ink";
		              break;
		      case 2: tabLabel = "Erase";
		              break;
		      case 3: tabLabel = "Rect";
		              break;
		      case 4: tabLabel = "";
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
			rect(width-180, 0, 180, (rowMargin*2+rowHeight)*numRows);
			fill(0);
			text("Draw Mode: " + drawMode, width-toolBoxWidth, rowMargin+rowHeight);
			drawSizeScale();
		}
		
		private void drawSizeScale(){
			fill(200);
			rect(width-toolBoxWidth, rowMargin*2+rowHeight*2,toolBoxWidth-rowMargin,15);
			fill(30,109,214);
			rect(width-toolBoxWidth+(int)(1.00*activeSize/maxSize*(toolBoxWidth-rowMargin*2)),rowMargin*2+rowHeight*2,(toolBoxWidth-rowMargin)/8,15);
			
			//draw white rect over - less hassle than updating EVERYTHING
			fill(255);
			rect(width-toolBoxWidth,rowMargin+rowHeight*2-15,width,20);
			fill(0);
			text("Brush Size: " + activeSize, width-toolBoxWidth, rowMargin+rowHeight*2);
		}
		
		private void updateSizeScale(){
			activeSize = (int)(((1.00*mouseX-(width-toolBoxWidth))/toolBoxWidth)*maxSize);
			println(activeSize);
			
			if(drawMode == "Ink"){
				inkSize = activeSize;
			}else if(drawMode == "Eraser"){
				eraserSize = activeSize;
			}else if(drawMode == "Rect"){
				rectSize  = activeSize;
			}
			
			drawSizeScale();
		}
	
	public static void main(String _args[]) {
		PApplet.main(new String[] { net.bucktower.drawcessing.Drawcessing.class.getName() });
	}
}