package pl.tcs.po.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tcs.po.exception.NotFoundException;
import pl.tcs.po.model.UserModel;

import java.util.List;

@Repository
public class UserRepository implements RepoInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String GET_USER_PROPERTIES_SQL = "SELECT id, first_name, last_name, address, city, " +
            "telephone, email FROM Users";
    private final String TABLE_NAME = "Users";

    public List<UserModel> getUsers(){
        return jdbcTemplate.query(GET_USER_PROPERTIES_SQL,
                BeanPropertyRowMapper.newInstance(UserModel.class));
    }
    public UserModel getById(int id){
        return getRecordByKind(jdbcTemplate, GET_USER_PROPERTIES_SQL, TABLE_NAME,
                UserModel.class, "id", id).get(0);
    }
    public List<UserModel> getByName(String name){
        return getRecordByKind(jdbcTemplate, GET_USER_PROPERTIES_SQL, TABLE_NAME,
                UserModel.class,"first_name", name);
    }

    public int save(List<UserModel> users){
        users.forEach( singlePer ->
                jdbcTemplate.update(
                        "INSERT INTO Users(first_name, last_name, address, city, telephone, email) " +
                                "VALUES(?, ?, ?, ?, ?, ?)",
                        singlePer.getFirst_name(), singlePer.getLast_name(), singlePer.getAddress(),
                        singlePer.getCity(), singlePer.getTelephone(), singlePer.getEmail()
                ));
        return 202;
    }

    public int update(int oldId, UserModel user){
        return jdbcTemplate.update(
                "UPDATE Users SET first_name = ?, last_name = ?, address = ?, city = ?, telephone = ?, " +
                        "email = ? WHERE id=?",
                user.getFirst_name(), user.getLast_name(), user.getAddress(), user.getCity(),
                user.getTelephone(), user.getEmail(), oldId) > 0 ? 202 : 418;
    }

    public int delete(int id){
        if (!isElementOfLibrary(jdbcTemplate,"Users", "id", id))
            throw new NotFoundException("User", id);
        return jdbcTemplate.update("DELETE FROM Users WHERE id = " + id) > 0 ? 202 : 418;
    }
}