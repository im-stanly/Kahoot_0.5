package pl.tcs.po.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tcs.po.model.UserModel;
import pl.tcs.po.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserModel> getPersons(){
        return userService.getPersons();
    }

    @GetMapping("/id={id}")
    public UserModel getPerson(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @GetMapping("/name={name}")
    public List<UserModel> getPerson(@PathVariable("name") String name){
        return userService.getByName(name);
    }

    @PostMapping("")
    public int add(@RequestBody List<UserModel> newPerson){
        System.out.println("Dodawanie");
        return userService.save(newPerson);
    }

    @PatchMapping("/id={id}")
    public int patch(@PathVariable("id") int id, @RequestBody UserModel newPerson){
        return userService.patch(id, newPerson);
    }

    @PutMapping("/id={id}")
    public int put(@PathVariable("id") int id, @RequestBody UserModel newPerson){
        return userService.update(id, newPerson);
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable("id") int id){
        return userService.delete(id) != 1 ? 202 : 500;
    }
}