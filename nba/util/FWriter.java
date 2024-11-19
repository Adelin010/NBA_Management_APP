package nba.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import nba.interfaces.FileBounded;
import nba.interfaces.IdBounded;


public class FWriter<T extends IdBounded & FileBounded> {
    private final String FNAME;
    private final Class<T> type;
    private BufferedWriter out;

    public FWriter(String fname, Class<T> type)throws IOException, NoSuchMethodException, SecurityException{
        FNAME = fname;
        this.type = type;
        
    }
    
    public void append(String text)throws IOException{
        //create the link
        FileWriter fr =  new FileWriter(FNAME, true);
        out = new BufferedWriter(fr);
        out.append("\n" + text);
        out.close();
        fr.close();
    }

    public void delete(int lineNumber)throws IOException{
        update(lineNumber, "");
    }

    public void update(int lineNumber, String text)throws IOException{
        change(lineNumber, text);
    }

    public String getFN(){
        return FNAME
    ;}
    public Class<T> getInstanceType(){
        return type;
    }
    
    //Private methods for internal usage
    private void write(String content)throws IOException{
        FileWriter fr = new FileWriter(FNAME);
        out = new BufferedWriter(fr);
        out.write(content);
        out.close();
        fr.close();
    }


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


