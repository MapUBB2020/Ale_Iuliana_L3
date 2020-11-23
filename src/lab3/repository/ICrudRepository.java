package lab3.repository;

import Exceptions.IncorrectFileNameException;

import java.io.IOException;
import java.text.ParseException;

/**
 * CRUD operations repository interface
 */
public interface ICrudRepository<E> {

    /**
     *
     * @throws IOException
     * @throws ParseException
     * @throws IncorrectFileNameException
     * Method to read from the json file a list of object with id's and convert it in a list of objects instead of id's
     * For ex. if the teacher has the list of courses [1,2,3], we will modify it in the list of objects with id 1,2,3
     */
    void initialise() throws IOException, ParseException, IncorrectFileNameException;

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null - if there is no entity with the given id
     */
    E findOne(Long id);

    /**
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id already exists)
     */
    E save(E entity);

    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    E delete(Long id);

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id does not exist).
     */
    E update(E entity);
}
