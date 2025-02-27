package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.Memento;
import is.system.shapes.specificCmd.MementoCmd;

import java.util.logging.Handler;

public abstract class AbstractStrategy implements MouseStrategy{

    protected Memento initialState;
    protected GraphicObject graphicObject;
    protected HistoryCmdHandler handler;
    protected double offsetX,offsetY;

    public AbstractStrategy(GraphicObject graphicObject, HistoryCmdHandler handler){
        this.graphicObject= graphicObject;
        this.handler = handler;
    }



}
