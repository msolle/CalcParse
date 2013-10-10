package calcparse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
    
/**
 * @author matt
 * github: github.com/msolle
*/

public class CalcParse {
    CalcScanner calc;
    int nextToken;
    int token;
    boolean error;
    
    public CalcParse(File input) throws FileNotFoundException, IOException {
        PushbackReader in = new PushbackReader(new FileReader(input));
        calc = new CalcScanner(in);
    }//CalcParse
    
    public void parse () throws IOException{ 
        nextToken = calc.nextToken();//Initial value of nextToken
        expression();//Begin recursive descent parsing
        if(error) {
            System.out.println("Failure!");//Parsing Error
        } else {
            System.out.println("Success!");
        }        
    }//parse
    
    public void match(int token) throws IOException {
        if(nextToken == token) {
            nextToken = calc.nextToken();               //Gets the next token
        } else {
            System.out.print("Error!");
        }
    }//match

    public void expression() throws IOException {
        term();                                         
        expressionprime();
    }//expression
    
    /*Addition Subtraction*/
    public void expressionprime() throws IOException {
        while(nextToken == Tokens.ADDOP) {
            match(Tokens.ADDOP);
            term();
            expressionprime();
        }
    }//expressionprime

    public void term() throws IOException {
        factor();
        termprime();
    }//term
    
    /*Multiplication Divides*/
    public void termprime() throws IOException {
        while(nextToken == Tokens.MULOP) {
            match(Tokens.MULOP);
            factor();
            term();
        }
    }//termprime
    
    /*ID Num Parens*/
    public void factor() throws IOException {
        if(nextToken == Tokens.ID) {
            match(Tokens.ID);
        } else if (nextToken == Tokens.NUMBER) {
            match(Tokens.NUMBER);
        } else if (nextToken == Tokens.LEFTPAREN) {
            match(Tokens.LEFTPAREN);
            expression();
            match(Tokens.RIGHTPAREN);
        } else {
            error = true;
        }
    }//factor
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length > 0) {
            File input = new File(args[0]);        
            CalcParse calcparse = new CalcParse(input);
            calcparse.parse();
        } else { 
            System.out.print("Error! No File Specified");
        }
    }//main
}//CalcParse