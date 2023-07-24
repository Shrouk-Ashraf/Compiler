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
public class ForStatementSyntax extends ExpressionSyntax {
    private final SyntaxToken forKeyword;
    private final SyntaxToken openParenthesisToken;
    private final ExpressionSyntax inititialExp;  
//    private final SyntaxToken semiColon;
    private final ExpressionSyntax conditionExp;  
    private final SyntaxToken semiColon1;
    private final ExpressionSyntax stepExp;
    private final SyntaxToken closeParenthesisToken;
    private final ExpressionSyntax Expression;

    public ForStatementSyntax(SyntaxToken forKeyword, SyntaxToken openParenthesisToken, ExpressionSyntax inititialExp, ExpressionSyntax conditionExp, SyntaxToken semiColon1, ExpressionSyntax stepExp
                            , SyntaxToken closeParenthesisToken, ExpressionSyntax Expression) {
        this.forKeyword = forKeyword;
        this.openParenthesisToken = openParenthesisToken;
        this.inititialExp = inititialExp;
        this.conditionExp = conditionExp;
        this.semiColon1 = semiColon1;
        this.stepExp = stepExp;
        this.closeParenthesisToken = closeParenthesisToken;
        this.Expression = Expression;
    }

    public SyntaxToken getForKeyword() {
        return forKeyword;
    }

    public SyntaxToken getOpenParenthesisToken() {
        return openParenthesisToken;
    }

    public ExpressionSyntax getInititialExp() {
        return inititialExp;
    }

    public ExpressionSyntax getConditionExp() {
        return conditionExp;
    }

    public SyntaxToken getSemiColon1() {
        return semiColon1;
    }

    public ExpressionSyntax getStepExp() {
        return stepExp;
    }

    public SyntaxToken getCloseParenthesisToken() {
        return closeParenthesisToken;
    }

    public ExpressionSyntax getExpression() {
        return Expression;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.ForStatement;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList( forKeyword,  openParenthesisToken,  inititialExp,  conditionExp
                ,  semiColon1, stepExp,  closeParenthesisToken,  Expression);
    }

  


}