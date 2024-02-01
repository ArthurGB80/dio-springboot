package dio.aula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dio.aula.model.User;
import dio.aula.repository.UserRepository;

@Component
public class StartApp implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        printUsersWithNameContaining("GLEYSON");
        insertUser();
    }

    private void printUsersWithNameContaining(String name) {
        List<User> users = repository.findByNameContaining(name);
        if (!users.isEmpty()) {
            printUsers(users);
        }
    }

    private void printUsers(List<User> users) {
        for (User u : users) {
            System.out.println(u);
        }
    }

    private void insertUser() {
        User user = new User();
        user.setName("GABRIEL NUNES");
        user.setUsername("gabriel");
        user.setPassword("santos");
        repository.save(user);

        printAllUsers();
    }

    private void printAllUsers() {
        List<User> users = repository.findAll();
        if (!users.isEmpty()) {
            printUsers(users);
        }
    }
}