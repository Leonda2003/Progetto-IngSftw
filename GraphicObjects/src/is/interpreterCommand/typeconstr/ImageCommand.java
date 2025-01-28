package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.Path;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class ImageCommand extends TypeconstrCommand{

    Path path;

    public ImageCommand(TypeCommand image, Path path) {
        this.shape = image;
        this.path = path;
    }

    @Override
    public String toString() {
        return shape +"("+path+")"+" ";
    }



    public TypeCommand getImage() {
        return shape;
    }

    public Path getPath() {
        return path;
    }
}
