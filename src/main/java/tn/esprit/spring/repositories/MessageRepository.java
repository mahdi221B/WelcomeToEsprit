package tn.esprit.spring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Message;
import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m WHERE m.deleted = false")
    List<Message> findAll();
    @Override
    @Modifying
    @Query("UPDATE Message m SET m.deleted = true WHERE m.id = ?1")
    void deleteById(Integer integer);
    @Query("SELECT m FROM Message m WHERE m.createdAt <= :oneYearAgo AND m.deleted = true")
    List<Message> findMessageByDeletedIAndCreatedAt(@Param("oneYearAgo") Date oneYearAgo);
}