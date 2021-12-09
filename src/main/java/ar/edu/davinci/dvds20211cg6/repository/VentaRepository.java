package ar.edu.davinci.dvds20211cg6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.davinci.dvds20211cg6.domain.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}

