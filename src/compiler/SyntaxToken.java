/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

/**
 *
 * @author lenovo
 */

import java.util.Collections;
import java.util.List;


public final class SyntaxToken extends SyntaxNode {
    private final SyntaxKind kind;
    private final int position;
    private final String text;
    final Object value;
    private final boolean isMissing;

    public SyntaxToken(SyntaxKind kind, int position, String text, Object value) {
        this.kind = kind;
        this.position = position;
        this.text = text != null ? text : "";
        this.value = value;
        this.isMissing = text == null;
    }

    public SyntaxKind getKind() {
        return kind;
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public Object getValue() {
        return value;
    }


    @Override
    public List<SyntaxNode> getChildren() {
        return Collections.emptyList();
    }

    public boolean isMissing() {
        return isMissing;
    }
}