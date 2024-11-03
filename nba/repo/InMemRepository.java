package nba.repo;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
//######################
import nba.model.IdBounded;


public class InMemRepository<T extends IdBounded> implements Repository<T>{


    private final Map<Integer, T> data = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(T instance){
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