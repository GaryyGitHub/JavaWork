package raven.forms;

import Work.Frame;
import raven.main.Main;

import javax.swing.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description 登录成功
 **/
public class LoginSuccess extends JFrame {
    static public int gggggg = 0;

    LoginSuccess() {
        System.out.println("aaa\n");
        gggggg = 1;
        Main.f();
        setTitle("登录成功");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("你说的对，但这就是沸羊羊~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
        new Frame();
    }
}
