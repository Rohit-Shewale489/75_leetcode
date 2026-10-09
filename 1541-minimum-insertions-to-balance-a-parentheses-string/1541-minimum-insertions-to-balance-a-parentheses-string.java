
class Solution {
    public int minInsertions(String s) {
        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } else {
                // If the next character is also ')',
                // consume the pair together.
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to complete the pair.
                    insertions++;
                }

                // Match this pair with an opening '('.
                if (open > 0) {
                    open--;
                } else {
                    // No opening '(' exists, so insert one.
                    insertions++;
                }
            }
        }

        // Each remaining '(' needs two ')'.
        return insertions + 2 * open;
    }
}
