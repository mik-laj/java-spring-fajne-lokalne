package fajnelokalne.demo.formdata;

import fajnelokalne.demo.entity.Role;
import fajnelokalne.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
public class EditUserFormData {
    @Size(min=5, max=32)
    String firstName;

    @Size(min=5, max=32)
    String lastName;

    @Size(min=5, max=32)
    String username;

    List<String> roles = new ArrayList<>();

    public EditUserFormData(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream().map(Role::getName).collect(toList());
    }
}
