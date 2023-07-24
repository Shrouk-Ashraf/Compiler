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
public class Lexer {
    private final String _text;
    private int _position;
    private final List<String> _diagnostics = new ArrayList<String>();

    Lexer(String text) {
        _text = text;
    }

    public List<String> getDiagnostics() {
        return _diagnostics;
    }

    private char Current() {
        if (_position >= _text.length())
            return '\0';

        return _text.charAt(_position);
    }

    private void Next() {
        _position++;
    }

    public SyntaxToken NextToken() {
        if (_position >= _text.length()) {
            return new SyntaxToken(SyntaxKind.EndOfFileToken, _position++, "\0", null);
        }
        
        if (Character.isDigit(Current())) {
            var start = _position;
            while (Character.isDigit(Current()))
                Next();
            var length = _position - start;
            var text = _text.substring(start, start + length);
            Integer value =0;
            if (!tryParseInt(text,value)) {
                _diagnostics.add(String.format("The number %s isn't a valid Int32", text));
            }
            return new SyntaxToken(SyntaxKind.NumberToken, start, text, Integer.parseInt(text));
        }

        if (Character.isWhitespace(Current())) {
            var start = _position;

            while (Character.isWhitespace(Current()))
                Next();

            var length = _position - start;
            var text = _text.substring(start, start + length);
            return new SyntaxToken(SyntaxKind.WhitespaceToken, start, text, null);
        }

        if(Character.isLetter(Current())){
            char ident = Character.toLowerCase(Current());
            if(ident =='i'){
                _position++;
                if(Current() == 'f'){
                    return new SyntaxToken(SyntaxKind.IfStatement, _position++, "if", null);
                }
            }
            if(ident == 'e'){
                _position++;
                if(Current() == 'l'){
                    return new SyntaxToken(SyntaxKind.ElseKeyword, _position+=3, "else", null);
                }
            }
            if (ident == 'f'){
                _position++;
                if(Current() == 'o'){
                    return new SyntaxToken(SyntaxKind.ForStatement, _position+=2, "for", null);
                }
            }
            
            if ( ident == 'w'){
                _position++;
                if(Current() == 'h'){
                    _position++;
                    if(Current() == 'i'){
                        return new SyntaxToken(SyntaxKind.WhileStatement, _position+=3, "while", null);
                    }
                }
            }
            
            if (ident == 'd'){
                _position++;
                if(Current() == 'o'){
                    return new SyntaxToken(SyntaxKind.DoKeyword, _position++, "do",null);
                }
            }
            
            var start = _position;

            while (Character.isLetter(Current()))
                Next();

            var length = _position - start;
            var text = _text.substring(start, start + length);
            
            return new SyntaxToken(SyntaxKind.IdentifierToken, start, text, null);
        }
        
        
        
        if (Current() == '=') {
            _position++;
            if(Current() == '='){
                return new SyntaxToken(SyntaxKind.EqualsEqualsToken, _position++, "==", null);
            }
            return new SyntaxToken(SyntaxKind.EqualsToken, _position++, "=", null);
        }else if (Current() == '>') {
            return new SyntaxToken(SyntaxKind.GreaterToken, _position++, ">", null);
        }else if (Current() == '<') {
            return new SyntaxToken(SyntaxKind.LessToken, _position++, "<", null);
        }else if (Current() == '+') {
            _position++;
            if(Current() == '=' ){
                return new SyntaxToken(SyntaxKind.PlusEqualsToken, _position++, "+=", null);
            }else{
                return new SyntaxToken(SyntaxKind.PlusToken, _position--, "+", null);
            }
        }else if (Current() == '-') {
            _position++;
            if(Current() == '=' ){
                return new SyntaxToken(SyntaxKind.MinusEqualsToken, _position++, "-=", null);
            }else{
                 return new SyntaxToken(SyntaxKind.MinusToken, _position--, "-", null);
            }
        } else if (Current() == '*') {
            return new SyntaxToken(SyntaxKind.StarToken, _position++, "*", null);
        } else if (Current() == '/') {
            return new SyntaxToken(SyntaxKind.SlashToken, _position++, "/", null);
        } else if (Current() == '(') {
            return new SyntaxToken(SyntaxKind.OpenParenthesisToken, _position++, "(", null);
        } else if (Current() == ')') {
            return new SyntaxToken(SyntaxKind.CloseParenthesisToken, _position++, ")", null);
        }else if (Current() == ';') {
            return new SyntaxToken(SyntaxKind.ColonToken, _position++, ";", null);
        }else if (Current() == '{') {
            return new SyntaxToken(SyntaxKind.OpenBraceToken, _position++, "{", null);
        }else if (Current() == '}') {
            return new SyntaxToken(SyntaxKind.CloseBraceToken, _position++, "}", null);
        }

        _diagnostics.add(String.format("ERROR: bad character input: '%s'", Current()));
        return new SyntaxToken(SyntaxKind.BadToken, _position++, _text.substring(_position - 1, _position), null);
    }
    private static boolean tryParseInt(String text, int value) {
    try {
        value = Integer.parseInt(text);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
}