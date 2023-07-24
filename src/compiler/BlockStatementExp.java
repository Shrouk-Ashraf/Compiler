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
public class BlockStatementExp extends ExpressionSyntax {
    //Block
    // { exp stmtexp }
    
    private final SyntaxToken openCurlyBracket;
    private final ExpressionSyntax expression;
    private final ExpressionSyntax stmtExpression;
    private final SyntaxToken closeCurlyBracket;

    public BlockStatementExp(SyntaxToken openCurlyBracket, ExpressionSyntax expression, ExpressionSyntax stmtExpression, SyntaxToken closeCurlyBracket) {
        this.openCurlyBracket = openCurlyBracket;
        this.expression = expression;
        this.stmtExpression = stmtExpression;
        this.closeCurlyBracket = closeCurlyBracket;
    }

    public SyntaxToken getOpenCurlyBracket() {
        return openCurlyBracket;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    public ExpressionSyntax getStmtExpression() {
        return stmtExpression;
    }

    public SyntaxToken getCloseCurlyBracket() {
        return closeCurlyBracket;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.BlockStatement;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(openCurlyBracket ,expression, stmtExpression,closeCurlyBracket);
    }
    
    
    
    
    
    
    
    
}
