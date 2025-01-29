package is.shapes.specificCmd;

import is.analyzer.Token;
import is.cmd.Cmd;
import is.shapes.model.GraphicObject;
import is.shapes.view.GraphicObjectPanel;

public class ListCmd implements Cmd {

    ;
    private final String id;

    private final Token token;

    public ListCmd(String id,Token t){
        this.id = id;
        this.token = t;
    }

    public ListCmd(Token t){
        this.token = t;
        id= "null";
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
