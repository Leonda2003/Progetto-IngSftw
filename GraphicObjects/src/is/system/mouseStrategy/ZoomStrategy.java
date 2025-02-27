package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.specificCmd.MementoCmd;

import java.awt.event.MouseWheelEvent;

public class ZoomStrategy extends AbstractStrategy{


    private double factor;




    public ZoomStrategy( AbstractStrategy abstractStrategy,MouseWheelEvent e){

        super(abstractStrategy.graphicObject, abstractStrategy.handler);
        if(abstractStrategy instanceof ZoomStrategy) initialState = abstractStrategy.initialState;
        else initialState = graphicObject.save();
        System.out.println(initialState);
        if(e.getWheelRotation() > 0){
            factor = 0.9;
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
