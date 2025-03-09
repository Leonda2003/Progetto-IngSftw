package is.system.prompt.handlerChain;

import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public abstract class TextHandler {

    TextHandler successor;
    public abstract boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException;
}
