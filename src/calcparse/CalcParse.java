package calcparse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
    
/**
 *
 * @author matt
 */
public class CalcParse {
    CalcScanner calc;
    int nextToken;
    int token;
    boolean error;
    
    public CalcParse(File input) throws FileNotFoundException, IOException {
        PushbackReader in = new PushbackReader(new FileReader(input));
        calc = new CalcScanner(in);
    }
    
    public void parse () throws IOException{ 
        nextToken = calc.nextToken();
        E();
        if(error) {    
            System.out.println("Failure!");
        } else {
            System.out.println("Success!");
        }
        
    }
    
    public void match(int token) throws IOException {
        if(nextToken == token) {
            nextToken = calc.nextToken();
        } else {
            System.out.print("Error!");
        }
    }
    
    public void E() throws IOException {
        T();
        EP();
    }
    
    public void EP() throws IOException {
        while(nextToken == Tokens.ADDOP) {
            match(Tokens.ADDOP);
            T();
            EP();
        }
    }
    
    public void T() throws IOException {
        F();
        TP();
    }
    
    public void TP() throws IOException {
        while(nextToken == Tokens.MULOP) {
            match(Tokens.MULOP);
            F();
            T();
        }
    }
    public void F() throws IOException {
        if(nextToken == Tokens.ID) {
            match(Tokens.ID);
        } else if (nextToken == Tokens.NUMBER) {
            match(Tokens.NUMBER);
        } else if (nextToken == Tokens.LEFTPAREN) {
            match(Tokens.LEFTPAREN);
            E();
            match(Tokens.RIGHTPAREN);
        } else {
            error = true;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        if (args.length > 0) {
            File input = new File(args[0]);        
            CalcParse calcparse = new CalcParse(input);
            calcparse.parse();
        } else { 
            System.out.print("Error! No File Specified");
        }
    }
}
