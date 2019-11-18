package dev.java2dgame.quests;

public class BeatMrPerronQuest extends Quest {

	public BeatMrPerronQuest() {
		super("Beat Mr. Perron", "You need to get the key Mr. Perron has by beating him in an arm wrestle. Can you do it?");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (handler.getWorld().getEntityManager().getPlayer().isPerronBeat())
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
