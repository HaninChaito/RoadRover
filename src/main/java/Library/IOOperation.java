package Library;

public interface IOOperation {
	
	public void oper(Database database, User user);

}
/*

Analysis of Your Implementation
IOOperation Interface:

The IOOperation interface is the strategy interface. It defines the contract (oper()) that all concrete strategies must implement.

Concrete Strategies:

In your application, classes like Search, BorrowBook, ReturnBook, AddBook, etc., are concrete strategies.
Each of these classes implements the IOOperation interface and provides its own implementation of the oper() method.

User Class:

The User class (and its subclasses like Admin and NormalUser) uses these strategies interchangeably. 
For example, in the Admin and NormalUser classes, you have arrays of IOOperation objects that represent the operations available to each type of user.

The Strategy Pattern is highly important in your library management system because it:

Enhances flexibility by making it easy to add new operations.

Decouples operations from the User class, improving modularity.

Enables runtime selection of operations, making the system more dynamic.

Improves maintainability by isolating changes to specific operations.

Promotes reusability by allowing operations to be used in multiple contexts.

Increases testability by isolating each operation for unit testing.

Adheres to SOLID principles, making the codebase more robust and scalable.

<<<y3ne lawleha ken bde a3mel implemenation lal methid kelon bil user class. fa huwe ha y5aline bi suhule bala ma 3addel 3al code d8re bd bde zid
operation e5la2 class jdid implements iooperation w bil admin aw normal bs bil opertaion a3mel call la hal class ljdid.

*/