package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entity.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
}