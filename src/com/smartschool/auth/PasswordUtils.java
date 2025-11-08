package com.smartschool.auth;

/**
 * Utility class for password hashing and validation.
 * This class demonstrates the use of a static nested class for password hashing operations.
 * 
 * <p>This class demonstrates:
 * <ul>
 *   <li>Static nested class (PasswordHasher)</li>
 *   <li>Wrapper class usage (Integer, String)</li>
 * </ul>
 * 
 * @author Smart School Development Team
 * @version 1.0
 */
public class PasswordUtils {
    
    /**
     * Static nested class that handles password hashing operations.
     * This simulates SHA-256 hashing for demonstration purposes.
     * In a real application, this would use a proper cryptographic library.
     * 
     * <p>This nested class demonstrates:
     * <ul>
     *   <li>Static nested class design pattern</li>
     *   <li>Encapsulation of hashing logic</li>
     * </ul>
     */
    public static class PasswordHasher {
        
        /**
         * Hashes a password using a simple algorithm (simulating SHA-256).
         * In production, use proper cryptographic libraries like BCrypt or Argon2.
         * 
         * @param password The plain text password to hash
         * @return A hashed representation of the password
         */
        public static String hashPassword(String password) {
            if (password == null || password.isEmpty()) {
                return "";
            }
            
            // Simple hash simulation (for demonstration only)
            // In production, use: return BCrypt.hashpw(password, BCrypt.gensalt());
            int hash = password.hashCode();
            Integer hashWrapper = Integer.valueOf(Math.abs(hash)); // Wrapper class usage
            return "HASHED_" + hashWrapper.toString() + "_" + password.length();
        }
        
        /**
         * Verifies if a plain text password matches a hashed password.
         * 
         * @param password The plain text password
         * @param hashedPassword The hashed password to compare against
         * @return true if passwords match, false otherwise
         */
        public static boolean verifyPassword(String password, String hashedPassword) {
            if (password == null || hashedPassword == null) {
                return false;
            }
            String newHash = hashPassword(password);
            return newHash.equals(hashedPassword);
        }
        
        /**
         * Generates a random salt for password hashing.
         * Uses wrapper classes for demonstration.
         * 
         * @return A random salt string
         */
        public static String generateSalt() {
            Integer randomInt = Integer.valueOf((int)(Math.random() * 1000000)); // Wrapper class
            return "SALT_" + randomInt.toString();
        }
    }
    
    /**
     * Validates password strength.
     * 
     * @param password The password to validate
     * @return true if password meets strength requirements
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        // Check for at least one digit and one letter
        boolean hasDigit = false;
        boolean hasLetter = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        
        return hasDigit && hasLetter;
    }
}

