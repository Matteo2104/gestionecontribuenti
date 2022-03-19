package it.prova.gestionecontribuenti.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	// CICLO INSERIMENTO
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_contribuente_attr", new ContribuenteDTO());
		return "contribuente/insert";
	}
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_contribuente_attr") ContribuenteDTO contribuenteDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors())
			return "contribuente/insert";

		contribuenteService.aggiungiNuovo(contribuenteDTO.buildContribuenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	
	// CICLO VISUALIZZA
	@GetMapping("/show/{idContribuente}")
	public String show(@PathVariable Long idContribuente, Model model) {
		model.addAttribute("show_contribuente_attr", ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(idContribuente)));
		return "contribuente/show";
	}
	
	
	// CICLO RIMOZIONE
	@GetMapping("/delete/{idContribuente}")
	public String delete(@PathVariable Long idContribuente, Model model) {
		ContribuenteDTO contribuenteTemp = ContribuenteDTO
				.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(idContribuente));
		model.addAttribute("delete_contribuente_attr", contribuenteTemp);
		return "contribuente/delete";
	}
	@PostMapping("/remove")
	public String remove(@RequestParam(required=true) Long idContribuente,
			RedirectAttributes redirectAttrs) {

		//System.out.println(idContribuente);
		contribuenteService.rimuovi(idContribuente);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
}
