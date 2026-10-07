import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        
        List<String> ans = new ArrayList<>();
        
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        queue.add(s);
        visited.add(s);
        
        boolean found = false;
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            
            if (isValid(current)) {
                ans.add(current);
                found = true;
            }
            
            // Don't remove more brackets after finding valid strings
            if (found) {
                continue;
            }
            
            // Remove one parenthesis
            for (int i = 0; i < current.length(); i++) {
                
                if (current.charAt(i) != '(' && 
                    current.charAt(i) != ')') {
                    continue;
                }
                
                String next = current.substring(0, i) 
                            + current.substring(i + 1);
                
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        
        return ans;
    }
    
    private boolean isValid(String s) {
        int count = 0;
        
        for (char ch : s.toCharArray()) {
            
            if (ch == '(') {
                count++;
            } 
            else if (ch == ')') {
                count--;
                
                if (count < 0) {
                    return false;
                }
            }
        }
        
        return count == 0;
    }
}