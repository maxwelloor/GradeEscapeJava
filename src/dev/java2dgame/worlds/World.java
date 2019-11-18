package dev.java2dgame.worlds;

import java.awt.Graphics;

import dev.java2dgame.collectibles.Collectible;
import dev.java2dgame.collectibles.CollectibleManager;
import dev.java2dgame.entities.EntityManager;
import dev.java2dgame.entities.creatures.Player;
import dev.java2dgame.entities.statics.AreaSwitch;
import dev.java2dgame.entities.statics.ComputerMonitor;
import dev.java2dgame.entities.statics.Teacher;
import dev.java2dgame.entities.statics.WoodenTable;
import dev.java2dgame.entities.statics.WoodshopSaw;
import dev.java2dgame.main.Handler;
import dev.java2dgame.tiles.Tile;
import dev.java2dgame.utils.Utils;

public class World {
	
	private Handler handler;
	private int worldNumber;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	//Entities
	private EntityManager entityManager;
	private CollectibleManager collectibleManager;
	
	public World(Handler handler, String path) {
		this.handler = handler;
		worldNumber = Integer.parseInt(path);
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		collectibleManager = new CollectibleManager(handler);
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		collectibleManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		
		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		collectibleManager.render(g);
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.gymFloorTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if (t == null)
			return Tile.gymFloorTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString("res/worlds/" + path + ".txt");
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
		int firstEntityI = ((width - 1) + (height - 1) * width) + 5;
		boolean listOver = false;
		
		while (!listOver) {
			try {
				
				String nameOfObj = tokens[firstEntityI];
				int xOfObj = Integer.valueOf(tokens[firstEntityI + 1]);
				int yOfObj = Integer.valueOf(tokens[firstEntityI + 2]);
				int idOfObj = Integer.valueOf(tokens[firstEntityI + 3]);
				
				// Entities.
				if (nameOfObj.equals("WoodshopSaw")) {
					entityManager.addEntity(new WoodshopSaw(handler, xOfObj, yOfObj));
				} else if (nameOfObj.equals("AreaSwitch")) {
					entityManager.addEntity(new AreaSwitch(handler, xOfObj, yOfObj, idOfObj));
				} else if (nameOfObj.equals("Teacher")) {
					entityManager.addEntity(new Teacher(handler, xOfObj, yOfObj, idOfObj));
				} else if (nameOfObj.equals("WoodenTable")) {
					entityManager.addEntity(new WoodenTable(handler, xOfObj, yOfObj));
				} else if (nameOfObj.equals("ComputerMonitor")) {
					entityManager.addEntity(new ComputerMonitor(handler, xOfObj, yOfObj));
				} 
				
				// Collectibles.
				if (nameOfObj.equals("MooresHair")) {
					collectibleManager.addCollectible(Collectible.mooresHair, xOfObj, yOfObj);
				} else if (nameOfObj.equals("MoucksBall")) {
					collectibleManager.addCollectible(Collectible.moucksSoccerBall, xOfObj, yOfObj);
				}
				
			} catch(ArrayIndexOutOfBoundsException e) {
				listOver = true;
			}
			firstEntityI += 4;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public CollectibleManager getCollectibleManager() {
		return collectibleManager;
	}

	public int getWorldNumber() {
		return worldNumber;
	}

	public void setWorldNumber(int worldNumber) {
		this.worldNumber = worldNumber;
	}
}
