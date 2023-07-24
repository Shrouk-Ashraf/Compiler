package compiler;

import java.util.List;

/**
 * Created by Rehab Fakhry on Sun, 5/14/2023 at 11:41 PM.
 */
public class VariableExpressionSyntax extends ExpressionSyntax {
    private String _name;
    private ExpressionSyntax _value;

    public VariableExpressionSyntax(SyntaxToken identifierToken) {
        _name = identifierToken.getText();
    }

    public String getName() {
        return _name;
    }

    public ExpressionSyntax getValue() {
        return _value;
    }

    public void setValue(ExpressionSyntax value) {
        _value = value;
    }

    @Override
    public SyntaxKind getKind() {
        return null;
    }

    @Override
    public List<SyntaxNode> getChildren() {
        return null;
    }
}
