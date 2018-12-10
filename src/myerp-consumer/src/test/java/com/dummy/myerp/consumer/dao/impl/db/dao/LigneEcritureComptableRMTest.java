package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.LigneEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class LigneEcritureComptableRMTest {
    @Mock
    private ResultSet resultSet;

    /**
     * test de LigneEcritureComptableRM mapRow(ResultSet pRS, int pRowNum) throws SQLException
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {
        // Création du resultSet en paramètre
       /* Mockito.when(resultSet.getBigDecimal("credit")).thenReturn(BigDecimal.valueOf(15));
        Mockito.when(resultSet.getBigDecimal("debit")).thenReturn(BigDecimal.valueOf(20));
        Mockito.when(resultSet.getString("libelle")).thenReturn("libelle");

        // Test
        LigneEcritureComptableRM vLigneEcritureComptableRM = new LigneEcritureComptableRM();
        LigneEcritureComptable vResult = vLigneEcritureComptableRM.mapRow(resultSet,0);

        // Vérifie vResult!=null
        Assert.assertTrue("Test LigneEcritureComptableRM.mapRow() : résultat null", vResult != null);

        Assert.assertTrue("Test EcritureComptableRM.mapRow() : credit faux", vResult.getCredit().compareTo(BigDecimal.valueOf(15))==0);
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : debit faux", vResult.getDebit().compareTo(BigDecimal.valueOf(20))==0);
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : libelle faux", vResult.getLibelle().equals("Libelle"));*/
    }
}
