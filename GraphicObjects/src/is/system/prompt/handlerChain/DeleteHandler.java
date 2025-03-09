package is.system.prompt.handlerChain;

import is.system.prompt.grammarCommand.area.AreaTypeGrammarCommand;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.endPosition;
import static is.system.support.Utility.startPosition;

public class DeleteHandler extends TextHandler{

    private final JTextArea outputArea;
    private final AtomicInteger lastLineIndex;


    public DeleteHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.lastLineIndex = lastLineIndex;
        this.successor = new SpaceHandler(outputArea,history,index,lastLineIndex);
    }
    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {

        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            e.consume();
            int caretPosition = outputArea.getCaretPosition();
            if (caretPosition >= startPosition(outputArea,lastLineIndex.get())) {
                if (caretPosition > 0 && caretPosition < endPosition(outputArea,lastLineIndex.get()) )
                    outputArea.getDocument().remove(caretPosition, 1);
            }
            return false;
        }
        return successor.handleRequest(e);
    }
}
