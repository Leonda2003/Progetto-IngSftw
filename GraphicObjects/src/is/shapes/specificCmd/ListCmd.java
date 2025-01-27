package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class ListCmd implements Cmd {
    private final GraphicObjectPanel panel;
    private final GraphicObject object;

    public AreaCmd(GraphicObject object,GraphicObjectPanel panel){
        this.panel = panel;
        this.object = object;
    }

    public AreaCmd(,GraphicObjectPanel panel){
        this.panel = panel;
        this.object = object;
    }

    public AreaCmd(GraphicObject object,GraphicObjectPanel panel){
        this.panel = panel;
        this.object = object;
    }

    @Override
    public boolean doIt() {
        //Stampa l'area sul pannello
        return true;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
