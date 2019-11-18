package dev.java2dgame.entities.statics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.java2dgame.gfx.Animation;
import dev.java2dgame.gfx.Assets;
import dev.java2dgame.interfaces.PostDialogueEvent;
import dev.java2dgame.main.Handler;
import dev.java2dgame.quests.Quest;
import dev.java2dgame.ui.DialogueBoxUI;

public class Teacher extends StaticEntity {
	
	private boolean showingDialogue = false;
	private DialogueBoxUI[] dialogues;
	private int currentDialogue = 0, teacherId;
	private Animation standingAnim;

	public Teacher(Handler handler, float x, float y, int id) {
		super(handler, x, y, true);
		
		hitbox.width = 64;
		hitbox.height = 64;
		teacherId = id;
		
		// Mr Breaton.
		if (id == 0) {
			currentDialogue = 1;
			standingAnim = new Animation(500, Assets.mr_breaton);
			dialogues = new DialogueBoxUI[] {new DialogueBoxUI(handler, "Hey thanks so much for helping with this!", new PostDialogueEvent() {

				@Override // The dialogue that thanks the player for doing the quest.
				public void afterDialogue(Handler handler, Teacher teacher) {
					teacher.currentDialogue = 0;
				}
				
			}), new DialogueBoxUI(handler, "Hey I'm a teacher nice to meet you. I need your help with something.", null), new DialogueBoxUI(handler, "Could you help me find Mr. Moores hair?", new PostDialogueEvent() {

				@Override // The dialogue that gives the quest.
				public void afterDialogue(Handler handler, Teacher teacher) {
					handler.getWorld().getEntityManager().getPlayer().giveQuest(Quest.findMooresHairQuest);
				}
				
			}), new DialogueBoxUI(handler, "Well what are you doing just standing there. Get a move on on need Mr. Moores hair!", null)};
		}
	}

	@Override
	public void tick() {
		// Checks for the player interacting with the teacher
		if (checkForPlayerInteract(handler.getWorld().getEntityManager().getPlayer()) && !showingDialogue) {
			getCurrentDialogue().setBuilding(true);
			handler.getMouseManager().getUiManager().addObject(getCurrentDialogue());
			showingDialogue = true;
		}
		
		if (showingDialogue) {
			checkForDialogueEnd(getCurrentDialogue());
		}
		
		standingAnim.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(standingAnim.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
	
	public DialogueBoxUI getCurrentDialogue() {
		return dialogues[currentDialogue];
	}
	
	private void checkForDialogueEnd(DialogueBoxUI d) {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !d.isBuilding()) {
			showingDialogue = false;
			d.reset();
			
			handler.getMouseManager().getUiManager().removeObject(d);
			
			if (currentDialogue < dialogues.length - 1)
				currentDialogue++;
			
			if (d.getPDE() != null) {
				d.getPDE().afterDialogue(handler, this);
			}
		}
	}

	public int getTeacherId() {
		return teacherId;
	}
	
	public int getCurrentDialogueIndex() {
		return currentDialogue;
	}
	
	public void setCurrentDialogue(int dialogue) {
		currentDialogue = dialogue;
	}
	
	public DialogueBoxUI[] getTeacherDialogues() {
		return dialogues;	
	}
	
	public boolean isShowingDialogue() {
		return showingDialogue;
	}
}
