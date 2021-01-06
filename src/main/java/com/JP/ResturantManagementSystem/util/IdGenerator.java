package com.JP.ResturantManagementSystem.util;

import java.util.concurrent.ThreadLocalRandom;

//first 3 characters of first name, last 3 characters of last name + a 5 digit number 00001

public class IdGenerator {
    public static String createId(String firstName, String lastName) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
        return firstName.substring(0, 3) + lastName.substring(lastName.length() - 3) + randomNum;
    }
}
