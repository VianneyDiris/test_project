package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ComptabiliteDaoImplTest {

    private ComptabiliteDaoImpl dao = new ComptabiliteDaoImpl();

    // ==================== JournalComptable - GET ====================

    @Test
    public void getListJournalComptable() {
        List<JournalComptable> vList = dao.getListJournalComptable();
        Assert.assertEquals(4, vList.size());
    }


    // ==================== EcritureComptable - GET ====================

    @Test
    public void getListEcritureComptable() {
        List<EcritureComptable> vList = dao.getListEcritureComptable();
        Assert.assertEquals(5, vList.size());
    }
/*
    @Test
    void getEcritureComptable() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptable(-3);
        Assert.assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

        Assert.assertThrows(NotFoundException.class, () -> dao.getEcritureComptable(0));
    }*/
}
