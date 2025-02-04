package is.prompt.grammarCommand.typeconstr;

import is.prompt.grammarCommand.terminal.Path;
import is.prompt.grammarCommand.type.TypeGrammarCommand;

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
