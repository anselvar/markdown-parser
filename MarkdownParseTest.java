import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;
public class MarkdownParseTest {

 
    @Test
    public void Snippet1() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-1.md"));
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void Snippet2() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    @Test
    public void Snippet3() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-3.md"));
        List<String> expect = List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule", "https://cse.ucsd.edu/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

}