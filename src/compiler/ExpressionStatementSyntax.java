/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class ExpressionStatementSyntax extends StatementSyntax {
    private final ExpressionSyntax expression;

    public ExpressionStatementSyntax(ExpressionSyntax expression) {
        super(null, null);
        this.expression = expression;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Collections.singletonList(expression);
    }

    @Override
    public SyntaxKind getKind() {
        return expression.getKind();
    }
}