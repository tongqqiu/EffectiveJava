package org.tongqing.effective.io;

import org.apache.commons.lang.time.StopWatch;

import java.io.*;

/**
 * User: TQiu
 * Date: 4/1/2014
 */
public class BufferDemo {
    public static void main (String... aArguments) {
        File file = new File("C:\\Temp\\blah.txt");
        verifyFile( file );

        StopWatch stopwatch = new StopWatch();

        stopwatch.start();
        readWithBuffer(file);
        stopwatch.stop();
        System.out.println("With buffering: " + stopwatch);

        stopwatch.start();
        readWithoutBuffer(file);
        stopwatch.stop();
        System.out.println("Without buffering: " + stopwatch);
    }

    /**
     * @param aFile is a file which already exists and can be read.
     */
    static public void readWithBuffer(File aFile) {
        try {
            //use buffering, with default buffer size of 8K
            Reader input =  new BufferedReader(new FileReader(aFile));
            try {
                int data = 0;
                while ((data = input.read()) != -1){
                    //do nothing
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * @param aFile is an existing file which can be read.
     */
    static public void readWithoutBuffer(File aFile) {
        try {
            Reader input =  new FileReader(aFile);
            try {
                //do not use buffering
                int data = 0;
                while ((data = input.read()) != -1){
                    //do nothing
                }
            }
            finally {
                input.close();
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static void verifyFile(File aFile) {
        if (aFile == null) {
            throw new IllegalArgumentException("File should not be null.");
        }
        if (!aFile.exists()) {
            throw new IllegalArgumentException ("File does not exist: " + aFile);
        }
        if (!aFile.isFile()) {
            throw new IllegalArgumentException("Should not be a directory: " + aFile);
        }
        if (!aFile.canWrite()) {
            throw new IllegalArgumentException("File cannot be written: " + aFile);
        }
    }
}
