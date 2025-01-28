package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.Path;
import is.interpreterCommand.type.TypeCommand;
import is.visitor.Visitor;

public class ImageCommand extends TypeconstrCommand{

    TypeCommand image;
    Path path;

    public ImageCommand(TypeCommand image, Path path) {
        this.image = image;
        this.path = path;
    }

    @Override
    public String toString() {
        return image +"("+path+")"+" ";
    }

    public void accept(Visitor v){
        v.interpret(this);
    }

    public TypeCommand getImage() {
        return image;
    }

    public Path getPath() {
        return path;
    }
}
