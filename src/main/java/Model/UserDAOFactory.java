package Model;

/**
 *
 * @author plkar
 */
public class UserDAOFactory {
    private static UserDAOFactory instance;
    
    static {
        instance = new UserDAOFactory();
    }
    
    private UserDAOFactory() {
    }

    public static UserDAOFactory getInstance() {

        return instance;
    }

    public UserDAO createUserDAO() {
        return new UserDAOImpl();
    }
}
