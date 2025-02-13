import static org.junit.Assert.*;
import java.nio.file.Path;
import org.junit.*;
import java.nio.file.Files;
import java.util.*;
import java.io.*;

//I made this change remotely

public class MarkdownParseTest {
    private static final int two = 2;
    ArrayList<String> expectedlinks; //ArrayList utilized by tests 1, 2, 3, and 4

    @Before
    public void setup() {
        //Populate array with desired output
        expectedlinks = new ArrayList<>();
        expectedlinks.add("https://something.com");
        expectedlinks.add("some-thing.html");
    }

    @Test
    public void Snippet1() throws IOException {
        String contents = Files.readString(Path.of("snippet1.md"));
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void Snippet2() throws IOException {
        String contents = Files.readString(Path.of("snippet2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    @Test
    public void Snippet3() throws IOException {
        String contents = Files.readString(Path.of("snippet3.md"));
        List<String> expect = List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule", "https://cse.ucsd.edu/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    // @Test
    // public void addition() {
    //     assertEquals(2, 1 + 1);
    // }

    // /*
    // / Test markdown file w/ two links present
    // */
    // @Test
    // public void testMarkdownParseFile1() {
    //     Path filePath = Path.of("test-file.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         for (int i = 0; i < parsedLinks.size(); ++i) {
    //             assertEquals(expectedlinks.get(i), parsedLinks.get(i));
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 1");
    //     }

    // }

    // /*
    // / Test markdown file w/ two links and an image present
    // */
    // @Test
    // public void testMarkdownParseFile2() {
    //     Path filePath = Path.of("test-file2.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         for (int i = 0; i < parsedLinks.size(); ++i) {
    //             assertEquals(expectedlinks.get(i), parsedLinks.get(i));
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 2");
    //     }

    // }

    // /*
    // / Test markdown file w/ two links, an image present, and no title
    // */
    // @Test
    // public void testMarkdownParseFile3() {
    //     Path filePath = Path.of("test-file3.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         for (int i = 0; i < parsedLinks.size(); ++i) {
    //             assertEquals(expectedlinks.get(i), parsedLinks.get(i));
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 3");
    //     }

    // }

    // /*
    // / Test markdown file w/ two links and an image present, with extra space at the end
    // */
    // @Test
    // public void testMarkdownParseFile4() {
    //     Path filePath = Path.of("test-file4.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         for (int i = 0; i < parsedLinks.size(); ++i) {
    //             assertEquals(expectedlinks.get(i), parsedLinks.get(i));
    //         }
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 4");
    //     }

    // }

    // /*
    // / Test markdown file w/ no links present
    // */
    // @Test
    // public void testMarkdownParseFile5() {
    //     Path filePath = Path.of("test-file5.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         assertEquals(0, parsedLinks.size());
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 5");
    //     }

    // }

    // /*
    // / Test markdown file w/ no links present, but empty brackets are present
    // */
    // @Test
    // public void testMarkdownParseFile6() {
    //     Path filePath = Path.of("test-file6.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         assertEquals(0, parsedLinks.size());
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 6");
    //     }

    // }

    // /*
    // / Test markdown file w/ no links present, but lines that look like links
    // */
    // @Test
    // public void testMarkdownParseFile7() {
    //     Path filePath = Path.of("test-file7.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         assertEquals(0, parsedLinks.size());
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found 7!");
    //     }

    // }

    // /*
    // / Test markdown file w/ only ")[" present, not a link
    // */
    // @Test
    // public void testMarkdownParseFile8() {
    //     Path filePath = Path.of("test-file8.md");

    //     try {
    //         String fileContents = Files.readString(filePath);
    //         ArrayList<String> parsedLinks = MarkdownParse.getLinks(fileContents);

    //         assertEquals(0, parsedLinks.size());
    //     } catch (Exception e) {
    //         System.out.println("Error: file not found! 8");
    //     }

    // }


}
