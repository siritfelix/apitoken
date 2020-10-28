package springboot.api1.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.api1.persistence.entity.UserApi;
@Repository
public interface UserRespository extends JpaRepository<UserApi, String> {
    public UserApi findByUsername(String username);
}
