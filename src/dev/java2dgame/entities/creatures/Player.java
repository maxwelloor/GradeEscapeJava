package dev.java2dgame.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.java2dgame.entities.Entity;
import dev.java2dgame.gfx.Animation;
import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;
import dev.java2dgame.quests.PlaceholderQuest;
import dev.java2dgame.quests.Quest;

public class Player extends Creature {

	private static final float DEFAULT_SPRINT_SPEED = 4.5f, DEFAULT_TIRED_SPEED = 2.0f;
	
	private Animation idleAnim;
	private Animation[] defaultAnims, sprintingAnims, tiredAnims, currentAnims;
	
	private Quest currentQuest;
	private boolean isSprinting, canInteract, staminaBarEmpty, isTired;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y);
		
		hitbox.x = 0;
		hitbox.y = 48;
		hitbox.width = 44;
		hitbox.height = 45;
		
		// Player idle animation.
		idleAnim = new Animation(333, Assets.player_standing);
		
		// ALL PLAYER ANIMATIONS
		defaultAnims = new Animation[]{
		new Animation(110, Assets.player_down),
		new Animation(110, Assets.player_up),
		new Animation(100, Assets.player_right),
		new Animation(100, Assets.player_left)
		};
		
		// TIRED ANIMATIONS
		tiredAnims = new Animation[] {
		new Animation(150, Assets.player_down),
		new Animation(150, Assets.player_up),
		new Animation(150, Assets.player_right),
		new Animation(150, Assets.player_left)
		};
		
		// SPRINTING ANIMATIONS.
		sprintingAnims = new Animation[] {
		new Animation(60, Assets.player_down),
		new Animation(60, Assets.player_up),
		new Animation(60, Assets.player_right),
		new Animation(60, Assets.player_left)
		};
		
		currentAnims = defaultAnims;
		
		isSprinting = false;
		canInteract = false;
		staminaBarEmpty = false;
		
		currentQuest = new PlaceholderQuest(handler, "I", "am");
	}
	
	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		// Quest stuff
		if (currentQuest.isQuestDone()) {
			currentQuest.finished();
		}
		// Quest stuff over.
		
		// Sprinting stuff.
		if (isTired) {
			speed = DEFAULT_TIRED_SPEED;
			currentAnims = tiredAnims;
		}  
		else if (isSprinting) {
			speed = DEFAULT_SPRINT_SPEED;
			currentAnims = sprintingAnims;
		} else {
			speed = Creature.DEFAULT_SPEED;
			currentAnims = defaultAnims;
		}
		
		tickAnimations();
		
		// Sprinting stuff over.
		
		// Checking to see if the player is in range to interact with anything.
		boolean inInteractRange = false;
		
		for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
			if (e.isInteractible()) {
				if(e.getInteractBox(0f, 0f).intersects(getHitbox(0f, 0f)))
					inInteractRange = true;
					canInteract = true;
			}
			
			if (inInteractRange)
				break;
			
		}
		
		if (!inInteractRange)
			canInteract = false;
		// Interact over.
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().up)
			yMove = -speed;
		if (handler.getKeyManager().down)
			yMove = +speed;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = +speed;
		
		if (handler.getKeyManager().shift && !staminaBarEmpty && !isTired) {
			isSprinting = true;
		} else {
			isSprinting = false;
		}
		
	}
	
	public void render(Graphics g) {
		if (canInteract)
			g.drawImage(Assets.interact_mark, (int) (x - handler.getGameCamera().getxOffset() + getCurrentAnimationFrame(currentAnims).getWidth()/2 - Assets.interact_mark.getWidth()/2), (int) (y - handler.getGameCamera().getyOffset()) - Assets.interact_mark.getHeight() - 10, null);
		
		g.drawImage(getCurrentAnimationFrame(currentAnims), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
	
	private void tickAnimations() {
		
		idleAnim.tick();
		
		for (Animation dAnim : defaultAnims)
			dAnim.tick();
		
		for (Animation sAnim : sprintingAnims)
			sAnim.tick();
		
		for (Animation tAnim : tiredAnims)
			tAnim.tick();
	}
	
	private BufferedImage getCurrentAnimationFrame(Animation[] animSet) {
		if (xMove < 0) {
			return animSet[3].getCurrentFrame(); // left
		} else if (xMove > 0) {
			return animSet[2].getCurrentFrame(); // right
		} else if (yMove < 0) {
			return animSet[1].getCurrentFrame(); // up
		} else if (yMove > 0) {
			return animSet[0].getCurrentFrame(); // down
		} else {
			return idleAnim.getCurrentFrame();
		}
	}

	public boolean getCanInteract() {
		return canInteract;
	}

	public void setCanInteract(boolean canInteract) {
		this.canInteract = canInteract;
	}

	public Quest getCurrentQuest() {
		return currentQuest;
	}

	public void setCurrentQuest(Quest quest) {
		this.currentQuest = currentQuest;
	}
	
	public void setSprinting(boolean sprinting) {
		this.isSprinting = sprinting;
	}
	
	public boolean getSprinting() {
		return this.isSprinting;
	}

	public void setStaminaBarEmpty(boolean staminaBarEmpty) {
		this.staminaBarEmpty = staminaBarEmpty;
	}
	
	public boolean isStaminaBarEmpty() {
		return staminaBarEmpty;
	}

	public boolean isTired() {
		return isTired;
	}

	public void setTired(boolean isTired) {
		this.isTired = isTired;
	}
	
	
}
