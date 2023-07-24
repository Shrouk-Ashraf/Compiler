/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;
import compiler.Parser;
import java.util.ArrayList;
import java.util.List;
import javax.tools.Diagnostic;

/**
 *
 * @author lenovo
 */
public class SyntaxTree {
    private List<String> diagnostics ;
    private ExpressionSyntax root;
    private SyntaxToken endOfFileToken;
    

    SyntaxTree(List<String> diagnostics, ExpressionSyntax root, SyntaxToken endOfFileToken) {
        this.diagnostics = diagnostics;
        this.root = root;
        this.endOfFileToken = endOfFileToken;
    }

    SyntaxTree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getDiagnostics() {
        return diagnostics;
    }

    public ExpressionSyntax getRoot() {
        return root;
    }
    
   
    public SyntaxToken getEndOfFileToken() {
        return endOfFileToken;
    }
public static Parser parser = null ;

    public static SyntaxTree parse(String text) {
        
        if (parser !=null){
            List<SyntaxToken> tokens = parser.tokens;
            parser = new Parser(text,parser.returnTokens());
        }else{
        parser = new Parser(text, null);
        }
        return parser.Parse();
    }
  
    
}

