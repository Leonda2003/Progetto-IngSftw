package is.system;

import is.system.cmd.HistoryCmdHandler;
import is.system.mouseStrategy.*;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.model.*;
import is.system.shapes.view.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.atomic.AtomicBoolean;


public class SystemInterface {

    private final HistoryCmdHandler handler;
    private final GraphicObjectPanel gpanel ;
    private final GraphicObjectPromptPanel prompt;
    private final GraphicObjectController goc ;
    private final JFrame f;
    private MouseAdapter mouseAdapter;


    public SystemInterface(){
        handler = new HistoryCmdHandler();
        gpanel = new GraphicObjectPanel();
        prompt = new GraphicObjectPromptPanel(handler);
        goc = new GraphicObjectController(handler);
        f = new JFrame();
    }

    private Point center(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.toString());
        System.out.println(frame.getSize().toString());
        int x = (screenSize.width/2)-frame.getWidth()/2;
        int y = (screenSize.height/2)-frame.getHeight()/2;
        return new Point(x,y);
    }

    public void configureSystem(){
        inizializeSystem(true, true, true);
        /*
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setTitle("Panel Configuration");
        f.setSize(420, 140);
        f.setLocation(center(f));
        option(f);

         */
    }

    private void option(Window dialog){

        JPanel panel = new JPanel();
        JTextArea messageLabel = new JTextArea("Choose the components to interact with the graphic panel");
        messageLabel.setOpaque(false);
        messageLabel.setEditable(false);
        panel.add(messageLabel);

        AtomicBoolean ctrl = new AtomicBoolean(false);
        AtomicBoolean prmt = new AtomicBoolean(false);
        AtomicBoolean ms = new AtomicBoolean(false);

        boolean[] isDefaultColor = {true,true,true};
        JButton controller = new JButton("Controller Component");
        JButton prompt = new JButton("Prompt Component");
        JButton mouse = new JButton("Mouse");
        JButton confirm = new JButton("Confirm");

        Color color = controller.getBackground();
        messageLabel.setFont(controller.getFont());

        controller.addActionListener(evt -> {
            if(isDefaultColor[0]){
                ctrl.set(true);
                controller.setBackground(Color.LIGHT_GRAY);
            }else{
                ctrl.set(false);
                controller.setBackground(color);
            }
            isDefaultColor[0] = ! isDefaultColor[0];
        });
        prompt.addActionListener(evt -> {
            if(isDefaultColor[1]){
                prmt.set(true);
                prompt.setBackground(Color.LIGHT_GRAY);
            }else{
                prmt.set(false);
                prompt.setBackground(color);
            }
            isDefaultColor[1] = ! isDefaultColor[1];
        });
        mouse.addActionListener(evt -> {
            if(isDefaultColor[2]){
                ms.set(true);
                mouse.setBackground(Color.LIGHT_GRAY);
            }else{
                ms.set(false);
                mouse.setBackground(color);
            }
            isDefaultColor[2] = ! isDefaultColor[2];
        });

        confirm.addActionListener(evt -> {
            if(ctrl.get() || prmt.get() || ms.get()){
                inizializeSystem(ctrl.get(), prmt.get(), ms.get());
                if(dialog instanceof JDialog) dialog.dispose();
            }else{
                messageLabel.setText("Choose the components to interact with the graphic panel.\n" +
                        "Please choose at least one component.");
            }
        });

        panel.add(controller);;panel.add(mouse);panel.add(prompt);panel.add(confirm);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void inizializeSystem(boolean ctrl,boolean prmt,boolean ms){

        f.getContentPane().removeAll();
        gpanel.removeMouseListener(mouseAdapter);
        gpanel.removeMouseWheelListener(mouseAdapter);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar toolbar = new JToolBar();
        JButton undoButt = new JButton("Undo");
        JButton redoButt = new JButton("Redo");

        undoButt.addActionListener(evt -> handler.undo());
        redoButt.addActionListener(evt -> handler.redo());
        toolbar.add(undoButt);
        toolbar.add(redoButt);

        Dimension screenSizes = Toolkit.getDefaultToolkit().getScreenSize();
        gpanel.setPreferredSize(new Dimension((int)(screenSizes.width*0.6),(int)(screenSizes.height*0.6)));

        JScrollPane panel = new JScrollPane(gpanel);
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


        if(ms)  mouseAdapter = new MyMouseAdapter();
        else mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        };

        gpanel.addMouseListener(mouseAdapter);

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


        JButton option = new JButton();
        option.setIcon(new ImageIcon(SystemInterface.class.getResource("shapes/model/settings.gif ")));

        option.addActionListener(evt -> {
            JDialog dialog = new JDialog(f, "Panel Configuration", true);
            dialog.setResizable(false);
            dialog.setSize(420, 140);
            dialog.setLocationRelativeTo(f);
            option(dialog);
        });
        toolbar.add(option);

        f.add(toolbar, BorderLayout.NORTH);
        f.add(splitPane);
        f.setTitle("Shapes");
        f.setResizable(true);
        f.pack();
        f.setPreferredSize(f.getPreferredSize());
        f.setLocation(center(f));
        f.setVisible(true);
    }

    public class MyMouseAdapter extends MouseAdapter {
        private AbstractStrategy mouseStrategy;
        private GraphicObject graphicObject;
        private boolean filled = false;
        public MyMouseAdapter(){
            handler.setMouse(this);
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            if(graphicObject != null || e.getButton() == MouseEvent.BUTTON3) {
                new PanelStrategy().execute();
            }else flush();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            graphicObject = gpanel.getGraphicObjectAt(e.getPoint());
            if(graphicObject != null) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    filled = true;
                    goc.setControlledObject(graphicObject);
                    gpanel.addMouseWheelListener(this);
                    gpanel.addMouseMotionListener(this);
                    mouseStrategy = new PressedStrategy(graphicObject, handler,e);
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e){
            gpanel.removeMouseMotionListener(this);
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            mouseStrategy=new ZoomStrategy(mouseStrategy,e); mouseStrategy.execute();
        }
        public void mouseDragged(MouseEvent e){
            new MoveStrategy(mouseStrategy,e).execute();
        }
        public void flush(){
            if(filled){
                graphicObject = null;
                filled = false;
                gpanel.removeMouseWheelListener(this);
            }
        }
    };

}
