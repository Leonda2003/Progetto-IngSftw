package is.prompt;

import is.cmd.CmdHandler;
import is.exception.SyntaxException;
import is.prompt.grammarCommand.Command;
import is.prompt.parser.ConcreteFactoryParser;
import is.prompt.parser.FactoryParser;
import is.prompt.visitor.Context;
import is.prompt.visitor.CommandVisitor;
import is.prompt.visitor.Visitor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


public class GraphicObjectPromptPanel extends JComponent {

    private JTextArea outputArea;
    private FactoryParser parser;
    private Visitor visitor;
    private final CmdHandler cmdHandler;
    private int lastLineIndex = 0;
    private final ArrayList<String> history = new ArrayList<>();
    private int index = 0;
    private final String prompt=">  ";
    private final String offset="   ";

    public GraphicObjectPromptPanel(CmdHandler cmdH) {

        cmdHandler = cmdH;
        visitor = new CommandVisitor(cmdHandler);
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.append("> ");
        outputArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        history.add("");


        outputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    char c = e.getKeyChar();
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        e.consume();
                        String command = outputArea.getText(startPosition(), endPosition()-startPosition());
                        if(command.trim().equalsIgnoreCase("clear")){
                            outputArea.setText(">  ");
                        }else{
                            processCommand(command);
                        }
                        lastLineIndex = outputArea.getLineCount() - 1;
                        outputArea.setCaretPosition(startPosition());

                    } else if ((Character.isLetterOrDigit(c) || Character.isWhitespace(c)) || isOKSymbol(c)) {
                        e.consume();
                        int caretPosition = outputArea.getCaretPosition();
                        if(caretPosition >= startPosition()) outputArea.getDocument().insertString(caretPosition, String.valueOf(e.getKeyChar()), null);

                    } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        e.consume();
                        int caretPosition = outputArea.getCaretPosition();
                        if (caretPosition >= startPosition()) {
                            if (caretPosition > 0 && caretPosition < endPosition() ) {
                                outputArea.getDocument().remove(caretPosition, 1);
                            }
                        }
                    }else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        e.consume();
                        String selectedText = outputArea.getSelectedText();
                        if (selectedText != null) {
                            int start = outputArea.getSelectionStart();
                            int end = outputArea.getSelectionEnd();
                            if (start >= startPosition()) {
                                outputArea.getDocument().remove(start, end - start);
                            }
                        }else{
                            int caretPosition = outputArea.getCaretPosition();
                            if (caretPosition > startPosition()) {
                                if (caretPosition > 0) {
                                    outputArea.getDocument().remove(caretPosition -1, 1);
                                }
                            }
                        }
                    }
                    else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C){
                        String selectedText = outputArea.getSelectedText();
                        if (selectedText != null) {
                            StringSelection stringSelection = new StringSelection(selectedText);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(stringSelection, null);
                        }
                    }else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V){
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
                            String string = (String) clipboard.getData(DataFlavor.stringFlavor);
                            String data = string.trim();
                            int caretPosition = outputArea.getCaretPosition();
                            if(caretPosition >= startPosition()) outputArea.getDocument().insertString(caretPosition, data, null);
                            outputArea.setCaretPosition(endPosition());
                        }
                    }else if(e.getKeyCode() == KeyEvent.VK_UP){
                        e.consume();
                        if(index >-1 && index < history.size()-1){
                            index++;
                            outputArea.getDocument().remove(startPosition(),endPosition()-startPosition());
                            outputArea.append(history.get(index));

                        }

                    }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                        e.consume();
                        if(index > 0 && index < history.size()){
                            index--;
                            outputArea.getDocument().remove(startPosition(),endPosition()-startPosition());
                            outputArea.append(history.get(index));
                        }
                    }

                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedFlavorException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        Context.CONTEXT.setGraphicObjectPromptPanel(this);



    }

    private void processCommand(String command){
        try{
            index = 0;
            if( history.size()==1 || !(history.get(1).equals(command.trim()))){
                history.removeFirst();
                history.addFirst(command.trim());
                history.addFirst("");
            }
            StringReader sr = new StringReader(command);
            parser = new ConcreteFactoryParser(sr);
            Command realCommand = parser.getCommandToInterpret();
            realCommand.accept(visitor);

        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
           write(e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch ( Exception e){
            e.printStackTrace();
            write(e.toString());
        }
    }


    public Dimension getPreferredSize() {
        return new Dimension(500, 600);
    }


    public void write(String s)  {
        outputArea.append("\n\n"+offset+s+"\n\n"+prompt);
        lastLineIndex = outputArea.getLineCount() - 1;
        try {
            outputArea.setCaretPosition(startPosition());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearLine()  {
        try {
            outputArea.getDocument().remove(startPosition(),endPosition()-startPosition());
            outputArea.setCaretPosition(startPosition());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isOKSymbol(char c) {
        switch (c) {
            case '!': case '@': case '#': case '$': case '%': case '^': case '&': case '*': case '(': case ')': case']': case'.': case',':
                case'[': case '_': case '+': case '{': case '}': case ':': case '"': case '<': case '>': case '?': case'/': case'-': return true;
            default:
                return false;
        }
    }

    private int startPosition() throws BadLocationException {
        return outputArea.getLineStartOffset(lastLineIndex)+2;
    }

    private int endPosition() throws BadLocationException {
        return outputArea.getLineEndOffset(lastLineIndex);
    }

}
