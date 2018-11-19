package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JournalComptableTest {

    @Test
    public void getJournalComptableByCode(){

        List<JournalComptable> journalList = new ArrayList<>();

        for(int i = 1; i < 4; i++){
            JournalComptable journal = Mockito.mock(JournalComptable.class);
            Mockito.when(journal.getCode()).thenReturn(i + "0");
            Mockito.when(journal.getLibelle()).thenReturn("Libelle " + i);
            journalList.add(journal);
        }

        for(int i = 1; i < 4; i++)
            Assert.assertEquals(JournalComptable.getByCode(journalList, i + "0")
                    .getLibelle(), "Libelle " + i);
    }

}

