package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LigneEcritureComptableTest {

    @Test
    public void toStringTest(){
        LigneEcritureComptable ligne = new LigneEcritureComptable();
        ligne.setCompteComptable(null);
        ligne.setLibelle("libelle");
        ligne.setCredit(BigDecimal.valueOf(10));
        ligne.setDebit(null);

        Assert.assertEquals(ligne.toString(),"LigneEcritureComptable{compteComptable=null, libelle='libelle', debit=null, credit=10}");
    }
}
