package nba.util;

import java.lang.reflect.Constructor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nba.interfaces.FileBounded;
import nba.interfaces.IdBounded;

//look\na 6 - 4 -2
// to the end of the file -> raf.seek(raf.length())
//raf.write() at the end to append to the file 
//if you want to change a line with raf

//

public class FWriter<T extends IdBounded & FileBounded> {
    private final String FNAME;
    private final Class<T> type;
    private BufferedWriter out;
    private final Constructor<T> constructor;
    private short newLineBytes;

    public FWriter(String fname, Class<T> type)throws IOException, NoSuchMethodException, SecurityException{
        FNAME = fname;
        this.type = type;
        this.constructor = this.type.getConstructor(String[].class);
        //set the number of bytes to substract for the new line
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            newLineBytes = 2;
        else if(os.contains("nix") || os.contains("nux"))
            newLineBytes = 1;
    }


    private void write(String content)throws IOException{
        FileWriter fr = new FileWriter(FNAME);
        out = new BufferedWriter(fr);
        out.write(content);
        out.close();
        fr.close();
    }
    

    public void update(int lineNumber, String text)throws IOException{
        //Read the file until the line we need to replace
        BufferedReader bfr = new BufferedReader(new FileReader(FNAME));
        String content  = "", line;
        boolean isFirst = true;
        //to stop right before the old line
        while((line = bfr.readLine()) != null && lineNumber != 0){
            lineNumber--;
            if(isFirst){
                content = content + line;
                System.out.println(content);
                isFirst = false;
            }
            else 
                content = content + "\n" + line;
                System.out.println(content);
        }        
        //replace the old line with the new text
        content = content + "\n" + text;
        System.out.println(content);
        while((line = bfr.readLine()) != null){
            content = content + "\n" + line;
            System.out.println(content);
        }
        bfr.close();
        write(content);
    }



    public String getFN(){return FNAME;}
    public short getNewLineBytes(){return newLineBytes;}

    public void append(String text)throws IOException{
        //create the link
        FileWriter fr =  new FileWriter(FNAME, true);
        out = new BufferedWriter(fr);
        out.append("\n" + text);
        out.close();
        fr.close();
    }
}
