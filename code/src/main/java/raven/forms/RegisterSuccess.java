package raven.forms;

import javax.swing.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description 注册成功
 **/
public class RegisterSuccess extends JFrame {
    RegisterSuccess() {
        setTitle("注册成功");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("哟，老弟眼光不错啊~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }
}
