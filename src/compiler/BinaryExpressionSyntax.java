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
public class BinaryExpressionSyntax extends ExpressionSyntax {
    private final ExpressionSyntax left;
    private final SyntaxToken operatorToken;
    private final ExpressionSyntax right;

    BinaryExpressionSyntax( ExpressionSyntax left,SyntaxToken operatorToken, ExpressionSyntax right) {
        this.left = left;
        this.operatorToken = operatorToken;
        this.right = right;
    }

    public SyntaxKind getKind() {
        return SyntaxKind.BinaryExpression;
    }

    public ExpressionSyntax getLeft() {
        return left;
    }

    public SyntaxToken getOperatorToken() {
        return operatorToken;
    }

    public ExpressionSyntax getRight() {
        return right;
    }
    
    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(left, operatorToken, right);
    }
}
