package is.prompt.grammarCommand.typeconstr;

import is.prompt.grammarCommand.terminal.Path;
import is.prompt.grammarCommand.type.TypeCommand;

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
