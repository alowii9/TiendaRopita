package ar.edu.davinci.dvds20211cg6.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20211cg6.domain.Cliente;
import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService  {


    private final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository repository;

    @Autowired
    public ClienteServiceImpl (final ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente save(Cliente cliente) throws BusinessException {
        LOGGER.debug("Grabamos la cliente con el id: " + cliente.getId());

        if (cliente.getId() == null) {
            return repository.save(cliente);
        }
        throw new BusinessException("No se puede crear una cliente con un id específico");
    }

    @Override
    public Cliente update(Cliente cliente) throws BusinessException {
        LOGGER.debug("Modificamos la cliente con el id: " + cliente.getId());

        if (cliente.getId() != null) {
            return repository.save(cliente);
        }
        throw new BusinessException("No se puede modificar una cliente no creada");

    }

    @Override
    public void delete(Cliente cliente) {
        LOGGER.debug("Borrando la cliente con el id: " + cliente.getId());

        repository.delete(cliente);
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("Borrando la cliente con el id: " + id);

        repository.deleteById(id);
    }

    @Override
    public Cliente findById(Long id) throws BusinessException {
        LOGGER.debug("Busqueda de una cliente por ID");

        Optional<Cliente> clienteOptional = repository.findById(id);
        if (clienteOptional.isPresent()) {
            return clienteOptional.get();
        }

        throw new BusinessException("No se encotró la cliente por el id: " + id);
    }

    @Override
    public List<Cliente> listAll() {
        LOGGER.debug("Listado de todas las clientes");

        return repository.findAll();
    }

    @Override
    public Page<Cliente> list(Pageable pageable) {
        LOGGER.debug("Listado de todas las clientes paginadas");
        LOGGER.debug("Pageable, offset:" + pageable.getOffset() + ", pageSize: " + pageable.getPageSize()+ ", pageNumber: " + pageable.getPageNumber());

        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

}

