package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.endPosition;
import static is.system.support.Utility.startPosition;

public class PasteHandler extends TextHandler{
    private final JTextArea outputArea;
    private final AtomicInteger lastLineIndex;
    public PasteHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.lastLineIndex = lastLineIndex;
        this.successor = new HistoryHandler(outputArea,history,index,lastLineIndex);
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V){
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                String string = (String) clipboard.getData(DataFlavor.stringFlavor);
                String data = string.trim();
                int caretPosition = outputArea.getCaretPosition();
                if(caretPosition >= startPosition(outputArea,lastLineIndex.get())) outputArea.getDocument().insertString(caretPosition, data, null);
                outputArea.setCaretPosition(endPosition(outputArea,lastLineIndex.get()));
            }
            return false;
        }
        return successor.handleRequest(e);
    }
}
