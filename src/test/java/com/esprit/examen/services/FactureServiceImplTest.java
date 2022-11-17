package com.esprit.examen.services;



import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;

import lombok.extern.slf4j.Slf4j;


import org.junit.Test;
import org.junit.jupiter.api.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FactureServiceImplTest {

	@Autowired
	IFactureService FactureService;
	
	
	@Test
	public void testAddfacture() throws ParseException {
		Facture f = new Facture();
		
		
		
		f.setMontantFacture(55.5f);
		f.setDateCreationFacture(java.sql.Date.valueOf("2022-09-04"));
		f.setDateDerniereModificationFacture(java.sql.Date.valueOf("2022-09-05"));
		f.setArchivee(true);
		f.setIdFacture(2);
		f.setMontantRemise(22f);
		
	    
		FactureService.addFacture(f);
		assertNotNull(f.getMontantFacture());
        assertNotNull(f.getMontantRemise());
		
		log.info("facture ajouter avec success");
	}
	
	
	@Test
	public void testRetrieveAllFacture() throws ParseException {
		List<Facture> listFacture = FactureService.retrieveAllFactures();
		log.info("Nombre facture: " + listFacture.size()+" \n");
		for(int i=0;i<listFacture.size();i++){
			log.info(""+listFacture.get(i).getMontantFacture());
		}
	}
	
	

	
	
	
	 @Test
	    @Order(3)
	    public void testRetrievefacturebyid() throws ParseException {
		 Facture cat = new Facture();
		 cat.setMontantRemise(22.3f);
			cat.setMontantFacture(500.33f);
			FactureService.addFacture(cat);
			FactureService.retrieveFacture(cat.getIdFacture());
	        log.info("secteur retrieved avec success");
	    }
	
	 
	 
	 
	 
	 @Test
		@Order(4)
		public void testDeleteFacture() throws ParseException {
		 Facture cat = new Facture();
			
		 cat.setIdFacture((long) 6);
		 cat.setMontantRemise(22.3f);
			cat.setMontantFacture(500.33f);
			
			FactureService.addFacture(cat);
			FactureService.cancelFacture(cat.getIdFacture());
			
			assertNotNull(cat.getMontantFacture());
	        assertNotNull(cat.getMontantRemise());
			log.info("Facture supprimer avec success");
		}
}