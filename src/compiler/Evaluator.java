/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;
import compiler.NameExpressionSyntax;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class Evaluator {
    
    private ExpressionSyntax root;
    private Map<String, Integer> symbolTable;

    public Evaluator(ExpressionSyntax root) {
        this.root = root;
        this.symbolTable = new HashMap<>();
    }

   

public int evaluate() {
    return evaluateExpression(root);
}

public boolean evaluteBooleanExpr(ExpressionSyntax node){
     if (node instanceof BinaryExpressionSyntax) {
        BinaryExpressionSyntax b = (BinaryExpressionSyntax)node;
        int left = evaluateExpression(b.getLeft());
        int right = evaluateExpression(b.getRight());

        switch (b.getOperatorToken().getKind()) {    
            case GreaterToken:
                return left > right;
            
            case LessToken :
                return left < right;
                
            case EqualsEqualsToken:
                return left == right;
            default:
                throw new RuntimeException("Unexpected binary operator");
        }
}
     
         if (node instanceof CondSyntax){
            CondSyntax c = (CondSyntax) node;
            var gg = symbolTable.get(c.getIdentifierToken().getText());
            int left = gg;
            int right = evaluateExpression(c.getExpression());

        switch (c.getOpToken().getKind()) {    
            case GreaterToken:
                return left > right;
                
            case LessToken :
                return left < right;
                
            case EqualsEqualsToken:
                return left == right;
            default:
                throw new RuntimeException("Unexpected binary operator");
        }
    }
     throw new RuntimeException("Unexpected node " + node.getKind());
}

public int evaluateExpression(ExpressionSyntax node) {
    // BinaryExpression
    // NumberExpression
    if (node instanceof NumberExpressionSyntax) {
        NumberExpressionSyntax n = (NumberExpressionSyntax)node;
        return (int)n.getNumberToken().getValue();
    }
    

    if (node instanceof BinaryExpressionSyntax) {
        BinaryExpressionSyntax b = (BinaryExpressionSyntax)node;
        int left = evaluateExpression(b.getLeft());
        int right = evaluateExpression(b.getRight());

        switch (b.getOperatorToken().getKind()) {
            case PlusToken:
                return left + right;
            case MinusToken:
                return left - right;
            case StarToken:
                return left * right;
            case SlashToken:
                return left / right;
            case MinusEqualsToken:
            { left = left - right;
                return left ;
            }
            case PlusEqualsToken :
            {
                left = left + right;
                return left;
            }
            case EqualsToken:
                // Assignment expression
                if (!(b.getLeft() instanceof NameExpressionSyntax)) {
                    throw new RuntimeException("Left side of assignment must be a name");
                }
                String name = ((NameExpressionSyntax) b.getLeft()).getName();
                symbolTable.put(name, evaluateExpression(b.getRight()));
                return symbolTable.get(name);
            default:
                throw new RuntimeException("Unexpected binary operator");
        }
    }
    
    if ( node instanceof IdentifierExpression){
        IdentifierExpression b = (IdentifierExpression)node;
        String name = b.getIdentifierToken().getText();
        int value = evaluateExpression(b.getExpression());
        int dd= symbolTable.get(name);
        switch (b.getAssignmentToken().getKind()){
            case MinusEqualsToken:
            {
              int newValue = dd - value;
              symbolTable.put(name, newValue);
              return newValue;
            }
            case PlusEqualsToken :
            {
              int newValue = dd + value;
              symbolTable.put(name, newValue);
              return newValue;  
            }
        }
        
    }

    if (node instanceof ParenthesizedExpressionSyntax) {
        ParenthesizedExpressionSyntax p = (ParenthesizedExpressionSyntax)node;
        return evaluateExpression(p.getExpression());
    }
    
    if(node instanceof AssignmentExpressionSyntax){
        AssignmentExpressionSyntax a = (AssignmentExpressionSyntax)node;
        String name = a.getIdentifierToken().getText();
        int value = evaluateExpression(a.getExpression());
        symbolTable.put(name, value);
        return value;
    }
        
    if(node instanceof IfStatemnetSyntax ){
        IfStatemnetSyntax z = (IfStatemnetSyntax) node;
        boolean value = evaluteBooleanExpr(z.getExpression());
        if(value){
            int x = evaluateExpression(z.getBodyExp()); 
            return x;
        }
        else { 
            int ffff = evaluateExpression(z.getElseExp());
            return ffff;
        }  
    }
    
    
    if( node instanceof WhileStatementSyntax){
        WhileStatementSyntax w =(WhileStatementSyntax)node;
        boolean cond = evaluteBooleanExpr(w.getExpression());
        List<Integer> allValues = new ArrayList<>();
        while(cond){
            Integer value = evaluateExpression (w.getBodyExp());
            allValues.add(value);
            cond = evaluteBooleanExpr(w.getExpression());
        }
        
        for ( int d =0 ; d <= allValues.size()-1 ; d ++){
            System.out.println(allValues.get(d));
        }
        return allValues.get(allValues.size()-1);
        
    }
    
    
    if (node instanceof DoWhileStatementSyntax){
       DoWhileStatementSyntax doWhile =(DoWhileStatementSyntax)node;
       List<Integer> allValues = new ArrayList<>();
       boolean cond;
       do{
            Integer value = evaluateExpression(doWhile.getBodyexpression());
            allValues.add(value);
       }while(cond = evaluteBooleanExpr(doWhile.getCondExp()));
       
       for ( int d =0 ; d <= allValues.size()-1 ; d ++){
            System.out.println(allValues.get(d));
        }
        return allValues.get(allValues.size()-1);
       
    }
       
  //for ( i =9 ; i > 0 ; i = i -1 ) i = 9 - 1 = 8
    // z = 4;
    
    if(node instanceof ForStatementSyntax){
        ForStatementSyntax f = (ForStatementSyntax) node;
        int i = evaluateExpression(f.getInititialExp()); // 3
        boolean cond = evaluteBooleanExpr(f.getConditionExp());//true
        List<Integer> allValues = new ArrayList<>();
        do{
            if (cond){
                Integer value =evaluateExpression(f.getExpression());//
                allValues.add(value);
            }
            
            evaluateExpression(f.getStepExp());
            cond = evaluteBooleanExpr(f.getConditionExp());
            
        }while(cond);
        
        for ( int d =0 ; d <= allValues.size()-1 ; d ++){
            System.out.println(allValues.get(d));
        }
        return allValues.get(allValues.size()-1);
    }
    
    if(node instanceof BlockStatementExp){
     BlockStatementExp b = (BlockStatementExp) node;
     ExpressionSyntax expression = b.getExpression();
     int value = evaluateExpression(expression);
     int stmtExpression =evaluateExpression (b.getStmtExpression());
     return stmtExpression;
    }

    throw new RuntimeException("Unexpected node " + node.getKind());
}
}