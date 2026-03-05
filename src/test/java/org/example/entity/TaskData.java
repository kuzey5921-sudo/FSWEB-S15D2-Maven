package org.example.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TaskData {
    private Set<Task> annsTasks;
    private Set<Task> bobsTasks;
    private Set<Task> carolsTasks;
    private Set<Task> unassignedTasks;

    public TaskData(Set<Task> annsTasks, Set<Task> bobsTasks, Set<Task> carolsTasks, Set<Task> unassignedTasks) {
        this.annsTasks = annsTasks;
        this.bobsTasks = bobsTasks;
        this.carolsTasks = carolsTasks;
        this.unassignedTasks = unassignedTasks;
    }

    public Set<Task> getTasks(String key) {
        return switch (key.toLowerCase()) {
            case "ann" -> annsTasks;
            case "bob" -> bobsTasks;
            case "carol" -> carolsTasks;
            case "all" -> getUnion(getUnion(annsTasks, bobsTasks), getUnion(carolsTasks, unassignedTasks));
            default -> new HashSet<>();
        };
    }

    public Set<Task> getUnion(Set<Task> set1, Set<Task> set2) {
        Set<Task> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    public Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    public Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }

    // Müdüre cevap verebilmek için yardımcı metodlar
    public Set<Task> getAllTasks() {
        return getTasks("all");
    }

    public Set<Task> getAssignedTasks() {
        return getUnion(getUnion(annsTasks, bobsTasks), carolsTasks);
    }

    public Set<Task> getUnassignedTasks() {
        return unassignedTasks;
    }

    public Set<Task> getDuplicateTasks() {
        Set<Task> duplicates = new HashSet<>();

        // Ann ve Bob arasındaki kesişim
        duplicates.addAll(getIntersect(annsTasks, bobsTasks));
        // Ann ve Carol arasındaki kesişim
        duplicates.addAll(getIntersect(annsTasks, carolsTasks));
        // Bob ve Carol arasındaki kesişim
        duplicates.addAll(getIntersect(bobsTasks, carolsTasks));

        return duplicates;
    }

    public Set<Task> getIntersection(Set<Task> taskSet, Set<Task> taskSet2) {
        return taskSet;
    }

    public Set<Task> getDifferences(Set<Task> taskSet, Set<Task> taskSet2) {
        return taskSet;
    }
}
