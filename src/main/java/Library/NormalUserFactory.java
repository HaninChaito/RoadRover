package Library;


public class NormalUserFactory implements UserFactory{
     @Override
    public User createUser(String name, String email, String phonenumber, String password) {
        return new NormalUser(name, email, phonenumber, password);
    }
    
}
