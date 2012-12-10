package model;

public class NodeRenderer {
	
	/*
	  Here is the idea. The node renderer does not need to know
	  anything about network topology. Instead, it will simply
	  draw the nodes together relatively equal distance apart.
	  As node A triggers node B, B will move toward node A on 
	  the display. Other unrelated nodes (very) near node A will 
	  move away. An arrow will be drawn High activity nodes may be 
	  some color... red? Or they may increase in size. In this manner 
	  the system will only display relevant information. Relationships 
	  that are not expressed often ex:
	  
	  person stranded for 5 days in desert ->>>> thirsty
	  
	  would not clutter up the overall view of the situation, namely
	  
	  person grabbing cup of water ->>>> thirsty.
	  person just drank 1 minute ago -<<<< thirsty
	  ->>> person likely putting drink in the fridge.
	  
	  Honestly, we wouldn't care that there may be a relationship
	  with some remote node when that situation is not applicable.
	
	 */

}
