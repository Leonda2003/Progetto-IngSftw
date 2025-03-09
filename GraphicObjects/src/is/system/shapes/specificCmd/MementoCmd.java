package is.system.shapes.specificCmd;

import is.system.cmd.Cmd;
import is.system.shapes.model.GraphicObject;
import is.system.shapes.model.Memento;

public class MementoCmd implements Cmd {

    private final GraphicObject graphicObject;
    private final Memento finalState;
    private final Memento initialState;
    public MementoCmd(GraphicObject graphicObject, Memento initialState){
        this.graphicObject = graphicObject;
        this.finalState = graphicObject.save();
        this.initialState = initialState;
    }

    @Override
    public boolean doIt() {
        graphicObject.restore(finalState);
        return true;
    }

    @Override
    public boolean undoIt() {
        graphicObject.restore(initialState);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MementoCmd)) return false;
        if(obj == this) return true;
        MementoCmd cmd = (MementoCmd) obj;
        return (cmd.graphicObject == graphicObject && cmd.initialState == initialState);
    }

    @Override
    public String toString() {
        return "memento command "+graphicObject.toString()+" initial state: "+initialState.toString()+" final state: "+finalState.toString();
    }
}
