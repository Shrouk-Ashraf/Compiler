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
public class DoWhileStatementSyntax  extends ExpressionSyntax {//do x-=1; while(x > 0 );
    private final SyntaxToken doToken;
    private final ExpressionSyntax bodyexpression;
    private final SyntaxToken colonToken;
    private final SyntaxToken whileToken;
    private final SyntaxToken openBracket;
    private final ExpressionSyntax condExp;
    private final SyntaxToken closeBracket;
    private final SyntaxToken whileColonToken;

    public DoWhileStatementSyntax(SyntaxToken doToken, ExpressionSyntax bodyexpression, SyntaxToken colonToken, SyntaxToken whileToken, SyntaxToken openBracket, ExpressionSyntax condExp, SyntaxToken closeBracket, SyntaxToken whileColonToken) {
        this.doToken = doToken;
        this.bodyexpression = bodyexpression;
        this.colonToken = colonToken;
        this.whileToken = whileToken;
        this.openBracket = openBracket;
        this.condExp = condExp;
        this.closeBracket = closeBracket;
        this.whileColonToken = whileColonToken;
    }

    public SyntaxToken getDoToken() {
        return doToken;
    }

    public ExpressionSyntax getBodyexpression() {
        return bodyexpression;
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

    public ExpressionSyntax getCondExp() {
        return condExp;
    }

    public SyntaxToken getCloseBracket() {
        return closeBracket;
    }

    public SyntaxToken getWhileColonToken() {
        return whileColonToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.DoWhileStatement;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(doToken,bodyexpression,colonToken,whileToken, openBracket
                ,condExp ,closeBracket,whileColonToken);
    }
    
    
}
