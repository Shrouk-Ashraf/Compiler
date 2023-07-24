/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class IfStatemnetSyntax extends ExpressionSyntax{
    //if ( exp ) bodyExp  
    private final SyntaxToken ifToken;
    private final SyntaxToken openBracket;
    private final ExpressionSyntax expression;
    private final SyntaxToken closeBracket;
    private final ExpressionSyntax bodyExp;
    private final SyntaxToken elseToken;
    private final ExpressionSyntax elseExp;
//    private final SyntaxToken colon;

    public IfStatemnetSyntax(SyntaxToken ifToken, SyntaxToken openBracket, ExpressionSyntax expression, SyntaxToken closeBracket
            , ExpressionSyntax bodyExp , SyntaxToken elseToken, ExpressionSyntax elseExp) {
        super();
        this.ifToken = ifToken;
        this.openBracket = openBracket;
        this.expression = expression;
        this.closeBracket = closeBracket;
        this.bodyExp = bodyExp;
        this.elseToken = elseToken;
        this.elseExp = elseExp;
    }

    public SyntaxToken getElseToken() {
        return elseToken;
    }

    public ExpressionSyntax getElseExp() {
        return elseExp;
    }

    public SyntaxToken getIfToken() {
        return ifToken;
    }

    public SyntaxToken getOpenBracket() {
        return openBracket;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    public SyntaxToken getCloseBracket() {
        return closeBracket;
    }

    public ExpressionSyntax getBodyExp() {
        return bodyExp;
    }

//    public SyntaxToken getColon() {
//        return colon;
//    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.IfStatement;
    }

    @Override
    public List<SyntaxNode> getChildren(){
        return Arrays.asList(ifToken, openBracket, expression,closeBracket,bodyExp,elseToken, elseExp);
    }
    
    
    
}
