package dev.java2dgame.quests;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.QuestCompleteUI;

// TODO add quest functionality.

public abstract class Quest {
	// Static Stuff
	protected static Handler handler;
	private static int numberOfQuests = 0;
	private static Quest[] quests = new Quest[256];
	
	public static Quest placeholderQuest = new PlaceholderQuest();
	public static Quest talkToBreatonQuest = new TalkToBreatonQuest();
	public static Quest enterNewAreaQuest = new EnterNewAreaQuest();
	
	public static Quest[] getQuests() {
		return quests;
	}
	
	public static void setHandler(Handler handler) {
		Quest.handler = handler;
	}
	
	// Dynamic Stuff
	protected boolean questCompleted = false, questGiven = false;
	protected String qName, qDesc;
	
	public Quest(String qName, String qDesc) {
		this.qName = qName;
		this.qDesc = qDesc;
		
		this.questCompleted = false;
		Quest.quests[Quest.numberOfQuests] = this;
		Quest.numberOfQuests++;
	}
	
	/*
	 *  This will run in the constructor of this Quest class and return the UIObject version of the quest when
	 *  the quest is made so that each quest can have some sort of UI in the side for the player to read with
	 *  the qName and qDesc variables there for the player to see.
	 */
	
	//public UIObject createQuestUIObj() {
		
	//}
	
	// An if statement that can be anything to check weather the quest has been completed.
	public abstract boolean isQuestRequirementFilled();
	
	// The method that is called on the quest when its completed
	public abstract void questReward();
	
	public void tick() {
		if (isQuestRequirementFilled())
			finished();
	}
	
	public void finished() {
		questCompleted = true;
		handler.getMouseManager().getUiManager().addObject(new QuestCompleteUI(handler, qName));
		questReward();
	}
	
	public boolean isQuestCompleted() {
		return questCompleted;
	}

	public boolean isQuestGiven() {
		return questGiven;
	}

	public void setQuestGiven(boolean questGiven) {
		this.questGiven = questGiven;
	}
	
	public String getQuestName() {
		return qName;
	}
	
	
}
