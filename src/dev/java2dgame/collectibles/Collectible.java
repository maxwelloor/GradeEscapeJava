package dev.java2dgame.collectibles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;
import dev.java2dgame.utils.Utils;

public class Collectible {
	
	// COLLECTIBLES
	
	public static Collectible[] collectibles = new Collectible[256];
	public static Collectible mooresHair = new Collectible(null, "Moore's Hair", "Collectible Desc: This legendary artifact has been gone since         before the current civilizations know, the fact that you have thismakes you one of the most dangerous people ever.");
	public static Collectible pencil = new Collectible(null, "Pencil", "dolo");
	public static Collectible testCollectible = new Collectible(null, "Pencil", "dolo");
	public static Collectible mathesonsJuul = new Collectible(null, "Pencil", "dolo");
	public static Collectible ellisEar = new Collectible(null, "Pencil", "dolo");
	public static Collectible testname = new Collectible(null, "Pencil", "dolo");
	public static Collectible jeje = new Collectible(null, "Pencil", "dolo");
	public static Collectible aas = new Collectible(null, "Pencil", "dolo");
	public static Collectible edf = new Collectible(null, "Pencil", "dolo");
	
	// DYNAMIC STUFF
	
	private static final int COLLECTIBLE_W = 32, COLLECTIBLE_H = 32;
	private static int totalCollectibles = 0;	
	private static Handler handler;
	
	private float x, y;
	private String name, desc;
	private ArrayList<String> displayDesc;
	private boolean collected;
	private BufferedImage image;
	private int id;
	
	public Collectible(BufferedImage img, String cName, String cDesc) {
		this.name = cName;
		this.desc = cDesc;
		this.displayDesc = buildDescrption();
		this.image = img;
		this.id = totalCollectibles;
		
		collectibles[id] = this;
		totalCollectibles++;
	}
	
	public void tick(Iterator<Collectible> it) {
		if (collected)
			return;
		
		// If the player is touching the collectible
		if (getHitbox().intersects(handler.getWorld().getEntityManager().getPlayer().getHitbox(0f, 0f))) {
			it.remove();
			collected = true;
		}	
	}

	public void render(Graphics g) {
		if (handler == null || collected)
			return;
		
		g.setColor(Color.red);
		if (image == null)
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), COLLECTIBLE_W, COLLECTIBLE_H);
		
		g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
	
	public void renderMenuImage(Graphics g, int x, int y) {
		if (image == null)
			g.fillRect((int) x, (int) y, 64, 64);
		
		g.drawImage(image, x, y, null);
	}
	
	private ArrayList<String> buildDescrption() {
			
			String[] fullTextArray = desc.split("");
			
			ArrayList<String> line = new ArrayList<String>();
			ArrayList<String> lines = new ArrayList<String>();
					
			for (String character : fullTextArray) {
				line.add(character);
				
				if (Fonts.getWidthOfString(Utils.arrayListToString(line), CollectibleMenu.INFO_FONT) > CollectibleMenu.MENU_W - 50) {
					// If the text width is to big then it finishes the line.
					lines.add(Utils.arrayListToString(line));
					line.clear();
				}
			}
			// Adds the last line to the list
			lines.add(Utils.arrayListToString(line));
			line.clear();
				
			return lines;
	}
	
	// GETTTERS AND SETTTERS.
	
	public void setPos(int x, int y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	public static void setHandler(Handler handler) {
		Collectible.handler = handler;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) x, (int) y, COLLECTIBLE_W, COLLECTIBLE_H);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public ArrayList<String> getDisplayDesc() {
		return displayDesc;
	}
	
	public static Collectible[] getAllCollectibles() {
		return collectibles;
	}		
}
