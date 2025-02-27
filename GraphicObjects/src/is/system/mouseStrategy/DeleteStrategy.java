package is.system.mouseStrategy;

import is.system.cmd.CmdHandler;
import is.system.cmd.HistoryCmdHandler;
import is.system.shapes.model.GraphicObject;

public class DeleteStrategy extends AbstractStrategy{

    public DeleteStrategy(GraphicObject graphicObject, HistoryCmdHandler handler) {
        super(graphicObject, handler);
    }

    @Override
    public void execute() {

    }
}
