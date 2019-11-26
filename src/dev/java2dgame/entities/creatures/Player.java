package dev.java2dgame.entities.creatures;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.java2dgame.collectibles.CollectibleMenu;
import dev.java2dgame.entities.Entity;
import dev.java2dgame.gfx.Animation;
import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;
import dev.java2dgame.quests.Quest;
import dev.java2dgame.quests.QuestMenu;
import dev.java2dgame.states.State;
import dev.java2dgame.ui.QuestGetUI;
import dev.java2dgame.ui.UIObject;

public class Player extends Creature {

	private static final float DEFAULT_SPRINT_SPEED = 4.5f, DEFAULT_TIRED_SPEED = 3.0f;
	public static ArrayList<Integer> keysFound = new ArrayList<Integer>();
	public static boolean perronBeat = false;
	
	// Player progression variables.
	
	private Animation idleAnim;
	private Animation[] defaultAnims, sprintingAnims, tiredAnims, currentAnims;
	
	private boolean isSprinting, canInteract, staminaBarEmpty, isTired;
	private CollectibleMenu collectibleMenu;
	private QuestMenu questMenu;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y);
		
		hitbox.x = 0;
		hitbox.y = 60;
		hitbox.width = 44;
		hitbox.height = 33;
		
		collectibleMenu = new CollectibleMenu(handler);
		questMenu = new QuestMenu(handler);
		
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
		giveQuest(Quest.goToTheAud);
		
		isSprinting = false;
		canInteract = false;
		staminaBarEmpty = false;
	}
	
	public void tick() {
		// TODO Remove this after testing.
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))
			State.setState(handler.getGame().getPerronFightState());
		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		tickAnimations();
		
		// Menu Stuff
		collectibleMenu.tick();
		questMenu.tick();
		// Menu stuff done.
		
		// TODO DELETE THIS WHEN DONE ITS FOR TESTING.
		if (handler.getMouseManager().isLeftPressed()) {
			System.out.println((handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset()) + ", " + (handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset()));
		}
		
		// Quest stuff
		for (Quest quest : Quest.getQuests()) {
			if (quest == null)
				break;
			
			if (quest.isQuestGiven())
				quest.tick();
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
		
		// Checks for dialogue happening.
		for (UIObject obj: handler.getMouseManager().getUiManager().getObjects()) {
			if (obj.getClass().getSimpleName().equals("DialogueBoxUI")) {
				xMove = 0;
				yMove = 0;
				return;
			}
		}
		
		if (collectibleMenu.isMenuActive() || questMenu.isMenuOpen()) { // Stops the player from moving if the collectible menu is active.
			xMove = 0;
			yMove = 0;
			return;
		}
		
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
			// Makes is so the player wont be sprinting if the shift key is held but the player is still.
			if (xMove != 0 || yMove != 0)
				isSprinting = true;
			else
				isSprinting = false;
		} else {
			isSprinting = false;
		}
		
	}
	
	public void render(Graphics g) {
		if (canInteract)
			g.drawImage(Assets.interact_mark, (int) (x - handler.getGameCamera().getxOffset() + getCurrentAnimationFrame(currentAnims).getWidth()/2 - Assets.interact_mark.getWidth()/2), (int) (y - handler.getGameCamera().getyOffset()) - Assets.interact_mark.getHeight() - 10, null);
		
		g.drawImage(getCurrentAnimationFrame(currentAnims), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
	
	public void afterRender(Graphics g) {
		collectibleMenu.render(g);
		questMenu.render(g);
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

	public void giveQuest(Quest quest) {
		if (!quest.isQuestGiven())
			handler.getMouseManager().getUiManager().addObject(new QuestGetUI(handler, quest.getQuestName()));
		quest.setQuestGiven(true);
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
	
	public void setPerronBeat(boolean isBeat) {
		this.perronBeat = isBeat;
	}
	
	public boolean isPerronBeat() {
		return perronBeat;
	}
	
	public CollectibleMenu getCollectibleMenu() {
		return collectibleMenu;
	}
	
	public QuestMenu getQuestMenu() {
		return questMenu;
	}
	
	public ArrayList<Integer> getKeysFound() {
		return keysFound;
	}
	
}
