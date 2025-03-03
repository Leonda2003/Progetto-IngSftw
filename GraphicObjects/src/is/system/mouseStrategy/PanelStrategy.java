package is.system.mouseStrategy;


import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.specificCmd.NewObjectCmd;
import is.system.shapes.specificCmd.RemoveObjectCmd;
import is.system.shapes.view.GraphicObjectPanel;
import is.system.support.Pair;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static is.system.support.Utility.*;


public class PanelStrategy implements MouseStrategy{

    private static GraphicObject memorized;
    private final Pair<String,GraphicObject> pair;
    private final HistoryCmdHandler handler;
    private  final GraphicObjectPanel gpanel;
    private final JPopupMenu popup;
    private final JPanel panel;

    protected double x,y;

    public PanelStrategy(Pair<String,GraphicObject> pair, HistoryCmdHandler handler, GraphicObjectPanel gpanel, MouseEvent e) {
        this.pair = pair;
        this.handler = handler;
        this.gpanel = gpanel;
        x = e.getX();y = e.getY();
        popup = new JPopupMenu();
        panel = new JPanel();
    }

    @Override
    public void execute() {
        if(pair != null && pair.notEmpty()) objectButton();
        else {
            JTextField position = new JTextField(x+" "+y);
            position.setOpaque(false); position.setForeground(Color.white); position.setBorder(BorderFactory.createEmptyBorder());
            panel.add(position);
        }

        if(memorized != null){
            JButton button4 = new JButton("Paste");
            button4.addActionListener(evt -> {
                memorized.moveTo(x,y);
                handler.handle(new NewObjectCmd(gpanel,memorized));
                resetMemorized();
                popup.setVisible(false);
            });panel.add(makeButtonV2(button4));
        }

        setPopuop(popup,panel);
        popup.show(gpanel, (int) x-80, (int) y);
    }



    private void objectButton(){
        JButton button1 = new JButton("Copy");
        button1.addActionListener(evt->{
            setMemorized();
            popup.setVisible(false);
        });panel.add(makeButtonV2(button1));

        JButton button2 = new JButton("Cut");
        button2.addActionListener(evt -> {
            setMemorized();
            handler.handle(new RemoveObjectCmd(gpanel,pair.getKey()));
            popup.setVisible(false);
        });panel.add(makeButtonV2(button2));

        JButton button3 = new JButton("Delete");
        button3.addActionListener(evt ->{
            handler.handle(new RemoveObjectCmd(gpanel,pair.getKey()));
            popup.setVisible(false);
        });panel.add(makeButtonV2(button3));

        JButton button4 = new JButton("Info");
        button4.addActionListener(evt ->{
            JTextArea textArea = new JTextArea();
            textArea.setText(pair.getValue().properties(pair.getKey())+"\n"+
                    "mouse current position: "+x+" "+y);
            setText(textArea,panel,popup);
            popup.show(gpanel,(int)x-80,(int)y);
        });panel.add(makeButtonV2(button4));
    }




    private void setMemorized(){
        memorized = pair.getValue().clone();
    }

    private void resetMemorized(){
        memorized = null;
    }
}
