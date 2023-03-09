package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Type;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Integer> {
    @Query("Select COUNT(*) FROM Reclamation c where c.type = :type")
    int NombreReclamationByType(@Param("type") Type type);
}


