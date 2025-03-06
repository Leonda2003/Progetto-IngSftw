package is.system.prompt;

import is.system.cmd.CmdHandler;
import is.system.exception.SyntaxException;
import is.system.prompt.grammarCommand.GrammarCommand;
import is.system.prompt.handlerChain.EnterHandler;
import is.system.prompt.handlerChain.TextHandler;
import is.system.prompt.parser.ConcreteBuilderParser;
import is.system.prompt.parser.BuilderParser;
import is.system.prompt.visitor.Context;
import is.system.prompt.visitor.CommandVisitor;
import is.system.prompt.visitor.Visitor;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


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
import java.util.concurrent.atomic.AtomicInteger;

import static is.system.support.Utility.endPosition;
import static is.system.support.Utility.startPosition;


public class GraphicObjectPromptPanel extends JComponent {

    private final AtomicInteger lastLineIndex = new AtomicInteger(0),index = new AtomicInteger(0);
    private final JTextArea outputArea = new JTextArea();
    private final JScrollPane scrollPane=new JScrollPane(outputArea);;
    private final BuilderParser parser = new ConcreteBuilderParser();
    private final LinkedList<String> history = new LinkedList<>();
    private final TextHandler textHandler = new EnterHandler(outputArea,history,index,lastLineIndex);
    private final String prompt=">  ";
    private final String offset="   ";
    private final Visitor visitor;


    public GraphicObjectPromptPanel(CmdHandler cmdH) {

        visitor = new CommandVisitor(cmdH);

        outputArea.setEditable(false);
        outputArea.append("> ");
        outputArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 80, 0));

        history.add("");


        outputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    if(textHandler.handleRequest(e)){
                        int start = startPosition(outputArea,lastLineIndex.get());
                        int end = endPosition(outputArea,lastLineIndex.get());
                        processCommand(outputArea.getText(start, end-start));
                    }
                } catch (BadLocationException | IOException | UnsupportedFlavorException ex) {
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
            index.set(0);
            if( history.size()==1 || !(history.get(1).equals(command.trim()))){
                history.removeFirst();
                history.addFirst(command.trim());
                history.addFirst("");
            }
            GrammarCommand realGrammarCommand = parser.getCommandToInterpret(command);
            realGrammarCommand.accept(visitor);

        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
           write(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch ( SyntaxException e){
            e.printStackTrace();
            write(e.getMessage());
        }
    }


    public Dimension getPreferredSize() {
        return new Dimension(500, 600);
    }


    public void write(String s)  {
        outputArea.append("\n\n"+offset+s+"\n\n"+prompt);
        lastLineIndex.set(outputArea.getLineCount() - 1);
        try {
            outputArea.setCaretPosition(startPosition(outputArea,lastLineIndex.get()));
            SwingUtilities.invokeLater(() -> {
                int scrollHeight = outputArea.getHeight();
                scrollPane.getVerticalScrollBar().setValue(scrollHeight);
            });
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearLine()  {
        try {
            int start = startPosition(outputArea,lastLineIndex.get());
            int end = endPosition(outputArea,lastLineIndex.get());
            outputArea.getDocument().remove(start,end-start);
            outputArea.setCaretPosition(startPosition(outputArea,lastLineIndex.get()));
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

}
