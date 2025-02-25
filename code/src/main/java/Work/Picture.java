package Work;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ikun568
 * @version 1.0
 * @Description 图片
 * @Extends InsertContainer
 **/
public class Picture extends InsertContainer {
    String path;
    JLabel piclabel;

    public Picture(Frame now_frame, int x, int y, int a, int b, String filepath) {

        this.type = 2;
        this.x = x;
        this.y = y;
        this.width = a;
        this.height = b;
        this.border_width = 0;
        this.belong_frame = now_frame;
        this.button_width = a;
        this.button_height = default_border_width;
        if (filepath == null) {
            try {
                path = find_Picture_Path(this.belong_frame);
            } catch (IOException e) {
                System.out.println("打开图片失败！");
                throw new RuntimeException(e);
            }
        } else {
            path = filepath;
        }
        if (path == null) {
            return;
        }
        File file = new File(path);
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(path));
            a = Math.min(bufferedImage.getWidth(), 640);
            b = Math.min(bufferedImage.getHeight(), 480);
        } catch (IOException e) {
            System.out.println("文件打开失败！");
            return;
        }
        piclabel = new JLabel();
        piclabel.setIcon(SwingUtil.createAutoAdjustIcon(path, false));
        borderLayout = new BorderLayout();
        panel = new JPanel(borderLayout);
        panel.setBorder(BorderFactory.createMatteBorder(border_width, border_width, border_width, border_width, Color.gray));
        north_change_button_left = new JButton(" ");
        north_change_button_right = new JButton(" ");
        east_change_button_down = new JButton(" ");
        east_change_button_up = new JButton(" ");
        west_change_button_down = new JButton(" ");
        west_change_button_up = new JButton(" ");
        south_change_button_left = new JButton(" ");
        south_change_button_right = new JButton(" ");
        textlabel = new JLabel();
        north_change_button_right.setBackground(focus_color);
        north_change_button_left.setBackground(focus_color);
        south_change_button_right.setBackground(focus_color);
        south_change_button_left.setBackground(focus_color);
        east_change_button_up.setBackground(focus_color);
        east_change_button_down.setBackground(focus_color);
        west_change_button_up.setBackground(focus_color);
        west_change_button_down.setBackground(focus_color);
        textlabel.setBackground(focus_color);
        north_change_button_left.setBorder(BorderFactory.createEmptyBorder());
        north_change_button_right.setBorder(BorderFactory.createEmptyBorder());
        east_change_button_down.setBorder(BorderFactory.createEmptyBorder());
        east_change_button_up.setBorder(BorderFactory.createEmptyBorder());
        west_change_button_down.setBorder(BorderFactory.createEmptyBorder());
        west_change_button_up.setBorder(BorderFactory.createEmptyBorder());
        south_change_button_left.setBorder(BorderFactory.createEmptyBorder());
        south_change_button_right.setBorder(BorderFactory.createEmptyBorder());
        textlabel.setBorder(BorderFactory.createEmptyBorder());
        panel.add(piclabel, BorderLayout.CENTER);
        panel.setBounds(x, y, a, b);
        buttonpanel_north = new JPanel();
        buttonpanel_north.setBounds(x, y - this.button_height, a, button_height);
        buttonpanel_north.setBackground(focus_color);
        buttonpanel_south = new JPanel();
        buttonpanel_south.setBounds(x, y + b, a, button_height);
        buttonpanel_south.setBackground(focus_color);
        buttonpanel_east = new JPanel();
        buttonpanel_east.setBounds(x + width, y - this.button_height, button_height, b + 2 * this.button_height);
        buttonpanel_east.setBackground(focus_color);
        buttonpanel_west = new JPanel();
        buttonpanel_west.setBounds(x - button_height, y - button_height, button_height, b + 2 * this.button_height);
        buttonpanel_west.setBackground(focus_color);
        north_change_button_left.setSize(10, button_height);
        north_change_button_right.setSize(10, button_height);
        south_change_button_left.setSize(10, button_height);
        south_change_button_right.setSize(10, button_height);
        east_change_button_up.setSize(button_height, 10 + button_height);
        east_change_button_down.setSize(button_height, 10 + button_height);
        west_change_button_up.setSize(button_height, 10 + button_height);
        west_change_button_down.setSize(button_height, 10 + button_height);
        textlabel.setSize(40, 20);
        textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
        north_change_button_left.setLocation(0, 0);
        north_change_button_right.setLocation((buttonpanel_north.getWidth() - 10), 0);
        south_change_button_left.setLocation(0, 0);
        south_change_button_right.setLocation((buttonpanel_north.getWidth() - 10), 0);
        west_change_button_up.setLocation(0, 0);
        west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
        east_change_button_up.setLocation(0, 0);
        east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - 10 - button_height);
        buttonpanel_north.add(north_change_button_left);
        buttonpanel_north.add(textlabel);
        buttonpanel_north.add(north_change_button_right);
        buttonpanel_north.setLayout(null);
        buttonpanel_west.add(west_change_button_up);
        buttonpanel_west.add(west_change_button_down);
        buttonpanel_west.setLayout(null);
        buttonpanel_south.add(south_change_button_left);
        buttonpanel_south.add(south_change_button_right);
        buttonpanel_south.setLayout(null);
        buttonpanel_east.add(east_change_button_up);
        buttonpanel_east.add(east_change_button_down);
        buttonpanel_east.setLayout(null);
        //测试用
        System.out.println(north_change_button_left.getHeight());
        System.out.println(north_change_button_left.getWidth());
        System.out.println(north_change_button_left.getX());
        System.out.println(north_change_button_left.getY());
        System.out.println(buttonpanel_north.getX());
        System.out.println(buttonpanel_north.getY());
        this.belong_pane = now_frame.frame;
        Frame.top_pane_number += 1;
        this.belong_pane.getLayeredPane().add(buttonpanel_north, Integer.valueOf(Frame.top_pane_number), 0);
        this.belong_pane.getLayeredPane().add(buttonpanel_west, Integer.valueOf(Frame.top_pane_number), 1);
        this.belong_pane.getLayeredPane().add(buttonpanel_east, Integer.valueOf(Frame.top_pane_number), 2);
        this.belong_pane.getLayeredPane().add(buttonpanel_south, Integer.valueOf(Frame.top_pane_number), 3);
        this.belong_pane.getLayeredPane().add(panel, Integer.valueOf(Frame.top_pane_number), 4);
        Select.select_current(this);
        Frame.TP_List.add(this);
        ID = Frame.top_pane_number;
        piclabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        buttonpanel_west.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_west.getLocation();
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                panel.setLocation(Picture.this.x, Picture.this.y);
                buttonpanel_north.setLocation(Picture.this.x, Picture.this.y - Picture.this.button_height);
                buttonpanel_south.setLocation(Picture.this.x, Picture.this.y - Picture.this.height);
                buttonpanel_west.setLocation(Picture.this.x - default_border_width, Picture.this.y - Picture.this.button_height);
                buttonpanel_east.setLocation(Picture.this.x + Picture.this.width, Picture.this.y - Picture.this.button_height);
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {//进入移动控制框内时改变鼠标指针样式

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_east.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_east.getLocation();
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                panel.setLocation(Picture.this.x, Picture.this.y);
                buttonpanel_north.setLocation(Picture.this.x, Picture.this.y - Picture.this.button_height);
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {//进入移动控制框内时改变鼠标指针样式

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_south.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                panel.setLocation(Picture.this.x, Picture.this.y);
                buttonpanel_north.setLocation(Picture.this.x, Picture.this.y - Picture.this.button_height);
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {//进入移动控制框内时改变鼠标指针样式

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_north.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                panel.setLocation(Picture.this.x, Picture.this.y);
                buttonpanel_north.setLocation(Picture.this.x, Picture.this.y - Picture.this.button_height);
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {//进入移动控制框内时改变鼠标指针样式

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_north.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - Picture.this.x - default_border_width, q.y + e.getY() - Picture.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y);

            }
        }));
        buttonpanel_west.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - Picture.this.x - default_border_width, q.y + e.getY() - Picture.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y);
            }
        }));
        buttonpanel_east.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - Picture.this.x - default_border_width, q.y + e.getY() - Picture.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y);
            }
        }));
        buttonpanel_south.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - Picture.this.x - default_border_width, q.y + e.getY() - Picture.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y);
            }
        }));
        west_change_button_up.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                System.out.println(x);
                System.out.println(y);
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        north_change_button_left.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                System.out.println(String.valueOf(x) + ' ' + y);
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        north_change_button_left.addMouseMotionListener(new MouseMotionAdapter() {//设置缩放功能
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
//                System.out.println(p);
//                System.out.println(q);
                System.out.println(String.valueOf(p) + ' ' + e.getY() + ' ' + Picture.this.y);
                panel.setBounds(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y, Picture.this.panel.getWidth() - e.getX() + Picture.this.x, panel.getHeight() - e.getY() + Picture.this.y);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y, Picture.this.buttonpanel_north.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight(), Picture.this.buttonpanel_south.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - Picture.this.x - buttonpanel_west.getWidth(), q.y + e.getY() - Picture.this.y, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - west_change_button_down.getHeight());
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - east_change_button_down.getHeight());
                set_minsize();
            }
        });
        west_change_button_up.addMouseMotionListener(new MouseMotionAdapter() {//设置缩放功能
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                System.out.println(e.getX());
                System.out.println(e.getY());
                panel.setBounds(p.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y, Picture.this.panel.getWidth() - e.getX() + Picture.this.x, panel.getHeight() - e.getY() + Picture.this.y);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - Picture.this.x, q.y + e.getY() - Picture.this.y, Picture.this.buttonpanel_north.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - Picture.this.x, p.y + e.getY() - Picture.this.y + panel.getHeight(), Picture.this.buttonpanel_south.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - Picture.this.x - buttonpanel_west.getWidth(), q.y + e.getY() - Picture.this.y, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - Picture.this.x + panel.getWidth(), q.y + e.getY() - Picture.this.y, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - west_change_button_down.getHeight());
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - east_change_button_down.getHeight());
                set_minsize();
            }
        });
        south_change_button_left.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                System.out.println(String.valueOf(Picture.this.x) + ' ' + Picture.this.x);
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        south_change_button_left.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
//                System.out.println(p);
//                System.out.println(q);
//                System.out.println(String.valueOf(p.y) +' ' +  String.valueOf(e.getY()) + ' ' + String.valueOf(MyTextArea.this.y));
                panel.setBounds(p.x + e.getX() - Picture.this.x, p.y, Picture.this.panel.getWidth() - e.getX() + Picture.this.x, panel.getHeight() + e.getY() - Picture.this.y);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - Picture.this.x, p.y - button_height, Picture.this.buttonpanel_north.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - Picture.this.x, panel.getLocation().y + panel.getHeight(), Picture.this.buttonpanel_south.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - Picture.this.x - buttonpanel_west.getWidth(), p.y - button_height, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - Picture.this.x + panel.getWidth(), p.y - button_height, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - west_change_button_down.getHeight());
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - east_change_button_down.getHeight());
                set_minsize();
