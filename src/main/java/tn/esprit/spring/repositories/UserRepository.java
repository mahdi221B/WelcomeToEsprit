package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select distinct u from User u join u.roles r join u.availablities a where r.roleName = 'admin' and :date between a.date_debut_diso and a.date_fin_diso")
    List<User> findAdminUsersByAvailability(@Param("date") Date date);
}
