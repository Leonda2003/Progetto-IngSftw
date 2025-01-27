package is.shapes.specificcommand;

import is.cmd.Cmd;

public class RemoveObjectCmd implements Cmd {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
