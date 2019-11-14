package dev.java2dgame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import dev.java2dgame.tiles.Tile;

// TODO add more tiles.

public class Assets {
	
	public static final int tWidth = Tile.TILE_WIDTH, tHeight = Tile.TILE_HEIGHT;
	
	//FONTS
	public static Font dialogueFont, testFont;

	// TILES
	public static BufferedImage nothing, hall_floor1, hall_floor2, aud_floor, gym_floor,
	classroom_floor1, classroom_floor2, classroom_floor3, front_wall, 
	left_wall, right_wall, shelf_wall, whiteboard_wall, closed_window,
	open_window, bookshelf1, bookshelf2, lockers, door;
	// TILES DONE
	
	// ENTITIES
	public static BufferedImage saw, wooden_table, computer_monitor;
	
	// ENTITIES DONE
	public static BufferedImage[] player_down, player_up, player_right, player_left, player_standing;
	
	// MISC
	public static BufferedImage interact_mark;
	
	//SPRITE SHEETS
	public static SpriteSheet tileSheet, playerRunDownSheet, playerStandingSheet, playerRunRightSheet,
	playerRunLeftSheet, playerRunUpSheet;
	
	public static void init() {
		// Player Stuff
		playerRunDownSheet = new SpriteSheet("/sprites/players/player_run_foreward.png");
		playerRunUpSheet = new SpriteSheet("/sprites/players/player_run_back.png");
		playerRunLeftSheet = new SpriteSheet("/sprites/players/player_run_left.png");
		playerRunRightSheet = new SpriteSheet("/sprites/players/player_run_right.png");
		playerStandingSheet = new SpriteSheet("/sprites/players/player_standing.png");
		
		player_down = playerRunDownSheet.getPlayerList(1, 7, 44, 86);
		player_right = playerRunRightSheet.getPlayerList(1, 8, 48, 100);
		player_left = playerRunLeftSheet.getPlayerList(1, 8, 48, 100);
		player_standing = playerStandingSheet.getPlayerList(1, 4, 44, 86);
		player_up = playerRunUpSheet.getPlayerList(1, 7, 44, 86);
		
		// Entities.
		saw = ImageLoader.loadImage("/sprites/entities/woodshop_saw.png");
		wooden_table = ImageLoader.loadImage("/sprites/entities/wooden_table.png");
		computer_monitor = ImageLoader.loadImage("/sprites/entities/computer_monitor.png");
		
		//Fonts.
		dialogueFont = Fonts.loadFont("/fonts/munro.ttf", 48);
		testFont = new Font("arial", Font.PLAIN, 32);
		
		// UI
		interact_mark = ImageLoader.loadImage("/sprites/ui/interact_mark.png");
		
		// Tile stuff.
		tileSheet = new SpriteSheet("/sprites/tiles/tilesheet.png");
		
		bookshelf1 = tileSheet.crop(0, 0, tWidth, tHeight);
		bookshelf2 = tileSheet.crop(0, tHeight, tWidth, tHeight);
		classroom_floor1 = tileSheet.crop(0, tHeight * 2, tWidth, tHeight);
		classroom_floor2 = tileSheet.crop(tWidth, 0, tWidth, tHeight);
		classroom_floor3 = tileSheet.crop(tWidth, tHeight, tWidth, tHeight);
		door = tileSheet.crop(tWidth, tHeight * 2, tWidth, tHeight);
		aud_floor = tileSheet.crop(tWidth * 2, 0, tWidth, tHeight);
		gym_floor = tileSheet.crop(tWidth * 2, tHeight, tWidth, tHeight);
		hall_floor1 = tileSheet.crop(tWidth * 2, tHeight * 2, tWidth, tHeight);
		hall_floor2 = tileSheet.crop(tWidth * 3, 0, tWidth, tHeight);
		lockers = tileSheet.crop(tWidth * 3, tHeight, tWidth, tHeight);
		nothing = tileSheet.crop(tWidth * 3, tHeight * 2, tWidth, tHeight);
		front_wall = tileSheet.crop(tWidth * 4, 0, tWidth, tHeight);
		left_wall = tileSheet.crop(tWidth * 4, tHeight, tWidth, tHeight);
		right_wall = tileSheet.crop(tWidth * 4, tHeight * 2, tWidth, tHeight);
		shelf_wall = tileSheet.crop(tWidth * 5, 0, tWidth, tHeight);
		whiteboard_wall = tileSheet.crop(tWidth * 6, 0, tWidth, tHeight);
		closed_window = tileSheet.crop(tWidth * 5, tHeight, tWidth, tHeight);
		open_window = tileSheet.crop(tWidth * 5, tHeight, tWidth, tHeight);
	}
}
