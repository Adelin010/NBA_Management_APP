package nba.model;

import java.io.Serializable;

@FunctionalInterface
public interface IdBounded extends Serializable{

    /**
     * Gets the unique identifier of the object.
     * 
     * @return the unique identifier.
     */
    Integer getId();
    
}
