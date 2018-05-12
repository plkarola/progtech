package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class GameDAOImpl implements GameDAO {
    
    Difficulty d;
    
    @Override
    public int[][] getBoard(Difficulty diff, int id){
        int[][] board = new int[9][9];
        List<Game> list = new ArrayList<>();
        switch(diff) {
            case EASY: 
                list = getEasyBoards();
                break;
            case MEDIUM: 
                list = getMediumBoards();
                break;
            case HARD: 
                list = getHardBoards();
                break;
            default:
                list = getEasyBoards();
        }

        if (list.size() >= id) {
            return list.get(id).getMatrix();
        }
        return null;
    }

    @Override
    public List<Game> getEasyBoards() {
        int[][] board;
        List<Game> games = new ArrayList<>();
        try{
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
        
            //File input = new File("Easy.xml");
            //ClassLoader classLoader = getClass().getClassLoader();
            //File input = new File(classLoader.getResource("Easy.xml").getFile());
            String input = this.getClass().getClassLoader().getResource("Tables/Easy.xml").toString();
            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
        
            NodeList tablelist = doc.getElementsByTagName("table");
            
            for(int t = 0; t < tablelist.getLength(); t++) {
                board = new int[9][9];
		Element element = (Element) tablelist.item(t);
		String elem;
		int counter = 0;
		for(int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
			elem = element.getElementsByTagName("column").item(counter).getTextContent();
			board[i][j] = Integer.parseInt(elem);
			counter++;
                    }
                }
                games.add(new Game(board, t, Difficulty.EASY));
            }
        }
        catch (Exception e) {e.printStackTrace(System.out);}
        return games;
    }

    @Override
    public List<Game> getMediumBoards() {
        int[][] board;
        List<Game> games = new ArrayList<>();
        
        try{
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
        
            //File input = new File("Medium.xml");
            String input = this.getClass().getClassLoader().getResource("Tables/Medium.xml").toString();
            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
        
            NodeList tablelist = doc.getElementsByTagName("table");
            
            for(int t = 0; t < tablelist.getLength(); t++) {
                board = new int[9][9];
                Game game;
		Element element = (Element) tablelist.item(t);
		String elem;
		int counter = 0;
		for(int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
			elem = element.getElementsByTagName("column").item(counter).getTextContent();
			board[i][j] = Integer.parseInt(elem);
			counter++;
                    }
                }
                game = new Game(board, t, Difficulty.MEDIUM);
                games.add(game);
            }
        }
        catch (Exception e) {e.printStackTrace(System.out);}
        return games;
    }

    @Override
    public List<Game> getHardBoards() {
        int[][] board;
        List<Game> games = new ArrayList<>();
        try{
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
        
            //File input = new File("Hard.xml");
            String input = this.getClass().getClassLoader().getResource("Tables/Hard.xml").toString();
            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
        
            NodeList tablelist = doc.getElementsByTagName("table");
            
            for(int t = 0; t < tablelist.getLength(); t++) {
                board = new int[9][9];

		Element element = (Element) tablelist.item(t);
		String elem;
		int counter = 0;
		for(int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
			elem = element.getElementsByTagName("column").item(counter).getTextContent();
			board[i][j] = Integer.parseInt(elem);
			counter++;
                    }
                }
                games.add(new Game(board, t, Difficulty.HARD));
            }
        }
        catch (Exception e) {e.printStackTrace(System.out);}
        return games;
    }

}
