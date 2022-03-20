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

import it.prova.gestionecontribuenti.dto.CartellaEsattorialeDTO;
import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.service.CartellaEsattorialeService;
import it.prova.gestionecontribuenti.service.ContribuenteService;


@Controller
@RequestMapping(value = "/cartellaesattoriale")
public class CartellaEsattorialeController {
	
	@Autowired
	private CartellaEsattorialeService cartellaEsattorialeService;
	@Autowired
	private ContribuenteService contribuenteService;
	
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<CartellaEsattoriale> results = cartellaEsattorialeService.listAllElements();
		mv.addObject("cartella_list_attribute", results);
		mv.setViewName("cartellaesattoriale/list");
		return mv;
	}
	
	
	// CICLO RICERCA (non implementata)
	@GetMapping("/search")
	public String search() {
		return "cartellaesattoriale/search";
	}
	@PostMapping("/find")
	public String find(CartellaEsattorialeDTO example, Model model, RedirectAttributes redirect) {
		
		//List<CartellaEsattorialeDTO> resultListDTO = CartellaEsattorialeDTO.createCartellaEsattorialeDTOListFromModelList(cartellaEsattorialeService.findByExampleWithPagination(example.buildCartellaEsattorialeModel(), null, null, null).toList(), false);
		//List<CartellaEsattoriale> resultList = (List<CartellaEsattoriale>) cartellaEsattorialeService.findByExampleWithPagination(example.buildCartellaEsattorialeModel(), null, null, null);
		
		/*
		for (CartellaEsattorialeDTO cartellaItem : resultListDTO) {
			System.out.println(cartellaItem);
		}
		
		model.addAttribute("cartella_list_attribute", resultListDTO);
		return "cartellaesattoriale/list";
		*/
		return "redirect:/cartellaesattoriale";
	}
	
	
	// CICLO INSERIMENTO
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_cartella_attr", new CartellaEsattorialeDTO());
		return "cartellaesattoriale/insert";
	}
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_cartella_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {
		
		if (cartellaEsattorialeDTO.getContribuente() == null || cartellaEsattorialeDTO.getContribuente().getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaEsattorialeDTO.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(cartellaEsattorialeDTO.getContribuente().getId())));



		if (result.hasErrors())
			return "cartellaesattoriale/insert";

		cartellaEsattorialeService.aggiungiNuovo(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}
	
	
	// CICLO VISUALIZZA
	@GetMapping("/show/{idCartella}")
	public String show(@PathVariable Long idCartella, Model model) {
		model.addAttribute("show_cartella_attr", CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartella), true));
		return "cartellaesattoriale/show";
	}
	
	
	// CICLO RIMOZIONE
	@GetMapping("/delete/{idCartella}")
	public String delete(@PathVariable Long idCartella, Model model) {
		model.addAttribute("delete_cartella_attr", CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(
				cartellaEsattorialeService.caricaSingoloElementoEager(idCartella), true));
		return "cartellaesattoriale/delete";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam(required=true) Long idCartella,
			RedirectAttributes redirectAttrs) {

		//System.out.println(idContribuente);
		cartellaEsattorialeService.rimuovi(idCartella);
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}
	
	
	// CICLO MODIFICA
	@GetMapping("/edit/{idCartella}")
	public String edit(@PathVariable Long idCartella, Model model) {
		CartellaEsattorialeDTO cartellaTemp = CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeService.caricaSingoloElementoEager(idCartella), true);
		model.addAttribute("edit_cartella_attr", cartellaTemp);
		return "cartellaesattoriale/edit";
	}
	
	@PostMapping("/update")
	public String remove(@Valid @ModelAttribute("edit_cartella_attr") CartellaEsattorialeDTO cartellaEsattorialeDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {
		//System.out.println(cartellaEsattorialeDTO);
		
		if (cartellaEsattorialeDTO.getContribuente() == null || cartellaEsattorialeDTO.getContribuente().getId() == null)
			result.rejectValue("contribuente", "contribuente.notnull");
		else
			cartellaEsattorialeDTO.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(cartellaEsattorialeDTO.getContribuente().getId())));

		
		if (result.hasErrors())
			return "cartellaesattoriale/edit";
		
		//System.out.println(cartellaEsattorialeDTO);
		
		cartellaEsattorialeService.aggiorna(cartellaEsattorialeDTO.buildCartellaEsattorialeModel());
		
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/cartellaesattoriale";
	}
	
	
}
