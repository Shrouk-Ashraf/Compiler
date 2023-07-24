/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler;

import java.util.List;

/**
 *
 * @author lenovo
 */
public  abstract class SyntaxNode {
    public abstract SyntaxKind getKind();

    public abstract List<SyntaxNode> getChildren();
}