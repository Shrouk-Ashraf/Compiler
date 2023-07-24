package compiler;

import java.util.List;

/**
 * Created by Rehab Fakhry on Sun, 5/14/2023 at 11:15 PM.
 */
public class NameExpressionSyntax extends ExpressionSyntax {
    private final SyntaxToken identifierToken;

    public NameExpressionSyntax( SyntaxToken identifierToken) {
        
        this.identifierToken = identifierToken;
    }

    @Override
    public SyntaxKind getKind() {
        return SyntaxKind.NameExpression;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return null;
    }

    public SyntaxToken getIdentifierToken() {
        return identifierToken;
    }

    public String getName() {
        return identifierToken.getText();
    }
}
