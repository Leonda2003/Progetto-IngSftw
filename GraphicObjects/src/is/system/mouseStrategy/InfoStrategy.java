package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.support.Pair;

import javax.swing.*;

public class InfoStrategy implements MouseStrategy{

    private final JButton button;
    private final Pair<String,GraphicObject> pair;
    private final JPanel panel;

    public InfoStrategy(Pair pair, JPanel panel) {
        this.button = new JButton("Info");
        this.pair = pair;
        this.panel = panel;
    }

    @Override
    public void execute() {
        button.addActionListener(evt ->{
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setVisible(true);
            textArea.setText(pair.getValue().properties(pair.getKey()));
            panel.add(textArea);
            System.out.println("ciao");
        });
        panel.add(button);
    }
}
