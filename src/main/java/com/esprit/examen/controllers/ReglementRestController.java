package com.esprit.examen.controllers;

import java.util.Date;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.services.IReglementService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;

@RestController
@Api(tags = "Gestion des reglements")
@RequestMapping("/reglement")
@CrossOrigin("*")
public class ReglementRestController {

    @Autowired
    IReglementService reglementService;


    @PostMapping("/add-reglement")
    @ResponseBody
    public Reglement addReglement(@RequestBody ReglementModel reglementModel) {
        Reglement reglement = new Reglement();
        reglement.setMontantPaye(reglementModel.getMontantPaye());
        reglement.setMontantRestant(reglementModel.getMontantRestant());
        reglement.setPayee(reglement.getPayee());
        reglement.setDateReglement(reglement.getDateReglement());
        reglement.setFacture(reglement.getFacture());
        return reglementService.addReglement(reglement);
         
    }
    @GetMapping("/retrieve-all-reglements")
    @ResponseBody
    public List<Reglement> getReglement() {
        return reglementService.retrieveAllReglements();
        
    }

    @GetMapping("/retrieve-reglement/{reglement-id}")
    @ResponseBody
    public Reglement retrieveReglement(@PathVariable("reglement-id") Long reglementId) {
        return reglementService.retrieveReglement(reglementId);
    }

    
    @GetMapping("/retrieveReglementByFacture/{facture-id}")
    @ResponseBody
    public List<Reglement> retrieveReglementByFacture(@PathVariable("facture-id") Long factureId) {
        return reglementService.retrieveReglementByFacture(factureId);
    }

    
    @GetMapping(value = "/getChiffreAffaireEntreDeuxDate/{startDate}/{endDate}")
    public float getChiffreAffaireEntreDeuxDate(
            @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        try {
            return reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        } catch (Exception e) {
            return 0;
        }
    }
}

@Getter
@Setter
class ReglementModel {
    
    private Long idReglement;
    private float montantPaye;
    private float montantRestant;
    private Boolean payee;
    @Temporal(TemporalType.DATE)
    private Date dateReglement;
    @ManyToOne
    @JsonIgnore
    private Facture facture;
       
}


