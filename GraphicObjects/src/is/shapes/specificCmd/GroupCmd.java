package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.shapes.model.GraphicObject;

import java.util.List;

public class GroupCmd implements Cmd {


    private final List<GraphicObject> grp;

    public GroupCmd(List<GraphicObject> grp){
        this.grp = grp;
    }



    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
