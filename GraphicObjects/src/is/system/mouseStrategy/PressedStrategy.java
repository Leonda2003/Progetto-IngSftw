package is.system.mouseStrategy;

import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;
import is.system.support.Pair;

import java.awt.event.MouseEvent;

public class PressedStrategy extends AbstractStrategy{



    public PressedStrategy(Pair<String,GraphicObject> pair, HistoryCmdHandler handler, MouseEvent e){
        super(
            pair.getValue(),
            pair.getKey(),
            handler,
            pair.getValue().save(),
            e.getX()-pair.getValue().getPosition().getX(),
            e.getY()-pair.getValue().getPosition().getY());
    }

    public void ciao(){
    }

    @Override
    public void execute() {
    }
}
