package dev.java2dgame.quests;

import dev.java2dgame.collectibles.Collectible;

public class FindMoucksBallQuest extends Quest {

	public FindMoucksBallQuest() {
		super("Find Mr. Moucks Ball", "Mr mouck lost his legendary ball and he need is back. Can you help?");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (Collectible.moucksSoccerBall.isCollected())
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public void questReward() {
		
	}
}
