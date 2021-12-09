package ar.edu.davinci.dvds20211cg6.repository;

import ar.edu.davinci.dvds20211cg6.domain.Prenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendaRepository extends JpaRepository<Prenda, Long> {

}
