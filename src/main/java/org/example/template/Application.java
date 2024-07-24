package org.example.template;

import org.example.template.config.Config;
import org.example.template.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;


public class Application {

    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User user = new User(3L, "James", "Brown", (byte) 33);
        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 25);
        communication.saveUser(user);

        communication.updateUser(updatedUser);

        communication.deleteUser(3L);

        System.out.println("Result = " + communication.result);
    }
}
