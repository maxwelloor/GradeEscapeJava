package dev.java2dgame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dev.java2dgame.main.Handler;

public class PerronFightState extends State {
	
	private final int scoreSubtractDelay = 10;
	private final int blackBarWidth = 1220, blackBarHeight = 70;
	private final int whiteBarWidth = blackBarWidth - 20, whiteBarHeight = blackBarHeight - 20;
	private final int redBarHeight = whiteBarHeight;
	
	private boolean fightHappening;
	private int playerScore, scoreToWin, timerLoops, scoreSubtractLoops, loopsTillSubtract, timer;
	private int redBarWidth;
	private boolean pressA, pressD, releasedBetween;

	public PerronFightState(Handler handler) {
		super(handler);
		
		timer = 10;
		timerLoops = 0;
		scoreSubtractLoops = 0;
		loopsTillSubtract = 18;
		playerScore = 0;
		scoreToWin = 48;
		redBarWidth = 0;
		pressA = true;
		pressD = false;
		releasedBetween = true;
		fightHappening = true;
	}

	@Override
	public void tick() {
		if (fightHappening) {
			
			if (handler.getKeyManager().pKeysReleased) {
				releasedBetween = true;
			}
			
			if (pressA) {
				System.out.println("aa");
				if (handler.getKeyManager().left && releasedBetween) {
					playerScore += 1;
					pressA = false;
					pressD = true;
					releasedBetween = false;
				}
			}
			
			if (pressD) {
				if (handler.getKeyManager().right && releasedBetween) {
					playerScore += 1;
					pressD = false;
					pressA = true;
					releasedBetween = false;
				}
			}
			
			// Timers and loops
			
			timerLoops++;
			scoreSubtractLoops++;
			
			if (timerLoops >= 60) {
				timer -= 1;
				timerLoops = 0;
			}
			
			if (scoreSubtractLoops >= loopsTillSubtract) {
				playerScore -= 1;
				// Never lets player score go below zero.
				if (playerScore < 0)
					playerScore = 0;
				
				scoreSubtractLoops = 0;
			}
			
			if (playerScore >= scoreToWin) {
				fightHappening = false;
			}
			
			// END
			
			redBarWidth = playerScore * 25;
		}
	}

	@Override
	public void render(Graphics g) {
		//STATIC BACKGROUND
		
		// Filling the background white
		g.setColor(Color.white);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		// Black rect centered in the screen
		g.setColor(Color.black);
		g.fillRect(handler.getWidth() / 2 - blackBarWidth / 2, handler.getHeight() - blackBarHeight - 20, blackBarWidth, blackBarHeight);
		
		//White rect in black one
		g.setColor(Color.white);
		g.fillRect(handler.getWidth() / 2 - whiteBarWidth / 2, handler.getHeight() - whiteBarHeight - 30, whiteBarWidth, whiteBarHeight);
		
		// BACKGROUND END
		g.setColor(Color.red);
		g.fillRect(handler.getWidth() / 2 - whiteBarWidth / 2, handler.getHeight() - redBarHeight - 30, redBarWidth, redBarHeight);
	}

}
