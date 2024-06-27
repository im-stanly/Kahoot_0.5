package pl.tcs.po.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tcs.po.model.UserModel;
import pl.tcs.po.repository.UserRepository;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getPersons(){
        return userRepository.getUsers();
    }

    public UserModel getById(int id){
        return userRepository.getById(id);
    }

    public List<UserModel> getByName(String name){
        if(Character.isLowerCase(name.charAt(0)))
            name = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
        return userRepository.getByName(name);
    }

    public int save(List<UserModel> personsModels){
        return userRepository.save(personsModels);
    }

    public int patch(int id, UserModel user){
        return updateOrPatch(id, user, true);
    }

    public int update(int id, UserModel person){
        return updateOrPatch(id, person, false);
    }
    public int delete(int id){
        return userRepository.delete(id);
    }

    private int updateOrPatch(int id, UserModel updatedUser, boolean isPatch){
        UserModel oldUser = userRepository.getById(id);

        if (isPatch) {
            if (updatedUser.getFirst_name() != null)
                oldUser.setFirst_name(updatedUser.getFirst_name());
            if (updatedUser.getLast_name() != null)
                oldUser.setLast_name(updatedUser.getLast_name());
            if (updatedUser.getTelephone() != null)
                oldUser.setTelephone(updatedUser.getTelephone());
            if (updatedUser.getCity() != null)
                oldUser.setCity(updatedUser.getCity());
            if (updatedUser.getEmail() != null)
                oldUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getAddress() != null)
                oldUser.setAddress(updatedUser.getAddress());
        }
        else
            oldUser = updatedUser;

        return userRepository.update(id, oldUser);
    }
}