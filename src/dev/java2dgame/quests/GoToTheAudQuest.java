package dev.java2dgame.quests;

public class GoToTheAudQuest extends Quest {

	public GoToTheAudQuest() {
		super("Go to the Aud", "You got an announcemnt to gothere. Better be fast!");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (handler.getCurrentWorldNumber() == 2) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public void questReward() {
		
	}
}
