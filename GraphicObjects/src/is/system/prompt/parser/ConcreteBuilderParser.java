package is.system.prompt.parser;

import is.system.prompt.parser.analyzer.LexicalAnalyzer;
import is.system.prompt.parser.analyzer.Token;
import is.system.exception.SyntaxException;
import is.system.prompt.grammarCommand.*;
import is.system.prompt.grammarCommand.area.AreaAllGrammarCommand;
import is.system.prompt.grammarCommand.area.AreaIDGrammarCommand;
import is.system.prompt.grammarCommand.area.AreaTypeGrammarCommand;
import is.system.prompt.grammarCommand.list.*;
import is.system.prompt.grammarCommand.move.MoveGrammarCommand;
import is.system.prompt.grammarCommand.move.MoveOffGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterAllGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterIDGrammarCommand;
import is.system.prompt.grammarCommand.perimeter.PerimeterTypeGrammarCommand;
import is.system.prompt.grammarCommand.terminal.*;
import is.system.prompt.grammarCommand.type.Circle;
import is.system.prompt.grammarCommand.type.Image;
import is.system.prompt.grammarCommand.type.Rectangle;
import is.system.prompt.grammarCommand.type.TypeGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.CircleGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.ImageGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.RectangleGrammarCommand;
import is.system.prompt.grammarCommand.typeconstr.TypeconstrGrammarCommand;

import java.io.Reader;


public class ConcreteBuilderParser implements BuilderParser {

    private GrammarCommand grammarCommand;
    private LexicalAnalyzer analyzer;
    private Token currentToken;
    public ConcreteBuilderParser(Reader in) {
        this.analyzer = new LexicalAnalyzer(in);
        grammarCommand =createCommandToInterpret();
        expected(Token.EOF);
    }

    private GrammarCommand createCommandToInterpret() {

        currentToken = analyzer.nexToken();
        if(currentToken == Token.NEW){

            TerminalGrammarCommand terminalCommand = createTerminal();
            TypeconstrGrammarCommand typeconstrCommand = createTypeconstr();
            PosGrammarCommand posCommand = createPos();
            return new CreateGrammarCommand(terminalCommand,typeconstrCommand,posCommand);

        }if(currentToken == Token.DEL){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            return new RemoveGrammarCommand(terminalCommand,objID);

        }if(currentToken == Token.MV){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            PosGrammarCommand posCommand = createPos();
            return new MoveGrammarCommand(terminalCommand,objID,posCommand);

        }if(currentToken == Token.MVOFF){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            PosGrammarCommand posCommand = createPos();
            return new MoveOffGrammarCommand(terminalCommand,objID,posCommand);

        }if(currentToken == Token.SCALE){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            Posfloat posfloat = createPosfloat();
            return new ScaleGrammarCommand(terminalCommand,objID,posfloat);

        }if(currentToken == Token.LS){

            TerminalGrammarCommand terminalCommand = createTerminal();
            return createList(terminalCommand);

        }if(currentToken == Token.GRP){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ListIDGrammarCommand listIDCommand = createListID();
            return new GroupGrammarCommand(terminalCommand,listIDCommand);

        }if(currentToken == Token.UNGRP){

            TerminalGrammarCommand terminalCommand = createTerminal();
            ObjID objID = createObjID();
            return new UngroupGrammarCommand(terminalCommand,objID);

        }if(currentToken == Token.AREA){

            TerminalGrammarCommand terminalCommand = createTerminal();
            return createArea(terminalCommand);

        }if(currentToken == Token.PERIMETER){

            TerminalGrammarCommand terminalCommand = createTerminal();
            return createPerimeter(terminalCommand);
        }
        String word = analyzer.getWord();
        throw new SyntaxException("No commands found that start like this"+"\n"+
                                   "   try one of new-del-mv-mvoff-scale-ls-grp-ungrp-area-perimeter");

    }

    private TypeconstrGrammarCommand createTypeconstr() {

        if(currentToken == Token.CIRCLE){

            TypeGrammarCommand typeCommand = createType();
            expected(Token.LEFT_PAR);
            Posfloat posfloat = createPosfloat();
            expected(Token.RIGHT_PAR);
            return new CircleGrammarCommand(typeCommand,posfloat);
        }
        if(currentToken == Token.RECTANGLE){

            TypeGrammarCommand typeCommand = createType();
            PosGrammarCommand posCommand= createPos();
            return new RectangleGrammarCommand(typeCommand,posCommand);
        }
        if(currentToken == Token.IMG){

            TypeGrammarCommand typeCommand = createType();
            expected(Token.LEFT_PAR);
            Path path = createPath();
            expected(Token.RIGHT_PAR);
            return new ImageGrammarCommand(typeCommand,path);
        }
        throw new SyntaxException("Command Syntax Error, expected a type of graphic object ");

    }

