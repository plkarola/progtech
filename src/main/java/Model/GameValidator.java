package Model;

import java.util.regex.Pattern;

/**
 *
 * @author plkar
 */
public class GameValidator {
    private GameDAO gameDao;
    
    public GameValidator(GameDAO gameDao) {
        this.gameDao = gameDao;
    }
    
    Pattern pattern = Pattern.compile("^[1-9]{0,1}$");

    public String validateCell(String oldValue, String newValue) {
        if (pattern.matcher(newValue).matches()) {
               return newValue; 
        }
        else {
            if(newValue.length() >= 1 
                    && !newValue.substring(newValue.length()-1, newValue.length()).equals("0") 
                    && pattern.matcher(newValue.substring(newValue.length()-1, newValue.length())).matches())
            {
                //System.out.println("OLDVALUE: " + oldValue);
                return newValue.substring(newValue.length()-1, newValue.length());}
        }
        return oldValue;
    }
    
    public boolean validateRow(int matr[][], int number, int pos_i, int pos_j){
        for(int i=0; i<9; i++) {
            if (matr[pos_i][i] == number && i!=pos_j) {
		return false;
            }
	}
        return true;
    }
    
    public boolean validateCol(int matr[][], int number, int pos_i, int pos_j){
        for(int j=0; j<9; j++) {
            if (matr[j][pos_j] == number && j!=pos_i) {
		return false;
            }
	}
        return true;
    }
    
    public boolean validate3X3(int matr[][], int number, int pos_i, int pos_j) {
	int osztas_i, osztas_j, i, j;
		
	osztas_i = (pos_i / 3) * 3;
	osztas_j = (pos_j / 3) * 3;
	for(i = osztas_i ; i < (osztas_i+3); i++) {
            for(j = osztas_j; j < (osztas_j+3); j++) {
		if(matr[i][j] == number && i!=pos_i && j!=pos_j){ 
                    return false;
                }
            }
	}
	return true;
    }
    
    public boolean validateTable(int matr[][], int number, int pos_i, int pos_j) {
        return (validateRow(matr, number, pos_i, pos_j) && validateCol(matr,number, pos_i, pos_j) && validate3X3(matr,number, pos_i, pos_j)) || number==0;
    }
    
    public boolean isTableComplete(int matr[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (matr[i][j] == 0)
                {
                    return false; 
                }
            }
        }
        return true;
    }
    
    public int[][] getErrors(int matr[][],int number, int pos_i, int pos_j ) {
        int[][] errors = new int[9][9];
        for (int i = 0; i < 9; i++) {

           if(matr[i][pos_j] == number && i != pos_i) 
               errors[i][pos_j] = 1;

           if(matr[pos_i][i] == number && i != pos_j)
               errors[pos_i][i] = 1;
        }
        
        int osztas_i = (pos_i / 3) * 3;
	int osztas_j = (pos_j / 3) * 3;
	for(int i = osztas_i ; i < (osztas_i+3); i++) {
            for(int j = osztas_j; j < (osztas_j+3); j++) {
		if(matr[i][j] == number && (i!=pos_i && j!=pos_j) )
                    errors[i][j] = 1;
		}
	}
        return errors;
    }
}
