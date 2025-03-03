package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.specificCmd.MementoCmd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MoveStrategy extends AbstractStrategy{

    private final double x,y;

    public MoveStrategy(AbstractStrategy abstractStrategy, MouseEvent e){
        super(
            abstractStrategy.graphicObject,
            abstractStrategy.id,
            abstractStrategy.handler,
            abstractStrategy.initialState,
            abstractStrategy.offsetX,
            abstractStrategy.offsetY)
        ;
        x=e.getX(); y=e.getY();
    }

    @Override
    public void execute() {
        graphicObject.moveTo(x-offsetX,y-offsetY);
        handler.handle(new MementoCmd(graphicObject,initialState));
    }
}
