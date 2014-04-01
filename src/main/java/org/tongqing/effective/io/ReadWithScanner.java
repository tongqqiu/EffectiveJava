package org.tongqing.effective.io;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * User: TQiu
 * Date: 4/1/2014
 */
public class ReadWithScanner {
    public static void main(String... aArgs) throws IOException {
        ReadWithScanner parser = new ReadWithScanner("/test.txt");
        parser.processLineByLine();
        log("Done.");
    }

    /**
     Constructor.
     @param aFileName full name of an existing, readable file.
     */
    public ReadWithScanner(String aFileName){
        try {
            URL resourceUrl = getClass().getResource(aFileName);
            fFilePath = Paths.get(resourceUrl.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /** Template method that calls {@link #processLine(String)}.  */
    public final void processLineByLine() throws IOException {
        try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                processLine(scanner.nextLine());
            }
        }
    }

    /**
     Overridable method for processing lines in different ways.

     <P>This simple default implementation expects simple name-value pairs, separated by an
     '=' sign. Examples of valid input:
     <tt>height = 167cm</tt>
     <tt>mass =  65kg</tt>
     <tt>disposition =  "grumpy"</tt>
     <tt>this is the name = this is the value</tt>
     */
    protected void processLine(String aLine){
        //use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(aLine);
        scanner.useDelimiter("=");
        if (scanner.hasNext()){
            //assumes the line has a certain structure
            String name = scanner.next();
            String value = scanner.next();
            log("Name is : " + quote(name.trim()) + ", and Value is : " + quote(value.trim()));
        }
        else {
            log("Empty or invalid line. Unable to process.");
        }
    }

    // PRIVATE
    private Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object aObject){
        System.out.println(String.valueOf(aObject));
    }

    private String quote(String aText){
        String QUOTE = "'";
        return QUOTE + aText + QUOTE;
    }
}
