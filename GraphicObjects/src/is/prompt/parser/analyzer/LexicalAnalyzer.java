package is.prompt.parser.analyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class LexicalAnalyzer {

    private StreamTokenizer input;
    private Token terminalToken;


    public LexicalAnalyzer(Reader in) {

        input = new StreamTokenizer(in);
        input.resetSyntax();                //reimposta la sintassi del tokenizer, rendendo tutti i caratteri ordinari (cioè, non hanno un significato speciale).
        input.eolIsSignificant(false); //imposta il tokenizer in modo che i caratteri di fine linea non siano significativi.
        input.wordChars('0', '9');
        input.wordChars('a', 'z');
        input.wordChars('A', 'Z');
        input.wordChars('/','/');
        input.wordChars(':', ':');
        input.wordChars('.', '.');
        input.parseNumbers();
        input.whitespaceChars('\u0000', ' ');
        input.ordinaryChar('(');
        input.ordinaryChar(')');
        input.ordinaryChar(',');
        input.quoteChar('"');

    }

    /*
    Se il token corrente è un token di parola, questo campo contiene una stringa che rappresenta i caratteri del token di parola.
    Se il token corrente è un token di stringa quotata, questo campo contiene il corpo della stringa.
    Il token corrente è una parola quando il valore del campo ttype è TT_WORD.
    Il token corrente è un token di stringa quotata quando il valore del campo ttype è un carattere di citazione.
    Il valore iniziale di questo campo è null.
     */
    public String getString() {
        return input.sval;
    }


    public Token nexToken() {
        try {
            switch (input.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    terminalToken = Token.EOF;
                    break;
                case StreamTokenizer.TT_WORD:

                    if (input.sval.equalsIgnoreCase("new"))
                        terminalToken = Token.NEW;
                    else if (input.sval.equalsIgnoreCase("del"))
                        terminalToken = Token.DEL;
                    else if (input.sval.equalsIgnoreCase("mv"))
                        terminalToken = Token.MV;
                    else if (input.sval.equalsIgnoreCase("mvoff"))
                        terminalToken = Token.MVOFF;
                    else if (input.sval.equalsIgnoreCase("scale"))
                        terminalToken = Token.SCALE;
                    else if (input.sval.equalsIgnoreCase("ls"))
                        terminalToken = Token.LS;
                    else if (input.sval.equalsIgnoreCase("all"))
                        terminalToken = Token.ALL;
                    else if (input.sval.equalsIgnoreCase("groups"))
                        terminalToken = Token.GROUPS;
                    else if (input.sval.equalsIgnoreCase("grp"))
                        terminalToken = Token.GRP;
                    else if (input.sval.equalsIgnoreCase("ungrp"))
                        terminalToken = Token.UNGRP;
                    else if (input.sval.equalsIgnoreCase("area"))
                        terminalToken = Token.AREA;
                    else if (input.sval.equalsIgnoreCase("perimeter"))
                        terminalToken = Token.PERIMETER;
                    else if (input.sval.equalsIgnoreCase("circle"))
                        terminalToken = Token.CIRCLE;
                    else if (input.sval.equalsIgnoreCase("rectangle"))
                        terminalToken = Token.RECTANGLE;
                    else if (input.sval.equalsIgnoreCase("img"))
                        terminalToken = Token.IMG;
                    else if (input.sval.startsWith("id") && isInteger(input.sval.substring(2)))
                        terminalToken = Token.OBJ_ID;
                    else
                        terminalToken = Token.NOT_VALID_WORD;
                    break;
                case StreamTokenizer.TT_NUMBER:
                    terminalToken = Token.POSFLOAT;
                    break;
                case '"':
                    terminalToken = Token.PATH;
                    break;
                case '(':
                    terminalToken = Token.LEFT_PAR;
                    break;
                case ',':
                    terminalToken = Token.COMA;
                    break;
                case ')':
                    terminalToken = Token.RIGHT_PAR;
                    break;
                default:
                    terminalToken = Token.NOT_VALID_CHAR;
            }
        } catch (IOException e) {
            terminalToken = Token.EOF;
        }
        return terminalToken;
    }// prossimoterminalToken


    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getWord(){
        return input.sval;
    }

    public double getNumber(){
        return input.nval;
    }

}// AnalizzatoreLessicale



