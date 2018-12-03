package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ComptabiliteManagerImplTest extends BusinessTestCase {

    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    private EcritureComptable ecriture;

    @Before
    public void setUp(){

       ecriture = new EcritureComptable();
       ecriture.setId(1);
       ecriture.setJournal(new JournalComptable("AC", "Achat"));
       ecriture.setDate(new Date());
       ecriture.setLibelle("Libelle");
       ecriture.setReference("AC-2018/00001");

    }


    @Test()
    public void checkEcritureComptableUnit() throws Exception {
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));


        manager.checkEcritureComptableUnit(ecriture);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
       manager.checkEcritureComptableUnit(ecriture);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        manager.RG_2_Equilibree(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));

        manager.RG_3_auMoins2Lignes(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG5() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setReference("AC-2016/00001");

        manager.RG_5_Annee_Verifie(vEcritureComptable);
    }

    @Test
    public void addReference(){
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(-1);
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        try{
        vEcritureComptable.setDate(new SimpleDateFormat("yyyy/MM/dd").parse("2018/12/31"));}
        catch(Exception e){
            e.printStackTrace();
        }
        manager.addReference(vEcritureComptable);

        Assert.assertTrue(vEcritureComptable.getReference()!=null);
    }

}
