package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserDAOImpl implements UserDAO{
    private String path = "";
    
    public UserDAOImpl(String path) {
        this.path = path;
    }
    
    public UserDAOImpl(){
        
    }
    
    @Override
    public void createUser(String username, String pw){
        String password = PasswordHasher.encodePassword(pw);
        try{
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();

            File input = new File("Users.xml");

            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
            
            Node users = doc.getFirstChild();
            
            Element user = doc.createElement("User");
            
            Element name = doc.createElement("Username");
            name.appendChild(doc.createTextNode(username));
            user.appendChild(name);
            
            Element passw = doc.createElement("Password");
            passw.appendChild(doc.createTextNode(password));
            user.appendChild(passw);
            
            Element difficulty = doc.createElement("Difficulty");
            difficulty.appendChild(doc.createTextNode(""));
            user.appendChild(difficulty);
            
            Element table = doc.createElement("Table");
            table.appendChild(doc.createTextNode(""));
            user.appendChild(table);
            
            users.appendChild(user);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("Users.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

        }
        catch (Exception e) {e.printStackTrace();}

    }    

    @Override
    public List<User> findUser(String username){
        List<User> users = new ArrayList<>();
        try{
            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
            File input = new File("Users.xml");
            Document doc = db.parse(input);
            doc.getDocumentElement().normalize();
        
            NodeList nl = doc.getElementsByTagName("User");
            for (int i = 0; i< nl.getLength(); i++) {
                Element user = (Element)nl.item(i);
                NodeList un = user.getElementsByTagName("Username");
                NodeList up = user.getElementsByTagName("Password");
                if (un.item(0).getTextContent().equals(username)) {
                    users.add(new User(username, up.item(0).getTextContent()));
                }
            }
        }
        catch (Exception e) {e.printStackTrace(System.out);}
        return users;
    }

}
