package raven.forms;

import javax.swing.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description 登录失败
 **/
public class LoginFail extends JFrame {
    LoginFail() {
        setTitle("用户姓名不存在");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("沸羊羊呢？救一救阿！~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }

    LoginFail(int i) {
        setTitle("密码错误");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("沸羊羊呢？救一救阿！~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }
}
