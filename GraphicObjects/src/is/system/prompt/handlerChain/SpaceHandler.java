package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.startPosition;

public class SpaceHandler extends TextHandler{

    private final JTextArea outputArea;
    private final AtomicInteger lastLineIndex;

    public SpaceHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.lastLineIndex = lastLineIndex;
        this.successor = new CopyHandler(outputArea,history,index,lastLineIndex);
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            e.consume();
            String selectedText = outputArea.getSelectedText();
            if (selectedText != null) {
                int start = outputArea.getSelectionStart();
                int end = outputArea.getSelectionEnd();
                if (start >= startPosition(outputArea,lastLineIndex.get())) {
                    outputArea.getDocument().remove(start, end - start);
                }
            }else{
                int caretPosition = outputArea.getCaretPosition();
                if (caretPosition > startPosition(outputArea,lastLineIndex.get())) {
                    if (caretPosition > 0) {
                        outputArea.getDocument().remove(caretPosition -1, 1);
                    }
                }
            }
            return false;
        }
        return successor.handleRequest(e);
    }
}
