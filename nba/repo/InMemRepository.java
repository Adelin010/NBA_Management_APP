package nba.repo;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
//######################
import nba.model.IdBounded;


public class InMemRepository<T extends IdBounded> implements Repository<T>{


    private final Map<Integer, T> data = new TreeMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(T instance){
        data.putIfAbsent(instance.getId(), instance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Integer id){
        data.remove(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override 
    public void update(T instance){
        data.replace(instance.getId(), instance);
    }
    /**
     * {@inheritDoc}
     */
    @Override 
    public T get(Integer id){
        return data.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<T> getAll(){
        return data.values().stream().toList();
    }
}