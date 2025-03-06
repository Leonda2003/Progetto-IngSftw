package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class CopyHandler implements TextHandler{

    private final JTextArea outputArea;
    private final TextHandler successor;

    public CopyHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.successor = new PasteHandler(outputArea,history,index,lastLineIndex);
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C){
            String selectedText = outputArea.getSelectedText();
            if (selectedText != null) {
                StringSelection stringSelection = new StringSelection(selectedText);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            }
            return false;
        }
        return successor.handleRequest(e);
    }
}
