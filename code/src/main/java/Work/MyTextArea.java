package Work;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ikun568
 * @version 1.0
 * @Description 文本框设置
 * @Extends InsertContainer
 **/
public class MyTextArea extends InsertContainer {
    protected JTextArea textarea;
    protected JLabel background_label;
    int linenum;
    int columnnum;
    int length;

    public MyTextArea(Frame now_frame, int x, int y, int a, int b) {
        this.linenum = 1;
        this.columnnum = 1;
        this.length = 0;
        this.type = 1;
        this.x = x;
        this.y = y;
        this.width = a;
        this.height = b;
        this.belong_frame = now_frame;
        this.button_width = a;
        this.button_height = default_border_width;
        UndoManager undoManager = new UndoManager();
        textarea = new JTextArea();
        background_label = new JLabel();
        textarea.getDocument().addUndoableEditListener(undoMgr);
        borderLayout = new BorderLayout();
        panel = new JPanel(borderLayout);
        panel.setBorder(BorderFactory.createMatteBorder(border_width, border_width, border_width, border_width, Color.gray));
        textarea.getDocument().addUndoableEditListener(undoManager);
        textarea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //实现撤销操作的快捷键
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    if (undoManager.canUndo()) {
                        System.out.println("ss\n");
                        undoManager.undo();
                    }
                }
                //实现重做操作的快捷键
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
                    if (undoManager.canRedo()) {
                        undoManager.redo();
                    }
                }
            }
        });
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
        panel.add(background_label, BorderLayout.CENTER);
        panel.add(textarea, BorderLayout.CENTER);
        panel.setBounds(x, y, a, b);
        buttonpanel_north = new JPanel();
        buttonpanel_north.setBounds(x, y - this.button_height, a, button_height);
        buttonpanel_north.setBackground(focus_color);
        buttonpanel_south = new JPanel();
        buttonpanel_south.setBounds(x, y + height, a, button_height);
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
        if (false) {
            init();
            useClock();
        }
        Frame.TP_List.add(this);

        ID = Frame.top_pane_number;
        textarea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_west.getLocation();
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                panel.setLocation(MyTextArea.this.x, MyTextArea.this.y);
                buttonpanel_north.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_south.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.height);
                buttonpanel_west.setLocation(MyTextArea.this.x - default_border_width, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_east.setLocation(MyTextArea.this.x + MyTextArea.this.width, MyTextArea.this.y - MyTextArea.this.button_height);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_east.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_east.getLocation();
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                panel.setLocation(MyTextArea.this.x, MyTextArea.this.y);
                buttonpanel_north.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_south.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.height);
                buttonpanel_west.setLocation(MyTextArea.this.x - default_border_width, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_east.setLocation(MyTextArea.this.x + MyTextArea.this.width, MyTextArea.this.y - MyTextArea.this.button_height);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_south.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_south.getLocation();
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                panel.setLocation(MyTextArea.this.x, MyTextArea.this.y);
                buttonpanel_north.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_south.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.height);
                buttonpanel_west.setLocation(MyTextArea.this.x - default_border_width, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_east.setLocation(MyTextArea.this.x + MyTextArea.this.width, MyTextArea.this.y - MyTextArea.this.button_height);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_north.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseDragged(MouseEvent e) {//设置移动功能
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                panel.setLocation(MyTextArea.this.x, MyTextArea.this.y);
                buttonpanel_north.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_south.setLocation(MyTextArea.this.x, MyTextArea.this.y - MyTextArea.this.height);
                buttonpanel_west.setLocation(MyTextArea.this.x - default_border_width, MyTextArea.this.y - MyTextArea.this.button_height);
                buttonpanel_east.setLocation(MyTextArea.this.x + MyTextArea.this.width, MyTextArea.this.y - MyTextArea.this.button_height);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
//                Select.select_current(MyTextArea.this);
            }
        });
        buttonpanel_north.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - MyTextArea.this.x - default_border_width, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y);

            }
        }));
        buttonpanel_west.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - MyTextArea.this.x - default_border_width, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y);
            }
        }));
        buttonpanel_east.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - MyTextArea.this.x - default_border_width, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y);
            }
        }));
        buttonpanel_south.addMouseMotionListener((new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = panel.getLocation();
                Point q = buttonpanel_north.getLocation();
                panel.setLocation(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y);
                buttonpanel_north.setLocation(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_south.setLocation(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight());
                buttonpanel_west.setLocation(q.x + e.getX() - MyTextArea.this.x - default_border_width, q.y + e.getY() - MyTextArea.this.y);
                buttonpanel_east.setLocation(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y);
            }
        }));
        west_change_button_up.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                System.out.println(x);
                System.out.println(y);
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                System.out.println(String.valueOf(x) + ' ' + y);
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(MyTextArea.this);
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
                System.out.println(String.valueOf(p) + ' ' + e.getY() + ' ' + MyTextArea.this.y);
                panel.setBounds(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y, MyTextArea.this.panel.getWidth() - e.getX() + MyTextArea.this.x, panel.getHeight() - e.getY() + MyTextArea.this.y);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y, MyTextArea.this.buttonpanel_north.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight(), MyTextArea.this.buttonpanel_south.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - MyTextArea.this.x - buttonpanel_west.getWidth(), q.y + e.getY() - MyTextArea.this.y, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
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
                panel.setBounds(p.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y, MyTextArea.this.panel.getWidth() - e.getX() + MyTextArea.this.x, panel.getHeight() - e.getY() + MyTextArea.this.y);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - MyTextArea.this.x, q.y + e.getY() - MyTextArea.this.y, MyTextArea.this.buttonpanel_north.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - MyTextArea.this.x, p.y + e.getY() - MyTextArea.this.y + panel.getHeight(), MyTextArea.this.buttonpanel_south.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - MyTextArea.this.x - buttonpanel_west.getWidth(), q.y + e.getY() - MyTextArea.this.y, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), q.y + e.getY() - MyTextArea.this.y, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
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
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                System.out.println(String.valueOf(MyTextArea.this.x) + ' ' + MyTextArea.this.x);
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(MyTextArea.this);
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
                panel.setBounds(p.x + e.getX() - MyTextArea.this.x, p.y, MyTextArea.this.panel.getWidth() - e.getX() + MyTextArea.this.x, panel.getHeight() + e.getY() - MyTextArea.this.y);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - MyTextArea.this.x, p.y - button_height, MyTextArea.this.buttonpanel_north.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - MyTextArea.this.x, panel.getLocation().y + panel.getHeight(), MyTextArea.this.buttonpanel_south.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - MyTextArea.this.x - buttonpanel_west.getWidth(), p.y - button_height, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), p.y - button_height, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
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
                MyTextArea.this.x = e.getX();
                MyTextArea.this.y = e.getY();
                System.out.println(String.valueOf(MyTextArea.this.x) + ' ' + MyTextArea.this.x);
                Select.select_current(MyTextArea.this);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseMoved(e);
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Select.select_current(MyTextArea.this);
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
                panel.setBounds(p.x + e.getX() - MyTextArea.this.x, p.y, MyTextArea.this.panel.getWidth() - e.getX() + MyTextArea.this.x, panel.getHeight() + e.getY() - MyTextArea.this.y);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
                textlabel.setLocation((buttonpanel_north.getWidth() - 30) / 2, 0);
                buttonpanel_north.setBounds(q.x + e.getX() - MyTextArea.this.x, p.y - button_height, MyTextArea.this.buttonpanel_north.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_south.setBounds(q.x + e.getX() - MyTextArea.this.x, panel.getLocation().y + panel.getHeight(), MyTextArea.this.buttonpanel_south.getWidth() - e.getX() + MyTextArea.this.x, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(q.x + e.getX() - MyTextArea.this.x - buttonpanel_west.getWidth(), p.y - button_height, buttonpanel_west.getWidth(), panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(q.x + e.getX() - MyTextArea.this.x + panel.getWidth(), p.y - button_height, buttonpanel_east.getWidth(), panel.getHeight() + 2 * button_height);
                north_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                north_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - north_change_button_right.getWidth(), 0, north_change_button_left.getWidth(), north_change_button_left.getHeight());
                south_change_button_left.setBounds(0, 0, north_change_button_left.getWidth(), south_change_button_left.getHeight());
                south_change_button_right.setBounds(MyTextArea.this.buttonpanel_north.getWidth() - south_change_button_right.getWidth(), 0, south_change_button_right.getWidth(), south_change_button_right.getHeight());
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
                MyTextArea.this.pressx = e.getX();
                MyTextArea.this.pressy = e.getY();
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
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
                buttonpanel_north.setBounds(q.x, q.y + e.getY() - MyTextArea.this.pressy, buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                panel.setBounds(p.x, p.y + e.getY() - MyTextArea.this.pressy, panel.getWidth() + e.getX() - MyTextArea.this.pressx, panel.getHeight() - e.getY() + MyTextArea.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_south.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
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
                MyTextArea.this.pressx = e.getX();
                MyTextArea.this.pressy = e.getY();
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
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
                buttonpanel_north.setBounds(q.x, q.y + e.getY() - MyTextArea.this.pressy, buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                panel.setBounds(p.x, p.y + e.getY() - MyTextArea.this.pressy, panel.getWidth() + e.getX() - MyTextArea.this.pressx, panel.getHeight() - e.getY() + MyTextArea.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_south.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
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
                MyTextArea.this.pressx = e.getX();
                MyTextArea.this.pressy = e.getY();
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
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
                buttonpanel_north.setBounds(q.x, p.y - button_height, buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                panel.setBounds(p.x, p.y, panel.getWidth() + e.getX() - MyTextArea.this.pressx, panel.getHeight() + e.getY() - MyTextArea.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
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
                MyTextArea.this.pressx = e.getX();
                MyTextArea.this.pressy = e.getY();
                Select.select_current(MyTextArea.this);
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
                MyTextArea.this.x = panel.getX();
                MyTextArea.this.y = panel.getY();
                MyTextArea.this.width = panel.getWidth();
                MyTextArea.this.height = panel.getHeight();
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
                buttonpanel_north.setBounds(q.x, p.y - button_height, buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                panel.setBounds(p.x, p.y, panel.getWidth() + e.getX() - MyTextArea.this.pressx, panel.getHeight() + e.getY() - MyTextArea.this.pressy);
                buttonpanel_south.setBounds(p.x, panel.getLocation().y + panel.getHeight(), buttonpanel_north.getWidth() + e.getX() - MyTextArea.this.pressx, MyTextArea.this.button_height);
                buttonpanel_west.setBounds(p.x - button_height, panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                buttonpanel_east.setBounds(p.x + panel.getWidth(), panel.getLocation().y - button_height, button_height, panel.getHeight() + 2 * button_height);
                textarea.setBounds(border_width, border_width, panel.getWidth() - border_width * 2, panel.getHeight() - border_width * 2);
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

    public void set_Focus_Color() {//设置焦点时边框颜色
        buttonpanel_north.setBackground(focus_color);
        buttonpanel_west.setBackground(focus_color);
        buttonpanel_south.setBackground(focus_color);
        buttonpanel_east.setBackground(focus_color);
        panel.setBackground(focus_color);
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
        panel.setBackground(unfocus_color);
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

    public void init() {
        JToolBar toolState = null;
        toolState.setSize(belong_frame.getSize().width, 10);
        JLabel tool_labelline;
        JLabel tool_labelcolumn;
        JLabel tool_labellength;
        tool_labelline = new JLabel("                         第" + linenum + "行  ");
        tool_labelcolumn = new JLabel("  第" + columnnum + "列  ");
        tool_labellength = new JLabel("  共" + length + "字  ");
        toolState.add(tool_labelline);
        toolState.addSeparator();  //添加分割线
        toolState.add(tool_labelcolumn);
        toolState.addSeparator();  //添加分割线
        toolState.add(tool_labellength);
        toolState.addSeparator();  //添加分割线
        toolState.setFloatable(false);
        GregorianCalendar c = new GregorianCalendar();  //获取系统时间
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        JLabel tool_label_time = new JLabel("  时间:" + hour + ":" + min + ":" + second);  //此标签用来显示从时钟获取的时间
        toolState.add(tool_label_time);
        //设置工具栏默认不可见
        toolState.setVisible(false);
        //创建时钟实例(自己创建的一个内部时钟类)
        Clock clock = new Clock();
        //时钟线程开始
        clock.start();
        Date date = new Date();// 获得当前日期
        /*
         * 日期格式化SimpleDateFormat h小时，m分钟 y年份 M月份 d天数
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    public void useClock() {
        JMenuItem usClock = new JMenuItem();
        usClock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int defaultwidth = 8;
                int defaultheight = 8;
                MyTextArea new_textarea = new MyTextArea(belong_frame, pressx, pressy, defaultwidth, defaultheight);
                JTextArea textArea = new_textarea.textarea;
                textArea.addCaretListener(new CaretListener() {  //文本改变监听器
                    public void caretUpdate(CaretEvent e) {
                        JTextArea manuArea = (JTextArea) e.getSource();
                        try {
                            int caretpos = manuArea.getCaretPosition();
                            new_textarea.linenum = manuArea.getLineOfOffset(caretpos);
                            new_textarea.columnnum = caretpos - textArea.getLineStartOffset(new_textarea.linenum);
                            new_textarea.linenum += 1;
                            Frame.tool_labelline.setText("  第" + new_textarea.linenum + "行  ");
                            Frame.tool_labelcolumn.setText("  第" + new_textarea.columnnum + "列  ");
                            new_textarea.length = textArea.getText().length();
                            Frame.tool_labellength.setText("  一共" + new_textarea.length + " 字 ");
                        } catch (Exception ex) {
                        }
                    }
                });
            }
        });
    }
}
