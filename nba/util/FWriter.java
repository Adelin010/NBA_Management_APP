package nba.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nba.interfaces.FileBounded;
import nba.interfaces.IdBounded;


/**
 * Util Class for writting in the file, replace or update the files
 */
public class FWriter<T extends IdBounded & FileBounded> {
    private final String FNAME;
    private final Class<T> type;
    private BufferedWriter out;
    /**
     * Constructor expect filename and class type 
     * @param fname
     * @param type
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public FWriter(String fname, Class<T> type)throws IOException, NoSuchMethodException, SecurityException{
        FNAME = fname;
        this.type = type;
        
    }
    /**
     * Append the text which should be in a data format at the end of the file
     * @param text
     * @throws IOException
     */
    public void append(String text)throws IOException{
        //create the link
        FileWriter fr =  new FileWriter(FNAME, true);
        out = new BufferedWriter(fr);
        out.append("\n" + text);
        out.close();
        fr.close();
    }

    /**
     * Delete the line which has the id
     * @param lineNumber
     * @throws IOException
     */
    public void delete(int lineNumber)throws IOException{
        update(lineNumber, "");
    }
    /**
     * Replace the line with id with a new line that has the good data
     * @param lineNumber
     * @param text
     * @throws IOException
     */
    public void update(int lineNumber, String text)throws IOException{
        change(lineNumber, text);
    }

    public String getFN(){
        return FNAME
    ;}
    public Class<T> getInstanceType(){
        return type;
    }
    
    /**
     * Private util method to open a connection to the file for writting
     * @param content
     * @throws IOException
     */
    private void write(String content)throws IOException{
        FileWriter fr = new FileWriter(FNAME);
        out = new BufferedWriter(fr);
        out.write(content);
        out.close();
        fr.close();
    }

    /**
     * Change funvtion is private and servers for both replacing a line for the update- transaction
     * and also for the deleting transaction
     * @param lineNumber
     * @param text
     * @throws IOException
     */
    private void change(int lineNumber, String text)throws IOException{
        //Read the file until the line we need to replace
        BufferedReader bfr = new BufferedReader(new FileReader(FNAME));
        String content  = "", line;
        boolean isFirst = true;
        //to stop right before the old line
        while((line = bfr.readLine()) != null && lineNumber != 0){
            lineNumber--;
            if(isFirst){
                content = content + line;
                isFirst = false;
            }
            else 
                content = content + "\n" + line;
        }        
        /*
         * Replace the old text with the new one 
         * Or in case we have a blank text="" => we execute a delete function
         */
        boolean deleteMode = true;
        if(text != ""){
            content = content + "\n" + text;
            deleteMode = false;
        }
        //Read the rest of the file
        while((line = bfr.readLine()) != null){
            if(deleteMode){
                String[] comp = line.split(",");
                comp[0] = String.valueOf(Integer.parseInt(comp[0]) -1);
                line = String.join(",",comp);
            }
            content = content + "\n" + line;
        }
        bfr.close();
        write(content);
    }

}


