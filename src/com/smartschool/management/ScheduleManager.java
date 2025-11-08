package com.smartschool.management;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages class schedules in the Smart School Management System.
 * This class handles scheduling of classes, courses, and time slots.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class ScheduleManager {
    
    private Map<String, Map<DayOfWeek, LocalTime>> schedules; // classId -> (day -> time)
    
    /**
     * Constructs a new ScheduleManager.
     */
    public ScheduleManager() {
        this.schedules = new HashMap<>();
    }
    
    /**
     * Adds a schedule entry for a class.
     * 
     * @param classId The ID of the class
     * @param day The day of the week
     * @param time The time of the class
     */
    public void addSchedule(String classId, DayOfWeek day, LocalTime time) {
        schedules.computeIfAbsent(classId, k -> new HashMap<>()).put(day, time);
    }
    
    /**
     * Gets the schedule for a specific class.
     * 
     * @param classId The ID of the class
     * @return Map of days to times
     */
    public Map<DayOfWeek, LocalTime> getSchedule(String classId) {
        Map<DayOfWeek, LocalTime> classSchedule = schedules.get(classId);
        if (classSchedule == null) {
            return new HashMap<>();
        }
        return new HashMap<>(classSchedule);
    }
    
    /**
     * Removes a schedule entry.
     * 
     * @param classId The ID of the class
     * @param day The day of the week
     */
    public void removeSchedule(String classId, DayOfWeek day) {
        Map<DayOfWeek, LocalTime> classSchedule = schedules.get(classId);
        if (classSchedule != null) {
            classSchedule.remove(day);
        }
    }
    
    /**
     * Gets all schedules.
     * 
     * @return Map of all class schedules
     */
    public Map<String, Map<DayOfWeek, LocalTime>> getAllSchedules() {
        return new HashMap<>(schedules);
    }
}

