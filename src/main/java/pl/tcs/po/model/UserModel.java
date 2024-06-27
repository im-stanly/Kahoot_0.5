package pl.tcs.po.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String telephone;
    private String email;
}
