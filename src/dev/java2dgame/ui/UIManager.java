package dev.java2dgame.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import dev.java2dgame.main.Handler;

public class UIManager {
	
	private Handler handler;
	private UIObject objectInQueue;
	private boolean queueHasItem = false;
	private ArrayList<UIObject> objects;
	
	public UIManager(Handler handler) {
		this.handler = handler;
		this.objectInQueue = null;
		this.objects = new ArrayList<UIObject>();
	}
	
	public void tick(ListIterator<UIObject> it) {
		while (it.hasNext()) {
			UIObject obj = it.next();
			obj.tick(it);
		}
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
	
	public ArrayList<UIObject> getObjects() {
		return objects;
	}
	
	public void addObject(UIObject obj) {
		if (obj.getClass().getSimpleName().equals("ReadyForHandInUI") || obj.getClass().getSimpleName().equals("QuestGetUI") || obj.getClass().getSimpleName().equals("QuestCompleteUI")) {
			for (UIObject object: handler.getMouseManager().getUiManager().getObjects()) {
				if (object.getClass().getSimpleName().equals("ReadyForHandInUI") || object.getClass().getSimpleName().equals("QuestGetUI") || object.getClass().getSimpleName().equals("QuestCompleteUI")) {
					objectInQueue = obj;
					queueHasItem = true;
					return;
				}
			}
			objects.add(obj);
		} else {
			objects.add(obj);
		}
	}
	
	public void removeObject(UIObject obj) {
		objects.remove(obj);
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
