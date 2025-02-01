package is.shapes.specificCmd;

import is.cmd.Cmd;
import is.prompt.visitor.Context;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class UngroupCmd implements Cmd {

    private final GroupObject go;

    private final String id;

    public UngroupCmd(GroupObject go, String id) {
        this.id = id;
        this.go = go;
    }

    @Override
    public boolean doIt() {
        Context.CONTEXT.removeTheGroup(id,go);
        return true;
    }

    @Override
    public boolean undoIt() {
        Context.CONTEXT.addRemovedGroup(id,go);
        return true;
    }
}
