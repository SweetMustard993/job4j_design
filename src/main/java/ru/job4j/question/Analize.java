package ru.job4j.question;

import ru.job4j.map.Map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        HashMap<Integer, String> mapPrevious = new HashMap<>(previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName)));
        for (User user : current) {
            if (mapPrevious.putIfAbsent(user.getId(), user.getName()) == null) {
                added++;
            }
            if (!Objects.equals(mapPrevious.putIfAbsent(user.getId(), user.getName()), user.getName())) {
                changed++;
            }
            mapPrevious.remove(user.getId());
        }
        deleted = mapPrevious.size();

        return new Info(added, changed, deleted);
    }

}