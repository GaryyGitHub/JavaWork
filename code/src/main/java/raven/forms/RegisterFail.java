package raven.forms;

import javax.swing.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description 注册失败
 **/
public class RegisterFail extends JFrame {
    RegisterFail() {
        setTitle("注册失败");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("用户昵称应为3~12位大小写字母~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }

    RegisterFail(int i, int j, int k) {
        setTitle("注册失败");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("用户昵称已存在~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }

    RegisterFail(int i) {
        setTitle("注册失败");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("用户密码应为6~12位的数字、大小写字母~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }

    RegisterFail(int i, int j) {
        setTitle("注册失败");
        setBounds(400, 400, 400, 400);
        JLabel jLabel1 = new JLabel("前后密码不一致~");
        JPanel p = new JPanel();
        p.add(jLabel1);
        add(p);
        setVisible(true);
    }
}
