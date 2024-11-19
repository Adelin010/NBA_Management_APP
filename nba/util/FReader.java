package nba.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import nba.interfaces.*;

public class FReader<T extends IdBounded & FileBounded> {
    public final String FNAME;
    private final RandomAccessFile in;
    private final Class<T> type;
    private Constructor<T> constructor;

    public FReader(String fname, Class<T> type) throws IOException,NoSuchMethodException, SecurityException{
        FNAME = fname;
        in = new RandomAccessFile(FNAME, "r");
        this.type = type;    
        this.constructor = this.type.getDeclaredConstructor(String[].class);
        
    }

    public List<T> initListElements()throws IOException{
        //read the line with the schema of the file
        List<T> instances = new ArrayList<>();
        //set the reader to the begging of the file 
        in.seek(0);
        String line = in.readLine();
        System.out.println(line);
        while((line = in.readLine()) != null){
            //read the next line from where the data strats
            String[] args = line.split(",");
            //create the intances
            try{
                instances.add(constructor.newInstance((Object)args)); 
            }catch(Exception exp){
                exp.printStackTrace();
            }
        }
        return instances;
    }  
    
    public T getKthEntry(int k) throws IOException{
        //set the reader to the begging of the file
        in.seek(0);
        String line;
        while((line = in.readLine()) != null && k != 0){
            k--;
        }
        //Instance not found
        if(k > 0)
            return null;

        String[] args = line.split(",");
        T instance = null;
        try{
            instance = constructor.newInstance((Object)args);
        }catch(Exception exp){
            exp.printStackTrace();
        }
        return instance;
    }
}
