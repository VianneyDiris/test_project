package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.cache.JournalComptableDaoCache;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.testconsumer.consumer.ConsumerTestCase;
import org.junit.Assert;
import org.junit.Before;
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

    @Mock
    private static DaoProxy daoProxy;

    @Mock
    private static ComptabiliteDao comptabiliteDao;

    @Before
    public void setUp(){
        ConsumerHelper.configure(daoProxy);
        Mockito.when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
    }

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
        Mockito.when(resultSet.getString("journal_code")).thenReturn("AC");




        // Test
        EcritureComptableRM vEcritureComptableRm = new EcritureComptableRM();
        EcritureComptable vResult = vEcritureComptableRm.mapRow(resultSet,0);

        // Vérifie vResult!=null
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : résultat null", vResult != null);

        // Vérifie les valeurs de vResult
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : id faux", vResult.getId()== 15);
        Assert.assertTrue("Test EcritureComptableRM.mapRow() : libelle faux", vResult.getLibelle().equals("libelle"));
    }



}
