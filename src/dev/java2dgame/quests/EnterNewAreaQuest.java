package dev.java2dgame.quests;

public class EnterNewAreaQuest extends Quest {

	public EnterNewAreaQuest() {
		super("Enter new area", "You need to leave the current area you are in to complete this quest");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (handler.getCurrentWorldNumber() != 1)
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public void questReward() {
		handler.getWorld().getEntityManager().getPlayer().giveQuest(Quest.findMooresHairQuest, "wait");
	}

}
