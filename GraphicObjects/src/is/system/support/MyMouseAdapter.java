package is.system.support;

import is.system.cmd.HistoryCmdHandler;
import is.system.mouseStrategy.*;
import is.system.shapes.controller.GraphicObjectController;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.view.GraphicObjectPanel;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MyMouseAdapter extends MouseAdapter {
    private AbstractStrategy mouseStrategy;
    private Pair<String, GraphicObject> pair;
    private boolean filled = false;
    private final HistoryCmdHandler handler;
    private final GraphicObjectPanel gpanel ;
    private final GraphicObjectController goc ;

    public MyMouseAdapter(HistoryCmdHandler handler, GraphicObjectPanel gpanel, GraphicObjectController goc) {
        this.handler = handler;
        this.gpanel = gpanel;
        this.goc = goc;
        handler.setMouse(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            new PanelStrategy(pair,handler,gpanel,e).execute();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pair = gpanel.getGraphicObjectAt(e.getPoint());
        gpanel.removeMouseWheelListener(this);
        if(pair.notEmpty()) {
            goc.setControlledObject(pair.getValue());
            if (e.getButton() == MouseEvent.BUTTON1) {
                filled = true;
                gpanel.addMouseWheelListener(this);
                gpanel.addMouseMotionListener(this);
                mouseStrategy = new PressedStrategy(pair, handler,e);
            }
        }else flush();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        gpanel.removeMouseMotionListener(this);
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseStrategy=new ZoomStrategy(mouseStrategy,e); mouseStrategy.execute();
    }
    @Override
    public void mouseDragged(MouseEvent e){
        new MoveStrategy(mouseStrategy,e).execute();
    }
    public void flush(){
        if(filled){
            pair = null;
            filled = false;
            gpanel.removeMouseWheelListener(this);
        }
    }
};
