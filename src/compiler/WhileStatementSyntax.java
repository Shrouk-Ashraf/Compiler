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
public class WhileStatementSyntax extends ExpressionSyntax{
    // while ( cond ) x-= 1 ;
    private final SyntaxToken whileToken;
    private final SyntaxToken openBracket;
    private final ExpressionSyntax expression;
    private final SyntaxToken closeBracket;
    private final ExpressionSyntax bodyExp;
    private final SyntaxToken colonToken;

    public WhileStatementSyntax(SyntaxToken whileToken, SyntaxToken openBracket, ExpressionSyntax expression, SyntaxToken closeBracket, ExpressionSyntax bodyExp, SyntaxToken colonToken) {
        this.whileToken = whileToken;
        this.openBracket = openBracket;
        this.expression = expression;
        this.closeBracket = closeBracket;
        this.bodyExp = bodyExp;
        this.colonToken = colonToken;
    }

    public SyntaxToken getColonToken() {
        return colonToken;
    }
    
    

    public SyntaxToken getWhileToken() {
        return whileToken;
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

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.WhileStatement;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(whileToken, openBracket, expression,closeBracket,bodyExp);
    }
    
}
