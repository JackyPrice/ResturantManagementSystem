package com.jp.resturantmanagementsystem.util;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

//first 3 characters of first name, last 3 characters of last name + a 5 digit number 00001

@Service
public class IdGenerator {
    public String createId(String firstName, String lastName) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
        return firstName.substring(0, 3) + lastName.substring(lastName.length() - 3) + randomNum;
    }
}
