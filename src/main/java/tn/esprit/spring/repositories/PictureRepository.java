package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.Picture;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
}