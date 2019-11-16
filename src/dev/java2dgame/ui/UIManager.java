package dev.java2dgame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.java2dgame.main.Handler;

public class UIManager {
	
	private Handler handler;
	private UIObject objectInQueue;
	private boolean queueHasItem;
	private ArrayList<UIObject> objects;
	protected ArrayList<UIObject> objectsToRemove;
	protected ArrayList<UIObject> objectsToAdd;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		this.objectInQueue = null;
		this.objects = new ArrayList<UIObject>();
		this.objectsToRemove = new ArrayList<UIObject>();
		this.objectsToAdd = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for (UIObject obj: objects) {
			obj.tick();
		}
		
		for (UIObject obj: objectsToRemove) {
			objects.remove(obj);
		}
		
		for (UIObject obj: objectsToAdd) {
			objects.add(obj);
		}
		
		objectsToAdd.clear();
		objectsToRemove.clear();
	}
	
	public void render(Graphics g) {
		for (UIObject obj: objects) {
			obj.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent e) {
		for (UIObject obj: objects) {
			obj.onMouseMove(e);
		}
	}
	
	public void onMouseRelease(MouseEvent e) {
		for (UIObject obj: objects) {
			obj.onMouseRelease(e);
		}
	}
	
	public void addObject(UIObject obj) {
		objects.add(obj);
	}
	
	public void removeObject(UIObject obj) {
		objects.remove(obj);
	}
	
	public ArrayList<UIObject> getObjectsToRemove() {
		return objectsToRemove;
	}
	
	public ArrayList<UIObject> getObjectsToAdd() {
		return objectsToAdd;
	}
	
	public void setObjectInQueue(UIObject obj) {
		objectInQueue = obj;
	}
	
	public UIObject getObjectInQueue() {
		return objectInQueue;
	}
	
	public boolean doesQueueHaveItem() {
		return queueHasItem;
	}
	
	public void setIfItemInQueue(boolean isThereItem) {
		queueHasItem = isThereItem;
	}
}
