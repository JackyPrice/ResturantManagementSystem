package com.jp.resturantmanagementsystem.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class IdGeneratorTest {

    private final IdGenerator idGenerator = new IdGenerator();
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";

    @Test
    @DisplayName("given call to createId when id is returned then it is 11 characters long")
    void createIdCheckLength(){
        //given

        //when
        String idGenerated = idGenerator.createId(FIRST_NAME,LAST_NAME);

        assertEquals(11, idGenerated.length());
    }

    @Test
    @DisplayName("given call to createId when id is returned then ends with 5 numbers between 10000-99999")
    void createIdCheckNumber(){

        //when
        String idGenerated = idGenerator.createId(FIRST_NAME, LAST_NAME);

        int number = getNumber(idGenerated);

        assertTrue(number>=10000);
        assertTrue(number<=99999);

    }
    @Test
    @DisplayName("given call to createId when id is returned then it uses correct format")
    void createIdCheckString(){

        String expectedString = "firame";

        //when
        String idGenerated = idGenerator.createId(FIRST_NAME, LAST_NAME);

        String actualString = getSubstring(idGenerated);

        assertEquals(expectedString, actualString);

    }

    private int getNumber(String idGenerated) {
        return Integer.parseInt(idGenerated.substring(6, 11));
    }

    private String getSubstring(String idGenerated) {
        return idGenerated.substring(0, 6);
    }
}
