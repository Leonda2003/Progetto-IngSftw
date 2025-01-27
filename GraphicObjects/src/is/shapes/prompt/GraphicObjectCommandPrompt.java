package is.shapes.prompt;

import is.exception.SyntaxException;
import is.interpreterCommand.Command;
import is.parser.ConcreteFactoryParser;
import is.parser.FactoryParser;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.io.StringReader;
import java.util.Scanner;


public class GraphicObjectCommandPrompt extends JFrame {

    private JTextArea outputArea;
    private JTextField inputField;

    public GraphicObjectCommandPrompt() {
        setTitle("Command Prompt Example");
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
            FactoryParser parser = new ConcreteFactoryParser(sr);
            Command command1 = parser.getCommandToInterpret();
            outputArea.append(command1.toString()+"\n");
        }catch (SyntaxException e){outputArea.append(e.toString());}
    }

    public static void main(String[] args) {
        new GraphicObjectCommandPrompt().setVisible(true);
    }
}
