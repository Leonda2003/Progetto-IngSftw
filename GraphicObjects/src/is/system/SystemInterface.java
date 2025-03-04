package is.system;

import is.system.cmd.HistoryCmdHandler;
import is.system.prompt.GraphicObjectPromptPanel;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.view.*;
import is.system.support.MyMouseAdapter;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import static is.system.support.Utility.*;

public class SystemInterface {
    private final HistoryCmdHandler handler;
    private final GraphicObjectPanel gpanel;
    private final GraphicObjectPromptPanel prompt;
    private final GraphicObjectController goc;
    private final JFrame f;
    private MouseAdapter mouseAdapter;
    private final Settings settings;
    private final AtomicBoolean ctrl = new AtomicBoolean(false);
    private final AtomicBoolean prmt = new AtomicBoolean(false);
    private final AtomicBoolean ms = new AtomicBoolean(false);
    private final KeyEventDispatcher k = new KeyEventDispatcher() {
        public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED){
                    if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {handler.undo();}
                    else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {handler.redo();}
                }return false;
        }};


    public SystemInterface(){
        handler = new HistoryCmdHandler();
        gpanel = new GraphicObjectPanel();
        prompt = new GraphicObjectPromptPanel(handler);
        goc = new GraphicObjectController(handler);
        f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handler.printStory();
            }
        });
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(k);
        settings = new Settings();
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
        packFrame(f);
    }


    private final class Settings {

        private final JDialog window = new JDialog(f, "Panel Configuration", true);
        private final boolean[] isDefaultColor = {true,true,true};
        private final JPanel panel = new JPanel();

        public Settings(){

           setSettings(f,window);
           inizializeSettings();
           f.add(panel, BorderLayout.CENTER);
           f.setVisible(true);
        }

        public void open(){

            window.setLocationRelativeTo(f);
            window.add(panel, BorderLayout.CENTER);
            window.setVisible(true);
        }

        private void inizializeSettings(){

            JTextArea messageLabel = new JTextArea("Choose the components to interact with the graphic panel");
            messageLabel.setOpaque(false);
            messageLabel.setEditable(false);
            panel.add(messageLabel);

            makeButtonV1("Controller Component",isDefaultColor,ctrl,0,panel);
            makeButtonV1("Prompt Component",isDefaultColor,prmt,1,panel);
            makeButtonV1("Mouse",isDefaultColor,ms,2,panel);

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
        }

    }

}
