package com.cardealership.dealership.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @GetMapping("")
    public Iterable<User> allUsers(){
        Iterable<User> users = usersRepository.findAll();
        return users;
    }

    @PostMapping("")
    public User newUser(@RequestBody User user){
        return this.usersRepository.save(user);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user){
        Long longId = Long.parseLong(id);
        User toUpdate = this.usersRepository.findById(longId).get();
        if(user.getEmail()!=null){
            toUpdate.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null){
            toUpdate.updatePassword(user.getPassword());
        }
        return this.usersRepository.save(toUpdate);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id){
        Long longId = Long.parseLong(id);
        User toDelete = this.usersRepository.findById(longId).get();
        this.usersRepository.delete(toDelete);
        return toDelete;
    }

    @PostMapping("/login")
    public HashMap<String, Object> authenticate(@RequestBody User user){
        List<User> usersList = usersRepository.findByEmail(user.getEmail());
        if(usersList.size()>0 && usersList.get(0).getPassword().equals(user.getPassword())){
            return new HashMap<String, Object>(){
                {
                    put("authenticated", true);
                    put("user", usersList.get(0));
                }
            };
        }
        else{
            return new HashMap<String, Object>(){
                {
                    put("authenticated", false);

                }
            };
        }
    }
}
