package is.system;

import is.system.cmd.HistoryCmdHandler;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.view.*;
import is.system.support.MyMouseAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static is.system.support.Utility.*;

public class SystemInterface {
    private final HistoryCmdHandler handler;
    private final GraphicObjectPanel gpanel ;
    private final GraphicObjectPromptPanel prompt;
    private final GraphicObjectController goc ;
    private final JFrame f;
    private MouseAdapter mouseAdapter;
    private Settings settings;
    private final AtomicBoolean ctrl = new AtomicBoolean(false);
    private final AtomicBoolean prmt = new AtomicBoolean(false);
    private final AtomicBoolean ms = new AtomicBoolean(false);


    public SystemInterface(){
        handler = new HistoryCmdHandler();
        gpanel = new GraphicObjectPanel();
        prompt = new GraphicObjectPromptPanel(handler);
        goc = new GraphicObjectController(handler);
        f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public void configureSystem(){
        settings = new Settings(f);
    }

    private void inizializeSystem(){

        f.getContentPane().removeAll();
        instalFactory();
        gpanel.removeMouseListener(mouseAdapter);
        gpanel.removeMouseWheelListener(mouseAdapter);

        JToolBar toolbar = new JToolBar();
        setToolBar(toolbar,gpanel,handler);

        JButton option = new JButton();
        option.setText("⚙ ");
        option.addActionListener(evt -> settings.open());
        toolbar.add(option);

        Dimension screenSizes = Toolkit.getDefaultToolkit().getScreenSize();
        gpanel.setPreferredSize(new Dimension((int)(screenSizes.width*0.6),(int)(screenSizes.height*0.6)));

        JScrollPane panel = new JScrollPane(gpanel);

        if(ms.get())  mouseAdapter = new MyMouseAdapter(handler,gpanel,goc);
        else {
            mouseAdapter = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()).getValue());
                }
            };
        }
        gpanel.addMouseListener(mouseAdapter);

        f.add(setPanel(panel,goc,prompt,ctrl.get(),prmt.get()));
        f.add(toolbar, BorderLayout.NORTH);
        f.setTitle("Shapes");
        f.setResizable(true);
        f.pack();
        f.setLocation(center(f));
        f.setVisible(true);
    }


    private class Settings {

        JDialog window = new JDialog(f, "Panel Configuration", true);
        private final boolean[] isDefaultColor = {true,true,true};
        private final JPanel panel = new JPanel();

        public Settings(JFrame frame){
            window.setResizable(false);
            window.setSize(420, 140);
            window.setLocationRelativeTo(f);

            frame.setResizable(false);
            frame.setTitle("Panel Configuration");
            frame.setSize(420, 140);
            frame.setLocation(center(frame));

            inizialize();
            f.add(panel, BorderLayout.CENTER);
            f.setVisible(true);
        }

        public void open(){

            window.setLocationRelativeTo(f);
            window.add(panel, BorderLayout.CENTER);
            window.setVisible(true);
        }

        private void inizialize(){

            JTextArea messageLabel = new JTextArea("Choose the components to interact with the graphic panel");
            messageLabel.setOpaque(false);
            messageLabel.setEditable(false);
            panel.add(messageLabel);

            makeButton("Controller Component",isDefaultColor,ctrl,0,panel);
            makeButton("Prompt Component",isDefaultColor,prmt,1,panel);
            makeButton("Mouse",isDefaultColor,ms,2,panel);
            JButton confirm = new JButton("Confirm");
            messageLabel.setFont(confirm.getFont());
            confirm.addActionListener(evt -> {
                if(ctrl.get() || prmt.get() || ms.get()){
                    inizializeSystem();
                    window.setVisible(false);
                }else{
                    messageLabel.setText("Choose the components to interact with the graphic panel.\n" +
                            "Please choose at least one component.");
                }
            });
            panel.add(confirm);
            panel.add(confirm);
            panel.add(confirm);
        }

    }

}