//                System.out.println(String.valueOf(p.y) +' ' +  String.valueOf(e.getY()) + ' ' + String.valueOf(MyTextArea.this.y));
            }
        });
        west_change_button_down.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.x = e.getX();
                Picture.this.y = e.getY();
                System.out.println(String.valueOf(Picture.this.x) + ' ' + Picture.this.x);
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_west.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        west_change_button_down.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
//                System.out.println(p);
//                System.out.println(q);
//                System.out.println(String.valueOf(p.y) +' ' +  String.valueOf(e.getY()) + ' ' + String.valueOf(MyTextArea.this.y));
                panel.setBounds(p.x + e.getX() - Picture.this.x, p.y, Picture.this.panel.getWidth() - e.getX() + Picture.this.x, panel.getHeight() + e.getY() - Picture.this.y);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - Picture.this.x, p.y - button_height, Picture.this.buttonpanel_north.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - Picture.this.x, panel.getLocation().y + panel.getHeight(), Picture.this.buttonpanel_south.getWidth() - e.getX() + Picture.this.x, Picture.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - Picture.this.x - buttonpanel_west.getWidth(), p.y - button_height, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - Picture.this.x + panel.getWidth(), p.y - button_height, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(Picture.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - west_change_button_down.getHeight());
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - east_change_button_down.getHeight());
                set_minsize();
