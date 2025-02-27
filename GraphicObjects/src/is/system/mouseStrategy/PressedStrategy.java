package is.system.mouseStrategy;

import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;

import java.awt.event.MouseEvent;

public class PressedStrategy extends AbstractStrategy{



    public PressedStrategy(GraphicObject graphicObject, HistoryCmdHandler handler, MouseEvent e){
        super(graphicObject,handler);

        initialState = graphicObject.save();
        offsetX=e.getX()-graphicObject.getPosition().getX();
        offsetY=e.getY()-graphicObject.getPosition().getY();
    }

    @Override
    public void execute() {
    }
}
