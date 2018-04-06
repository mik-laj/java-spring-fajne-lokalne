package fajnelokalne.demo.formdata;

import fajnelokalne.demo.validator.FieldMatch;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "passwordConfirm", message = "The password fields must match")
public class RegistrationFormData {

    @Size(min=5, max=32)
    String firstName;

    @Size(min=5, max=32)
    String lastName;

    @Size(min=5, max=32)
    String username;

    @Size(min=5, max=32)
    String password;

    @Size(min=5, max=32)
    String passwordConfirm;
}
