package com.example.nba.util;

import com.example.nba.interfaces.Entity;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        //add a new object in the file
        if(append){
            bfw = new BufferedWriter(new FileWriter(fileSource, append));
            bfw.append(object.seq(false) + "\n");
            bfw.close();
            return;
        }
        //replace the object 
        String lines = "";
        String line = "";
        int id = object.getId(), i = 1;
        bfr = new BufferedReader(new FileReader(fileSource));
        lines += bfr.readLine();
        lines += "\n";
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
        bfr.close();
        bfw = new BufferedWriter(new FileWriter(fileSource));
        bfw.write(lines);
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
        writeLine(Object, false );
    
    }
    public void removeObject(Integer id)throws IOException{
        int i = 1;
        String lines = "", line = "";
        bfr = new BufferedReader(new FileReader(fileUrl.getFile()));
        lines += bfr.readLine() + "\n";
        while((line = bfr.readLine()) != null){
            if(id == i){
                i++;
                continue;
            }
            //appends the current line to the new one 
            if(i > id){
                //all the object after the removed one must have a 
                String[] args = line.split(",");
                Integer id_ = Integer.parseInt((args[0]));
                id_ -= 1;
                args[0] = Integer.toString(id_);
                line = String.join(",", args);
            }
            lines += line + "\n";
            i++;
        }
        bfr.close();
        bfw = new BufferedWriter(new FileWriter(fileUrl.getFile()));
        bfw.write(lines);
        bfw.close();
    }

    public List<T> getAllObjects() throws Exception{
        String line = "";
        ArrayList<T> array = new ArrayList<>();
        bfr = new BufferedReader(new FileReader(fileUrl.getFile()));
        //read the schema line
        bfr.readLine();
        Constructor<T> constr = type.getDeclaredConstructor(String[].class);
        while((line = bfr.readLine()) != null){
            array.add(constr.newInstance((Object)line.split(",")));
        }
        return array;
    }

    
}
