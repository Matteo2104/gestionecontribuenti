package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.service.ContribuenteService;


@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;
	
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> results = contribuenteService.listAllElements();
		mv.addObject("contribuente_list_attribute", results);
		mv.setViewName("contribuente/list");
		return mv;
	}
	
	
	// CICLO RICERCA
	@GetMapping("/search")
	public String search() {
		return "contribuente/search";
	}
	@PostMapping("/find")
	public String find(ContribuenteDTO example, Model model) {
		List<ContribuenteDTO> resultList = ContribuenteDTO.createContribuenteDTOListFromModelList(contribuenteService.findByExampleWithPagination(example.buildContribuenteModel(), null, null, null).toList());
		model.addAttribute("contribuente_list_attribute", resultList);
		return "contribuente/list";
	}
	
	/*
	// CICLO INSERIMENTO
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_impiegato_attr", new Impiegato());
		return "impiegato/insert";
	}
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_impiegato_attr") Impiegato impiegato, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors())
			return "impiegato/insert";

		impiegatoService.inserisciNuovo(impiegato);

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/impiegato";
	}
	*/
}
