package atm.view;

import atm.controller.OperationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by myxom on 24.09.2016.
 */
public class AtmWindow extends JFrame implements Presentable {

    JTextArea screen;
    JTextArea input;

    public AtmWindow(OperationManager controller) {
        setSize(800, 800);
        setTitle("ATM");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel mainWindow = new JPanel();
        mainWindow.setLayout(new GridLayout(4, 1));

        screen = new JTextArea(7, 70);
        screen.setLineWrap(false);
        screen.setEditable(false);
        mainWindow.add(screen);

        JButton cardBtn = new JButton("Вставить карточку");
        cardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                controller.insertCard();
                controller.cardBtn();
            }
        });
        mainWindow.add(cardBtn);

        input = new JTextArea(1, 10);
        input.setLineWrap(false);
        input.setEditable(false);
        mainWindow.add(input);



        JPanel buttonField = new JPanel();
        buttonField.setLayout(new GridLayout(4, 3));

        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        JButton btn0 = new JButton("0");
        JButton btnOk = new JButton("OK");
        JButton btnBack = new JButton("<<");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("1");
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("2");
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("3");
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("4");
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("5");
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("6");
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("7");
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("8");
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("9");
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.append("0");
            }
        });
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(input.getText().length() > 0){
                    input.setText(input.getText().substring(0,input.getText().length()-1));
                } else {
                    controller.backBtn();
                }
            }
        });
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.okBtn();
            }
        });

        buttonField.add(btn1);
        buttonField.add(btn2);
        buttonField.add(btn3);
        buttonField.add(btn4);
        buttonField.add(btn5);
        buttonField.add(btn6);
        buttonField.add(btn7);
        buttonField.add(btn8);
        buttonField.add(btn9);
        buttonField.add(btnBack);
        buttonField.add(btn0);
        buttonField.add(btnOk);

        mainWindow.add(buttonField);

        add(mainWindow);

        setVisible(true);
    }


    @Override
    public void showScreen(String msg, boolean clear) {
        System.err.println(msg);
        StringBuilder str = new StringBuilder("=================================================\n");
        for (int i = 1; i < (50 - msg.length()) / 2 - 1; i++) {
            str.append('=');
        }
        str.append(' ' + msg.toUpperCase() + ' ');
        for (int i = (50 - msg.length()) / 2 + msg.length() + 1; i < 50; i++) {
            str.append('=');
        }
        str.append("\n");
        str.append("=================================================\n");
        if(clear) {
            screen.setText(str.toString());
        } else {
            screen.append(str.toString());
        }
//        screen.setForeground(Color.black);
    }

    @Override
    public void showError(String msg, boolean clear) {
        System.err.println(msg);
        StringBuilder str = new StringBuilder("=================================================\n");
        for (int i = 1; i < (50 - msg.length()) / 2 - 1; i++) {
            str.append('=');
        }
        str.append(' ' + msg.toUpperCase() + ' ');
        for (int i = (50 - msg.length()) / 2 + msg.length() + 1; i < 50; i++) {
            str.append('=');
        }
        str.append("\n");
        str.append("=================================================\n");
        if(clear) {
            screen.setText(str.toString());
        } else {
            screen.append(str.toString());
        }
//        screen.setForeground(Color.RED);
    }

    public void addToScreen(String str) {
        screen.append(str);
    }

    public String getInput() {
        return screen.getText();
    }
}
