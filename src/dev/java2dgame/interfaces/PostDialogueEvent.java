package dev.java2dgame.interfaces;

import dev.java2dgame.entities.statics.Teacher;
import dev.java2dgame.main.Handler;

public interface PostDialogueEvent {
	
	public void afterDialogue(Handler handler, Teacher teacher);

}
