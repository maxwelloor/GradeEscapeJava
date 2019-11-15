package dev.java2dgame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.java2dgame.gfx.Animation;
import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.DialogueBoxUI;

public class Teacher extends StaticEntity {
	
	private boolean showingDialogue = false;
	private DialogueBoxUI[] dialogues;
	private int currentDialogue = 0;
	private Animation standingAnim;

	public Teacher(Handler handler, float x, float y, int id) {
		super(handler, x, y);
		
		this.interactible = true;
		hitbox.width = 64;
		hitbox.height = 64;
		
		// Mr Breaton.
		if (id == 0) {
			standingAnim = new Animation(500, Assets.mr_breaton);
			dialogues = new DialogueBoxUI[] {new DialogueBoxUI(handler, "Hey I'm a garbage teacher"), new DialogueBoxUI(handler, "Nice to see you")};
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
	
	private DialogueBoxUI getCurrentDialogue() {
		return dialogues[currentDialogue];
	}
	
	private void checkForDialogueEnd(DialogueBoxUI d) {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !d.isBuilding()) {
			showingDialogue = false;
			d.reset();
			handler.getMouseManager().getUiManager().removeObject(d);
			
			if (currentDialogue < dialogues.length - 1)
				currentDialogue++;
		}
	}
}
