package dev.java2dgame.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.GameCamera;
import dev.java2dgame.input.KeyManager;
import dev.java2dgame.input.MouseManager;
import dev.java2dgame.quests.Quest;
import dev.java2dgame.states.GameState;
import dev.java2dgame.states.MenuState;
import dev.java2dgame.states.PerronFightState;
import dev.java2dgame.states.State;
import dev.java2dgame.window.Window;

public class Game implements Runnable {
	
	private Window window;
	
	private int width;
	private int height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	private State perronFightState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager(handler);
	}
	
	private void init() {
		window = new Window(title, width, height);
		
		// Key and mouse listeners
		window.getFrame().addKeyListener(keyManager);
		window.getFrame().addMouseListener(mouseManager);
		window.getFrame().addMouseMotionListener(mouseManager);
		window.getCanvas().addMouseListener(mouseManager);
		window.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		perronFightState = new PerronFightState(handler);
		
		State.setState(gameState);
		Quest.setHandler(handler);
	}
	
	public void tick() {
		keyManager.tick();
		
		if (State.getState() != null)
			State.getState().tick();
	}
	
	public void render() {
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clearing the screen.
		g.clearRect(0, 0, width, height);
		// Draw under here.
		
		if (State.getState() != null)
			State.getState().render(g);
		
		// End Drawings.
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		int FPS = 60;
		double timePerTick = 1000000000/FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000) {
				System.out.println("Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Window getWindow() {
		return window;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
}
