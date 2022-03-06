package CalculatorProject;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class myFrame extends JFrame {
    private JPanel panel;
    private JButton [] buttons = new JButton[20];
    private JLabel label;

    private StringBuilder curNum = new StringBuilder("0");
    private int op = 0;

    private calculator c = new calculator();

    public myFrame(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(410, 680);
        this.setVisible(true);
        this.setResizable(false);
    }
    public void setPanel(){
        panel = new JPanel();
        panel.setLayout(null);
        this.setContentPane(panel);
    }

    public void setLabel(){
        label = new JLabel();
        label.setOpaque(true);
        label.setBackground(new Color(255,255,255));
        label.setBounds(20,20,350,170);
        label.setHorizontalAlignment(SwingConstants.SOUTH_EAST);
        label.setFont(new Font("微软雅黑",Font.BOLD,40));
        label.setText(curNum.toString());
        panel.add(label);
    }

    public void setButtons(){
        int cnt = 0;
        buttons[cnt++] = new JButton("AC");
        buttons[cnt++] = new JButton("DEL");
        buttons[cnt++] = new JButton("^");
        buttons[cnt++] = new JButton("/");
        buttons[cnt++] = new JButton("7");
        buttons[cnt++] = new JButton("8");
        buttons[cnt++] = new JButton("9");
        buttons[cnt++] = new JButton("x");
        buttons[cnt++] = new JButton("4");
        buttons[cnt++] = new JButton("5");
        buttons[cnt++] = new JButton("6");
        buttons[cnt++] = new JButton("-");
        buttons[cnt++] = new JButton("1");
        buttons[cnt++] = new JButton("2");
        buttons[cnt++] = new JButton("3");
        buttons[cnt++] = new JButton("+");
        buttons[cnt++] = new JButton("(");
        buttons[cnt++] = new JButton(")");
        buttons[cnt++] = new JButton("0");
        buttons[cnt++] = new JButton("=");

        System.out.println(cnt);

        int x = 20, y = 200;
        for (int i = 0; i < cnt; i++) {
            JButton button = buttons[i];

            if(x +90 > 400){
                x = 20;
                y += 90;
            }
            button.setBounds(x,y,80,80);
            button.setFont(new Font("微软雅黑",Font.PLAIN, 18));
            button.setBackground(Color.white);
            x += 90;
            panel.add(button);
        }
    }

    public void setListener(){
        //等于
        buttons[19].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                c.getExpression(curNum.toString());
                String res = c.getRes();
                curNum = new StringBuilder(res);
                op = 1;
                if(curNum.length() > 14){
                    label.setFont(new Font("微软雅黑",Font.BOLD,20));
                }else{
                    label.setFont(new Font("微软雅黑",Font.BOLD,40));
                }
                label.setText(curNum.toString());
            }
        });

        //AC
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curNum = new StringBuilder("0");
                label.setText(curNum.toString());
            }
        });

        //退格
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curNum.length() > 0){
                    curNum.delete(curNum.length()-1, curNum.length());
                    if(curNum.length() == 0){
                        curNum.append("0");
                    }
                    if(curNum.length() > 14){
                        label.setFont(new Font("微软雅黑",Font.BOLD,20));
                    }else{
                        label.setFont(new Font("微软雅黑",Font.BOLD,40));
                    }
                    label.setText(curNum.toString());
                }

            }
        });

        //加法
        buttons[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressOperatorButton("+");
            }
        });

        //减法
        buttons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressOperatorButton("-");
            }
        });

        //乘法
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressOperatorButton("*");
            }
        });

        //除法
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressOperatorButton("/");
            }
        });

        //数字7
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(7);
            }
        });

        //数字8
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(8);
            }
        });

        //数字9
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(9);
            }
        });

        //数字4
        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(4);
            }
        });

        //数字5
        buttons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(5);
            }
        });

        //数字6
        buttons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(6);
            }
        });

        //数字1
        buttons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(1);
            }
        });

        //数字2
        buttons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(2);
            }
        });

        //数字3
        buttons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(3);
            }
        });

        //数字0
        buttons[18].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressNumButton(0);
            }
        });

        //左括号
        buttons[16].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curNum.charAt(0) == '0'){
                    curNum.replace(0,1,"(");
                }else {
                    curNum.append("(");
                }
                if(curNum.length() > 14){
                    label.setFont(new Font("微软雅黑",Font.BOLD,20));
                }else{
                    label.setFont(new Font("微软雅黑",Font.BOLD,40));
                }
                label.setText(curNum.toString());
            }
        });

        //右括号
        buttons[17].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curNum.charAt(0) == '0'){
                    curNum.replace(0,1,")");
                }else {
                    curNum.append(")");
                }
                if(curNum.length() > 14){
                    label.setFont(new Font("微软雅黑",Font.BOLD,20));
                }else{
                    label.setFont(new Font("微软雅黑",Font.BOLD,40));
                }
                label.setText(curNum.toString());
            }
        });
    }

    public void pressNumButton(int num){
        if(op == 1){
            curNum = new StringBuilder("0");
            op = 0;
        }

        if(curNum.charAt(0) == '0'){
            curNum.replace(0,1,String.valueOf(num));
        }else {
            curNum.append(String.valueOf(num));
        }
        if(curNum.length() > 14){
            label.setFont(new Font("微软雅黑",Font.BOLD,20));
        }else{
            label.setFont(new Font("微软雅黑",Font.BOLD,40));
        }
        label.setText(curNum.toString());
    }

    public void pressOperatorButton(String operator){
        if(op == 1){
            curNum = new StringBuilder("0");
            op = 0;
            label.setText(curNum.toString());
            return;
        }

        if(Character.isDigit(curNum.charAt(curNum.length()-1))){
            curNum.append(operator);
        }
        if(curNum.length() > 14){
            label.setFont(new Font("微软雅黑",Font.BOLD,20));
        }else{
            label.setFont(new Font("微软雅黑",Font.BOLD,40));
        }
        label.setText(curNum.toString());
    }
}
