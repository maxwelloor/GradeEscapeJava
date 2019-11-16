package dev.java2dgame.quests;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.UIObject;

// TODO add quest functionality.

public abstract class Quest {
	
	protected Handler handler;
	protected boolean questCompleted;
	protected String qName, qDesc;
	
	public Quest(Handler handler, String qName, String qDesc) {
		this.handler = handler;
		this.qName = qName;
		this.qDesc = qDesc;
		
		this.questCompleted = false;
	}
	
	/*
	 *  This will run in the constructor of this Quest class and return the UIObject version of the quest when
	 *  the quest is made so that each quest can have some sort of UI in the side for the player to read with
	 *  the qName and qDesc variables there for the player to see.
	 */
	
	//public UIObject createQuestUIObj() {
		
	//}
	
	public abstract void tick();
	
	// An if statement that can be anything to check weather the quest has been completed.
	public abstract boolean isQuestRequirementFilled();
	
	// The method that is called on the quest when its completed
	public abstract void finished();
	
	public void setQuestCompleted(boolean completed) {
		questCompleted = completed;
	}
	
	public boolean isQuestCompleted() {
		return questCompleted;
	}
}
