package com.esprit.examen.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.services.IFournisseurService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;


@RestController
@Api(tags = "Gestion des fournisseurss")
@RequestMapping("/fournisseur")
public class FournisseurRestController {

	@Autowired
	IFournisseurService fournisseurService;


	@GetMapping("/retrieve-all-fournisseurs")
	@ResponseBody
	public List<Fournisseur> getFournisseurs() {
		return fournisseurService.retrieveAllFournisseurs();
		
	}

	
	@GetMapping("/retrieve-fournisseur/{fournisseur-id}")
	@ResponseBody
	public Fournisseur retrieveFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		return fournisseurService.retrieveFournisseur(fournisseurId);
	}

	
	@PostMapping("/add-fournisseur")
	@ResponseBody
	public Fournisseur addFournisseur(@RequestBody FournisseurModel fournisseurModel) {
	    
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setCode(fournisseurModel.getCode());
		fournisseurModel.setLibelle(fournisseurModel.getLibelle());
		fournisseur.setCategorieFournisseur(fournisseurModel.getCategorieFournisseur());
		return fournisseurService.addFournisseur(fournisseur);
	}

	
	@DeleteMapping("/remove-fournisseur/{fournisseur-id}")
	@ResponseBody
	public void removeFournisseur(@PathVariable("fournisseur-id") Long fournisseurId) {
		fournisseurService.deleteFournisseur(fournisseurId);
	}

	
	@PutMapping("/modify-fournisseur")
	@ResponseBody
	public Fournisseur modifyFournisseur(@RequestBody FournisseurModel fournisseurModel) {
	    
	    Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCode(fournisseurModel.getCode());
        fournisseurModel.setLibelle(fournisseurModel.getLibelle());
        fournisseur.setCategorieFournisseur(fournisseurModel.getCategorieFournisseur());
		return fournisseurService.updateFournisseur(fournisseur);
	}

		@PutMapping(value = "/assignSecteurActiviteToFournisseur/{idSecteurActivite}/{idFournisseur}")
		public void assignProduitToStock(@PathVariable("idSecteurActivite") Long idSecteurActivite, @PathVariable("idFournisseur") Long idFournisseur) {
			fournisseurService.assignSecteurActiviteToFournisseur(idSecteurActivite, idFournisseur);
		}

}

@Getter
@Setter
class FournisseurModel{
    
    private Long idFournisseur;
    private String code;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private CategorieFournisseur  categorieFournisseur;
    @OneToMany(mappedBy="fournisseur")
    @JsonIgnore
    private Set<Facture> factures;
    @ManyToMany
    @JsonIgnore
    private Set<SecteurActivite> secteurActivites;
    @OneToOne(cascade= CascadeType.ALL,fetch=FetchType.EAGER)
    private DetailFournisseur detailFournisseur;
}

