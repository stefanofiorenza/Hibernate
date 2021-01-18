package com.hibernate.bootstrap.util.test;


import org.junit.Test;

import com.hibernate.bootstrap.util.AbstractJPAProgrammaticBootstrapTest;

import lombok.Data;

import javax.persistence.*;





public class BootstrapTest extends AbstractJPAProgrammaticBootstrapTest {

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
        		DemoTable.class
        };
    }



    @Test
    public void test() {
        doInJPATransaction(entityManager -> {
            for (long id = 1; id <= 3; id++) {
            	DemoTable demoTableRecord = new DemoTable();
            	demoTableRecord.setId(id);
            	demoTableRecord.setName(
                    String.format(
                        "Some Insert Test for DemoTable, Part %d", id
                    )
                );
                entityManager.persist(demoTableRecord);
            }
        });
    }

    
    @Entity(name = "DemoTable")
    @Table(name = "DemoTable")
    @Data
    static class DemoTable {

        @Id
        private Long id;
        private String name;

    }
    
}
