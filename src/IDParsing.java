import utils.ParseUtility;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDParsing {
    public static void main(String[] args) throws IOException {
        //get all files in the folder
        String folderPath = "/Users/gabriel/Downloads/Queries/txt/sgen";
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        String outPath = folderPath + "/output.txt";
        File outFile = new File(outPath);
        outFile.createNewFile();
        PrintStream out = new PrintStream(new FileOutputStream(outFile));
        if(files == null) return;
        for(File file : files){
            if(!file.getName().endsWith(".txt")) continue;
            String str = ParseUtility.fileToString(file);
            Set<String> ids = extractIDs(str, Pattern.compile("https:\\/\\/battleofthebits\\.com\\/arena\\/Entry\\/[^\\/]+\\/"), Pattern.compile("\\/"));
            for(String id : ids){
                out.println(id);
            }
        }
    }

    /**
     * Extracts all strings in an HTML document (or any string, really, but this is meant primarily for web scraping) that are enclosed by {@code pattern} and {@code breakPattern}.
     * @param html The body of text to search through. Doesn't have to be HTML :P
     * @param pattern The pattern that indicates the start of a target string.
     * @param breakPattern The pattern that indicates the end of a target string.
     * @return An unordered set of all strings.
     */
    public static Set<String> extractIDs(String html, Pattern pattern, Pattern breakPattern){
        Set<String> ids = new HashSet<>();
        Pattern master = Pattern.compile(pattern + "(.*?)(?=" + breakPattern + ")");
        System.out.println("Master pattern: " + master);
        Matcher m = master.matcher(html);
        while(m.find()){
            ids.add(m.group(1));
        }
        return ids;
    }

    /**
     * Extract a set of IDs from an HTML document. {@code pattern} and {@code breakPattern} are not compiled as p'atterns.
     * @see IDParsing#extractIDs(String, Pattern, Pattern)
     */
    public static Set<String> extractIDs(String html, String pattern, String breakPattern){
        return extractIDs(html, Pattern.compile(Pattern.quote(breakPattern)), Pattern.compile(Pattern.quote(breakPattern)));
    }
}
