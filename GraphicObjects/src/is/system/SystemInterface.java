package is.system;

import is.TestGraphics2;
import is.system.exception.cmd.HistoryCmdHandler;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.model.*;
import is.system.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SystemInterface {

    private final HistoryCmdHandler handler;
    private final GraphicObjectPanel gpanel ;
    private final GraphicObjectPromptPanel prompt;
    private final GraphicObjectController goc ;
    private final JFrame f;

    public SystemInterface(){
        handler = new HistoryCmdHandler();
        gpanel = new GraphicObjectPanel();
        prompt = new GraphicObjectPromptPanel(handler);
        goc = new GraphicObjectController(handler);
        f = new JFrame();
    }

    public void startSystem(){


        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        undoButt.addActionListener(evt -> handler.undo());

        redoButt.addActionListener(evt -> handler.redo());

        toolbar.add(undoButt);
        toolbar.add(redoButt);

        gpanel.setPreferredSize(new Dimension(1100, 800));

        JScrollPane panel = new JScrollPane(gpanel);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.add(goc);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel, goc);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, prompt);

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(10, 10), 20, 50);
        JButton rectButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        go = new CircleObject(new Point(10, 10), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        go = new CircleObject(new Point(10, 10), 100);
        JButton circButton2 = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton2.setText("big " + go.getType());
        toolbar.add(circButton2);

        go = new ImageObject(new ImageIcon(SystemInterface.class.getResource("shapes/model/NyaNya.gif")), new Point(240, 187));
        JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });

        f.add(splitPane2);
        f.add(toolbar, BorderLayout.NORTH);

        f.setTitle("Shapes");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new SystemInterface();
    }
}
