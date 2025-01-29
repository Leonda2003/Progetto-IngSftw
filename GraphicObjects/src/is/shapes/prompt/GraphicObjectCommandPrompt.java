package is.shapes.prompt;

import is.cmd.CmdHandler;
import is.exception.SyntaxException;
import is.interpreterCommand.Command;
import is.parser.ConcreteFactoryParser;
import is.parser.FactoryParser;
import is.shapes.view.GraphicObjectPanel;
import is.visitor.CommandVisitor;
import is.visitor.Visitor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;


public class GraphicObjectCommandPrompt extends JFrame {

    private JTextArea outputArea;
    private JTextField inputField;
    private FactoryParser parser;
    private Visitor visitor;
    private final CmdHandler cmdHandler;
    private final GraphicObjectPanel graphicObjectPanel;

    public GraphicObjectCommandPrompt(CmdHandler cmdH,GraphicObjectPanel panel) {

        cmdHandler = cmdH;
        graphicObjectPanel = panel;

        visitor = new CommandVisitor(graphicObjectPanel,cmdHandler,this);

        setTitle("Command Prompt");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);


        inputField = new JTextField();

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = inputField.getText();
                processCommand(command);
                inputField.setText("");
            }
        });


        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);
    }

    private void processCommand(String command) {
        try{
            StringReader sr = new StringReader(command);
            parser = new ConcreteFactoryParser(sr);
            Command realCommand = parser.getCommandToInterpret();
            realCommand.accept(visitor);
        }catch (SyntaxException e){outputArea.append(e.toString()+"\n");}
        catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String s){
        outputArea.append(s+"\n");
    }

    public static void main(String[] args) {

    }
}
