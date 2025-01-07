package com.example.nba.interfaces;

import java.util.List;

/**
 * An interface that defines the basic CRUD operations for a repository.
 *
 * @param <T> The type of objects stored in the repository
 */
public interface Repo<T extends Entity> {
    /**
     * Creates a new object in the repository.
     *
     * @param obj The object to create.
     */
    void add(T obj) ;
     /**
     * Updates an existing object in the repository.
     *
     * @param obj The object to update.
     */
    void update(T obj);
      /**
     * Retrieves an object from the repository by its ID.
     *
     * @param id The unique identifier of the object to retrieve.
     * @return The object with the specified ID, or null if not found.
     */
    T get(Integer id);
    
    /**
     * Deletes an object from the repository by its ID.
     *
     * @param id The unique identifier of the object to delete.
     */
    void delete(Integer id);
      /**
     * Retrieves all objects from the repository.
     *
     * @return A list of all objects in the repository.
     */
    List<T> getAll();
}