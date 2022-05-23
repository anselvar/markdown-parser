import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;
public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void newFile2Test() throws IOException{
        String markdown = Files.readString(Path.of("new-file-2.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    @Test
    public void newFile5Test() throws IOException{
        String markdown = Files.readString(Path.of("new-file-5.md"));
        assertEquals(List.of(), MarkdownParse.getLinks(markdown));
    }

    //For lab report week 4
    @Test
    public void LabReportTest1() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-1.md"));
        List<String> expect = List.of("`google.com", "google.com", "ucsd.edu");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void LabReportTest2() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-2.md"));
        List<String> expect = List.of("a.com", "a.com(())", "example.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    
    @Test
    public void LabReportTest3() throws IOException {
        String contents = Files.readString(Path.of("lab-report-4-3.md"));
        List<String> expect = List.of("https://www.twitter.com", "https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule", "https://cse.ucsd.edu/");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

}