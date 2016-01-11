package io.github.yas99en.clipshare.view;

import java.time.LocalDate;
import java.time.Period;

import org.junit.Test;

public class PeriodTest {
    @Test
    public void period() {
        Period oneMonth = Period.ofMonths(1);
        System.out.println(oneMonth);

        LocalDate date1 = LocalDate.of(2015, 1, 30);
        LocalDate date2 = date1.plus(oneMonth);
        System.out.println(date2);
        System.out.println(Period.between(date1, date2));

        LocalDate date3 = LocalDate.of(2016, 1, 31);
        LocalDate date4 = date3.plus(oneMonth);
        System.out.println(date4);
        System.out.println(Period.between(date3, date4));
    }
}
