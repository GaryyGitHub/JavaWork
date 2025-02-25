package raven.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ikun568
 * @version 1.0
 * @Description 注册
 **/
public class Register extends JFrame {
    Register() {
        setTitle("注册界面");

        setBounds(620, 420, 300, 200);
        JLabel l4 = new JLabel("新建账号");
        JLabel l5 = new JLabel("设置密码");
        JLabel l6 = new JLabel("确认密码");
        JTextField t4 = new JTextField(12);
        JPasswordField pass5 = new JPasswordField(12);
        JPasswordField pass6 = new JPasswordField(12);
        pass5.setEchoChar('*');
        pass6.setEchoChar('*');
        setLayout(new GridLayout(4, 1));
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();

        p5.add(l4);
        p5.add(t4);
        p6.add(l5);
        p6.add(pass5);
        p7.add(l6);
        p7.add(pass6);
        add(p5);
        add(p6);
        add(p7);

        JButton jb = new JButton("确认注册");
        p8.add(jb);
        add(p8);

        setVisible(true);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == jb) {
                    String name;
                    String pwd_old;
                    String pwd_new;
                    name = t4.getText();
                    pwd_old = new String(pass5.getPassword());
                    pwd_new = new String(pass6.getPassword());
                    if (Tools.name_judge(name) == 0 || Database.searchUserInDatabase_byName(name) == 1) {
                        System.out.println("ss\n");
                        new RegisterFail();
                    } else if (Tools.pwd_judge(pwd_old) == 0) {
                        new RegisterFail(1);
                    } else if (Tools.pwd_check(pwd_new, pwd_old) == 0) {
                        new RegisterFail(1, 2);
                    } else {
                        User user = new User();
                        user.setName(name);
                        user.setPwd(pwd_new);
                        Database.InsertUser(user);
                        new RegisterSuccess();
                    }
                }
            }
        });
    }
}
