package ar.edu.davinci.dvds20211cg6.service;

import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.domain.Prenda;

import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrendaService {

    Prenda save(Prenda prenda) throws BusinessException;    // POST
    Prenda update(Prenda prenda) throws BusinessException;  // PUT-PATCH
    void delete(Prenda prenda) throws BusinessException;    // DELETE
    void delete(Long id) throws BusinessException;          // DELETE
    Prenda findById(Long id) throws BusinessException;      // GET

    List<Prenda> lista();
    Page<Prenda> lista(Pageable pageable);
    long count();
    List<TipoPrenda> getTiposPrenda();

    List<Prenda> list();
}
