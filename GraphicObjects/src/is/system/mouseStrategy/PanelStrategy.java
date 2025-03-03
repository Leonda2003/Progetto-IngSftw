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


public class PanelStrategy implements MouseStrategy{

    private static GraphicObject memorized;
    private final Pair<String,GraphicObject> pair;
    private final HistoryCmdHandler handler;
    private  GraphicObjectPanel gpanel;
    private final JPopupMenu popup;

    protected double x,y;

    public PanelStrategy(Pair<String,GraphicObject> pair, HistoryCmdHandler handler, GraphicObjectPanel gpanel, MouseEvent e) {
        this.pair = pair;
        this.handler = handler;
        this.gpanel = gpanel;
        x = e.getX();y = e.getY();
        popup = new JPopupMenu();
        popup.setBackground(Color.DARK_GRAY);
        popup.setBorderPainted(false);

    }

    @Override
    public void execute() {
        if(pair != null) setButton(pair.notEmpty());

        popup.show(gpanel, (int) x-30, (int) y-30);
        System.out.println(x+" "+y);
    }

    private void setButton(boolean onObject){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        if(onObject){
            JButton button1 = new JButton("Copy");
            button1.addActionListener(evt->{
                setMemorized();
                popup.setVisible(false);
            });
            panel.add(button1);

            JButton button2 = new JButton("Cut");
            button2.addActionListener(evt -> {
                setMemorized();
                handler.handle(new RemoveObjectCmd(gpanel,pair.getKey()));
                popup.setVisible(false);
            });
            panel.add(button2);

            JButton button3 = new JButton("Delete");
            button3.addActionListener(evt ->{
                setMemorized();
                handler.handle(new RemoveObjectCmd(gpanel,pair.getKey()));
                popup.setVisible(false);
            });
            panel.add(button3);
            new InfoStrategy(pair,panel).execute();
        }
        if(memorized != null){
            JButton button4 = new JButton("Paste");
            button4.addActionListener(evt -> {
                memorized.moveTo(x,y);
                handler.handle(new NewObjectCmd(gpanel,memorized));
                resetMemorized();
                popup.setVisible(false);
            });
            panel.add(button4);
        }
        JTextField text = new JTextField(x+" "+y);
        text.setOpaque(false); text.setBorder(BorderFactory.createEmptyBorder());
        text.setEditable(false);text.setForeground(Color.WHITE);
        panel.add(text);
        popup.add(panel);
    }

    private void setMemorized(){
        memorized = pair.getValue().clone();
    }

    private void resetMemorized(){
        memorized = null;
    }
}
