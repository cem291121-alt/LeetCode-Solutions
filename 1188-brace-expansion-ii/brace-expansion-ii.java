import java.util.*;

class Solution {
    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {
        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();
        List<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        return answer;
    }

    // Parses comma-separated expressions as a union.
    private Set<String> parseExpression() {
        Set<String> result = parseConcatenation();

        while (index < expression.length() && expression.charAt(index) == ',') {
            index++; // consume comma
            result.addAll(parseConcatenation());
        }

        return result;
    }

    // Parses adjacent letters/groups as concatenation.
    private Set<String> parseConcatenation() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != ','
                && expression.charAt(index) != '}') {
            Set<String> part;

            if (expression.charAt(index) == '{') {
                index++; // consume '{'
                part = parseExpression();
                index++; // consume '}'
            } else {
                part = Collections.singleton(
                        String.valueOf(expression.charAt(index++)));
            }

            Set<String> combined = new HashSet<>();
            for (String left : result) {
                for (String right : part) {
                    combined.add(left + right);
                }
            }
            result = combined;
        }

        return result;
    }
}