package springboot.api1.persistence.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_api")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserApi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull    
    @Email
    private String mail;
    @Column(length = 60)
    private String password;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @ElementCollection(targetClass = String.class)
    private List<String> rol = new ArrayList<>();
    @NotNull
    private Boolean enable;

    public List<String> addRol(String roles) {
        this.rol.add(roles);
        return this.rol;
    }

}
