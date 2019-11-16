package dev.java2dgame.quests;

import dev.java2dgame.entities.Entity;
import dev.java2dgame.entities.statics.Teacher;

public class TalkToBreatonQuest extends Quest {

	public TalkToBreatonQuest() {
		super("Talk to Mr. Breaton", "You need to locate and talk to Mr. Breaton.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
				if (e.getClass().getSimpleName().equals("Teacher")) {
					Teacher t = (Teacher) e;
					if (t.getTeacherId() == 0 && t.getCurrentDialogueIndex() != 0)
						return true;
				}
			}
			return false;
		} else
			return false;
	}

	@Override
	public void questReward() {
		handler.getWorld().getEntityManager().getPlayer().giveQuest(Quest.enterNewAreaQuest, "wait");
	}

}
