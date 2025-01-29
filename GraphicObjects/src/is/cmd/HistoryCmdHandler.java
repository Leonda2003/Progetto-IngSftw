package is.cmd;

import java.util.LinkedList;

public class HistoryCmdHandler implements CmdHandler {

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

		if (cmd.doIt()) {
			// restituisce true: può essere annullato
			addToHistory(cmd);
		} else {
			// restituisce false: non può essere annullato
			history.clear();
		}
		if (redoList.size() > 0)
			redoList.clear();
	}



	public void redo() {
		if (redoList.size() > 0) {
			Cmd redoCmd = redoList.removeFirst();
			redoCmd.doIt();
			history.addFirst(redoCmd);
		}
	}

	public void undo() {
		if (history.size() > 0) {
			Cmd undoCmd = history.removeFirst();
			undoCmd.undoIt();
			redoList.addFirst(undoCmd);
		}
	}

	private void addToHistory(Cmd cmd) {
		history.addFirst(cmd);
		if (history.size() > maxHistoryLength) {
			history.removeLast();
		}

	}

}
