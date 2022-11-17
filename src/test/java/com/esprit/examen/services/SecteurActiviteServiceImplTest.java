package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.SecteurActivite;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class SecteurActiviteServiceImplTest {
    
    @Autowired
    ISecteurActiviteService secteurActiviteService;
    
    @Test
    @Order(1)
    public void testAddSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("SEC1");
        sec.setLibelleSecteurActivite("secteur 1");
        SecteurActivite x = secteurActiviteService.addSecteurActivite(sec);
        assertNotNull(x.getCodeSecteurActivite());
        assertNotNull(x.getLibelleSecteurActivite());
        log.info("secteur ajouter avec success");
    }
    
    @Test
    @Order(2)
    public void testModifierSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("SEC2");
        sec.setLibelleSecteurActivite("secteur 2");
        secteurActiviteService.addSecteurActivite(sec);
        log.info("secteur ajouter avec success");
        sec.setCodeSecteurActivite("SEC3");
        sec.setLibelleSecteurActivite("secteur 3");
        SecteurActivite x = secteurActiviteService.updateSecteurActivite(sec);
        assertNotNull(x.getCodeSecteurActivite());
        assertNotNull(x.getLibelleSecteurActivite());
        log.info("secteur modifier avec success");
    }
    
    @Test
    @Order(3)
    public void testRetrieveAllSecteurActivite() throws ParseException {
        List<SecteurActivite> listSecteur = secteurActiviteService.retrieveAllSecteurActivite();
        Assertions.assertNotEquals(0, listSecteur.size());
        log.info("Nombre : " + listSecteur.size()+" \n");
        for(int i=0;i<listSecteur.size();i++){
            log.info("==>"+listSecteur.get(i).getLibelleSecteurActivite());
        }
    }
    
    @Test
    @Order(4)
    public void testDeleteSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("SEC4");
        sec.setLibelleSecteurActivite("secteur 4");
        assertNotNull(sec.getIdSecteurActivite());
        assertNotNull(sec.getLibelleSecteurActivite());
        secteurActiviteService.addSecteurActivite(sec);
        secteurActiviteService.deleteSecteurActivite(sec.getIdSecteurActivite());
        log.info("secteur supprimer avec success");
    }
    
    
    @Test
    @Order(5)
    public void testRetrieveSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite();
        sec.setCodeSecteurActivite("SEC5");
        sec.setLibelleSecteurActivite("secteur 5");
        assertNotNull(sec.getIdSecteurActivite());
        assertNotNull(sec.getLibelleSecteurActivite());
        secteurActiviteService.addSecteurActivite(sec);
        secteurActiviteService.retrieveSecteurActivite(sec.getIdSecteurActivite());
        log.info("secteur retrieved avec success");
    }
    
    
    
    
}
