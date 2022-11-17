package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;

import org.junit.jupiter.api.Order;


import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

import lombok.extern.slf4j.Slf4j;






import org.junit.Test;












@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplMockTest {
	
	
	@Autowired
	OperateurServiceImpl operateurServiceImpl;
	@MockBean
	OperateurRepository operateurRepository;
	
	@Test
	@Order(1)
	public void saveCategorieTest() {
		Operateur P = new Operateur();
		P.setIdOperateur(3);
		P.setNom("samir");
		P.setPrenom("ounifii");
		P.setPassword("abcd");
		
		Mockito.when(operateurRepository.save(P)).thenReturn(P);
		assertEquals(P,operateurServiceImpl.addOperateur(P));
		log.info("categorie ajouter avec success");
	}
	@Test
	@Order(2)
	public void updateOperateurTest() {
		Operateur P = new Operateur();
		P.setIdOperateur(3);
		P.setNom("samir");
		P.setPrenom("ounifii");
		P.setPassword("abcd");
		Mockito.when(operateurRepository.save(P)).thenReturn(P);
		assertEquals(P,operateurServiceImpl.addOperateur(P));
		log.info("categorie ajouter avec success");
		P.setNom("dhiaa");
		P.setPrenom("mejrii");
		Mockito.when(operateurRepository.save(P)).thenReturn(P);
		assertEquals(P,operateurServiceImpl.updateOperateur(P));
		log.info("categorie mdofier avec success");
	}
	
	@Test
	@Order(3)
	public void deleteOperateurTest() {
		Operateur P = new Operateur();
		P.setIdOperateur(3);
		P.setNom("dhiaaaa");
		P.setPrenom("mejriiiii");
		P.setPassword("abcd");
		assertNotNull(P.getNom());
		assertNotNull(P.getPrenom());
		operateurServiceImpl.deleteOperateur(P.getIdOperateur());
		verify(operateurRepository, times(1)).deleteById(P.getIdOperateur());
		log.info("categorie supprimer avec success");
	}
	@Test
    @Order(4)
    public void testRetrieveOperateur() throws ParseException {
		Operateur P = new Operateur();
		P.setIdOperateur(3);
		P.setNom("dhiaaaa");
		P.setPrenom("mejriiiii");
		P.setPassword("abcd");
        Mockito.when(operateurRepository.save(P)).thenReturn(P);
        assertEquals(P,operateurServiceImpl.updateOperateur(P));
        log.info("secteur ajouter avec success");
        Operateur OP  = operateurServiceImpl.retrieveOperateur(P.getIdOperateur());
        log.info("secteur retrieved avec success:"+P);
    }
}
