
package Library;

public class AdminFactory implements UserFactory{
    @Override
    public User createUser(String name, String email, String phonenumber, String password) {
        return new Admin(name, email, phonenumber, password);
    }
    
}
