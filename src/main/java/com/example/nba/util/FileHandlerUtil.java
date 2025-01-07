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
    private int MAXID;

    public FileHandlerUtil(Class<T> type,String fileName )throws IllegalArgumentException{
        this.type = type;
        this.fileName = fileName;
        this.fileUrl = this.getClass().getClassLoader().getResource(this.fileName);
        if(this.fileUrl == null)
            throw new IllegalArgumentException("File %s not found".formatted(fileName));

        //init the id variable in each Class
        // try {
        //     bfr = new BufferedReader(new FileReader(fileUrl.getFile()));
        //     String line = bfr.readLine();
        //     while(line != null){
        //         line = bfr.readLine();
        //     }
        //     int id = -1;
        //     if(line != null){
        //         id = Integer.parseInt(line.split(",")[0]);
        //     }
        //     MAXID = id;

        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }catch (IOException e){
        //     e.printStackTrace();
        // }

    }

    public int getMAXID(){
        return MAXID;
    }
    private String readLine(Integer id)throws IOException{
        
        bfr = new BufferedReader(new FileReader(this.fileUrl.getFile()));
        //Read the line with the schema
        bfr.readLine();
        String line = "";
        while((line = bfr.readLine()) != null){
            if(Integer.parseInt(line.split(",")[0]) == id){
                bfr.close();
                return line;
            }
        }
        bfr.close();
        return "";
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
        int id = object.getId();
        bfr = new BufferedReader(new FileReader(fileSource));
        lines += bfr.readLine();
        lines += "\n";
        while((line = bfr.readLine()) != null){
            System.out.println("BEGGING: " + lines);
            if(id == Integer.parseInt(line.split(",")[0])){
                lines += object.seq(false);
                lines += "\n";
            }else{
                lines += line;
                lines += "\n";
            }
            System.out.println("AFTER: " + lines);
        }
        bfr.close();
        bfw = new BufferedWriter(new FileWriter(fileSource));
        bfw.write(lines);
        bfw.close();
    }

    public T getObject(Integer id)throws IllegalArgumentException, IOException, Exception{
        String line = readLine(id);
        System.out.println(line);
        if(line.equals(""))
            throw new Exception("The id provided is not found in the db");
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
        String lines = "", line = "";
        bfr = new BufferedReader(new FileReader(fileUrl.getFile()));
        lines += bfr.readLine() + "\n";
        while((line = bfr.readLine()) != null){
            if(id == Integer.parseInt(line.split(",")[0])){
                continue;
            }
            //appends the current line to the new one 
            if(Integer.parseInt(line.split(",")[0]) > id){
                //all the object after the removed one must have a 
                String[] args = line.split(",");
                Integer id_ = Integer.parseInt((args[0]));
                id_ -= 1;
                args[0] = Integer.toString(id_);
                line = String.join(",", args);
            }
            lines += line + "\n";
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
