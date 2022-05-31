// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
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

            int openQuote = markdown.indexOf("`", currentIndex);
            int closeQuote =  markdown.indexOf("`", openQuote);
            int closeIndex = Math.max(closeParen, closeQuote);


            if(openBracket == -1 || closeBracket == -1 || openParen == -1 || closeParen == -1){
                break;
            }

            if(openBracket > 0 && markdown.charAt(openBracket - 1) == '!'){
                currentIndex = closeParen + 1;
            }
            
            
            
            else{
                System.out.println(openQuote);
                System.out.println(openBracket);
                System.out.println(markdown.substring(50,55));
                System.out.println(markdown.substring(openParen + 1, closeParen));
                if(openQuote == -1 || openQuote >= openBracket){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                currentIndex = closeIndex + 1;
            }
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}