package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;

import javax.swing.*;
import java.awt.*;

public class PanelStrategy extends AbstractStrategy{

    public PanelStrategy(GraphicObject graphicObject, HistoryCmdHandler handler) {
        super(graphicObject, handler);
    }

    @Override
    public void execute() {

    }

    private void createBanner() {
        JDialog banner = new JDialog(f,"ciao",true);
        banner.setVisible(true);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        JButton button1 = new JButton("Copy",new CopyStrategy());
        JButton button2 = new JButton("Cut",new CutStrategy());
        JButton button3 = new JButton("Delete",new DeleteStrategy());
        JButton button4 = new JButton("Info",new InfoStrategy());
        JButton button5 = new JButton("Paste",new PasteStrategy());
    }
}
