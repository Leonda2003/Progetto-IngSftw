package is.system.support;

import is.system.SystemInterface;
import is.system.cmd.HistoryCmdHandler;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.model.AbstractGraphicObject;
import is.system.shapes.model.CircleObject;
import is.system.shapes.model.ImageObject;
import is.system.shapes.model.RectangleObject;
import is.system.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utility {

    public static void makeButtonV1(String title, boolean[] isDefaultColor, AtomicBoolean mes, int pos, JPanel panel){
        JButton button = new JButton(title);
        Color color = button.getBackground();
        button.addActionListener(evt -> {
            if(isDefaultColor[pos]){
                mes.set(true);
                button.setBackground(Color.LIGHT_GRAY);
            }else{
                mes.set(false);
                button.setBackground(color);
            }
            isDefaultColor[pos] = !isDefaultColor[pos];
        });
        panel.add(button);
    }

    public static JButton makeButtonV2(JButton button){

        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        return button;
    }

    public static Point center(Window frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.toString());
        System.out.println(frame.getSize().toString());
        int x = (screenSize.width/2)-frame.getWidth()/2;
        int y = (screenSize.height/2)-frame.getHeight()/2;
        return new Point(x,y);
    }

    public static void installObject(AbstractGraphicObject go, JToolBar toolbar, String property, GraphicObjectPanel gpanel, HistoryCmdHandler handler){
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(property+" "+ go.getType());
        toolbar.add(circButton);
    }

    public static void instalFactory(){
        GraphicObjectViewFactory.FACTORY.installView(RectangleObject .class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject .class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject .class, new ImageObjectView());
        }

    public static JComponent setPanel(JScrollPane panel, GraphicObjectController goc, GraphicObjectPromptPanel prompt,boolean ctrl, boolean prmt){
        JComponent splitPane=panel;
        if(ctrl && prmt ){
            JPanel controlPanel = new JPanel(new FlowLayout());
            controlPanel.add(goc);
            JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, goc);
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, prompt);
        }
        else if(ctrl){
            JPanel controlPanel = new JPanel(new FlowLayout());
            controlPanel.add(goc);
            splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, goc);
        }
        else if(prmt){
            splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, prompt);
        }

        return splitPane;
    }

    public static void setToolBar( JToolBar toolbar, GraphicObjectPanel gpanel, HistoryCmdHandler handler){

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");
        undoButt.addActionListener(evt -> handler.undo());
        redoButt.addActionListener(evt -> handler.redo());
        toolbar.add(undoButt);
        toolbar.add(redoButt);

        installObject(new RectangleObject(new Point(10, 10), 20, 50),toolbar,"",gpanel,handler);
        installObject(new CircleObject(new Point(10, 10), 10),toolbar,"",gpanel,handler);
        installObject(new CircleObject(new Point(10, 10), 100),toolbar,"big",gpanel,handler);
        installObject(new ImageObject(new ImageIcon(SystemInterface.class.getResource("shapes/model/NyaNya.gif")), new Point(240, 187)),toolbar,"",gpanel,handler);
        JButton showInfoButt = new JButton("Show Info");
        showInfoButt.addActionListener(evt -> gpanel.switchInfo());
        toolbar.add(showInfoButt);
    }

    public static  void setPopuop(JPopupMenu popup, JPanel panel){

        popup.setBackground(Color.DARK_GRAY);
        popup.setBorderPainted(false);

        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        popup.add(panel);
    }

    public static void setText(JTextArea textArea, JPanel panel, JPopupMenu popup){
        textArea.setEditable(true); textArea.setVisible(true);
        textArea.setOpaque(false); textArea.setForeground(Color.white);
        Dimension textAreaSize = textArea.getPreferredSize();
        textArea.setPreferredSize(new Dimension(textAreaSize.width, textAreaSize.height));

        panel.removeAll();
        panel.add(textArea);
        panel.setPreferredSize(new Dimension(textAreaSize.width + 20, textAreaSize.height + 20));
        popup.pack();popup.revalidate();popup.repaint();
    }


    public static void setSettings(JFrame frame, JDialog window){
        window.setResizable(false);
        window.setSize(420, 140);
        window.setLocationRelativeTo(frame);

        frame.setResizable(false);
        frame.setTitle("Panel Configuration");
        frame.setSize(420, 140);
        frame.setLocation(center(frame));
    }

    public static void packFrame(JFrame f){
        f.setTitle("Shapes");
        f.setResizable(true);
        f.pack();
        f.setLocation(center(f));
        f.setVisible(true);
    }
}
