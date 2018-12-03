package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ComptabiliteDaoImplTest extends ConsumerTestCase {

    private ComptabiliteDao dao = getDaoProxy().getComptabiliteDao();



    // ==================== CompteComptable - GET ====================

    @Test
    public void getListCompteComptable() {
        List<CompteComptable> vList = dao.getListCompteComptable();
        Assert.assertEquals(7, vList.size());
    }


    // ==================== JournalComptable - GET ====================

    @Test
    public void getListJournalComptable() {
        List<JournalComptable> vList = dao.getListJournalComptable();
        Assert.assertEquals(4, vList.size());
    }

    // ==================== SequenceEcritureComptable - GET ====================

    @Test
    public void getListSequenceEcritureComptable(){
        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        System.out.println(vList.size());
        Assert.assertEquals(5, vList.size());
    }


    // ==================== EcritureComptable - GET ====================

    @Test
    public void getListEcritureComptable() {
        List<EcritureComptable> vList = dao.getListEcritureComptable();
        Assert.assertEquals(5, vList.size());
    }

    @Test
    public void getEcritureComptable() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptable(-3);
        Assert.assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

        Assertions.assertThrows(NotFoundException.class, () -> dao.getEcritureComptable(0));
    }

    @Test
    public void getEcritureComptableByRef() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptableByRef("BQ-2016/00003");
        Assert.assertEquals("BQ", vEcritureComptable.getJournal().getCode());
        String vEcritureYear = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        Assert.assertEquals("2016", vEcritureYear);
        Assert.assertEquals(-3, vEcritureComptable.getId().intValue());

        Assertions.assertThrows(NotFoundException.class, ()-> dao.getEcritureComptableByRef("BQ-2016/33333"));
    }

    @Test
    public void loadListLigneEcriture() {
        EcritureComptable ecriture  = new EcritureComptable();
        ecriture.setId(-5);
        dao.loadListLigneEcriture(ecriture);
        Assert.assertEquals(2, ecriture.getListLigneEcriture().size());
    }


    // ==================== EcritureComptable - INSERT ====================

    @Test
    public void insertEcritureComptable() throws NotFoundException {
        EcritureComptable ecriture  = new EcritureComptable();
       // ecriture.setId(-6);
        Date currentDate = new Date();
        Integer currentYear = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        ecriture.setJournal(new JournalComptable("OD", "Opérations Diverses"));;
        ecriture.setReference("AC-" + currentYear + "/00200");
        ecriture.setDate(currentDate);
        ecriture.setLibelle("Sandwichs");

        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),"Club saumon", new BigDecimal(10),null));
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),"TVA 20%", new BigDecimal(2),null));
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),"Facture S110001", null,new BigDecimal(12)));

        dao.insertEcritureComptable(ecriture);
        EcritureComptable ecritureBis = dao.getEcritureComptableByRef("AC-" + currentYear + "/00200");

        Assert.assertTrue(ecriture.getReference().equals(ecritureBis.getReference()));
        Assert.assertTrue(ecriture.getLibelle().equals(ecritureBis.getLibelle()));

        dao.deleteEcritureComptable(ecritureBis.getId());

    }


    // ==================== EcritureComptable - UPDATE ====================

    @Test
    public void updateEcritureComptable() throws NotFoundException{
/*        EcritureComptable ecriture = dao.getEcritureComptable(-6);
        ecriture.setLibelle("panini");

        dao.updateEcritureComptable(ecriture);

        Assert.assertTrue(ecriture.getLibelle().equals(dao.getEcritureComptable(-6).getLibelle()));*/
    }


    // ==================== EcritureComptable - DELETE ====================

    @Test(expected = NotFoundException.class)
    public void deleteEcritureComptable() throws NotFoundException{
        dao.deleteEcritureComptable(-6);
        dao.getEcritureComptable(-6);

    }




    // ==================== SequenceEcritureComptable - INSERT ====================

    @Test
    public void insertSequenceEcritureComptable(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2018);
        sequence.setDerniereValeur(5);

        dao.insertSequenceEcritureComptable(sequence);
        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        Assert.assertEquals(5, vList.size());

    }

    // ==================== SequenceEcritureComptable - UPDATE ====================
    @Test
    public void updateSequenceEcritureComptable(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2018);
        sequence.setDerniereValeur(5);

        sequence.setDerniereValeur(100);

        dao.updateSequenceEcritureComptable(sequence);

        List<SequenceEcritureComptable> list = dao.getListSequenceEcritureComptable();
        SequenceEcritureComptable sequence2 = new SequenceEcritureComptable();

        for(SequenceEcritureComptable tempSequence : list){
            if (tempSequence.getCodeJournal().equals("AC") && tempSequence.getAnnee().equals(2018)){
                sequence2 = tempSequence;
            }
        }

        Assert.assertTrue(sequence2.getDerniereValeur().equals(100));

    }

    // ==================== SequenceEcritureComptable - DELETE ====================
    @Test
    public void deleteSequenceEcritureComptable(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2018);
        sequence.setDerniereValeur(100);

        dao.deleteSequenceEcritureComptable(sequence);

        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        Assert.assertEquals(4, vList.size());
    }


}
