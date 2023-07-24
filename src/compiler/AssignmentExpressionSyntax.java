package compiler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Rehab Fakhry on Sun, 5/14/2023 at 11:34 PM.
 */
public final class AssignmentExpressionSyntax extends ExpressionSyntax {
    private final SyntaxToken identifierToken;
    private final SyntaxToken assignmentToken;
    private final ExpressionSyntax expression;
    private final SyntaxToken colonToken;

    public AssignmentExpressionSyntax(SyntaxToken identifierToken, SyntaxToken assignmentToken, ExpressionSyntax expression, SyntaxToken colonToken) {
        super();
        this.identifierToken = identifierToken;
        this.assignmentToken = assignmentToken;
        this.expression = expression;
        this.colonToken = colonToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.AssignmentExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return Arrays.asList(identifierToken, assignmentToken, expression,colonToken);
    }

    public SyntaxToken getIdentifierToken() {
        return identifierToken;
    }
    
    public SyntaxToken getColonToken() {
        return colonToken;
    }

    public SyntaxToken getAssignmentToken() {
        return assignmentToken;
    }

    public ExpressionSyntax getExpression() {
        return expression;
    }
}
