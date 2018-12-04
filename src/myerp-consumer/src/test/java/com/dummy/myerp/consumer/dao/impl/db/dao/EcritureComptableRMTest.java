package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class EcritureComptableRMTest {

    @Mock
    private ResultSet resultSet;

    /**
     * test de EcritureComptableRM mapRow(ResultSet pRS, int pRowNum) throws SQLException
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {
        // Création du resultSet en paramètre
        Mockito.when(resultSet.getInt("id")).thenReturn(15);
        Mockito.when(resultSet.getString("journal_code")).thenReturn("AC");
        Mockito.when(resultSet.getString("reference")).thenReturn("reference");
        Mockito.when(resultSet.getString("libelle")).thenReturn("libelle");

        // Test
        EcritureComptableRM vEcritureComptableRm = new EcritureComptableRM();
        EcritureComptable vResult = vEcritureComptableRm.mapRow(resultSet,0);

        // Vérifie vResult!=null
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : résultat null", vResult != null);

        // Vérifie les valeurs de vResult
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : id faux", vResult.getId()== 15);
       // Assert.assertTrue("Test EcritureComptableRM.mapRow() : journal code faux", vResult.getJournal().getCode().equals("Libelle"));
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : libelle faux", vResult.getLibelle().equals("Libelle"));
    }



}
