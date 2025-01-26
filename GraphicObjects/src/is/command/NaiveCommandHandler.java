package is.command;

public class NaiveCommandHandler implements CommandHandler{
    @Override
    public void handle(Cmd cmd) {
        cmd.doIt();
    }
}
