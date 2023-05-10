package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte one = 1;
        short two = 2;
        int three = 3;
        long four = 4L;
        float five = 5.0F;
        double six = 6;
        char seven = 'g';
        boolean eight = true;
        LOG.warn("first: {}, second: {}, third: {}, fourths: {}, fifth: {}, sixth: {}, seventh: {}, eighth: {}", one,
                two, three, four, five, six, seven, eight);
    }
}