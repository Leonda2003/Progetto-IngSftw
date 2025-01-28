package is;

import is.cmd.HistoryCmdHandler;
import is.shapes.controller.GraphicObjectController;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.CircleObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;
import is.shapes.prompt.GraphicObjectCommandPrompt;
import is.shapes.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TestGraphics3 {

    public static void main(String[] args) {

        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();

        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        final HistoryCmdHandler handler = new HistoryCmdHandler();

        undoButt.addActionListener(evt -> handler.undo());

        redoButt.addActionListener(evt -> handler.redo());

        toolbar.add(undoButt);
        toolbar.add(redoButt);

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();


        gpanel.setPreferredSize(new Dimension(400, 400));

        GraphicObjectViewFactory.FACTORY.installView(RectangleObject.class, new RectangleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(CircleObject.class, new CircleObjectView());
        GraphicObjectViewFactory.FACTORY.installView(ImageObject.class, new ImageObjectView());

        AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);
        JButton rectButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        go = new CircleObject(new Point(200, 100), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        go = new CircleObject(new Point(200, 100), 100);
        JButton circButton2 = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton2.setText("big " + go.getType());
        toolbar.add(circButton2);

        go = new ImageObject(new ImageIcon(TestGraphics2.class.getResource("shapes/model/NyaNya.gif")),
                new Point(240, 187));

        JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

        final GraphicObjectController goc = new GraphicObjectController(handler);
        final GraphicObjectCommandPrompt gocp = new GraphicObjectCommandPrompt(handler,gpanel);
        gocp.setVisible(true);

        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });

        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);



        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        f.setTitle("Shapes");
        f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);

    }
}
