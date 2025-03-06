package is.system.prompt.handlerChain;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import static is.system.support.Utility.startPosition;

public class CharHandler implements TextHandler{

    private final JTextArea outputArea;
    private final AtomicInteger lastLineIndex;
    private final TextHandler successor;

    public CharHandler(JTextArea outputArea, LinkedList<String> history, AtomicInteger index, AtomicInteger lastLineIndex) {
        this.outputArea = outputArea;
        this.lastLineIndex = lastLineIndex;
        this.successor = new DeleteHandler(outputArea,history,index,lastLineIndex);
    }

    @Override
    public boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException {
        char c = e.getKeyChar();
        if ((Character.isLetterOrDigit(c) || Character.isWhitespace(c)) || isOKSymbol(c)) {
            e.consume();
            int caretPosition = outputArea.getCaretPosition();
            if(caretPosition >= startPosition(outputArea,lastLineIndex.get()))
                outputArea.getDocument().insertString(caretPosition, String.valueOf(e.getKeyChar()), null);
            return false;
        }
        return successor.handleRequest(e);
    }

    private boolean isOKSymbol(char c) {
        switch (c) {
            case '!': case '@': case '#': case '$': case '%': case '^': case '&': case '*': case '(': case ')': case']': case'.': case',':
            case'[': case '_': case '+': case '{': case '}': case ':': case '"': case '<': case '>': case '?': case'/': case'-': return true;
            default:
                return false;
        }
    }
}
