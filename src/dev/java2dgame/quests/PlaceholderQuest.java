package dev.java2dgame.quests;

public class PlaceholderQuest extends Quest {

	public PlaceholderQuest() {
		super("Testing Quest", "This is a testing quest for testing the funcionality of the quests system in the game.");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (handler.getWorld().getEntityManager().getPlayer().isTired())
				return true;
			else
				return false;
		} else {
			return false;
		}
		
	}

	public void questReward() {

	}

}
