package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {

        Set<Integer> idAddedPrevious = previous.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        Set<Integer> idAddedCurrent = current.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        idAddedCurrent.removeAll(idAddedPrevious);
        int added = idAddedCurrent.size();

        Set<User> changedPrevious = new HashSet<>(previous);
        Set<User> changedCurrent = new HashSet<>(current);
        changedCurrent.removeAll(changedPrevious);
        Set<Integer> idRest = changedCurrent.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        Set<Integer> idPrevious = changedPrevious.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        idPrevious.retainAll(idRest);
        int changed = idPrevious.size();

        Set<Integer> idDelPrevious = previous.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        Set<Integer> idDelCurrent = current.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        idDelPrevious.removeAll(idDelCurrent);
        int deleted = idDelPrevious.size();
        return new Info(added, changed, deleted);
    }

}