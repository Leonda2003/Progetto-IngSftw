package is.system.exception.cmd;

public class NaiveCmdHandler implements CmdHandler {
    @Override
    public void handle(Cmd cmd) {
        cmd.doIt();
    }
}
