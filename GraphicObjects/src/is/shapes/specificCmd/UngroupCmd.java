package is.shapes.specificCmd;

import is.cmd.Cmd;

public class UngroupCmd implements Cmd {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