    private TypeGrammarCommand createType() {

        switch (currentToken) {
            case Token.CIRCLE:
                Circle circle = new Circle(currentToken);
                currentToken = analyzer.nexToken();
                return circle;
            case Token.RECTANGLE:
                Rectangle rectangle = new Rectangle(currentToken);
                currentToken = analyzer.nexToken();
                return rectangle;
            case Token.IMG:
                Image image = new Image(currentToken);
                currentToken = analyzer.nexToken();
                return image;
            default:
                throw new SyntaxException("Expected one of circle-rectangle-img");
        }
    }

    private GrammarCommand createPerimeter(TerminalGrammarCommand terminalCommand) {

        try{
            if(currentToken == Token.OBJ_ID){

                ObjID objID = createObjID();
                return new PerimeterIDGrammarCommand(terminalCommand,objID);

            }else if(currentToken == Token.ALL){

                TerminalGrammarCommand terminalCommand2 = createAll_Groups();
                return new PerimeterAllGrammarCommand(terminalCommand,terminalCommand2);

            }else{

                TypeGrammarCommand typeCommand = createType();
                return new PerimeterTypeGrammarCommand(terminalCommand,typeCommand);
            }
        }catch (SyntaxException e){
            throw new SyntaxException("Expected one of objID-circle-rectangle-img-all");
        }

    }

    private GrammarCommand createArea(TerminalGrammarCommand terminalCommand) {

        try{
            if(currentToken == Token.OBJ_ID){
                ObjID objID = createObjID();
                return new AreaIDGrammarCommand(terminalCommand,objID);

            }else if(currentToken == Token.ALL){

                TerminalGrammarCommand terminalCommand2 = createAll_Groups();
                return new AreaAllGrammarCommand(terminalCommand,terminalCommand2);

            }else{
                TypeGrammarCommand typeCommand = createType();
                return new AreaTypeGrammarCommand(terminalCommand,typeCommand);
            }

        }catch (SyntaxException e){
            throw new SyntaxException("Expected one of objID-circle-rectangle-img-all");
        }


    }

    private ListGrammarCommand createList(TerminalGrammarCommand terminalCommand) {

        try{
        if(currentToken == Token.OBJ_ID){

            ObjID objID = createObjID();
            return new ListObjIDGrammarCommand(terminalCommand,objID);

        }else if(currentToken == Token.ALL){

            All_Groups allGroups = createAll_Groups();
            return new ListAllGrammarCommand(terminalCommand,allGroups);

        }else if(currentToken == Token.GROUPS){

            All_Groups allGroups = createAll_Groups();
            return new ListGroupsGrammarCommand(terminalCommand,allGroups);

        }else{

            TypeGrammarCommand typeCommand = createType();
            return new ListTypeGrammarCommand(terminalCommand,typeCommand);
        }
        }catch (SyntaxException e){
            throw new SyntaxException("Expected one of objID-circle-rectangle-img-groups-all");
        }
    }


    private ListIDGrammarCommand createListID() {

        ObjID objID = createObjID();
        ListIDGrammarCommand listIDCommand= new ListIDGrammarCommand(objID);
        while(currentToken == Token.COMA){
            expected(Token.COMA);
            listIDCommand.addObjectID(createObjID());
        }
        return listIDCommand;
    }

    private PosGrammarCommand createPos() {

        expected(Token.LEFT_PAR);
        Posfloat posfloat1 =  createPosfloat();
        expected(Token.COMA);
        Posfloat posfloat2 = createPosfloat();
        expected(Token.RIGHT_PAR);
        return new PosGrammarCommand(posfloat1,posfloat2);

    }

    private TerminalGrammarCommand createTerminal() {

        TerminalGrammarCommand terminalCommand = new TerminalGrammarCommand(currentToken);
        currentToken = analyzer.nexToken();
        return terminalCommand;
    }

    private ObjID createObjID() {
        if(currentToken == Token.OBJ_ID){
            ObjID objID = new ObjID(currentToken,analyzer.getWord());
            currentToken = analyzer.nexToken();
            return objID;
        }
        throw new SyntaxException("Insert a valid ID");
    }

    private Posfloat createPosfloat() {

        if(analyzer.getNumber() < 0) throw new SyntaxException("only positive numbers accepted");
        Posfloat posfloat = new Posfloat(currentToken, analyzer.getNumber());
        currentToken = analyzer.nexToken();
        return posfloat;
    }

    private Path createPath() {

        if(currentToken == Token.PATH){
            Path path = new Path(currentToken, analyzer.getWord());
            currentToken = analyzer.nexToken();
            return path;
        }
        throw new SyntaxException("Insert a valid Path in quotes");
    }

    private All_Groups createAll_Groups() {
        switch (currentToken){
            case Token.ALL:
            case Token.GROUPS:
                Token t = currentToken;
                currentToken = analyzer.nexToken();
                return new All_Groups(t);
        }
        throw new SyntaxException("Insert All or Groups");
    }

    private void expected(Token t) {
        if (currentToken != t) {
            String msg = "found "+currentToken+" while expepected "+t;
            throw new SyntaxException(msg);
        }
        currentToken = analyzer.nexToken();
    }

    public GrammarCommand getCommandToInterpret() {
        return grammarCommand;
    }
}
