package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.terminal.Path;
import is.interpreterCommand.type.TypeCommand;

public class ImageCommand extends TypeconstrCommand{

    TypeCommand image;
    Path path;

    public ImageCommand(TypeCommand image, Path path) {
        this.image = image;
        this.path = path;
    }
}
