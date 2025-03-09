package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.endPosition;
import static is.system.support.Utility.startPosition;

public class EnterHandler extends TextHandler{

    private final JTextArea outputArea;
    private final AtomicInteger lastLineIndex;

    public EnterHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.lastLineIndex = lastLineIndex;
        successor = new CharHandler(outputArea,history,index,lastLineIndex);
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            int start = startPosition(outputArea,lastLineIndex.get());
            int end = endPosition(outputArea,lastLineIndex.get());
            String command = outputArea.getText(start, end-start);
            if(command.trim().equalsIgnoreCase("clear")){
                outputArea.setText("> ");
            }else{
                return true;
            }
            lastLineIndex.set(outputArea.getLineCount()-1);
            outputArea.setCaretPosition(startPosition(outputArea,lastLineIndex.get()));
            return false;
        }
        return successor.handleRequest(e);
    }
}
