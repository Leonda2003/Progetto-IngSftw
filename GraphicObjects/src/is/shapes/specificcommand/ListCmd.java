package is.shapes.specificcommand;

import is.cmd.Cmd;

public class ListCmd implements Cmd {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
