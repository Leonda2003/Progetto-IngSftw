package is.system.prompt.grammarCommand.typeconstr;

import is.system.prompt.grammarCommand.terminal.Path;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;

public class ImageGrammarCommand extends TypeconstrGrammarCommand {

    Path path;

    public ImageGrammarCommand(TypeGrammarCommand image, Path path) {
        this.shape = image;
        this.path = path;
    }

    @Override
    public String toString() {
        return shape +"("+path+")"+" ";
    }



    public TypeGrammarCommand getImage() {
        return shape;
    }

    public Path getPath() {
        return path;
    }
}
