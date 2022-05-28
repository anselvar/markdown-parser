// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {

    static int findCloseParen(String markdown, int openParen) {
        int closeParen = openParen + 1;
        int openParenCount = 1;
        while (openParenCount > 0 && closeParen < markdown.length()) {
            if (markdown.charAt(closeParen) == '(') {
                openParenCount++;
            } else if (markdown.charAt(closeParen) == ')') {
                openParenCount--;
            }
            closeParen++;
        }
        if(openParenCount == 0) {
          return closeParen - 1;
        }
        else {
          return -1;
        }
    }
    // public static ArrayList<String> getLinks(String markdown) {
    //     ArrayList<String> toReturn = new ArrayList<>();
    //     // find the next [, then find the ], then find the (, then read link upto next )
    //     int currentIndex = 0;
    //     while(currentIndex < markdown.length()) {
    //         int openBracket = markdown.indexOf("[", currentIndex);
    //         int closeBracket = markdown.indexOf("]", openBracket);
    //         int openParen = markdown.indexOf("(", closeBracket);
    //         int closeParen = markdown.indexOf(")", openParen);
    //         int exclamation = markdown.indexOf("!", currentIndex);
    //         int innerParen = markdown.indexOf("(", openParen+1);
    //         if(innerParen > openParen && innerParen < closeParen){
    //             closeParen++;
    //             currentIndex = closeParen +1;
    //             continue;
    //         }
    //         //handles cases where it tries to read images as a link
    //         if (exclamation >= currentIndex && exclamation < openBracket){
    //             currentIndex = closeParen + 1;
    //             continue;
    //         }
    //         if (openBracket == -1 || closeBracket == -1 ||
    //             openParen == -1 || closeParen == -1){
    //                 break;
    //         }
    //             currentIndex = closeParen + 1;
    //         //handles cases where the brackets and parentheses are not right next
    //         // to each other
    //         if (closeBracket+1 != openParen){
    //             continue;
    //         }
    //         toReturn.add(markdown.substring(openParen + 1, closeParen));
    //     }

    // }
    // public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
    //     Map<String, List<String>> result = new HashMap<>();
    //     if(dirOrFile.isDirectory()) {
    //         for(File f: dirOrFile.listFiles()) {
    //             result.putAll(getLinks(f));
    //         }
    //         return result;
    //     }
    //     else {
    //         Path p = dirOrFile.toPath();
    //         int lastDot = p.toString().lastIndexOf(".");
    //         if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
    //             return result;
    //         }
    //         ArrayList<String> links = getLinks(Files.readString(p));
    //         result.put(dirOrFile.getPath(), links);
    //         return result;
    //     }
    // }
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCodeBlock = markdown.indexOf("\n```");
            if(nextCodeBlock < nextOpenBracket && nextCodeBlock != -1) {
                int endOfCodeBlock = markdown.indexOf("\n```");
                currentIndex = endOfCodeBlock + 1;
                continue;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);

            // The close paren we need may not be the next one in the file
            int closeParen = findCloseParen(markdown, openParen);
            
            if(nextOpenBracket == -1 || nextCloseBracket == -1
                  || closeParen == -1 || openParen == -1) {
                return toReturn;
            }
            String potentialLink = markdown.substring(openParen + 1, closeParen).trim();
            if(potentialLink.indexOf(" ") == -1 && potentialLink.indexOf("\n") == -1) {
                toReturn.add(potentialLink);
                currentIndex = closeParen + 1;
            }
            else {
                currentIndex = currentIndex + 1;
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}