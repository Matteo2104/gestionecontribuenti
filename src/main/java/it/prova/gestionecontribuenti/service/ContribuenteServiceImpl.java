package it.prova.gestionecontribuenti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionecontribuenti.model.Contribuente;
import it.prova.gestionecontribuenti.repository.ContribuenteRepository;

@Service
public class ContribuenteServiceImpl implements ContribuenteService {
	@Autowired
	private ContribuenteRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Contribuente> listAllElements() {
		return (List<Contribuente>) repository.findAll();
	}
	
	@Override
	@Transactional
	public void aggiungiNuovo(Contribuente contribuente) {
		repository.save(contribuente);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Contribuente> findByExampleWithPagination(Contribuente example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Contribuente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + example.getNome().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + example.getCognome().toUpperCase() + "%"));

			if (example.getDataDiNascita() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataDiNascita"), example.getDataDiNascita()));

			if (StringUtils.isNotEmpty(example.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + example.getCodiceFiscale().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getIndirizzo()))
				predicates.add(cb.like(cb.upper(root.get("indirizzo")), "%" + example.getIndirizzo().toUpperCase() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}
}
