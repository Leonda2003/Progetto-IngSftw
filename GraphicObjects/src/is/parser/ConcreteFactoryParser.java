package is.parser;

import is.analyzer.Context;
import is.analyzer.LexicalAnalyzer;
import is.analyzer.Token;
import is.exception.SyntaxException;
import is.interpreterCommand.Command;
import is.interpreterCommand.CreateCommand;
import is.interpreterCommand.PosCommand;
import is.interpreterCommand.typeconstr.TypeconstrCommand;

import java.io.Reader;

public class ConcreteFactoryParser extends FactoryParser{

    private Command command;
    private LexicalAnalyzer analyzer;
    private Token expectedToken;
    private
    ConcreteFactoryParser(Reader in) {
        this.analyzer = new LexicalAnalyzer(in);
        command =createCommandToInterpret();
        expectedToken = Token.EOF;
    }
    @Override
    public Command createCommandToInterpret() {

        if(analyzer.nexToken().equals(Token.NEW)){


            TypeconstrCommand typeconstrCommand = createTypeconstr();
            PosCommand posCommand = createPos();


        }else if(analyzer.nexToken().equals(Token.DEL)){

        }else if(analyzer.nexToken().equals(Token.MV)){

        }else if(analyzer.nexToken().equals(Token.MVOFF)){

        }else if(analyzer.nexToken().equals(Token.SCALE)){

        }else if(analyzer.nexToken().equals(Token.LS)){

        }else if(analyzer.nexToken().equals(Token.GRP)){

        }else if(analyzer.nexToken().equals(Token.UNGRP)){

        }else if(analyzer.nexToken().equals(Token.AREA)){

        }else if(analyzer.nexToken().equals(Token.PERIMETER)){

        }else if(analyzer.nexToken().equals(Token.NOT_VALID_WORD)){
            String word = analyzer.getWord();
            throw new SyntaxException("No command found starting with '"+word+"'\n"+
                                       "try one of new-del-mv-mvoff-scale-ls-grp-ungrp-area-perimeter");
        }
        return command;
    }

    private PosCommand createPos() {
    }

    private TypeconstrCommand createTypeconstr() {
        TypeconstrCommand typeconstrCommand;
        if(analyzer.nexToken().equals(Token.CIRCLE)){



        }else if(analyzer.nexToken().equals(Token.RECTANGLE)){



        }else if(analyzer.nexToken().equals(Token.IMG)){

        }
        return typeconstrCommand;
    }


}
