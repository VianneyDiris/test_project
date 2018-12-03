package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.SequenceEcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.SequenceEcritureComptable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class SequenceEcritureComptableRMTest {

    @Mock
    private ResultSet resultSet;

    /**
     * test de SequenceEcritureComptableRM mapRow(ResultSet pRS, int pRowNum) throws SQLException
     *
     * @throws SQLException
     */
    @Test
    public void mapRow() throws SQLException {
        // Création du resultSet en paramètre
        Mockito.when(resultSet.getString("journal_code")).thenReturn("code");
        Mockito.when(resultSet.getInt("annee")).thenReturn(2018);
        Mockito.when(resultSet.getInt("derniere_valeur")).thenReturn(15);

        // Test
        SequenceEcritureComptableRM vSequenceEcritureComptableRM = new SequenceEcritureComptableRM();
        SequenceEcritureComptable vResult = vSequenceEcritureComptableRM.mapRow(resultSet,0);

        // Vérifie vResult!=null
        Assert.assertTrue("Test sequenceEcritureComptableRM.mapRow() : résultat null", vResult != null);

        // Vérifie les valeurs de vResult
        Assert.assertTrue("Test sequenceEcritureComptableRM.mapRow() : code faux", vResult.getCodeJournal().equals("code"));
        Assert.assertTrue("Test sequenceEcritureComptableRM.mapRow() : annee faux", vResult.getAnnee()==2018);
        Assert.assertTrue("Test sequenceEcritureComptableRM.mapRow() : derniere_valeur faux", vResult.getDerniereValeur()==15);


    }
}
