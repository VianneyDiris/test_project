package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.Test;

import java.util.List;

public class ComptabiliteDaoImplTest {

    private ComptabiliteDaoImpl dao = new ComptabiliteDaoImpl();

    @Test
    public void getListCompteComptable(){
        List<CompteComptable> listCompteComptable =  dao.getListCompteComptable();
        for(CompteComptable compte : listCompteComptable){
            System.out.println(compte.toString());
        }
    }
}
