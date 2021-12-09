package ar.edu.davinci.dvds20211cg6.model.controller.view;

import ar.edu.davinci.dvds20211cg6.model.controller.TiendaApp;
import ar.edu.davinci.dvds20211cg6.exception.BusinessException;
import ar.edu.davinci.dvds20211cg6.domain.Prenda;
import ar.edu.davinci.dvds20211cg6.service.PrendaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// TODO Implement tests for controller
@Controller
public class PrendaController extends TiendaApp {

    private final Logger logger = LoggerFactory.getLogger(PrendaController.class);

    @Autowired
    private PrendaService service;

    @GetMapping(path = "prenda/list")
    public String showPrendaPage(Model model) {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Prenda> prendas = service.lista(pageable);
        model.addAttribute("listPrendas", prendas);
        return "prendas/list_prendas";
    }

    @GetMapping(path = "prenda/new")
    public String showNewPrendaPage(Model model) {
        Prenda prenda = new Prenda();
        model.addAttribute("prenda", prenda);
        model.addAttribute("tipoPrenda", prenda.getTipo());
        return "prendas/new_prendas";
    }

    // TODO Fix vulnerability of persistent Entity with POJO/DTO class
    @PostMapping(path = "/prendas/save")
    public String savePrenda (@ModelAttribute("prenda") Prenda prenda) {
        try {
            if (prenda.getId() != 0) {
                service.save(prenda);
            }
            else {
                service.update(prenda);
            }

        } catch (BusinessException e) {
            // TODO implement catch
            e.printStackTrace();
        }
        return "redirect:/tienda/prendas/list";
    }

    @GetMapping(path = "/prendas/edit/{id}")
    public ModelAndView showEditPrendaPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("prendas/edit_prendas");
        Prenda prenda = null;
        try {
            prenda = service.findById(id);
            mav.addObject("prenda", prenda);
            mav.addObject("tipoPrendaActual", prenda.getTipo());
        } catch (BusinessException e) {
            // TODO implement catch
            e.printStackTrace();
        }
        mav.addObject("tipoPrendas", service.getTiposPrenda());
        return mav;
    }

    @GetMapping(path = "/prendas/delete/{id}")
    public String deletePrenda(@PathVariable(name = "id") Long id) {
        try {
            service.delete(id);
        } catch (BusinessException e) {
            // TODO implement catch
            e.printStackTrace();
        }
        return "redirect:/tienda/prendas/list";
    }

}
