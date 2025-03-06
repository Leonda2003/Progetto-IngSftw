package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.endPosition;
import static is.system.support.Utility.startPosition;

public class HistoryHandler implements TextHandler{

    private final JTextArea outputArea;
    private final LinkedList<String> history;
    private final AtomicInteger index,lastLineIndex;

    public HistoryHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.history = history;
        this.index = index;
        this.lastLineIndex = lastLineIndex;
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException {
        int start = startPosition(outputArea,lastLineIndex.get());
        int end = endPosition(outputArea,lastLineIndex.get());
        if(e.getKeyCode() == KeyEvent.VK_UP){
            e.consume();
            if(index.get() >-1 && index.get() < history.size()-1){
                index.incrementAndGet();
                outputArea.getDocument().remove(start,end-start);
                outputArea.append(history.get(index.get()));
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            e.consume();
            if(index.get() > 0 && index.get() < history.size()){
                index.decrementAndGet();
                outputArea.getDocument().remove(start,end-start);
                outputArea.append(history.get(index.get()));
            }
        }
        return false;
    }
}
