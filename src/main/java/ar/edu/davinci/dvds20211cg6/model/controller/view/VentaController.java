package ar.edu.davinci.dvds20211cg6.model.controller.view;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20211cg6.model.controller.TiendaApp;
import ar.edu.davinci.dvds20211cg6.domain.Venta;
import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.service.VentaService;


@Controller
public class VentaController extends TiendaApp {
    private final Logger LOGGER = LoggerFactory.getLogger(VentaController.class);


    @Autowired
    private VentaService ventaService;


    @GetMapping(path = "ventas/list")
    public String showVentaPage(Model model) {
        LOGGER.info("GET - showVentaPage  - /ventas/list");

        Pageable pageable = PageRequest.of(0, 20);
        Page<Venta> ventas = ventaService.list(pageable);
        LOGGER.info("GET - showVentaPage venta importe final: " + ventas.getContent().toString());

        model.addAttribute("listVentas", ventas);

        LOGGER.info("ventas.size: " + ventas.getNumberOfElements());
        return "ventas/list_ventas";
    }

    @GetMapping(path = "/ventas/new")
    public String showNewVentaPage(Model model) {
        LOGGER.info("GET - showNewVentaPage - /ventas/new");
//		Venta venta = new Venta();
//		model.addAttribute("venta", venta);
//		model.addAttribute("tipoVentas", ventaService.getTipoVentas());
//
//		LOGGER.info("ventas: " + venta.toString());

        return "ventas/new_ventas";
    }

    @PostMapping(value = "/ventas/save")
    public String saveVenta(@ModelAttribute("venta") Venta venta) {
        LOGGER.info("POST - saveVenta - /ventas/save");
        LOGGER.info("venta: " + venta.toString());
//		ventaService.save(venta);

        return "redirect:/tienda/ventas/list";
    }

    @RequestMapping(value = "/ventas/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showEditVentaPage(@PathVariable(name = "id") Long ventaId) {
        LOGGER.info("GET - showEditVentaPage - /ventas/edit/{id}");
        LOGGER.info("venta: " + ventaId);

        ModelAndView mav = new ModelAndView("ventas/edit_ventas");
        try {

            Venta venta = ventaService.findById(ventaId);
            mav.addObject("venta", venta);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/ventas/delete/{id}", method = RequestMethod.GET)
    public String deleteVenta(@PathVariable(name = "id") Long ventaId) {
        LOGGER.info("GET - deleteVenta - /ventas/delete/{id}");
        LOGGER.info("venta: " + ventaId);
        ventaService.delete(ventaId);
        return "redirect:/tienda/ventas/list";
    }
}

