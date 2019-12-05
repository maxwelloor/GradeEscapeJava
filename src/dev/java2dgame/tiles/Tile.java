package dev.java2dgame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	// STATIC STUFF
	
	public static Tile[] tiles = new Tile[256];
	
	public static Tile nothingTile = new NothingTile();
	public static Tile gymFloorTile = new GymFloorTile();
	public static Tile audFloorTile = new AudFloorTile();
	public static Tile classFloorTile1 = new ClassroomFloorTile1();
	public static Tile classFloorTile2 = new ClassroomFloorTile2();
	public static Tile classFloorTile3 = new ClassroomFloorTile3();
	public static Tile hallFloorTile = new HallFloorTile();
	public static Tile frontHallWallTile = new HallWallFrontTile();
	public static Tile rightHallWallTile = new HallWallRightTile();
	public static Tile leftHallWallTile = new HallWallLeftTile();
	public static Tile frontDoorTile = new HallDoorFrontTile();
	public static Tile shelfWallTile = new ShelfWallTile();
	public static Tile whiteboardWallTile = new WhiteboardWallTile();
	public static Tile lockerTile = new FrontLockerTile();
	public static Tile bookshelfTile1 = new BookshelfTile1();
	public static Tile bookshelfTile2 = new BookshelfTile2();
	public static Tile openWindowTile = new OpenWindowTile();
	public static Tile closedWindowTile = new ClosedWindowTile();
	public static Tile gymWallFrontTile = new GymWallFrontTile();
	public static Tile gymWallLeftTile = new GymWallLeftTile();
	public static Tile gymWallRightTile = new GymWallRightTile();
	public static Tile bathroomFloorTile = new BathroomFloorTile();
	public static Tile audStageTile = new AudStageTile();
	public static Tile gymWallTopLeft = new GymWallTopLeftTile();
	public static Tile gymWallTopRight = new GymWallTopRightTile();
	public static Tile gymWallBotLeft = new GymWallBotLeftTile();
	public static Tile gymWallBotRight = new GymWallBotRightTile();
	public static Tile audChairTile = new AudChairTile();
	public static Tile toiletTile = new ToiletTile();
	public static Tile downHallWallTile = new HallWallDownTile();
	public static Tile hallTopLeftTile = new HallWallTopLeftTile();
	public static Tile hallBotLeftTile = new HallWallBotLeftTile();
	public static Tile hallTopRightTile = new HallWallTopRightTile();
	public static Tile hallBotRightTile = new HallWallBotRightTile();
	public static Tile hallDoorRightTile = new HallDoorRightTile();
	public static Tile hallDoorLeftTile = new HallDoorLeftTile();
	public static Tile hallDoorFrontTile = new HallDoorFrontTile();
	public static Tile hallDoorDownTile = new HallDoorDownTile();
	public static Tile urinalTile = new UrinalTile();
	
	// CLASS STUFF
	
	public static final int TILE_WIDTH = 64;
	public static final int TILE_HEIGHT = 64;
	
	protected static int totalTiles = 0;
	
	protected BufferedImage image;
	protected final int id;
	protected boolean isSolid;
	
	public Tile(BufferedImage image, boolean isSolid) {
		this.image = image;
		this.isSolid = isSolid;
		
		this.id = totalTiles;
		tiles[id] = this;
		totalTiles++;
	}
	
	public void tick() {
		
	}
	
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static void printIds() {
		for (int x = 0; x < tiles.length; x++) {
			
			if (tiles[x] == null)
				return;
			
			System.out.println(tiles[x].getClass().getSimpleName() + ": " + tiles[x].id);
		}		
	}
}
