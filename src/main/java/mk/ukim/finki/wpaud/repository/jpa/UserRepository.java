package mk.ukim.finki.wpaud.repository.jpa;

import mk.ukim.finki.wpaud.model.Role;
import mk.ukim.finki.wpaud.model.User;
import mk.ukim.finki.wpaud.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    // FETCH - site specificirani atributi kje bidat tretirani so EAGER type, drugite so LAZY
    // LOAD - site specificirani atributi kje bidat tretirani so EAGER type, drugite spored ona vo modelot/default-noto

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts"})
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"carts", "discount"})
    @Query("select u from User u")
    List<User> loadAll();

    // Projections

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

    UserProjection findByRole(Role role);
}
