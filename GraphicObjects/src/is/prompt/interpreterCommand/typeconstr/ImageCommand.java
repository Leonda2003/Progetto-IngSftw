package is.prompt.interpreterCommand.typeconstr;

import is.prompt.interpreterCommand.terminal.Path;
import is.prompt.interpreterCommand.type.TypeCommand;

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
