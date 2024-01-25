package org.example.streams;

import java.util.Arrays;
import java.util.Optional;

public enum DayOfWeek {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7),
    ;

    final int dbValue;

    DayOfWeek(int i) {
        this.dbValue = i;
    }

//    public static Optional<DayOfWeek> fromDbValue(int dbValue) {
//        for (DayOfWeek value : DayOfWeek.values()) {
//            if (value.dbValue == dbValue) {
//                return Optional.of(value);
//            }
//        }
//        return Optional.empty();
//    }

    public static Optional<DayOfWeek> fromDbValue(int value) {
        return Arrays.stream(DayOfWeek.values())
                    .filter(dayOfWeek -> dayOfWeek.dbValue == value)
                    .findFirst();
    }
}
