package realExample.dao;

import realExample.pojo.Mobile;

public interface DAO<Entity, id> {
    boolean create(Entity model);
    Entity read(Integer id);
    boolean update(Entity model);
    boolean delete(Integer id);
}
