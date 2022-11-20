package com.esprit.examen.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.services.IOperateurService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;

@RestController
@Api(tags = "Gestion des opérateurs")
@RequestMapping("/operateur")
@CrossOrigin("*")
public class OperateurController {

	@Autowired
	IOperateurService operateurService;
	
	
	@GetMapping("/retrieve-all-operateurs")
	@ResponseBody
	public List<Operateur> getOperateurs() {
		return operateurService.retrieveAllOperateurs();
	
	}

	
	@GetMapping("/retrieve-operateur/{operateur-id}")
	@ResponseBody
	public Operateur retrieveOperateur(@PathVariable("operateur-id") Long operateurId) {
		return operateurService.retrieveOperateur(operateurId);
	}

	
	@PostMapping("/add-operateur")
	@ResponseBody
	public Operateur addOperateur(@RequestBody OperateurModel operateurModel) {
		Operateur operateur = new Operateur();
		operateur.setNom(operateurModel.getNom());
		operateur.setPrenom(operateurModel.getPrenom());
		operateur.setPassword(operateurModel.getPassword());
		operateur.setFactures(operateurModel.getFactures());
		return operateurService.addOperateur(operateur);
	}

	@DeleteMapping("/remove-operateur/{operateur-id}")
	@ResponseBody
	public void removeOperateur(@PathVariable("operateur-id") Long operateurId) {
		operateurService.deleteOperateur(operateurId);
	}

	
	@PutMapping("/modify-operateur")
	@ResponseBody
	public Operateur modifyOperateur(@RequestBody OperateurModel operateurModel) {
	    Operateur operateur = new Operateur();
        operateur.setNom(operateurModel.getNom());
        operateur.setPrenom(operateurModel.getPrenom());
        operateur.setPassword(operateurModel.getPassword());
        operateur.setFactures(operateurModel.getFactures());
		return operateurService.updateOperateur(operateur);
	}

	
}

@Getter
@Setter
class OperateurModel{
    
    private Long idOperateur;
    private String nom;
    private String prenom;
    
    private String password;
    @OneToMany
    @JsonIgnore
    private Set<Facture> factures;
    
}




