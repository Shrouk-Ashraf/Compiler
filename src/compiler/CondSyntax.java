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
public final class CondSyntax extends ExpressionSyntax {

    private final SyntaxToken identifierToken;
    private final SyntaxToken opSyntaxToken;
    private final ExpressionSyntax expression;

    public CondSyntax(SyntaxToken identifierToken, SyntaxToken opSyntaxToken, ExpressionSyntax expression) {
        this.identifierToken = identifierToken;
        this.opSyntaxToken = opSyntaxToken;
        this.expression = expression;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.AssignmentExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(identifierToken, opSyntaxToken, expression);
    }

    public SyntaxToken getIdentifierToken() {
        return identifierToken;
    }

    public SyntaxToken getOpToken() {
        return opSyntaxToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }
}