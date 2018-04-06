package fajnelokalne.demo.formdata;

import fajnelokalne.demo.validator.FieldMatch;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match")
@NoArgsConstructor
public class CreateUserFormData extends EditUserFormData {
    @Size(min=5, max=32)
    String password;

    @Size(min=5, max=32)
    String passwordConfirm;
}
