package is.interpreterCommand.typeconstr;

import is.interpreterCommand.PosCommand;
import is.interpreterCommand.type.TypeCommand;

public class ImageCommand extends TypeconstrCommand{

    TypeCommand image;
    PosCommand pos;

    public ImageCommand(TypeCommand image, PosCommand pos) {
        this.image = image;
        this.pos = pos;
    }
}
