package com.example.nba.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.example.nba.interfaces.*;

/**
 * Util class for reading from the file and instantiate each entry 
 */
public class FReader<T extends IdBounded & FileBounded> {
    public final String FNAME;
    private final RandomAccessFile in;
    private final Class<T> type;
    private Constructor<T> constructor;

    /**
     * The filename as a string and the class type for intantianting a entity 
     * @param fname
     * @param type
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public FReader(String fname, Class<T> type) throws IOException,NoSuchMethodException, SecurityException{
        FNAME = fname;
        in = new RandomAccessFile(FNAME, "r");
        this.type = type;    
        this.constructor = this.type.getDeclaredConstructor(String[].class);
        
    }
    /**
     * List all the entries in the file each row(entry) <=> each instance
     * @return
     * @throws IOException
     */
    public List<T> initListElements()throws IOException{
        //read the line with the schema of the file
        List<T> instances = new ArrayList<>();
        //set the reader to the begging of the file 
        in.seek(0);
        String line = in.readLine();
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
    /**
     * get the kth entry which is equal in logic with get by id
     * @param k
     * @return
     * @throws IOException
     */
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
