
package compiler;

import java.util.Scanner;

/**
 *
 * @author lenovo
 */

import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



public class Compiler {
    public static void main(String[] args) throws Exception {
        boolean showTree = false;

        while (true) {
            System.out.print("> ");
             Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();


            if (line.isBlank()) {
                return;
            }

            if (line.equals("#showTree")) {
                showTree = !showTree;
                System.out.println(showTree ? "Showing parse trees." : "Not showing parse trees");
                continue;
            } else if (line.equals("#cls")) {
                System.console().flush();
                continue;
            }

            SyntaxTree syntaxTree = SyntaxTree.parse(line);

            if (showTree) {
                prettyPrint(syntaxTree.getRoot());
            }

            if (syntaxTree.getDiagnostics().isEmpty()) {
                
                Evaluator e = new Evaluator(syntaxTree.getRoot());
                int result = e.evaluate();
                System.out.println(result);
            } else {
                for (String diagnostic : syntaxTree.getDiagnostics()) {
                    System.out.println(diagnostic);
                }
            }
        }
    }

    private static void prettyPrint(SyntaxNode node, String indent, boolean isLast) {
        // |_
        // |--
        // |

        String marker = isLast ? "|_" : "|--";

        System.out.print(indent);
        System.out.print(marker);
        System.out.print(node.getKind());

        if (node instanceof SyntaxToken t && t.getValue() != null) {
        System.out.print(" ");
        System.out.print(t.getValue());
        
    }

        System.out.println();

        // indent +="   ";

        indent += isLast ? "   " : "|   ";

        SyntaxNode lastChild = node.getChildren().stream().reduce((first, second) -> second).orElse(null);
        for (SyntaxNode child : node.getChildren()) {
            prettyPrint(child, indent, child == lastChild);
        }
     
    }
    
       private static void prettyPrint(SyntaxNode node) {
        prettyPrint(node, "", false);
    }

}