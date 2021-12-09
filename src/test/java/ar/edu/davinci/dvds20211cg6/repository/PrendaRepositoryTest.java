package ar.edu.davinci.dvds20211cg6.repository;

import ar.edu.davinci.dvds20211cg6.domain.Prenda;

import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PrendaRepositoryTest {
    final Logger LOGGER = LoggerFactory.getLogger(PrendaRepository.class);

    @Autowired
    private PrendaRepository prendaRepository;

    @Test
    void testFindAll() {
        assertNotNull(prendaRepository);
        List<Prenda> prendas = prendaRepository.findAll();

        LOGGER.info("Prendas encontradas: "+prendas.size());

        assertNotNull(prendas, "La lista de prendas es nula");
        assertTrue(!prendas.isEmpty(), "No existen prendas");
    }

    @Test
    void testFindById() {
        Long id_no_existe = 58L;
        Long id_existe = 3L;
        BigDecimal precio = new BigDecimal(2499.99).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        Prenda prendaExiste = prendaRepository.findById(id_existe).get();
        Optional<Prenda> prendaNoExiste = prendaRepository.findById(id_no_existe);

        LOGGER.info(String.format("La prenda para el id %s no fue encontrada", id_no_existe));
        assertTrue(!prendaNoExiste.isPresent());

        assertEquals("Hoodie One Punch Man", prendaExiste.getDescripcion());
        assertEquals(precio, prendaExiste.getPrecioBase().stripTrailingZeros());
        assertEquals(TipoPrenda.HODDIE, prendaExiste.getTipo());
    }
}