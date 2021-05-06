package com.jp.resturantmanagementsystem.util;

import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;

//first 3 characters of first name, last 3 characters of last name + a 5 digit number > 10000

@Component
public class IdGenerator {
    public String createId(String firstName, String lastName) {
        firstName = checkLength(firstName);
        lastName = checkLength(lastName);
        return generateId(firstName, lastName);
    }

    private String generateId(String firstName, String lastName) {
            int randomNum = generateRandomNumber();
            return firstName.substring(0, 3) + lastName.substring(lastName.length() - 3) + randomNum;
    }

    private int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
    }

    //error around first name/last name not existing in request
    private String checkLength(String input) {
        StringBuilder sb = new StringBuilder(input);
        if (input.length() < 3) {
            int diff = 3 - input.length();
            sb.append("0".repeat(diff));
        }
        return sb.toString();
    }
}