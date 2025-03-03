package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.Memento;
import is.system.shapes.specificCmd.MementoCmd;

import java.util.logging.Handler;

public abstract class AbstractStrategy implements MouseStrategy{

    protected final Memento initialState;
    protected final GraphicObject graphicObject;
    protected final String id;
    protected final HistoryCmdHandler handler;
    protected double offsetX,offsetY;

    public AbstractStrategy(GraphicObject graphicObject, String id, HistoryCmdHandler handler,Memento initialState,double offsetX, double offsetY){
        this.graphicObject= graphicObject;
        this.id = id;
        this.handler = handler;
        this.initialState = initialState;
        this.offsetX =  offsetX;
        this.offsetY =  offsetY;
    }



}
