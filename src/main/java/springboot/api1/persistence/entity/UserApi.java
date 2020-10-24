package springboot.api1.persistence.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="users_api")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserApi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String mail;
    @Column(length = 60)
    private String password;
    private String username;
    private List<String> rol;
    private String enable;
}