//                System.out.println(String.valueOf(p.y) +' ' +  String.valueOf(e.getY()) + ' ' + String.valueOf(MyTextArea.this.y));
            }
        });
        north_change_button_right.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.pressx = e.getX();
                Picture.this.pressy = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_north.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        north_change_button_right.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
//                MyTextArea.this.y = e.getY();
//                MyTextArea.this.a = MyTextArea.this.button_width = e.getX()- MyTextArea.this.x;
//                MyTextArea.this.b = MyTextArea.this.b+(p.y-e.getY());
                buttonpanel_north.setBounds(q.x, q.y + e.getY() - Picture.this.pressy, buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                panel.setBounds(p.x, p.y + e.getY() - Picture.this.pressy, panel.getWidth() + e.getX() - Picture.this.pressx, panel.getHeight() - e.getY() + Picture.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_south.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                set_minsize();
            }
        });
        east_change_button_up.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.pressx = e.getX();
                Picture.this.pressy = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        east_change_button_up.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
//                MyTextArea.this.y = e.getY();
//                MyTextArea.this.a = MyTextArea.this.button_width = e.getX()- MyTextArea.this.x;
//                MyTextArea.this.b = MyTextArea.this.b+(p.y-e.getY());
                buttonpanel_north.setBounds(q.x, q.y + e.getY() - Picture.this.pressy, buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                panel.setBounds(p.x, p.y + e.getY() - Picture.this.pressy, panel.getWidth() + e.getX() - Picture.this.pressx, panel.getHeight() - e.getY() + Picture.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_south.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                set_minsize();
            }
        });
        south_change_button_right.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.pressx = e.getX();
                Picture.this.pressy = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_south.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });

        south_change_button_right.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
