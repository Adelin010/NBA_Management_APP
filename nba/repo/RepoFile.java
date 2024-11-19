package nba.repo;

import java.util.List;

import nba.interfaces.*;

public class RepoFile<T extends IdBounded & FileBounded> implements Repo<T> {

    @Override
    public void add(T obj) {
    }

    @Override
    public void update(T obj) {
    }

    @Override
    public T get(Integer id) {
        return null;
    }



    @Override
    public void delete(Integer id) {
    }

    @Override
    public List<T> getAll() {
        return null;
    }
    
}
