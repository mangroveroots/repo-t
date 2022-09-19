# repo-test

This is a software solution for a sample course registration system using OOP principles. It includes the following: 
▪  Abstraction, Encapsulation, Inheritance, Method Overriding, Method Overloading and Polymorphism
▪  Sorting  Objects
▪  File/IO in Java
▪  Serialization and Binary Files in Java.

![image](https://user-images.githubusercontent.com/82541758/191068085-9f894e8c-2b92-4ae4-8b03-d1d21682e58f.png)
The above image details the workflow of the program, including user login with the corresponding menu displayed.

![image](https://user-images.githubusercontent.com/82541758/191068191-e8b7b380-76c4-47bb-8595-071943d1165f.png)
This image shows the relationship between my three main classes, User, Admin and Student, along with two interfaces. The other classes in this program are the Course class, which defines course objects, and the Main class (driver class).

This was done as a class project.

--

Method overloading is the quality of two methods to have the same name but accept different parameters. When the method name is called, the compiler will determine which of the two versions of the method to use depending on which input parameters it is being called with. In my code there is a findIndex() method in my User class which accepts an arraylist of type course and an integer value. This method is overloaded in the child class Student, where it takes in different parameters of an arraylist of Student objects along with two String values that give reference to the student object. 

Method overriding is similar to overloading in that the methods share the same name, nut here they share the same parameters as well. Often method overloads are an integral facet of polymorphism when used between parent and child classes. 
I have a viewAll() method in my User class (parent) and in my Admin class (child). Both take an ArrayList of Courses as input parameter but perform slightly differently when called. The decision of which method to use is made at run-time depending on who is calling the method. In the Main class Admin menu choice 6, an admin object calls the method, and so the version of viewAll() in the Admin class is called. In the Main class Student menu choice 1, a student object calls viewAll(), and as a result the method version in User is called, as the child class of Student cannot view the methods of its fellow child class Admin. An interesting point is that an Admin object is capable of calling either version of viewAll(); it depends entirely on the parameters in this case.
Another example of method overriding is through the register() method in the Admin class and the Student class. Both take very different parameters and only an admin object can call the admin version of register, and only a student object can call the student version of register. A User object, on the other hand, has the potential to call either (depending on parameters as well of course).

Methods of a Java interface are implicitly abstract. These methods are implemented by classes that implement the interface and are detailed and made non-abstract by addition of a method body. I could have declared the User class in my program as abstract so that no User objects could be made. This would be helpful as User provides a common base for admin and student, providing many methods that are accessible to both objects.

In Java, if an object “is a” member of more than one type, it is said to be polymorphic. All objects are polymorphic as they can are a member both of their own types and the Object type. I have four objects in my code: user, admin, student and course objects. Out of these, Admin and Student are polymorphic for the User type as well as they are child classes of User.

Polymorphism is also demonstrated through overridden methods (see section above on overriding methods).

My code demonstrates the concept of encapsulation through the definition of certain variables as private, thus reducing visibility of these variables to outside classes. In the User class, the userID and password variables are private, which means that Admin and Student cannot directly access the values unless they use getter and setter methods.
