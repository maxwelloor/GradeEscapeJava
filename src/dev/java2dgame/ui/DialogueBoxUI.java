package dev.java2dgame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;
import dev.java2dgame.utils.Utils;

public class DialogueBoxUI extends UIObject{
	
	private static int boxHeight = 250, totalEdgeGap = 50, textGap = 20, loopsToTick = 2;
	private static Font font = Assets.dialogue_font;
	
	private Handler handler;
	private int boxX, boxY, boxWidth, loops;
	private boolean buildingBox;
	private String fullText;
	private ArrayList<ArrayList<String>> lines, displayLines;
	private int currentLine, currentLetterIndex;
	
	public DialogueBoxUI(Handler handler, String text) {
		// In this case these variables mean nothing to the super-class.
		super(0, 0, 0, 0);
		
		this.handler = handler;
		this.fullText = text;
		
		// Adds an empty list to the display text which is the first list that gets written in.
		this.displayLines = new ArrayList<ArrayList<String>>();
		this.displayLines.add(new ArrayList<String>());
		this.currentLine = 0;
		this.currentLetterIndex = -1;
		this.boxWidth = handler.getWidth() - totalEdgeGap;
		this.boxX = totalEdgeGap/2 - 4; // the -4 is an offset to make it centered because its not for some reason.
		this.boxY = handler.getHeight() - totalEdgeGap - boxHeight;

		this.lines = buildLines();
		this.buildingBox = true;
	}
	
	@Override
	public void tick() {
		
		// only adds stuff onto the end of the box if the buildingBox variable is true.
		if (buildingBox) {
			loops++;
			// loops is for setting the speed of the animating.
			if (loops == loopsToTick) {
				loops = 0;
				// checks if its on the last letter of the line
				if (currentLetterIndex >= lines.get(currentLine).size() - 1) {
					// This if means its on the last letter of the last line
					if (currentLine >= lines.size() - 1) {
						buildingBox = false;
						return;
					} else { // the else will trigger if it was the last letter of the line but not the last line.
						displayLines.add(new ArrayList<String>());
						currentLine++;
						currentLetterIndex = 0;
					}
				} else {
					currentLetterIndex++;
				}
				displayLines.get(currentLine).add(lines.get(currentLine).get(currentLetterIndex));
			}
		}	
	}
	
	@Override
	public void render(Graphics g) {
		// Drawing the box
		g.setColor(Color.black);
		g.fillRect(boxX, boxY, boxWidth, boxHeight);
		
		// Drawing the white outline.
		g.setColor(Color.white);
		
		for (int offset = -2; offset < 3; offset++)
		g.drawRect(boxX + offset, boxY + offset, boxWidth, boxHeight);
		
		// xCord and yCord for the text to be rendered at.
		int xCord = boxX + textGap;
		int yCord = boxY + textGap + Fonts.getFontHeight(font) / 2;
		
		for (ArrayList<String> line : displayLines) {
			
			String textLine = Utils.arrayListToString(line);
			
			g.setFont(font);
			g.drawString(textLine, xCord, yCord);
			
			yCord += Fonts.getFontHeight(font);
		}
	}
	
	@Override
	public void onClick() {
		
	}
	
	private ArrayList<ArrayList<String>> buildLines() {
		
		String[] fullTextArray = fullText.split("");
		
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		ArrayList<String> line = new ArrayList<String>();
				
		for (String character : fullTextArray) {
			line.add(character);
			
			if (Fonts.getWidthOfString(Utils.arrayListToString(line), font) > boxWidth - textGap * 2) {
				// If the text width is to big then it finishes the line.
				lines.add(new ArrayList<String>(line));
				line.clear();
			}
		}
		// Adds the last line to the list
		lines.add(new ArrayList<String>(line));
		line.clear();
			
		return lines;
	}
	
	public void reset() {
		displayLines.clear();
		this.displayLines.add(new ArrayList<String>());
		
		currentLine = 0;
		currentLetterIndex = -1;
	}
	
	public boolean isBuilding() {
		return buildingBox;
	}
	
	public void setBuilding(boolean toWhat) {
		buildingBox = toWhat;
	}
}
