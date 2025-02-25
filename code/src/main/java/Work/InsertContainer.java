package Work;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description 插入文本框/图片，是MyTextArea和Picture的父类
 **/
public class InsertContainer {
    public static int default_border_width = 5;
    public UndoManager undoMgr = new UndoManager();
    protected int border_width = 2;
    int ID;
    JPanel panel;
    JPanel buttonpanel_north;
    JPanel buttonpanel_south;
    JPanel buttonpanel_east;
    JPanel buttonpanel_west;
    JButton north_change_button_left;
    JButton north_change_button_right;
    JButton east_change_button_up;
    JButton east_change_button_down;
    JButton south_change_button_left;
    JButton south_change_button_right;
    JButton west_change_button_up;
    JButton west_change_button_down;
    JLabel textlabel;
    BorderLayout borderLayout;
    JFrame belong_pane;//注意是Jframe，因为命名容易混淆就不改了
    Color focus_color = Color.CYAN;
    Color unfocus_color = Color.gray;
    Frame belong_frame;
    Color border_color = Color.gray;
    int x;
    int y;
    int width;
    int height;
    int button_height;
    int button_width;
    int pressx, pressy;
    int type;//1表示文本框，2表示图片

    @Override
    public String toString() {
        return panel.getX() + "  " + panel.getY() + "  " + panel.getWidth() + "  " + panel.getHeight() + "  ";
    }

    public boolean set_minsize() {
        int minlength = 50;
        boolean flag = true;
        if (panel.getHeight() < minlength) {
            panel.setBounds(panel.getX(), panel.getY(), panel.getWidth(), minlength + 1);
            buttonpanel_north.setBounds(panel.getX(), panel.getY() - button_height, panel.getWidth(), button_height);
            buttonpanel_south.setBounds(panel.getX(), panel.getY() + panel.getHeight(), panel.getWidth(), button_height);
            buttonpanel_west.setBounds(panel.getX() - button_height, buttonpanel_north.getY(), button_height, panel.getHeight() + 2 * button_height);
            buttonpanel_east.setBounds(panel.getX() + panel.getWidth(), buttonpanel_north.getY(), button_height, panel.getHeight() + 2 * button_height);
            north_change_button_left.setLocation(0, 0);
            north_change_button_right.setLocation(buttonpanel_north.getWidth() - 10, 0);
            south_change_button_left.setLocation(0, 0);
            south_change_button_right.setLocation(buttonpanel_north.getWidth() - 10, 0);
            west_change_button_up.setLocation(0, 0);
            west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
            east_change_button_up.setLocation(0, 0);
            east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
            if (type == 1) {
                MyTextArea now = (MyTextArea) this;
                now.textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
            } else {
                Picture pic = (Picture) this;
                pic.piclabel.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
            }
            flag = false;
        }
        if (panel.getWidth() < minlength) {
            panel.setBounds(panel.getX(), panel.getY(), minlength + 1, panel.getHeight());
            buttonpanel_north.setBounds(panel.getX(), panel.getY() - button_height, panel.getWidth(), button_height);
            buttonpanel_south.setBounds(panel.getX(), panel.getY() + panel.getHeight(), panel.getWidth(), button_height);
            buttonpanel_west.setBounds(panel.getX() - button_height, buttonpanel_north.getY(), button_height, panel.getHeight() + 2 * button_height);
            buttonpanel_east.setBounds(panel.getX() + panel.getWidth(), buttonpanel_north.getY(), button_height, panel.getHeight() + 2 * button_height);
            north_change_button_left.setLocation(0, 0);
            north_change_button_right.setLocation(buttonpanel_north.getWidth() - 10, 0);
            south_change_button_left.setLocation(0, 0);
            south_change_button_right.setLocation(buttonpanel_north.getWidth() - 10, 0);
            west_change_button_up.setLocation(0, 0);
            west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
            east_change_button_up.setLocation(0, 0);
            east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
            if (type == 1) {
                MyTextArea now = (MyTextArea) this;
                now.textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
            } else {
                Picture pic = (Picture) this;
                pic.piclabel.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
            }
            flag = false;
        }
        return flag;
    }


    public void add_rightkeymenu(Frame frame) {
        buttonpanel_south.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        buttonpanel_north.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        buttonpanel_east.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        buttonpanel_west.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        panel.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        if (type == 1) {
            MyTextArea cur = (MyTextArea) this;
            cur.textarea.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        } else {
            Picture pic = (Picture) this;
            pic.piclabel.addMouseListener(new Frame.mouselistener(frame.rightkey_menu));
        }
    }
}
