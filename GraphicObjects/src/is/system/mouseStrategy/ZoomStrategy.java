package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.specificCmd.MementoCmd;

import java.awt.event.MouseWheelEvent;

public class ZoomStrategy extends AbstractStrategy{


    private double factor;

    public ZoomStrategy( AbstractStrategy abstractStrategy,MouseWheelEvent e){

        super(
            abstractStrategy.graphicObject,
            abstractStrategy.id,
            abstractStrategy.handler,
            abstractStrategy instanceof ZoomStrategy ?
                    abstractStrategy.initialState : abstractStrategy.graphicObject.save(),
            abstractStrategy.offsetX,
            abstractStrategy.offsetY
        );
        if(e.getWheelRotation() > 0){
            factor = 1/1.1;
        }else{
            factor = 1.1;
        }
    }


    @Override
    public void execute() {
        graphicObject.scale(factor);
        handler.handle(new MementoCmd(graphicObject,initialState));
    }

}
