/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class ParenthesizedExpressionSyntax extends ExpressionSyntax {
    private final SyntaxToken openParenthesisToken;
    private final ExpressionSyntax expression;
    private final SyntaxToken closeParenthesisToken;

    ParenthesizedExpressionSyntax(SyntaxToken openParenthesisToken,ExpressionSyntax expression, SyntaxToken closeParenthesisToken) {
        this.openParenthesisToken = openParenthesisToken;
        this.expression = expression;
        this.closeParenthesisToken = closeParenthesisToken;
    }

    public SyntaxKind getKind() {
        return SyntaxKind.ParenthesizedExpression;
    }

    public SyntaxToken getOpenParenthesisToken() {
        return openParenthesisToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }

    public SyntaxToken getCloseParenthesisToken() {
        return closeParenthesisToken;
    }
    
    public List<SyntaxNode> getChildren() {
        List<SyntaxNode> children = new ArrayList<>();
        children.add(openParenthesisToken);
        children.add(expression);
        children.add(closeParenthesisToken);
        return children;
    }
}