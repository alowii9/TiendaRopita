package ar.edu.davinci.dvds20211cg6.service;
import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.domain.Prenda;

import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import ar.edu.davinci.dvds20211cg6.repository.PrendaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrendaServiceImpl implements PrendaService{

    private final Logger logger = LoggerFactory.getLogger(PrendaServiceImpl.class);

    private final PrendaRepository repository;

    public PrendaServiceImpl(final PrendaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Prenda save(Prenda prenda) throws BusinessException {
        if (prenda.getId() != 0) { // Long default value is '0'
            throw new BusinessException("No se puede crear una prenda con un id especifico");
        }
        logger.info("La prenda ha sido guardada.");
        return repository.save(prenda);
    }

    @Override
    public Prenda update(Prenda prenda) throws BusinessException {
        if (!isPrendaExistente(prenda.getId())) {
            throw new BusinessException("No se puede modificar una prenda no existente");
        }
        logger.info("La prenda ha sido modificada.");
        return repository.save(prenda);
    }

    @Override
    public void delete(Prenda prenda) throws BusinessException {
        if (!isPrendaExistente(prenda.getId())) {
            throw new BusinessException("No se puede borrar una prenda no existente");
        }
        logger.info("La prenda ha sido borrada.");
        repository.delete(prenda);
    }

    @Override
    public void delete(Long id) throws BusinessException {
        if (!isPrendaExistente(id)) {
            throw new BusinessException("No se puede borrar una prenda no existente");
        }
        logger.info("La prenda ha sido borrada.");
        repository.deleteById(id);
    }

    @Override
    public Prenda findById(Long id) throws BusinessException {
        if (!isPrendaExistente(id)){
            throw new BusinessException("No existe la prenda buscada");
        }
        return repository.findById(id).get();
    }

    @Override
    public List<Prenda> lista() {
        return repository.findAll();
    }

    @Override
    public Page<Prenda> lista(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<TipoPrenda> getTiposPrenda() {
        return TipoPrenda.getTiposPrenda();
    }

    @Override
    public List<Prenda> list() {
        return null;
    }

    private boolean isPrendaExistente(Long id) {
        Optional<Prenda> prendaBuscada = repository.findById(id);
        return prendaBuscada.isPresent();
    }
}
