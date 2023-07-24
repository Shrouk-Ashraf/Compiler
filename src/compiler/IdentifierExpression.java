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
public class IdentifierExpression extends ExpressionSyntax {//i += 2 
    private final SyntaxToken identifierToken;
    private final SyntaxToken assignmentToken;
    private final ExpressionSyntax expression;

    public IdentifierExpression(SyntaxToken identifierToken, SyntaxToken assignmentToken, ExpressionSyntax expression) {
        this.identifierToken = identifierToken;
        this.assignmentToken = assignmentToken;
        this.expression = expression;
    }

    public SyntaxToken getIdentifierToken() {
        return identifierToken;
    }

    public SyntaxToken getAssignmentToken() {
        return assignmentToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.AssignmentExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(identifierToken,assignmentToken, expression);
    }
    
    
}
