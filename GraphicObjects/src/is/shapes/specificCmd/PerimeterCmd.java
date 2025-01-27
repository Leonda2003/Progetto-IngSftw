package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterCmd implements Cmd {

    private final GraphicObjectPanel panel;
    private final GraphicObject object;

    public PerimeterCmd(GraphicObject object,GraphicObjectPanel panel){
        this.panel = panel;
        this.object = object;
    }

    @Override
    public boolean doIt() {
        //Stampa il perimetro sul pannello
        return true;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
