package is.system.cmd;

import is.system.shapes.specificCmd.AreaCmd;
import is.system.shapes.specificCmd.ListCmd;
import is.system.shapes.specificCmd.MementoCmd;
import is.system.shapes.specificCmd.PerimeterCmd;
import is.system.support.MyMouseAdapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HistoryCmdHandler implements CmdHandler {

	private final ArrayList<String> story = new ArrayList<String>();
	private int maxHistoryLength = 100;

	private final LinkedList<Cmd> history = new LinkedList<>();

	private final LinkedList<Cmd> redoList = new LinkedList<>();

	public HistoryCmdHandler() {
		this(100);
	}

	public HistoryCmdHandler(int maxHistoryLength) {

		if (maxHistoryLength < 0)
			throw new IllegalArgumentException();
		this.maxHistoryLength = maxHistoryLength;
	}

	public void handle(Cmd cmd) {

		flushMouse();
		story.add(cmd.toString());

		if (cmd.doIt()) {
			// restituisce true: può essere annullato
			addToHistory(cmd);
		} else {
			if(cmd instanceof ListCmd || cmd instanceof AreaCmd || cmd instanceof PerimeterCmd) return;
			// restituisce false: non può essere annullato
			history.clear();
		}
		if (redoList.size() > 0)
			redoList.clear();

		okStory();
		System.out.println(story.toString());
	}

	private void okStory() {
		String lastString = story.getLast();
		String updatedString = lastString + " OK \n";
		story.removeLast();story.addLast(updatedString);
	}


	public void redo() {
		flushMouse();
		if (redoList.size() > 0) {
			Cmd redoCmd = redoList.removeFirst();
			story.add("REDO "+redoCmd.toString());
			redoCmd.doIt();
			history.addFirst(redoCmd);
			okStory();
		}
	}

	public void undo() {
		if (history.size() > 0) {
			Cmd undoCmd = history.removeFirst();
			story.add("UNDO "+undoCmd.toString());
			undoCmd.undoIt();
			redoList.addFirst(undoCmd);
			okStory();
		}
	}

	private void addToHistory(Cmd cmd) {
		history.addFirst(cmd);
		if (history.size() > maxHistoryLength) {
			history.removeLast();
		}
	}


	public void handle(MementoCmd cmd) {

		if(history.getFirst().equals(cmd)){
			history.removeFirst();
		}
		addToHistory(cmd);
		if (redoList.size() > 0)
			redoList.clear();
	}


	private MyMouseAdapter mouseAdapter;
	public void setMouse( MyMouseAdapter mouseAdapter){
		this.mouseAdapter = mouseAdapter;
	}


	private void flushMouse(){
		if(mouseAdapter != null) mouseAdapter.flush();
	}


	public void printStory() {
		File file= new File("GraphicObjects/src/test/fileStory.txt");

		FileWriter writer = null;
		try {

			writer = new FileWriter(file, false);

			for (String str : story) {
				writer.write(str + System.lineSeparator());
			}
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
