package is.shapes.specificcommand;

import is.command.Cmd;

public class GroupCmd implements Cmd {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
