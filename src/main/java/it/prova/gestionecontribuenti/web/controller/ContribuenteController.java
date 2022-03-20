package it.prova.gestionecontribuenti.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionecontribuenti.dto.ContribuenteDTO;
import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.model.Stato;
import it.prova.gestionecontribuenti.service.ContribuenteService;


@Controller
@RequestMapping(value = "/contribuente")
public class ContribuenteController {

	@Autowired
	private ContribuenteService contribuenteService;
	
	
	@GetMapping
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView();
		List<Contribuente> resultList = contribuenteService.listAllElements();
		Map<ContribuenteDTO, Boolean> map = new HashMap<>();
		
		boolean check;
		for (Contribuente contribuente : resultList) {
			check=false;
			for (CartellaEsattoriale cartella : contribuente.getCartelleEsattoriali()) {
				if (cartella.getStato().equals(Stato.IN_CONTENZIOSO)) {
					map.put(ContribuenteDTO.buildContribuenteDTOFromModel(contribuente), true);
					check=true;
				}
			}
			if (!check) {
				map.put(ContribuenteDTO.buildContribuenteDTOFromModel(contribuente), false);
			}
		}
		mv.addObject("contribuente_map_attribute", map);
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
		List<Contribuente> resultList = contribuenteService.findByExampleWithPagination(example.buildContribuenteModel(), null, null, null).toList();
		Map<ContribuenteDTO, Boolean> map = new HashMap<>();
		
		boolean check;
		for (Contribuente contribuente : resultList) {
			check=false;
			for (CartellaEsattoriale cartella : contribuente.getCartelleEsattoriali()) {
				if (cartella.getStato().equals(Stato.IN_CONTENZIOSO)) {
					map.put(ContribuenteDTO.buildContribuenteDTOFromModel(contribuente), true);
					check=true;
				}
			}
			if (!check) {
				map.put(ContribuenteDTO.buildContribuenteDTOFromModel(contribuente), false);
			}
		}
		
		model.addAttribute("contribuente_map_attribute", map);
		return "contribuente/list";
	}
	
	
	// CICLO INSERIMENTO
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_contribuente_attr", new ContribuenteDTO());
		return "contribuente/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_contribuente_attr") ContribuenteDTO contribuenteDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors())
			return "contribuente/insert";

		contribuenteService.aggiungiNuovo(contribuenteDTO.buildContribuenteModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	
	// CICLO VISUALIZZA
	@GetMapping("/show/{idContribuente}")
	public String show(@PathVariable Long idContribuente, Model model) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoEager(idContribuente);
		model.addAttribute("show_contribuente_attr", ContribuenteDTO.buildContribuenteDTOFromModel(contribuente));
		
		// calcolo l'importo totale
		int importo_totale = 0;
		int importo_concluso = 0;
		int importo_contenzioso = 0;
		for (CartellaEsattoriale cartella : contribuente.getCartelleEsattoriali()) {
			importo_totale += cartella.getImporto();
			if (cartella.getStato().equals(Stato.CONCLUSA))
				importo_concluso += cartella.getImporto();
			if (cartella.getStato().equals(Stato.IN_CONTENZIOSO))
				importo_contenzioso += cartella.getImporto();
		}
		model.addAttribute("importo_totale", importo_totale);
		model.addAttribute("importo_concluso", importo_concluso);
		model.addAttribute("importo_contenzioso", importo_contenzioso);
		
		return "contribuente/show";
	}
	
	
	// CICLO RIMOZIONE
	@GetMapping("/delete/{idContribuente}")
	public String delete(@PathVariable Long idContribuente, Model model, RedirectAttributes redirect) {
		Contribuente contribuente = contribuenteService.caricaSingoloElementoEager(idContribuente);
		model.addAttribute("delete_contribuente_attr", ContribuenteDTO
				.buildContribuenteDTOFromModel(contribuente));
		
		if (!contribuente.getCartelleEsattoriali().isEmpty()) {
			redirect.addFlashAttribute("errorMessage", "Operazione non permessa: non si può cancellare un contribuente con delle cartelle attive");
			return "redirect:/contribuente";
		}
		
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
	
	
	// CICLO MODIFICA
	@GetMapping("/edit/{idContribuente}")
	public String edit(@PathVariable Long idContribuente, Model model) {
		ContribuenteDTO contribuenteTemp = ContribuenteDTO
				.buildContribuenteDTOFromModel(contribuenteService.caricaSingoloElemento(idContribuente));
		model.addAttribute("edit_contribuente_attr", contribuenteTemp);
		return "contribuente/edit";
	}
	@PostMapping("/update")
	public String remove(@Valid @ModelAttribute("edit_contribuente_attr") ContribuenteDTO contribuenteDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {
		System.out.println(contribuenteDTO);
		
		if (result.hasErrors())
			return "contribuente/edit";

		contribuenteService.aggiorna(contribuenteDTO.buildContribuenteModel());
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/contribuente";
	}
	
	@GetMapping(value = "/searchContribuentiAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchContribuente(@RequestParam String term) {

		List<Contribuente> listaContribuenteByTerm = contribuenteService.cercaByCognomeENomeILike(term);
		return buildJsonResponse(listaContribuenteByTerm);
	}

	private String buildJsonResponse(List<Contribuente> listaContribuenti) {
		JsonArray ja = new JsonArray();

		for (Contribuente contribuenteItem : listaContribuenti) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", contribuenteItem.getId());
			jo.addProperty("label", contribuenteItem.getNome() + " " + contribuenteItem.getCognome());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}
}
