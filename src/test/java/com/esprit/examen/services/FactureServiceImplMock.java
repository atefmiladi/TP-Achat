package com.esprit.examen.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class FactureServiceImplMock {
	
	
	
	
	@Autowired
	FactureServiceImpl factureServiceImpl;
	@MockBean
	FactureRepository FactureRepository;

	@Test
	@Order(1)
	public void saveFactureTest() {
		
		
		Facture f = new Facture();
		f.setMontantFacture(121.5f);
		f.setDateCreationFacture(java.sql.Date.valueOf("2022-11-04"));
		f.setDateDerniereModificationFacture(java.sql.Date.valueOf("2022-11-05"));
		f.setArchivee(true);
		f.setIdFacture(1);
		f.setMontantRemise(22f);		
		Mockito.when(FactureRepository.save(f)).thenReturn(f);
		assertEquals(f, factureServiceImpl.addFacture(f));
	}
	
	
	
	
	
	
	@Test
	@Order(2)
	public void getAllfacture() {
		Mockito.when(FactureRepository.findAll()).thenReturn(Stream
				.of(new Facture()).collect(Collectors.toList()));
		List<Facture> list = factureServiceImpl.retrieveAllFactures();
		log.info("==>size:"+list.size());
		for(int i=0;i<list.size();i++){
			log.info("==>"+list.get(i).getIdFacture());
		}
	}
	
	
	
	
	 @Test
	    @Order(3)
	    public void deleteTest() {
		 Facture sec = new Facture(222.2f, 555.f);
		 factureServiceImpl.cancelFacture(sec.getIdFacture());
	        log.info("facture supprimer avec success");
	    }
	    
	 
	 @Test
	    @Order(4)
	    public void testRetrievebyid() throws ParseException {
		 Facture sec = new Facture(222.2f, 555.f);
	        Mockito.when(FactureRepository.save(sec)).thenReturn(sec);
	        assertEquals(sec, factureServiceImpl.addFacture(sec));
	        log.info("facteur ajouter avec success");
	        Facture fff = factureServiceImpl.retrieveFacture(sec.getIdFacture());
	        log.info("facteur retrieved avec success:"+fff);
	    }
	    
	

}
