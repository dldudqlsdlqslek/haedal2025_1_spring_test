package haedalSpring2025_1.MyFirstProj.repository;

import haedalSpring2025_1.MyFirstProj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
}