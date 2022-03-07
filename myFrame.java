package CalculatorProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myFrame extends JFrame {


    /**
     * panel : 设置面板
     * label : 计算器显示屏
     * buttons : 按钮数组
     * names : 按钮名数组
     */
    private JPanel panel;
    private JLabel label;
    private JButton [] buttons = new JButton[25];
    private final String [] names = {"AC", "DEL", "^", "=", "7", "8", "9", "x", "4", "5", "6", "-", "1", "2", "3", "+",
                              "(", ")", "0", "/"};
    /**
     * curNum : 存储当前的表达式
     * op : 操作码，当前curNum是结果，如果是按下其他按钮时要先清空
     * calculator : 计算器实例，用于计算表达式结果
     */
    private int op = 0;
    private StringBuilder curNum = new StringBuilder("0");
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
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("微软雅黑",Font.BOLD,40));
        label.setText(curNum.toString());
        panel.add(label);
    }

    public void setButtons(){
        for(int i = 0 ; i < names.length ; i++){
            buttons[i] = new JButton(names[i]);
        }

        int x = 20, y = 200;
        for (int i = 0; i < names.length; i++) {
            System.out.println(i);
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
        //AC按钮
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curNum = new StringBuilder("0");
                label.setText(curNum.toString());
            }
        });

        //退格按钮
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(curNum.length() > 0){
                    curNum.delete(curNum.length()-1, curNum.length());
                    if(curNum.length() == 0){
                        curNum.append("0");
                    }
                    setFontChange();
                    label.setText(curNum.toString());
                }

            }
        });

        //等于按钮
        buttons[3].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(curNum.toString());
                c.getExpression(curNum.toString());
                String res = c.getRes();
                curNum = new StringBuilder(res);
                op = 1;
                setFontChange();
                label.setText(curNum.toString());
            }
        });

        //其他按钮
        for (int i = 4; i < names.length; i++) {
            JButton button = buttons[i];
            String str = names[i];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pressOperatorButton(str);
                }
            });
        }
   }

    public void pressOperatorButton(String operator){
        if(op == 1){
            curNum = new StringBuilder("0");
            op = 0;
            label.setText(curNum.toString());
            return;
        }

        if(operator.equals("+") || operator.equals("-") || operator.equals("x") || operator.equals("/")){
            if(Character.isDigit(curNum.charAt(curNum.length()-1)) || curNum.charAt(curNum.length()-1) == ')'){
                if(operator.equals("x")) curNum.append("*");
                else curNum.append(operator);
            }
        }else{
            if (curNum.charAt(0) == '0' && curNum.length() == 1) {
                curNum.replace(0, 1, operator);
            } else {
                curNum.append(operator);
            }
        }

        setFontChange();

        label.setText(curNum.toString());
    }

    public void setFontChange(){
        if(curNum.length() > 14){
            label.setFont(new Font("微软雅黑",Font.BOLD,20));
        }else{
            label.setFont(new Font("微软雅黑",Font.BOLD,40));
        }
    }
}
