package game;

import Model.GameDAO;
import Model.GameDAOFactory;
import Model.GameValidator;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author plkar
 */
public class GameTest {
    GameDAO gameDao = GameDAOFactory.getInstance().createGameDAO();
    GameValidator validator = new GameValidator(gameDao);
    int[][] board;
    
    @Before
    public void setUp() {
        board = new int[][]{
                            { 0, 4, 7, 3, 5, 2, 1, 6, 0 },
                            { 5, 1, 3, 9, 8, 6, 4, 2, 7 },
                            { 6, 2, 0, 4, 1, 7, 9, 5, 3 },
                            { 4, 8, 9, 1, 6, 3, 2, 7, 5 },
                            { 2, 7, 5, 8, 4, 9, 6, 3, 1 },
                            { 1, 3, 6, 2, 7, 5, 8, 4, 9 },
                            { 0, 6, 4, 5, 9, 1, 3, 8, 2 },
                            { 3, 5, 1, 6, 2, 8, 7, 9, 4 },
                            { 8, 9, 2, 7, 3, 4, 0, 1, 6 }};
    }
    
    @Test
    public void validateCellNumber() {
        assertEquals("5", validator.validateCell("", "5"));
        assertEquals("2", validator.validateCell("1", "12"));
        assertEquals("", validator.validateCell("5", ""));
        assertEquals("", validator.validateCell("", "a"));
        assertEquals("2", validator.validateCell("2", "a"));
    }
    
    @Test
    public void valdateRowWithCorrectNumber(){
        assertEquals(true, validator.validateRow(board, 9, 0, 0));
        assertEquals(true, validator.validateRow(board, 8, 0, 8));
    }
    
    @Test
    public void valdateRowWithInCorrectNumber(){
        assertEquals(false, validator.validateRow(board, 6, 0, 0));
        assertEquals(false, validator.validateRow(board, 5, 6, 0));
    }
    
    @Test
    public void validateColWithInCorrectNumbers(){
        assertEquals(false, validator.validateCol(board, 3, 0, 0));
        assertEquals(false, validator.validateCol(board, 5, 6, 0));
    }
    
    @Test
    public void validateColWithCorrectNumbers(){
        assertEquals(true, validator.validateCol(board, 9, 0, 0));
        assertEquals(true, validator.validateCol(board, 9, 6, 0));
    }
    
    @Test
    public void validate3x3WithCorrectNumers(){
        assertEquals(true, validator.validate3X3(board, 9, 0, 0));
        assertEquals(true, validator.validate3X3(board, 8, 0, 0));
        assertEquals(true, validator.validate3X3(board, 5, 8, 6));
    }
    
    @Test
    public void validate3x3WithInCorrectNumers(){
        assertEquals(false, validator.validate3X3(board, 3, 0, 0));
        assertEquals(false, validator.validate3X3(board, 7, 3, 3));
        assertEquals(false, validator.validate3X3(board, 4, 8, 6));
    }
    
    @Test
    public void validateTable() {
       assertEquals(true, validator.validateTable(board, 9, 0, 0));
       assertEquals(false, validator.validateTable(board, 3, 0, 0));
    }
    
    @Test
    public void isTableCompleteNo() {
       assertEquals(false, validator.isTableComplete(board)); 
    }
    
    @Test
    public void isTableCompleteYes(){
        board[0][0] = 9;
        board[0][8] = 8;
        board[2][2] = 8;
        board[6][0] = 7;
        board[8][6] = 5;
        assertEquals(true, validator.isTableComplete(board));
    }
    
    @Test
    public void getNoErrors(){
        int[][] errors = new int[9][9];
        assertArrayEquals(errors, validator.getErrors(board, 9, 0, 0));
        assertArrayEquals(errors, validator.getErrors(board, 5, 8, 6));
    }
    
    @Test
    public void getErrorsOne(){
        int[][] errors = new int[9][9];
        errors[1][2] = 1;
        errors[0][3] = 1;
        errors[7][0] = 1;
        assertArrayEquals(errors, validator.getErrors(board, 3, 0, 0));
    }
    
    @Test
    public void getErrorsTwo(){
        int[][] errors = new int[9][9];
        errors[8][5] = 1;
        errors[7][8] = 1;
        errors[1][6] = 1;
        assertArrayEquals(errors, validator.getErrors(board, 4, 8, 6));
    }
}
