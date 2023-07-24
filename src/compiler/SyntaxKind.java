/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

/**
 *
 * @author lenovo
 */
public enum SyntaxKind {
    BadToken,

    // Trivia
    SkippedTextTrivia,
    LineBreakTrivia,
    WhitespaceToken,
    SingleLineCommentTrivia,
    MultiLineCommentTrivia,

    // Tokens
    EndOfFileToken,
    EndOfLineToken,
    NumberToken,
    StringToken,
    PlusToken,
    PlusEqualsToken,
    MinusToken,
    MinusEqualsToken,
    StarToken,
    StarEqualsToken,
    SlashToken,
    SlashEqualsToken,
    BangToken,
    EqualsToken,
    TildeToken,
    HatToken,
    HatEqualsToken,
    AmpersandToken,
    AmpersandAmpersandToken,
    AmpersandEqualsToken,
    PipeToken,
    PipeEqualsToken,
    PipePipeToken,
    EqualsEqualsToken,
    BangEqualsToken,
    LessToken,
    LessOrEqualsToken,
    GreaterToken,
    GreaterOrEqualsToken,
    OpenParenthesisToken,
    CloseParenthesisToken,
    OpenBraceToken,
    CloseBraceToken,
    ColonToken,
    CommaToken,
    IdentifierToken,

    // Keywords
    BreakKeyword,
    ContinueKeyword,
    ElseKeyword,
    FalseKeyword,
    ForKeyword,
    FunctionKeyword,
    IfKeyword,
    LetKeyword,
    ReturnKeyword,
    ToKeyword,
    TrueKeyword,
    VarKeyword,
    WhileKeyword,
    DoKeyword,

    // Nodes
    CompilationUnit,
    FunctionDeclaration,
    GlobalStatement,
    Parameter,
    TypeClause,
    ElseClause,

    // Statements
    BlockStatement,
    VariableDeclaration,
    IfStatement,
    CondStatement,
    WhileStatement,
    DoWhileStatement,
    ForStatement,
    BreakStatement,
    ContinueStatement,
    ReturnStatement,
    ExpressionStatement,

    // Expressions
    LiteralExpression,
    NameExpression,
    UnaryExpression,
    BinaryExpression,
    CompoundAssignmentExpression,
    ParenthesizedExpression,
    AssignmentExpression,
    CallExpression,
}