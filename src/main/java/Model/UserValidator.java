package Model;

import java.util.List;

/**
 *
 * @author plkar
 */
public class UserValidator {
    private UserDAO userDao;
    
    public UserValidator(UserDAO userDao) {
        this.userDao = userDao;
    }
    
    
    public boolean loginValidate(String username, String password) {
        
        List<User> users = userDao.findUser(username);
        
        return users.size()>0 && users.get(0).getPassword().equals(PasswordHasher.encodePassword(password));        
    }
    
    public boolean regValidateUN(String username) {
        List<User> users = userDao.findUser(username);
        
        return users.isEmpty() && !username.isEmpty();
    }
    
    public boolean regValidatePW(String password, String confirmpw){
        return !password.isEmpty() && !confirmpw.isEmpty() && password.equals(confirmpw);
    }
}
