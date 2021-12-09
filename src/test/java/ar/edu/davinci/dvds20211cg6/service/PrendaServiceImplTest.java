package ar.edu.davinci.dvds20211cg6.service;

import static org.junit.jupiter.api.Assertions.*;

import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.domain.Prenda;
import ar.edu.davinci.dvds20211cg6.domain.TipoPrenda;
import ar.edu.davinci.dvds20211cg6.repository.PrendaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PrendaServiceImplTest {

    private String descripcion = "Remera Azul Talle M 100% Algodón";
    private TipoPrenda tipo = TipoPrenda.REMERA;
    private BigDecimal precioBase = new BigDecimal(795);

    @Autowired
    private PrendaServiceImpl service;

    @Autowired
    private PrendaRepository prendaRepository;

    @BeforeEach
    void setUp() {
        service = new PrendaServiceImpl(prendaRepository);
    }

    @Test
    void savePrendaWithException() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            Prenda prenda = getPrenda(99L);
            service.save(prenda); // Should throw a BusinessException
        });
        assertEquals("No se puede crear una prenda con un id especifico", exception.getMessage());
    }

    @Test
    void savePrenda() throws BusinessException {
        Long expectedLength = service.count() + 1;
        Prenda prenda = getPrendaNoId();
        Prenda prendaGuardada = service.save(prenda);

        assertEquals(expectedLength,service.count());
        assertNotNull(prendaGuardada);
        assertEquals(descripcion, prendaGuardada.getDescripcion());
        assertEquals(tipo, prendaGuardada.getTipo());
        assertEquals(precioBase, prendaGuardada.getPrecioBase());
    }

    @Test
    void updatePrendaWithException() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            Prenda prenda = getPrendaNoId();
            service.update(prenda); // Should throw a BusinessException
        });
        assertEquals("No se puede modificar una prenda no existente", exception.getMessage());
    }

    @Test
    void updatePrenda() throws BusinessException {
        Prenda prenda = getPrenda(1L); // Prenda with id 1 exists in H2 Database
        Prenda prendaGuardada = service.update(prenda);

        assertNotNull(prendaGuardada);
        assertEquals(descripcion, prendaGuardada.getDescripcion());
        assertEquals(tipo.getDescripcion(), prendaGuardada.getTipo().getDescripcion());
        assertEquals(precioBase, prendaGuardada.getPrecioBase());
    }

    @Test
    void deletePrendaWithException() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            Prenda prenda = getPrendaNoId();
            System.out.println(prenda.toString());
            service.delete(prenda); // Should throw a BusinessException
        });
        assertEquals("No se puede borrar una prenda no existente", exception.getMessage());
    }

    @Test
    void deletePrenda() throws BusinessException {
        Prenda prenda = getPrendaNoId(); //Prenda with id 1 exists in H2 Database
        service.save(prenda);
        Long expectedLength = service.count() - 1;
        service.delete(prenda);
        assertEquals(expectedLength,service.count());
    }

    @Test
    void findPrendaByIdWithException() {
        Exception exception = assertThrows(BusinessException.class, () -> {
            Long idNoExistente = 30L;
            service.findById(idNoExistente); // Should throw a BusinessException
        });
        assertEquals("No existe la prenda buscada", exception.getMessage());
    }

    @Test
    void findPrendaById() throws BusinessException {
        Prenda prendaGuardada = service.save(getPrendaNoId());
        Prenda prendaBuscada = service.findById(prendaGuardada.getId());
        assertNotNull(prendaBuscada);
    }

    private Prenda getPrenda(Long id) {
        return Prenda.builder()
                .id(id)
                .descripcion(descripcion)
                .tipo(tipo)
                .precioBase(precioBase)
                .build();
    }

    private Prenda getPrendaNoId() {
        return Prenda.builder()
                .descripcion(descripcion)
                .tipo(tipo)
                .precioBase(precioBase)
                .build();
    }

}