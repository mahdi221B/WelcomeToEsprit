package tn.esprit.spring.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Hashtag;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    Hashtag findByText(String hashtagText);
    @Query("SELECT h FROM Hashtag h GROUP BY h.id ORDER BY h.count DESC ")
    List<Hashtag> findTopByCount();
}