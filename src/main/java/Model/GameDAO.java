package Model;

import java.util.List;

/**
 *
 * @author plkar
 */
public interface GameDAO {
    public int[][] getBoard(Difficulty diff, int id);
    public List<Game> getEasyBoards();
    public List<Game> getMediumBoards();
    public List<Game> getHardBoards();
}
