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
    public void getListCompteComptableTest() {
        List<CompteComptable> vList = dao.getListCompteComptable();
        Assert.assertTrue(vList.size()>1);
    }


    // ==================== JournalComptable - GET ====================

    @Test
    public void getListJournalComptableTest() {
        List<JournalComptable> vList = dao.getListJournalComptable();
        Assert.assertTrue(vList.size()>1);
    }

    // ==================== SequenceEcritureComptable - GET ====================

    @Test
    public void getListSequenceEcritureComptableTest(){
        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        Assert.assertTrue(vList.size()>1);
    }


    // ==================== EcritureComptable - GET ====================

    @Test
    public void getListEcritureComptableTest() {
        List<EcritureComptable> vList = dao.getListEcritureComptable();
        Assert.assertTrue(vList.size()>1);
    }

    @Test
    public void getEcritureComptableTest() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptable(-3);
        Assert.assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

        Assertions.assertThrows(NotFoundException.class, () -> dao.getEcritureComptable(0));
    }

    @Test
    public void getEcritureComptableByRefTest() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptableByRef("BQ-2016/00003");
        Assert.assertEquals("BQ", vEcritureComptable.getJournal().getCode());
        String vEcritureYear = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        Assert.assertEquals("2016", vEcritureYear);
        Assert.assertEquals(-3, vEcritureComptable.getId().intValue());

        Assertions.assertThrows(NotFoundException.class, ()-> dao.getEcritureComptableByRef("BQ-2016/33333"));
    }

    @Test
    public void loadListLigneEcritureTest() {
        EcritureComptable ecriture  = new EcritureComptable();
        ecriture.setId(-5);
        dao.loadListLigneEcriture(ecriture);
        Assert.assertEquals(2, ecriture.getListLigneEcriture().size());
    }


    // ==================== EcritureComptable - INSERT ====================

    @Test
    public void insertEcritureComptableTest() throws NotFoundException {
        EcritureComptable ecriture  = new EcritureComptable();
        Date currentDate = new Date();
        Integer currentYear = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        ecriture.setJournal(new JournalComptable("OD", "Opérations Diverses"));
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
    public void updateEcritureComptableTest() throws NotFoundException{
        EcritureComptable ecriture  = new EcritureComptable();
        Date currentDate = new Date();
        Integer currentYear = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
        ecriture.setJournal(new JournalComptable("OD", "Opérations Diverses"));
        ecriture.setReference("AC-" + currentYear + "/00200");
        ecriture.setDate(currentDate);
        ecriture.setLibelle("Sandwichs");

        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),"Club saumon", new BigDecimal(10),null));
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),"TVA 20%", new BigDecimal(2),null));
        ecriture.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),"Facture S110001", null,new BigDecimal(12)));

        dao.insertEcritureComptable(ecriture);
        EcritureComptable ecritureBis = dao.getEcritureComptableByRef("AC-" + currentYear + "/00200");

        ecritureBis.setLibelle("panini");

        dao.updateEcritureComptable(ecritureBis);

        Assert.assertTrue(dao.getEcritureComptableByRef("AC-" + currentYear + "/00200").getLibelle().equals("panini"));
        dao.deleteEcritureComptable(ecritureBis.getId());
    }


    // ==================== EcritureComptable - DELETE ====================

    @Test(expected = NotFoundException.class)
    public void deleteEcritureComptableTest() throws NotFoundException{
        dao.deleteEcritureComptable(-6);
        dao.getEcritureComptable(-6);

    }




    // ==================== SequenceEcritureComptable - INSERT ====================

    @Test
    public void insertSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2018);
        sequence.setDerniereValeur(5);

        dao.insertSequenceEcritureComptable(sequence);
        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        SequenceEcritureComptable sequenceBis = new SequenceEcritureComptable();
        for (SequenceEcritureComptable seq :vList){
            if(seq.getCodeJournal().equals("AC") && seq.getAnnee()==2018){
                sequenceBis=seq;
            }
        }


        Assert.assertEquals("AC", sequenceBis.getCodeJournal());
        Assert.assertTrue(sequenceBis.getAnnee()==2018);
        Assert.assertTrue(sequenceBis.getDerniereValeur()==5);

        dao.deleteSequenceEcritureComptable(sequence);

    }

    // ==================== SequenceEcritureComptable - UPDATE ====================
    @Test
    public void updateSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2019);
        sequence.setDerniereValeur(5);

        dao.insertSequenceEcritureComptable(sequence);
        sequence.setDerniereValeur(100);

        dao.updateSequenceEcritureComptable(sequence);

        List<SequenceEcritureComptable> list = dao.getListSequenceEcritureComptable();
        SequenceEcritureComptable sequence2 = new SequenceEcritureComptable();

        for(SequenceEcritureComptable tempSequence : list){
            if (tempSequence.getCodeJournal().equals("AC") && tempSequence.getAnnee().equals(2019)){
                sequence2 = tempSequence;
            }
        }

        Assert.assertTrue(sequence2.getDerniereValeur().equals(100));

        dao.deleteSequenceEcritureComptable(sequence);
    }

    // ==================== SequenceEcritureComptable - DELETE ====================
    @Test
    public void deleteSequenceEcritureComptableTest(){
        SequenceEcritureComptable sequence = new SequenceEcritureComptable();
        sequence.setCodeJournal("AC");
        sequence.setAnnee(2020);
        sequence.setDerniereValeur(5);

        dao.insertSequenceEcritureComptable(sequence);

        dao.deleteSequenceEcritureComptable(sequence);

        List<SequenceEcritureComptable> vList = dao.getListSequenceEcritureComptable();
        Assert.assertEquals(4, vList.size());
    }


}
