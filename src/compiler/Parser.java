/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.*;

import compiler.Lexer;



/**
 *
 * @author lenovo
 */
public class Parser {
    private final SyntaxToken[] _tokens;
    private final List<String> _diagnostics = new ArrayList<String>();
    private int _position;

    private Map<String, VariableExpressionSyntax> _variables = new HashMap<>();
    List<SyntaxToken> tokens = new ArrayList();

    
  
    Parser(String text,List<SyntaxToken> tt) {
//        List<SyntaxToken> tokens = new ArrayList<SyntaxToken>();
        if (tt != null){
            for ( SyntaxToken t : tt){
                tokens.add(t);
            }
        }
        Lexer lexer = new Lexer(text);
        SyntaxToken token;
        do {
            token = lexer.NextToken();
            if (token.getKind() != SyntaxKind.WhitespaceToken && token.getKind() != SyntaxKind.BadToken) {
                tokens.add(token);
            }
        } while (token.getKind() != SyntaxKind.EndOfFileToken);

        _tokens =tokens.toArray(new SyntaxToken[0]);
        _diagnostics.addAll(lexer.getDiagnostics());
    }

    public List<String> getDiagnostics() {
        return _diagnostics;
    }

    private SyntaxToken Peek(int offset) {
        int index = _position + offset;
        if (index >= _tokens.length)
            return _tokens[_tokens.length - 1];

        return _tokens[index];
    }

    private SyntaxToken Current() {
        return Peek(0);
    }

    private SyntaxToken NextToken() {
        SyntaxToken current = Current();
        _position++;
        return current;
    }

    private SyntaxToken Match(SyntaxKind kind) {
        if (Current().getKind() == kind)
            return NextToken();

        _diagnostics.add(String.format("ERROR: Unexpected token <%s>, expected <%s>", Current().getKind(), kind));
        return new SyntaxToken(kind, Current().getPosition(), null, null);
    }

    private ExpressionSyntax ParseExpression() {
        return ParseTerm();
    }

