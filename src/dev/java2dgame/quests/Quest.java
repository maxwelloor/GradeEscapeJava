package dev.java2dgame.quests;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.UIObject;

// TODO add quest functinality.

public abstract class Quest {
	
	private Handler handler;
	private String qName, qDesc;
	
	public Quest(Handler handler, String qName, String qDesc) {
		this.qName = qName;
		this.qDesc = qDesc;
	}
	
	//public UIObject createQuestUIObj() {
		
	//}
	
	public abstract void tick();
}
