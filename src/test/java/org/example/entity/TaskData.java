package org.example.entity;

import java.util.HashSet;
import java.util.Set;

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

    // ✅ UNION
    public Set<Task> getUnion(Set<Task> set1, Set<Task> set2) {
        Set<Task> union = new HashSet<>(set1);
        union.addAll(set2);
        return union;
    }

    // ✅ INTERSECT (kesişim)
    public Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> intersect = new HashSet<>(set1);
        intersect.retainAll(set2);
        return intersect;
    }

    // ✅ DIFFERENCE (set1 - set2)
    public Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        return difference;
    }

    // --- yardımcılar (senin dosyada var, kalsın) ---
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
        duplicates.addAll(getIntersect(annsTasks, bobsTasks));
        duplicates.addAll(getIntersect(annsTasks, carolsTasks));
        duplicates.addAll(getIntersect(bobsTasks, carolsTasks));
        return duplicates;
    }

    // ✅ TESTİN İSTEDİĞİ İSİMLER (stub değil!)
    public Set<Task> getIntersection(Set<Task> taskSet, Set<Task> taskSet2) {
        return getIntersect(taskSet, taskSet2);
    }

    public Set<Task> getDifferences(Set<Task> taskSet, Set<Task> taskSet2) {
        return getDifference(taskSet, taskSet2);
    }
}