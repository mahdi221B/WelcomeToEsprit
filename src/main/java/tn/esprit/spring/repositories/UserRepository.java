package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {



    @Query("select u from User u join u.roles role where role.roleName = 'ROLE_TEACHER' ")
    List<User> findTeachersdisponibilty();




   /* @Query("select distinct u from User u join u.roles r join u.availablities a where r.roleName = 'ROLE_TEACHER' and :date between a.date_debut_diso and a.date_fin_diso")
    List<User> findAdminUsersByAvailability(@Param("date") Date date);*/
}
