//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            int exclamation = markdown.indexOf("!", currentIndex);
            int innerParen = markdown.indexOf("(", openParen+1);
            // if(innerParen > openParen && innerParen < closeParen){
            //     closeParen++;
            //     currentIndex = closeParen +1;
            //     continue;
            // }
            //handles cases where it tries to read images as a link
            if (exclamation >= currentIndex && exclamation < openBracket){
                currentIndex = closeParen + 1;
                continue;
            }
            if (openBracket == -1 || closeBracket == -1 ||
                openParen == -1 || closeParen == -1){
                    break;
            }
                currentIndex = closeParen + 1;
            //handles cases where the brackets and parentheses are not right next
            // to each other
            if (closeBracket+1 != openParen){
                continue;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
        }

        return toReturn;
    }


    public static void main(String[] args)  throws IOException{
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}