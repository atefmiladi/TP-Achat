package com.esprit.examen.controllers;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.services.IProduitService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;


@RestController
@CrossOrigin("*")
@Api(tags = "Gestion des produits")
@RequestMapping("/produit")
public class ProduitRestController {

	@Autowired
	IProduitService produitService;


	@GetMapping("/retrieve-all-produits")
	@ResponseBody
	public List<Produit> getProduits() {
		return produitService.retrieveAllProduits();
		
	}

	@GetMapping("/retrieve-produit/{produit-id}")
	@ResponseBody
	public Produit retrieveRayon(@PathVariable("produit-id") Long produitId) {
		return produitService.retrieveProduit(produitId);
	}

	@PostMapping("/add-produit")
	@ResponseBody
	public Produit addProduit(@RequestBody ProduitModel produitModel) {
	    Produit produit =new Produit();
		produit.setCodeProduit(produitModel.getCodeProduit());
		produit.setLibelleProduit(produitModel.getLibelleProduit());
		produit.setPrix(produitModel.getPrix());
		produit.setDateCreation(produitModel.getDateCreation());
		produit.setDateDerniereModification(produitModel.getDateDerniereModification());
		return produitService.addProduit(produit);
	}

	
	@DeleteMapping("/remove-produit/{produit-id}")
	@ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteProduit(produitId);
	}

	
	@PutMapping("/modify-produit")
	@ResponseBody
	public Produit modifyProduit(@RequestBody ProduitModel produitModel) {
	    Produit produit =new Produit();
        produit.setCodeProduit(produitModel.getCodeProduit());
        produit.setLibelleProduit(produitModel.getLibelleProduit());
        produit.setPrix(produitModel.getPrix());
        produit.setDateCreation(produitModel.getDateCreation());
        produit.setDateDerniereModification(produitModel.getDateDerniereModification());
        return produitService.updateProduit(produit);
	}



}


@Getter
@Setter
class ProduitModel{
    
    private Long idProduit;
    private String codeProduit;
    private String libelleProduit;
    private float prix;
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Temporal(TemporalType.DATE)
    private Date dateDerniereModification;
    @ManyToOne
    @JsonIgnore
    private Stock stock;
    @OneToMany(mappedBy = "produit")
    @JsonIgnore
    private Set<DetailFacture> detailFacture;
    @ManyToOne
    @JsonIgnore
    private CategorieProduit categorieProduit;
    
    
}
