package Model;

import java.util.List;

/**
 *
 * @author plkar
 */
public interface UserDAO {
    public void createUser(String username,String password);    
    public List<User> findUser(String username);
}