    public SyntaxTree Parse() {
        ExpressionSyntax expression = ParseTerm();
        SyntaxToken endOfFileToken = Match(SyntaxKind.EndOfFileToken);

        return new SyntaxTree(_diagnostics, expression, endOfFileToken);
    }

    
    public ExpressionSyntax ParseTerm() {
        ExpressionSyntax left = ParseFactor();

        while (Current().getKind() == SyntaxKind.PlusToken || Current().getKind() == SyntaxKind.EqualsEqualsToken || Current().getKind() == SyntaxKind.GreaterToken || Current().getKind() == SyntaxKind.MinusToken) {
            SyntaxToken operatorToken = NextToken();
            ExpressionSyntax right = ParseFactor();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    public ExpressionSyntax ParseFactor() {
        ExpressionSyntax left = ParsePrimaryExpression();

        while (Current().getKind() == SyntaxKind.StarToken || Current().getKind() == SyntaxKind.SlashToken) {
            SyntaxToken operatorToken = NextToken();
            ExpressionSyntax right = ParsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }

        return left;
    }

    private ExpressionSyntax ParsePrimaryExpression() {
        if (Current().getKind() == SyntaxKind.OpenParenthesisToken) {
            SyntaxToken left = NextToken();
            ExpressionSyntax expression = ParseExpression();
            SyntaxToken right = Match(SyntaxKind.CloseParenthesisToken);
            return new ParenthesizedExpressionSyntax(left, expression, right);
        }

        if (Current().getKind() == SyntaxKind.IdentifierToken) {
            return ParseAssignmentExpression();
        }
        
        if(Current().getKind() == SyntaxKind.IfStatement){
            return ParseIfStatement();
        }
        if(Current().getKind() == SyntaxKind.ForStatement){
            return ParseForStatement();
        }
        
        if (Current().getKind() == SyntaxKind.OpenBraceToken){
            return ParseBlockStatement();
        }
        if (Current().getKind() == SyntaxKind.WhileStatement){
            return ParseWhileStatement();
        }
        
        if(Current().getKind() == SyntaxKind.DoKeyword){
            return ParseDoWhileStatement();
        }

        SyntaxToken numberToken = Match(SyntaxKind.NumberToken);
        return new NumberExpressionSyntax(numberToken);
    }
    
    
    
    
    private ExpressionSyntax ParseBlockStatement() {
        SyntaxToken openCurlyBracket = Match(SyntaxKind.OpenBraceToken);
        ExpressionSyntax expression= ParsePrimaryExpression();
        ExpressionSyntax stmtExpr = ParsePrimaryExpression();
        SyntaxToken closeCurlyBracket = Match(SyntaxKind.CloseBraceToken);
        return new BlockStatementExp(openCurlyBracket, expression, stmtExpr, closeCurlyBracket);
    }
    
    private ExpressionSyntax ConditionStatement(){
        SyntaxToken identifierToken =NextToken();
        SyntaxToken opToken = null;
        switch (Current().getKind()){
            case  GreaterToken :{
               opToken = Match(SyntaxKind.GreaterToken);
               break;
            }
            case LessToken : {
                opToken = Match(SyntaxKind.LessToken);
                break;
            }
            case EqualsEqualsToken : {
                opToken = Match(SyntaxKind.EqualsEqualsToken);
                break;
            }
        }
        ExpressionSyntax expression = ParseExpression();
        return new CondSyntax(identifierToken, opToken, expression);
    }
    
    
    
    private ExpressionSyntax ParseIfStatement() {
        SyntaxToken ifToken = Match(SyntaxKind.IfStatement);
        SyntaxToken left = Match(SyntaxKind.OpenParenthesisToken);
        ExpressionSyntax condition = ConditionStatement();
        SyntaxToken right = Match(SyntaxKind.CloseParenthesisToken);
        ExpressionSyntax thenExpression = ParseExpression();
        ExpressionSyntax elseExpression = null;
        SyntaxToken elseToken = null;
        if (Current().getKind() == SyntaxKind.ElseKeyword) {
            elseToken = NextToken();
            elseExpression = ParseExpression();
        }
        return new IfStatemnetSyntax(ifToken, left, condition, right, thenExpression, elseToken, elseExpression);
    }
    
    
    private ExpressionSyntax ParseWhileStatement(){
       SyntaxToken whileToken = Match(SyntaxKind.WhileStatement);
        SyntaxToken left = Match(SyntaxKind.OpenParenthesisToken);
        ExpressionSyntax condition = ConditionStatement();
        SyntaxToken right = Match(SyntaxKind.CloseParenthesisToken);
        ExpressionSyntax bodyExpression = ParseStepExpression();
        SyntaxToken colonToken = Match (SyntaxKind.ColonToken);
        return new WhileStatementSyntax(whileToken, left, condition, right, bodyExpression,colonToken); 
    }
    
    
    private ExpressionSyntax ParseDoWhileStatement(){
        SyntaxToken doToken = Match(SyntaxKind.DoKeyword);
        ExpressionSyntax bodyExpr = ParseStepExpression();
        SyntaxToken colon = Match(SyntaxKind.ColonToken);
        SyntaxToken whileToken = Match(SyntaxKind.WhileStatement);
        SyntaxToken open = Match(SyntaxKind.OpenParenthesisToken);
        ExpressionSyntax condition = ConditionStatement();
        SyntaxToken close = Match(SyntaxKind.CloseParenthesisToken);
        SyntaxToken whileColon = Match (SyntaxKind.ColonToken);
        return new DoWhileStatementSyntax(doToken, bodyExpr,colon, whileToken,open,condition,close,whileToken);
    }
    
    private ExpressionSyntax ParseForStatement(){
        SyntaxToken forToken = Match (SyntaxKind.ForStatement);
        SyntaxToken open = Match (SyntaxKind.OpenParenthesisToken);
        ExpressionSyntax assignExpr = ParseExpression();
        ExpressionSyntax condExp = ConditionStatement();
        SyntaxToken colon = Match (SyntaxKind.ColonToken);
        ExpressionSyntax stepExp = ParseStepExpression();
        SyntaxToken close = Match (SyntaxKind.CloseParenthesisToken);
        ExpressionSyntax exp = ParseExpression();
        
        return new ForStatementSyntax(forToken, open , assignExpr, condExp, colon,stepExp, 
                                close, exp);
        
    }
    
    private ExpressionSyntax ParseStepExpression() {
        SyntaxToken identifierToken = NextToken();
        // i = i + 2  for ( i = 0 ; i< 2 ; i+=2)i = i +2
        SyntaxToken equalsToken = null;
        switch (Current().getKind()){
            case MinusEqualsToken :
            {
                equalsToken = Match(SyntaxKind.MinusEqualsToken);
                break;
            }
            case PlusEqualsToken : 
            {
                equalsToken = Match(SyntaxKind.PlusEqualsToken);
                break;
            }
        }
        ExpressionSyntax expression = ParseExpression();
        return new IdentifierExpression(identifierToken, equalsToken, expression);
    }
    
    
    
    private ExpressionSyntax ParseAssignmentExpression() {
        SyntaxToken identifierToken = NextToken();
        
        SyntaxToken equalsToken = Match(SyntaxKind.EqualsToken);
        
        ExpressionSyntax expression = ParseExpression();
        SyntaxToken colonToken = Match(SyntaxKind.ColonToken);
        return new AssignmentExpressionSyntax(identifierToken, equalsToken, expression, colonToken);
    }

     private SyntaxNode parseNumberExpression() {
        SyntaxToken numberToken = NextToken();
        return new NumberExpressionSyntax(numberToken);
    }

    public ExpressionSyntax parse() {
        ExpressionSyntax left = (ExpressionSyntax) ParsePrimaryExpression();
        while (Current().getKind() == SyntaxKind.PlusToken || Current().getKind() == SyntaxKind.MinusToken) {
            SyntaxToken operatorToken = NextToken();
            ExpressionSyntax right = (ExpressionSyntax) ParsePrimaryExpression();
            left = new BinaryExpressionSyntax(left, operatorToken, right);
        }
        return left;
    }

    void addTokens(List<SyntaxToken> tokens) {
       for  (SyntaxToken s : tokens){
           tokens.add(s);
       }
    }
    
    List <SyntaxToken> returnTokens(){
        return tokens;
    }

    
}