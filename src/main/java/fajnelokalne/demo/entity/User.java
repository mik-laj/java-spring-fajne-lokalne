package fajnelokalne.demo.entity;

import io.springlets.format.EntityFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="lokalnes_user")
@EntityFormat("#{firstName} #{lastName}")
@EqualsAndHashCode(exclude = "roles")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    String username;
    String firstName;
    String lastName;
    String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable()
    Set<Role> roles;

    public void addRole(Role role){
        if(!containsTag(role)){
            roles.add(role);
        }
    }

    public void removeRole(Role tag) {
        roles.stream()
                .filter(t -> t.getName().equals(tag.getName()))
                .findAny()
                .ifPresent(tag1 -> roles.remove(tag1));
    }


    private boolean containsTag(Role role) {
        return roles.stream()
                .anyMatch(t -> t.getName().equals(role.getName()));
    }
}
