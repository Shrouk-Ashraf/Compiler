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
public class NumberExpressionSyntax extends ExpressionSyntax {
    private final SyntaxToken numberToken;

    public NumberExpressionSyntax(SyntaxToken numberToken) {
        this.numberToken = numberToken;
    }

    public SyntaxToken getNumberToken() {
        return numberToken;
    }

    public SyntaxKind getKind() {
        return SyntaxKind.NumberToken;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Collections.singletonList(numberToken);
    }
}