package dev.java2dgame.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import dev.java2dgame.tiles.Tile;

// TODO add more tiles.

public class Assets {
	
	public static final int tWidth = Tile.TILE_WIDTH, tHeight = Tile.TILE_HEIGHT;
	
	//FONTS
	public static Font dialogue_font, quest_complete_font, quest_name_font, collectible_menu_font, collectible_menu_info_font;

	// TILES
	public static BufferedImage nothing, hall_floor, aud_floor, gym_floor,
	gym_wall_front, gym_wall_right, gym_wall_left, classroom_floor1, classroom_floor2,
	classroom_floor3, front_wall, left_wall, right_wall, shelf_wall, whiteboard_wall, 
	closed_window, open_window, bookshelf1, bookshelf2, lockers, door, bathroom_floor,
	aud_stage, gym_wall_topleft, gym_wall_topright, gym_wall_botleft, gym_wall_botright,
	aud_chair;
	// TILES DONE
	
	// STATIC ENTITIES
	public static BufferedImage saw, wooden_table, computer_monitor;
	// ENTITIES DONE
	
	//TEACHERS
	public static SpriteSheet mr_breaton_sheet, mr_kitchen_sheet, mr_witterick_sheet, mr_watson_sheet;
	public static BufferedImage[] mr_breaton, mr_kitchen, mr_witterick, mr_watson;
	// TEACHERS DONE
	
	public static BufferedImage[] player_down, player_up, player_right, player_left, player_standing;
	
	// UI
	public static BufferedImage interact_mark;
	
	//SPRITE SHEETS
	public static SpriteSheet tileSheet, playerRunDownSheet, playerStandingSheet, playerRunRightSheet,
	playerRunLeftSheet, playerRunUpSheet;
	
	public static void init() {
		// Player Stuff
		playerRunDownSheet = new SpriteSheet("/sprites/entities/people/player_run_foreward.png");
		playerRunUpSheet = new SpriteSheet("/sprites/entities/people/player_run_back.png");
		playerRunLeftSheet = new SpriteSheet("/sprites/entities/people/player_run_left.png");
		playerRunRightSheet = new SpriteSheet("/sprites/entities/people/player_run_right.png");
		playerStandingSheet = new SpriteSheet("/sprites/entities/people/player_standing.png");
		
		player_down = playerRunDownSheet.getPlayerList(1, 7, 44, 86);
		player_right = playerRunRightSheet.getPlayerList(1, 8, 48, 100);
		player_left = playerRunLeftSheet.getPlayerList(1, 8, 48, 100);
		player_standing = playerStandingSheet.getPlayerList(1, 4, 44, 86);
		player_up = playerRunUpSheet.getPlayerList(1, 7, 44, 86);
		
		// Entities.
		saw = ImageLoader.loadImage("/sprites/entities/statics/woodshop_saw.png");
		wooden_table = ImageLoader.loadImage("/sprites/entities/statics/wooden_table.png");
		computer_monitor = ImageLoader.loadImage("/sprites/entities/statics/computer_monitor.png");
		
		// Teachers.
		mr_breaton_sheet = new SpriteSheet("/sprites/entities/people/teachers/mr_breaton.png");
		mr_kitchen_sheet = new SpriteSheet("/sprites/entities/people/teachers/mr_kitchen.png");
		mr_watson_sheet = new SpriteSheet("/sprites/entities/people/teachers/mr_watson.png");
		mr_witterick_sheet = new SpriteSheet("/sprites/entities/people/teachers/mr_watson.png");
		
		mr_breaton = mr_breaton_sheet.getPlayerList(1, 2, 22, 40);
		mr_witterick = mr_witterick_sheet.getPlayerList(1, 2, 22, 40);
		mr_watson = mr_watson_sheet.getPlayerList(1, 2, 22, 22);
		mr_kitchen = mr_kitchen_sheet.getPlayerList(1, 2, 24, 26);
		
		//Fonts.
		dialogue_font = Fonts.loadFont("/fonts/munro.ttf", 48);
		quest_name_font = Fonts.loadFont("/fonts/munro.ttf", 24);
		quest_complete_font = Fonts.loadFont("/fonts/munro.ttf", 36);
		collectible_menu_font = Fonts.loadFont("/fonts/munro.ttf", 56);
		collectible_menu_info_font = Fonts.loadFont("/fonts/munro.ttf", 28);
		
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
		lockers = tileSheet.crop(tWidth * 3, tHeight, tWidth, tHeight);
		nothing = tileSheet.crop(tWidth * 3, tHeight * 2, tWidth, tHeight);
		front_wall = tileSheet.crop(tWidth * 4, 0, tWidth, tHeight);
		left_wall = tileSheet.crop(tWidth * 4, tHeight, tWidth, tHeight);
		right_wall = tileSheet.crop(tWidth * 4, tHeight * 2, tWidth, tHeight);
		shelf_wall = tileSheet.crop(tWidth * 5, 0, tWidth, tHeight);
		whiteboard_wall = tileSheet.crop(tWidth * 6, 0, tWidth, tHeight);
		closed_window = tileSheet.crop(tWidth * 5, tHeight, tWidth, tHeight);
		open_window = tileSheet.crop(tWidth * 5, tHeight, tWidth, tHeight);
		
		gym_wall_front = ImageLoader.loadImage("/sprites/tiles/gym_wall_front.png");
		gym_wall_right = ImageLoader.loadImage("/sprites/tiles/gym_wall_right.png");
		gym_wall_left = ImageLoader.loadImage("/sprites/tiles/gym_wall_left.png");
		hall_floor = ImageLoader.loadImage("/sprites/tiles/hallway_floor_light.png");
		bathroom_floor = ImageLoader.loadImage("/sprites/tiles/bathroom_floor.png");
		aud_stage = ImageLoader.loadImage("/sprites/tiles/aud_stage.png");
		gym_wall_topleft = ImageLoader.loadImage("/sprites/tiles/gym_wall_topleft.png");
		gym_wall_topright = ImageLoader.loadImage("/sprites/tiles/gym_wall_topright.png");
		gym_wall_botleft = ImageLoader.loadImage("/sprites/tiles/gym_wall_botleft.png");
		gym_wall_botright = ImageLoader.loadImage("/sprites/tiles/gym_wall_botright.png");
		aud_chair = ImageLoader.loadImage("/sprites/tiles/aud_chair.png");
	}
}
