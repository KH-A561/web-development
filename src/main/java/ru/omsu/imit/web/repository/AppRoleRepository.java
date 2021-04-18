package ru.omsu.imit.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.omsu.imit.web.model.AppRole;

import java.util.stream.Stream;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    @Query("select user.appRoles from AppUser user where user.userId = :userId")
    Stream<AppRole> findRoleNamesByUserId(@Param("userId") Long userId);
}
