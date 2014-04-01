package org.tongqing.effective.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: TQiu
 * Date: 4/1/2014
 */
public class ExerciseSerializableNew {
    @SuppressWarnings("unchecked")
    public static void main(String... aArguments) {
        //create a Serializable List
        List<String> quarks = Arrays.asList(
                "up", "down", "strange", "charm", "top", "bottom"
        );

        //serialize the List
        try (
                OutputStream file = new FileOutputStream("quarks.ser");
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(quarks);
        }
        catch(IOException ex){
            fLogger.log(Level.SEVERE, "Cannot perform output.", ex);
        }

        //deserialize the quarks.ser file
        try(
                InputStream file = new FileInputStream("quarks.ser");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            //deserialize the List
            List<String> recoveredQuarks = (List<String>)input.readObject();
            //display its data
            for(String quark: recoveredQuarks){
                System.out.println("Recovered Quark: " + quark);
            }
        }
        catch(ClassNotFoundException ex){
            fLogger.log(Level.SEVERE, "Cannot perform input. Class not found.", ex);
        }
        catch(IOException ex){
            fLogger.log(Level.SEVERE, "Cannot perform input.", ex);
        }
    }

    // PRIVATE

    private static final Logger fLogger =
            Logger.getLogger(ExerciseSerializableNew.class.getPackage().getName());
}
