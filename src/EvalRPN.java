import java.util.Stack;

/**
 * EvalRPN
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-02-05 19:30
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }

    public int evalRPN2(String[] tokens) {
        int[] stack = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack[index - 2] += stack[--index];
                    break;
                case "-":
                    stack[index - 2] -= stack[--index];
                    break;
                case "*":
                    stack[index - 2] *= stack[--index];
                    break;
                case "/":
                    stack[index - 2] /= stack[--index];
                    break;
                default:
                    stack[index++] = Integer.parseInt(token);
            }
        }
        return stack[0];
    }

}
