package com.esprit.examen.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.ManyToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.services.ISecteurActiviteService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;

@RestController
@Api(tags = "Gestion des secteurs activites")
@RequestMapping("/secteurActivite")
@CrossOrigin("*")
public class SecteurActiviteController {

	@Autowired
	ISecteurActiviteService secteurActiviteService;
	
	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-all-secteurActivite
	@GetMapping("/retrieve-all-secteurActivite")
	@ResponseBody
	public List<SecteurActivite> getSecteurActivite() {
		return secteurActiviteService.retrieveAllSecteurActivite();
		
	}

	// http://localhost:8089/SpringMVC/secteurActivite/retrieve-secteurActivite/8
	@GetMapping("/retrieve-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public SecteurActivite retrieveSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		return secteurActiviteService.retrieveSecteurActivite(secteurActiviteId);
	}

	// http://localhost:8089/SpringMVC/secteurActivite/add-secteurActivite
	@PostMapping("/add-secteurActivite")
	@ResponseBody
	public SecteurActivite addSecteurActivite(@RequestBody SecteurActiviteModel secteurActiviteModel) {
	    
	    SecteurActivite sec = new SecteurActivite();
	    sec.setCodeSecteurActivite(secteurActiviteModel.getCodeSecteurActivite());
	    sec.setLibelleSecteurActivite(secteurActiviteModel.getLibelleSecteurActivite());
	    secteurActiviteService.addSecteurActivite(sec);
        return secteurActiviteService.addSecteurActivite(sec);
		
	}

	
	@DeleteMapping("/remove-secteurActivite/{secteurActivite-id}")
	@ResponseBody
	public void removeSecteurActivite(@PathVariable("secteurActivite-id") Long secteurActiviteId) {
		secteurActiviteService.deleteSecteurActivite(secteurActiviteId);
	}

	// http://localhost:8089/SpringMVC/secteurActivite/modify-secteurActivite
	@PutMapping("/modify-secteurActivite")
	@ResponseBody
	public SecteurActivite modifySecteurActivite(@RequestBody SecteurActiviteModel secteurActiviteModel) {
		return secteurActiviteService.updateSecteurActivite(new SecteurActivite(secteurActiviteModel.getIdSecteurActivite(),secteurActiviteModel.getCodeSecteurActivite(),
		        secteurActiviteModel.getLibelleSecteurActivite(),secteurActiviteModel.getFournisseurs()));
	}

	
}

@Getter
@Setter
class SecteurActiviteModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;
    @ManyToMany(mappedBy="secteurActivites")
    @JsonIgnore
    private Set<Fournisseur> fournisseurs;
}
