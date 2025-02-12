/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Library;


public interface UserFactory {
        User createUser(String name, String email, String phonenumber, String password);

}
/*
Why Is This Beneficial?
Single Responsibility Principle (SRP):

Without the Factory Pattern, the Database class (or any other class) would be responsible for both managing user data and creating
user objects.
This violates the Single Responsibility Principle, which states that a class should have only one reason to change.

With the Factory Pattern, the responsibility for creating users is moved to the UserFactory and its
implementations (AdminFactory, NormalUserFactory).
This makes the Database class simpler and more focused on managing user data.

Flexibility to Add New User Types:

Without the Factory Pattern, if you wanted to add a new type of user (e.g., Librarian), you would need to modify the Database class to include the logic for
creating the new user type. This violates the Open/Closed Principle, which states that classes should be open for extension but closed for modification.

With the Factory Pattern, you can simply create a new factory (e.g., LibrarianFactory) without modifying the existing Database class.
This makes the system more flexible and easier to extend.

Centralized Creation Logic:

Without the Factory Pattern, the logic for creating users might be scattered across multiple parts of the system. For example,
if you create Admin and NormalUser objects in different places, you might end up duplicating code or making inconsistent changes.

With the Factory Pattern, all the creation logic is centralized in the factory classes. This makes it easier to maintain and update the creation logic in
one place.

Improved Testability:

Without the Factory Pattern, testing the Database class becomes harder because it is tightly coupled to the creation of Admin and NormalUser objects.
You would need to mock or stub the creation logic, which can be complex.

With the Factory Pattern, you can easily mock the UserFactory interface in your tests, making it easier to test the Database class in isolation.

Easier to Switch Implementations:

Without the Factory Pattern, if you wanted to change how Admin or NormalUser objects are created (e.g., adding validation or logging),
you would need to modify every place where these objects are created.

With the Factory Pattern, you only need to modify the factory classes. The rest of the system remains unchanged, making it easier to switch 
implementations or add new behavior.


Problems Without Factory Pattern:
The Database class is tightly coupled to the creation of Admin and NormalUser objects.

If you add a new user type, you must modify the Database class.

The creation logic is not reusable and is scattered across the system.


The additional benefit that the Factory Pattern adds is decoupling the object creation logic from the client code. Without the Factory Pattern
, the creation logic is tightly coupled to the classes that use the objects, making the system harder to maintain, extend, and test. 
With the Factory Pattern, you achieve a cleaner, more modular, and flexible design that adheres to the Single Responsibility Principle and the Open/Closed
Principle. This benefit is not easily achievable without the Factory Pattern.


*/