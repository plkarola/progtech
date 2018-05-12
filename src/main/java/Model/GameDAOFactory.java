package Model;

/**
 *
 * @author plkar
 */
public class GameDAOFactory {
    private static GameDAOFactory instance;
    static {
        instance = new GameDAOFactory();
    }
    private GameDAOFactory() {
    }

    public static GameDAOFactory getInstance() {

        return instance;
    }

    public GameDAO createGameDAO() {
        return new GameDAOImpl();
    }
}
