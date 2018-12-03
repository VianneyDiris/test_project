package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.*;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Test;

import java.sql.ResultSet;

public class RowMapperTest extends ConsumerTestCase {

    CompteComptableRM compteRM             = new CompteComptableRM();
    EcritureComptableRM ecritureRM         = new EcritureComptableRM();
    JournalComptableRM journalRM           = new JournalComptableRM();
    LigneEcritureComptableRM ligneRM       = new LigneEcritureComptableRM();
    SequenceEcritureComptableRM sequenceRM = new SequenceEcritureComptableRM();

    @Test
    public void sequenceEcritureRMTest(){

    }

    @Test
    public void ligneEcritureComptableRMTest(){

    }

    @Test
    public void ecritureComptableRMTest(){

    }

    @Test
    public void journalComptableRMTest(){

    }

    @Test
    public void compteComptableRMTest(){

    }


}
