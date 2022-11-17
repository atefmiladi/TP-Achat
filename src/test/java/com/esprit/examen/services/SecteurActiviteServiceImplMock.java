package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
@ExtendWith(MockitoExtension.class)
public class SecteurActiviteServiceImplMock {
    
    @Autowired
    SecteurActiviteServiceImpl secteurActiviteServiceImpl;
    @MockBean
    SecteurActiviteRepository secteurActiviteRepository;

    @Test
    @Order(1)
    public void saveSecteurActiviteTest() {
        SecteurActivite sec = new SecteurActivite("SEC1", "secteur 1");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteServiceImpl.addSecteurActivite(sec));
    }
    
    
    @Test
    @Order(2)
    public void getSecteursTest() {
        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(Stream
                .of(new SecteurActivite("SEC2", "secteur 2"), new SecteurActivite("SEC3", "secteur 3")).collect(Collectors.toList()));
        assertEquals(2, secteurActiviteServiceImpl.retrieveAllSecteurActivite().size());
        List<SecteurActivite> listSecteur = secteurActiviteServiceImpl.retrieveAllSecteurActivite();
        log.info("==>size:"+listSecteur.size());
        for(int i=0;i<listSecteur.size();i++){
            log.info("==>"+listSecteur.get(i).getLibelleSecteurActivite());
        }
    }

    @Test
    @Order(3)
    public void deleteSecteurActiviteTest() {
        SecteurActivite sec = new SecteurActivite("SEC4", "secteur 4");
        assertNotNull(sec.getCodeSecteurActivite());
        assertNotNull(sec.getLibelleSecteurActivite());
        secteurActiviteServiceImpl.deleteSecteurActivite(sec.getIdSecteurActivite());
        log.info("secteur supprimer avec success");
    }
    
    
    
    @Test
    @Order(4)
    public void testRetrieveSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite("SEC5","secteur 5");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteServiceImpl.addSecteurActivite(sec));
        log.info("secteur ajouter avec success");
        SecteurActivite secteur = secteurActiviteServiceImpl.retrieveSecteurActivite(sec.getIdSecteurActivite());
        log.info("secteur retrieved avec success:"+secteur);
    }
    
    
    @Test
    @Order(5)
    public void testModifierSecteurActivite() throws ParseException {
        SecteurActivite sec = new SecteurActivite("SEC6","secteur 6");
        Mockito.when(secteurActiviteRepository.save(sec)).thenReturn(sec);
        assertEquals(sec, secteurActiviteServiceImpl.addSecteurActivite(sec));;
        log.info("secteur ajouter avec success");
        SecteurActivite secteur = secteurActiviteServiceImpl.updateSecteurActivite(new SecteurActivite("SEC7","secteur 7"));
        log.info("secteur modifier avec success:"+secteur);
    }
    
    
    
    

}
