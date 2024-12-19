package com.example.nba.util;

import com.example.nba.interfaces.Entity;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;


import java.io.BufferedReader;

public class FileHandlerUtil<T extends Entity> {
    private BufferedWriter bfw;
    private BufferedReader bfr;
    private final Class<T> type;
    private final String fileName;
    private final URL fileUrl;

    public FileHandlerUtil(Class<T> type,String fileName )throws IllegalArgumentException{
        this.type = type;
        this.fileName = fileName;
        this.fileUrl = this.getClass().getClassLoader().getResource(this.fileName);
        if(this.fileUrl == null)
            throw new IllegalArgumentException("File %s not found".formatted(fileName));

    }

    private String readLine(Integer id)throws IOException{
        
        bfr = new BufferedReader(new FileReader(this.fileUrl.getFile()));
        //Read the line with the schema
        String line = "";
        bfr.readLine();
        for(int i = 1; i <= id; ++i){
            line = bfr.readLine();
        }
        bfr.close();
        return line;
    }

    /**
     * Function for writting a line in the file which serves as database
     * if the append mode is set than the bufferedWriter will append the new info to the end
     * if set to false then we want to replace a already existing object
     * @param object a generic object that extends Entity
     * @throws IOException
     */
    private void writeLine(T object, boolean append)throws IOException{
        String fileSource = fileUrl.getFile();
        bfw = new BufferedWriter(new FileWriter(fileSource, append));
        //add a new object in the file
        if(append){
            System.out.println(object.seq(false));
            bfw.append(object.seq(false) + "\n");
            bfw.close();
            return;
        }
        //replace the object 
        String lines = "";
        String line = "";
        int id = object.getId(), i = 1;
        System.out.println(id);
        bfr = new BufferedReader(new FileReader(fileSource));
        System.out.println(bfr);
        System.out.println(bfr.readLine());
        while((line = bfr.readLine()) != null){
            System.out.println("BEGGING: " + lines);
            if(id == i){
                lines += object.seq(false);
                lines += "\n";
            }else{
                lines += line;
                lines += "\n";
            }
            System.out.println("AFTER: " + lines);
            i++;
        }

        bfw.write(lines);
        bfr.close();
        bfw.close();
    }

    public T getObject(Integer id)throws IllegalArgumentException, IOException, Exception{
        String line = readLine(id);
        System.out.println(line);
        Constructor<T> constr = type.getDeclaredConstructor(String[].class);
        T object = constr.newInstance((Object)(line.split(",")));
        return object;
    }

    public void addObject(T Object)throws IOException{
        writeLine(Object, true);
    }

    public void updateObject(T Object) throws IOException{
        writeLine(Object, false);
    }
    
}
