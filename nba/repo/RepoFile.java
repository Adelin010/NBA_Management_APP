package nba.repo;

import java.util.List;

import nba.interfaces.*;
import nba.util.*;

public class RepoFile<T extends IdBounded & FileBounded> implements Repo<T> {

    private  FReader<T> fr = null;
    private  FWriter<T> fw = null;
    private final String filename;

    public RepoFile(String fn, Class<T> type){
        filename = fn;
        try{
            fr = new FReader<>(filename, type);
            fw = new FWriter<>(filename, type);
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    @Override
    public void add(T obj) {
        try{
            fw.append(obj.fileFormat());
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    @Override
    public void update(T obj) {
        try{
            fw.update(obj.getId(), obj.fileFormat());
        }catch(Exception exp){
            exp.printStackTrace();
        }
    }

    @Override
    public T get(Integer id) {
        try{
            return fr.getKthEntry(id);
        }catch(Exception exp){
            exp.printStackTrace();
        }
        return null;
    }



    @Override
    public void delete(Integer id) {
        try{
            fw.delete(id);
        }catch(Exception exp){

        }
    }

    @Override
    public List<T> getAll() {
        try{
            return fr.initListElements();
        }catch(Exception exp){
            exp.printStackTrace();
        }
        return null;
    }
    
}
