package is.system.prompt.handlerChain;

import javax.swing.text.BadLocationException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

public interface TextHandler {

    boolean handleRequest(KeyEvent e) throws BadLocationException, IOException, UnsupportedFlavorException;
}
