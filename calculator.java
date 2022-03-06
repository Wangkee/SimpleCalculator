package CalculatorProject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Deque;
import java.util.LinkedList;

public class calculator {
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    String expression;

    public void getExpression(String str){
        expression = str;
    }

    public Boolean correctExp(String s){
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                deque.push(')');
            } else if (ch == ')' && (deque.isEmpty() || deque.peek() != ch)) {
                return false;
            }else if(ch != '(' && ch != ')'){
                continue;
            } else {
                deque.pop();
            }
        }
        return deque.isEmpty();

    }

    public String getRes() {
        if(correctExp(expression)) {
            long res = 0;
            try {
                if(jse.eval(expression) instanceof Long || jse.eval(expression) instanceof Integer){
                    res = (long)Math.floor(Long.parseLong(String.valueOf(jse.eval(expression))));
                }else{
                    res = (long)Math.floor((double)jse.eval(expression));
                }
                if(res > Integer.MAX_VALUE){
                    return "overflow";
                }
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            return String.valueOf(res);
        }

        else{
            return "表达式错误";
        }
    }
}