//                MyTextArea.this.y = e.getY();
//                MyTextArea.this.a = MyTextArea.this.button_width = e.getX()- MyTextArea.this.x;
//                MyTextArea.this.b = MyTextArea.this.b+(p.y-e.getY());
                buttonpanel_north.setBounds(q.x, p.y - button_height, buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                panel.setBounds(p.x, p.y, panel.getWidth() + e.getX() - Picture.this.pressx, panel.getHeight() + e.getY() - Picture.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                set_minsize();
            }
        });
        east_change_button_down.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Picture.this.pressx = e.getX();
                Picture.this.pressy = e.getY();
                Select.select_current(Picture.this);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));

            }

            @Override

            public void mouseExited(MouseEvent e) {

                buttonpanel_east.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

            }
        });
        east_change_button_down.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Picture.this.x = panel.getX();
                Picture.this.y = panel.getY();
                Picture.this.width = panel.getWidth();
                Picture.this.height = panel.getHeight();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
                buttonpanel_north.setBounds(q.x, p.y - button_height, buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                panel.setBounds(p.x, p.y, panel.getWidth() + e.getX() - Picture.this.pressx, panel.getHeight() + e.getY() - Picture.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_north.getWidth() + e.getX() - Picture.this.pressx, Picture.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                piclabel.setBounds(border_width, border_width, Picture.this.panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_right.setBounds(buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_right.getWidth(), north_change_button_right.getHeight());
                west_change_button_up.setLocation(0, 0);
                west_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                east_change_button_up.setLocation(0, 0);
                east_change_button_down.setLocation(0, buttonpanel_west.getHeight() - button_height * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                set_minsize();
            }
        });
        add_rightkeymenu(now_frame);
    }

    public static String find_Picture_Path(Frame frame) throws IOException {
        frame.openDia.setVisible(true);
        String dirPath = frame.openDia.getDirectory();
        String fileName = frame.openDia.getFile();
        if (dirPath == null || fileName == null) {
            System.out.println("打开图片失败！");
            return null;
        }
        return dirPath + fileName;
    }

    public void set_Focus_Color() {//设置焦点时边框颜色
        buttonpanel_north.setBackground(focus_color);
        buttonpanel_west.setBackground(focus_color);
        buttonpanel_south.setBackground(focus_color);
        buttonpanel_east.setBackground(focus_color);
        panel.setOpaque(false);
        north_change_button_right.setBackground(focus_color);
        north_change_button_left.setBackground(focus_color);
        south_change_button_right.setBackground(focus_color);
        south_change_button_left.setBackground(focus_color);
        east_change_button_up.setBackground(focus_color);
        east_change_button_down.setBackground(focus_color);
        west_change_button_up.setBackground(focus_color);
        west_change_button_down.setBackground(focus_color);
        textlabel.setBackground(focus_color);
        buttonpanel_south.setVisible(true);
        buttonpanel_east.setVisible(true);
        buttonpanel_north.setVisible(true);
        buttonpanel_west.setVisible(true);
    }

    public void set_Unfocus_Color() {//设置非焦点时边框颜色
        buttonpanel_north.setBackground(unfocus_color);
        buttonpanel_west.setBackground(unfocus_color);
        buttonpanel_south.setBackground(unfocus_color);
        buttonpanel_east.setBackground(unfocus_color);
//        panel.setBackground(unfocus_color);
        north_change_button_right.setBackground(unfocus_color);
        north_change_button_left.setBackground(unfocus_color);
        south_change_button_right.setBackground(unfocus_color);
        south_change_button_left.setBackground(unfocus_color);
        east_change_button_up.setBackground(unfocus_color);
        east_change_button_down.setBackground(unfocus_color);
        west_change_button_up.setBackground(unfocus_color);
        west_change_button_down.setBackground(unfocus_color);
        textlabel.setBackground(unfocus_color);
        buttonpanel_south.setVisible(false);
        buttonpanel_east.setVisible(false);
        buttonpanel_north.setVisible(false);
        buttonpanel_west.setVisible(false);
    }

}
