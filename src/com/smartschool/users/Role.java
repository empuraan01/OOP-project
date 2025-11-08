package com.smartschool.users;

/**
 * Interface defining role-based behavior for users in the system.
 * Classes implementing this interface must define how they perform
 * their role-specific duties.
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public interface Role {
    
    /**
     * Performs duties specific to the user's role.
     * Implementation varies based on whether the user is Admin, Teacher, or Student.
     * 
     * @return A string description of the duties performed
     */
    String performRoleDuties();
    
    /**
     * Returns the name of the role.
     * 
     * @return The role name as a String
     */
    String getRoleName();
}

