package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CompteComptableTest {

    @Test
    public void getCompteComptableByCode(){

        List<CompteComptable> compteList = new ArrayList<>();

        for(int i = 1; i < 4; i++){
            CompteComptable compte = Mockito.mock(CompteComptable.class);
            Mockito.when(compte.getNumero()).thenReturn(i);
            Mockito.when(compte.getLibelle()).thenReturn("Libelle " + i);
            compteList.add(compte);
        }

        for(int i = 1; i < 4; i++)
            Assert.assertEquals(CompteComptable.getByNumero(compteList, i)
                    .getLibelle(), "Libelle " + i);
    }
}
