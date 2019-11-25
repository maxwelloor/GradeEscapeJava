package dev.java2dgame.quests;

import java.util.ArrayList;

import dev.java2dgame.collectibles.CollectibleMenu;
import dev.java2dgame.entities.Entity;
import dev.java2dgame.entities.statics.Teacher;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.DialogueBoxUI;
import dev.java2dgame.ui.QuestCompleteUI;
import dev.java2dgame.ui.ReadyForHandInUI;
import dev.java2dgame.utils.Utils;


public abstract class Quest {
	// Static Stuff
	protected static Handler handler;
	private static int numberOfQuests = 0;
	private static Quest[] quests = new Quest[256];
	
	public static Quest talkToBreatonQuest = new TalkToBreatonQuest();
	public static Quest enterNewAreaQuest = new EnterNewAreaQuest();
	public static Quest goToTheAud = new GoToTheAudQuest();
	public static Quest findMooresHairQuest = new FindMooresHairQuest();
	public static Quest beatMrPerronQuest = new BeatMrPerronQuest();
	public static Quest findMrMoucksBallQuest = new FindMoucksBallQuest();
	public static Quest placeHolder = new TalkToBreatonQuest();
	public static Quest placeHolder2 = new TalkToBreatonQuest();
	
	public static Quest[] getQuests() {
		return quests;
	}
	
	public static void setHandler(Handler handler) {
		Quest.handler = handler;
	}
	
	// Dynamic Stuff
	protected boolean questCompleted = false, questGiven = false, readyForHandIn = false, questHandedIn = false, handInQuest;
	protected String qName, qDesc;
	protected int teacherForHandIn;
	private ArrayList<String> displayDesc;
	
	public Quest(String qName, String qDesc) {
		this.qName = qName;
		this.qDesc = qDesc;
		this.handInQuest = false;
		
		this.displayDesc = buildDescrption();
		
		this.questCompleted = false;
		Quest.quests[Quest.numberOfQuests] = this;
		Quest.numberOfQuests++;
	}
	
	// This constructor is for quests that have to be handed in. The teacher id is 
	// the id of the teacher that the quest is required to be handed into upon completion.
	public Quest(String qName, String qDesc, int teacherId) {
		this.qName = qName;
		this.qDesc = qDesc;
		
		this.displayDesc = buildDescrption();
		
		this.handInQuest = true;
		this.questCompleted = false;
		this.teacherForHandIn = teacherId;
		
		Quest.quests[Quest.numberOfQuests] = this;
		Quest.numberOfQuests++;
	}
	
	// An if statement that can be anything to check weather the quest has been completed.
	public abstract boolean isQuestRequirementFilled();
	
	// The method that is called on the quest when its completed
	public abstract void questReward();
	
	public void tick() {
		if (handInQuest) {
			if (isQuestRequirementFilled() && !readyForHandIn) {
				readyForHandIn = true;
				handler.getMouseManager().getUiManager().addObject(new ReadyForHandInUI(handler, qName));
			}
			else if (readyForHandIn) {
				
				for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
					if (e.getClass().getSimpleName().equals("Teacher")) {
						Teacher t = (Teacher) e;
					
						if (t.getTeacherId() == teacherForHandIn && t.isShowingDialogue() && !questHandedIn) {
							// Sets it to the first index which should be the default teacher thanking for a quest.
							DialogueBoxUI currentDB = t.getTeacherDialogues()[t.getCurrentDialogueIndex()];
							handler.getMouseManager().getUiManager().removeObject(currentDB);
							currentDB.setBuilding(false);
							currentDB.reset();
							
							t.setCurrentDialogue(0);
							handler.getMouseManager().getUiManager().addObject(t.getTeacherDialogues()[t.getCurrentDialogueIndex()]);
							questHandedIn = true;
							finished();
						}
					}
				}
			}
		} else {
			if (isQuestRequirementFilled())
				finished();
		}
	}
	
	public void finished() {
		questCompleted = true;
		handler.getMouseManager().getUiManager().addObject(new QuestCompleteUI(handler, qName));
		questReward();
	}
	
	private ArrayList<String> buildDescrption() {
		
		String[] fullTextArray = qDesc.split("");
		
		ArrayList<String> line = new ArrayList<String>();
		ArrayList<String> lines = new ArrayList<String>();
				
		for (String character : fullTextArray) {
			line.add(character);
			
			if (Fonts.getWidthOfString(Utils.arrayListToString(line), QuestMenu.INFO_FONT) > QuestMenu.MENU_W/2 - 50) {
				// If the text width is to big then it finishes the line.
				lines.add(Utils.arrayListToString(line));
				line.clear();
			}
		}
		// Adds the last line to the list
		lines.add(Utils.arrayListToString(line));
		line.clear();
			
		return lines;
	}
	
	public boolean isQuestCompleted() {
		return questCompleted;
	}

	public boolean isQuestGiven() {
		return questGiven;
	}
	
	public boolean isReadyForHandIn() {
		return readyForHandIn;
	}
	
	public boolean isHandInQuest() {
		return handInQuest;
	}

	public void setQuestGiven(boolean questGiven) {
		this.questGiven = questGiven;
	}
	
	public String getQuestName() {
		return qName;
	}
	
	public String getQuestDescription() {
		return qDesc;
	}
	
	public ArrayList<String> getDisplayLines() {
		return displayDesc;
	}
}
