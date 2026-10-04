import java.util.*;

class Solution {
    public boolean checkValidString(String s) {

        Stack<Integer> open = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (ch == '(') {
                open.push(i);
            }

            else if (ch == '*') {
                star.push(i);
            }

            else { // ')'

                // First use '('
                if (!open.isEmpty()) {
                    open.pop();
                }

                // Otherwise use '*'
                else if (!star.isEmpty()) {
                    star.pop();
                }

                // No '(' or '*' available
                else {
                    return false;
                }
            }
        }

        // Match remaining '(' with '*' 
        while (!open.isEmpty() && !star.isEmpty()) {

            // '*' must come AFTER '('
            if (open.peek() > star.peek()) {
                return false;
            }

            open.pop();
            star.pop();
        }

        // If '(' are still remaining, invalid
        return open.isEmpty();
    }
}