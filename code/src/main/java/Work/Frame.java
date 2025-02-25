package Work;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author ikun568
 * @version 1.0
 * @Description
 * @Extends JFrame
 **/

public class Frame extends JFrame {
    static int linenum = 1;
    static int columnnum = 1;
    static int length = 0;
    static JLabel tool_labelline;
    static JLabel tool_labelcolumn;
    static JLabel tool_labellength;
    static int top_pane_number = -1;
    static JTextArea main_textarea;
    static JLabel tool_label_time;
    static int pressx;
    static int pressy;
    static ArrayList<InsertContainer> TP_List;
    public String backgroundPicPath = null;
    JFrame frame;
    JMenuBar menuBar;//创建一个新的菜单栏
    JMenu file, edit, help, check, set;//创建菜单栏中的一级菜单
    JMenuItem open_file, create_file, save_file, save_another_way, exit;//创建一级菜单中的二级菜单
    JMenuItem withdraw, reoperate, search, searchNext, searchLast, replace, select_all;
    JMenuItem withdraw1, reoperate1, delete1, select_all1;
    JMenuItem new_textarea, new_image;
    JCheckBoxMenuItem item_of_Statement;
    JMenuItem other_set, background_set, background_pic_set, font_set, font_color_set;
    JMenuItem show_statusbar;
    JMenuItem remove, copy, paste, cut;
    JMenuItem findHelp, about; //帮助
    JMenuItem time_date;
    JCheckBoxMenuItem auto_shift_row;
    JMenu scale;//缩放
    JTextField textfield;// 查找输入框
    JRadioButton up;// 向上
    JRadioButton down;// 向下单选按钮
    JCheckBox check1;// 区分大小写
    JCheckBox check2;// 循环
    JMenuItem layup, laydown, laytop, laybottom;
    JToolBar toolState;
    JPanel main_panel = new JPanel();
    ArrayList<InsertContainer> top, middle, bottom;
    FileDialog openDia;
    FileDialog saveDia;
    JPanel hold_panel;
    JScrollPane pane;
    MenuMethod menuMethod;
    JPopupMenu rightkey_menu;
    int defaultwidth, defaultheight;
    UndoManager undoManager = new UndoManager();
    private File f;

    /**
     * @return null
     * @Description 构造函数
     * @author ikun568
     **/
    public Frame() {
        TP_List = new ArrayList<>();
        defaultheight = 200;
        defaultwidth = 200;
        this.frame = new JFrame();
        frame.setTitle("新建笔记");//窗体标题
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//窗体退出方式(不直接退出）
        frame.setSize(900, 1000);//窗体大小
        createMenu();
        frame.setVisible(true);//设置是否显示窗体
        frame.setFocusable(true);
        frame.setJMenuBar(menuBar);
        //实例化背景图片（暂时不做）
        this.menuMethod = new MenuMethod();
    }

    /**
     * @Description 由于不同的Menu和 MenuItem实例还要实现不同的功能，所以没有add方法只能一个个加
     * @author ikun568
     **/
    private void createMenu() {
        menuBar = new JMenuBar();
        file = new JMenu("文件");
        menuBar.add(file);
        edit = new JMenu("编辑");
        menuBar.add(edit);
        check = new JMenu("查看");
        menuBar.add(check);
        help = new JMenu("帮助");
        menuBar.add(help);
        set = new JMenu("设置");
        menuBar.add(set);
        toolState = new JToolBar();
        menuBar.add(toolState);
        scale = new JMenu("缩放");
        open_file = new JMenuItem("打开");
        create_file = new JMenuItem("新建");
        save_file = new JMenuItem("保存");
        save_another_way = new JMenuItem("另存为");
        withdraw = new JMenuItem("撤销");
        reoperate = new JMenuItem("重做");
        search = new JMenuItem("查找");
        searchNext = new JMenuItem("查找下一个");
        searchLast = new JMenuItem("查找上一个");
        replace = new JMenuItem("替换");
        select_all = new JMenuItem("全选");
        exit = new JMenuItem("退出");
        new_textarea = new JMenuItem("新建文本框");
        new_image = new JMenuItem("插入图片");
        withdraw1 = new JMenuItem("撤销");
        reoperate1 = new JMenuItem("重做");
        delete1 = new JMenuItem("删除");
        select_all1 = new JMenuItem("全选");
        auto_shift_row = new JCheckBoxMenuItem("自动换行", false);
        background_set = new JMenuItem("背景颜色");
        background_pic_set = new JMenuItem("背景图片设置");
        font_set = new JMenuItem("字体设置");
        font_color_set = new JMenuItem("字体颜色");
        other_set = new JMenuItem("更多设置");
        layup = new JMenuItem("上移一层");
        laydown = new JMenuItem("下移一层");
        laybottom = new JMenuItem("置于底层");
        laytop = new JMenuItem("置于顶层");
        remove = new JMenuItem("删除");
        check1 = new JCheckBox("区分大小写(C)");// 复选框
        check2 = new JCheckBox("循环(R)");// 复选框
        findHelp = new JMenuItem("查看帮助");
        about = new JMenuItem("关于记事本");
        time_date = new JMenuItem("时间/日期");
        copy = new JMenuItem("复制");
        cut = new JMenuItem("剪切");
        paste = new JMenuItem("粘贴");
        item_of_Statement = new JCheckBoxMenuItem("状态栏(S)");

        check2.setSelected(true);//默认是选中的

        rightkey_menu = new JPopupMenu();
        frame.add(rightkey_menu);
        file.add(open_file);
        file.add(create_file);
        file.add(save_file);
        file.add(save_another_way);
        file.add(exit);
        edit.add(withdraw);
        edit.add(reoperate);
        edit.add(search);
        edit.add(searchNext);
        edit.add(searchLast);
        edit.add(replace);
        edit.add(select_all);
        rightkey_menu.add(select_all1);
        rightkey_menu.add(copy);
        rightkey_menu.add(paste);
        rightkey_menu.add(cut);
        rightkey_menu.add(withdraw1);
        rightkey_menu.add(reoperate1);
        rightkey_menu.add(remove);
        rightkey_menu.add(new_textarea);
        rightkey_menu.add(new_image);
        rightkey_menu.add(layup);
        rightkey_menu.add(laydown);
//        rightkey_menu.add(laybottom);
//        rightkey_menu.add(laytop);
        set.add(auto_shift_row);
        set.add(background_set);
        set.add(background_pic_set);
        set.add(font_set);
        set.add(font_color_set);
        set.add(other_set);
        help.add(findHelp);
        help.add(about);
        check.add(scale);
        check.add(item_of_Statement);
        openDia = new FileDialog(frame, "我的打开", FileDialog.LOAD);
        saveDia = new FileDialog(frame, "我的保存", FileDialog.SAVE);

        main_textarea = new JTextArea();
//        main_textarea.setLineWrap(true);
        hold_panel = new JPanel();
        pane = new JScrollPane(main_textarea);
        main_panel.setBounds(500, 500, 200, 200);
        hold_panel.setLayout(new BorderLayout());
        hold_panel.add(pane);
        hold_panel.setBackground(Color.white);
//        hold_panel.setBackground(Color.white);
        frame.getContentPane().add(hold_panel, BorderLayout.CENTER);
        main_textarea.getDocument().addUndoableEditListener(undoManager);
        main_textarea.addKeyListener(new KeyAdapter() {
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
        top = new ArrayList<>();
        bottom = new ArrayList<>();
        middle = new ArrayList<>();

        all_menu_event();
    }

    public void init() {
        toolState.setSize(frame.getSize().width, 10);
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
        tool_label_time = new JLabel("  时间:" + hour + ":" + min + ":" + second);  //此标签用来显示从时钟获取的时间
        toolState.add(tool_label_time);
        //设置工具栏默认不可见
        toolState.setVisible(false);
        //创建时钟实例(自己创建的一个内部时钟类)
        Frame.Clock clock = new Frame.Clock();
        //时钟线程开始
        clock.start();
        Date date = new Date();// 获得当前日期
        /*
         * 日期格式化SimpleDateFormat h小时，m分钟 y年份 M月份 d天数
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    /**
     * @Description 新建
     * 下面直至all_menu_event以前都是文件操作方法和监听事件，
     * 因为键盘监听和鼠标监听都需要触发，所以单独提出来避免代码冗余，
     * 命名方式为取其对应按钮的单词首字母组合,以后的修改也就在此中修改。
     * @author ikun568
     **/
    private void cf() {
        if (if_changed()) {
            switch (JOptionPane.showConfirmDialog(null, "当前文件尚未保存，是否保存？", "确认", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.YES_OPTION:
                    //同样需要用自行编写扩展后的方法来代替。
                    try {
                        menuMethod.save_File(f, Frame.this);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return;
            }
            frame.dispose();
            new Frame();
        }
    }

    /**
     * @Description 保持
     * @author ikun568
     **/
    private void sf() {
        try {
            if (f == null) {
                f = menuMethod.find_New_File_Path(Frame.this);
            }
            menuMethod.save_File(f, Frame.this);//这个是以后扩展做完后应当使用的保存文件的方法
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //打印用户选择的文件路径和名称
        System.out.println("用户选择的保存文件路径:" + saveDia.getDirectory());
        System.out.println("用户选择的保存文件名称:" + saveDia.getFile());
    }

    /**
     * @Description 打开
     * @author ikun568
     **/
    private void of() {
        if (f != null && if_changed()) {
            //需要先验证当前是否有文件是否已经打开，如果是，需要先询问是否保存
            switch (JOptionPane.showConfirmDialog(null, "当前文件可能未保存，是否保存？", "确认", JOptionPane.YES_NO_CANCEL_OPTION)) {
                case JOptionPane.YES_OPTION:
                    //同样需要用自行编写扩展后的方法来代替。
                    try {
                        menuMethod.save_File(f, Frame.this);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    return;
            }
        } else if (if_changed()) {

        } else if (main_textarea.getText().length() != 0) {
            if (!menuMethod.ask_If_Need_Newsave(f, Frame.this)) {
                return;
            }
        }
        openDia.setVisible(true);
        String open_directory = openDia.getDirectory();
        String open_filename = openDia.getFile();
        if (open_filename == null || open_directory == null) {
            JOptionPane.showMessageDialog(null, "打开失败，原因：未指定路径", "error", JOptionPane.YES_OPTION);
        } else {
            //打印用户选择的文件路径和名称
            System.out.println("用户选择的打开文件路径:" + open_directory);
            System.out.println("用户选择的打开文件名称:" + open_filename);
            //文件操作
            String open_path = open_directory + open_filename;//现在暂时用下面这个代码顶替一下
            init(open_filename);
            menuMethod.open_File(new File(open_path), this);

        }
    }

    /**
     * @Description 另存为
     * @author ikun568
     **/
    private void saw() {
        try {
            if (f == null) {
                f = menuMethod.find_New_File_Path(Frame.this);
            } else {
                menuMethod.find_New_File_Path(Frame.this);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @Description 退出
     * @author ikun568
     **/
    private void exit() {
        if (f == null) {
            if (!menuMethod.ask_If_Need_Newsave(f, Frame.this))
                return;
        } else if (if_changed()) {
            if (!menuMethod.ask_If_Need_Resave(f, Frame.this))
                return;
        }
        switch (JOptionPane.showConfirmDialog(null, "确认退出？", "exit", JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.YES_OPTION:
                System.exit(1);
                break;
            case JOptionPane.NO_OPTION:
        }
    }

    /**
     * @Description 查找
     * @author ikun568
     **/
    private void Find() {
        final JDialog dislog = new JDialog(this, "查找", false);// 创建一个窗体
        Container con = dislog.getContentPane();// 将窗体转化为容器
        con.setLayout(null);// 取消布局
        final JLabel j1 = new JLabel("查找内容(N)：");
        textfield = new JTextField(18);// 文本框
        final JButton b1 = new JButton("查找下一个(F)");
        final JButton b2 = new JButton("取消");
        b1.setBounds(330, 10, 115, 25);
        b1.setContentAreaFilled(false);
        b2.setBounds(330, 40, 115, 25);
        b2.setContentAreaFilled(false);

        /*
         * 加入到按钮组中的按钮只能选中其一，其他的咋会关闭
         */
        up = new JRadioButton("向上(U)");
        down = new JRadioButton("向下(D)");
        final ButtonGroup group = new ButtonGroup();// 按钮组
        group.add(up);
        group.add(down);
        down.setSelected(true);// 默认选中向下
        check1.setBounds(0, 100, 140, 30);
        check1.setFont(new Font("宋体", Font.ITALIC, 14));// 设置字体
        check2.setBounds(0, 130, 140, 30);
        check2.setFont(new Font("宋体", Font.ITALIC, 14));
        j1.setFont(new Font("宋体", Font.ITALIC, 14));

        /*
         * 设置快捷键
         */
        up.setMnemonic('U');
        down.setMnemonic('D');
        b1.setMnemonic('F');
        check1.setMnemonic('C');
        check2.setMnemonic('R');

        JPanel p1 = new JPanel();// j1,textfield
        JPanel p4 = new JPanel();// 放up down
        // 设置面板p1
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));// 流体布局，左对齐
        p1.setLocation(0, 10);
        p1.setSize(330, 60);
        p1.add(j1);
        p1.add(textfield);

        /*
         * 设置d4组件的边框; BorderFactory.createTitledBorder(String title)创建一个新标题边框，
         * 使用默认边框（浮雕化）、默认文本位置（位于顶线上）、默认调整 (leading) 以及由当前外观确定的默认字体和文本颜色，并指定了标题文本。
         */
        p4.setBorder(BorderFactory.createTitledBorder("方向"));
        p4.setBounds(150, 80, 180, 70);
        up.setFont(new Font("宋体", Font.ITALIC, 14));
        down.setFont(new Font("宋体", Font.ITALIC, 14));
        p4.add(up);
        p4.add(down);

        con.add(p1);
        con.add(b1);
        con.add(b2);
        con.add(check1);
        con.add(check2);
        con.add(p4);
        dislog.setBounds(200, 200, 460, 220);
        dislog.setResizable(false);// 设置窗体大小不可改变
        dislog.setVisible(true);// 显示窗体
        dislog.setAlwaysOnTop(true);
        upFind_downFind(null);
        b1.addActionListener(new ActionListener() {// 查找下一个按钮

            public void actionPerformed(ActionEvent e) {
                JTextArea textArea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textArea = myTextArea.textarea;
                } else textArea = main_textarea;
                StringBuilder areastr = new StringBuilder(textArea.getText());// 获取文本区文本
                String fieldstr = textfield.getText();// 获取文本框文本
                String toupparea = areastr.toString().toUpperCase();// 转为大写，用做区分大小写判断方便查找
                String touppfield = fieldstr.toUpperCase();
                String A;// 用做查找的文本域内容
                String B;// 用作查找的文本框内容
                if (check1.isSelected()) {// 区分大小写
                    A = areastr.toString();
                    B = fieldstr;
                } else {// 全部换为大写
                    A = toupparea;
                    B = touppfield;
                }
                int n = textArea.getCaretPosition();
                // 获取光标的位置
                int m = 0;
                if (up.isSelected()) {// 向上查找
                    if (textArea.getSelectedText() == null) {
                        m = A.lastIndexOf(B, n - 1);
                    } else {
                        m = A.lastIndexOf(B, n - textfield.getText().length() - 1);
                    }
                    if (m != -1) {
                        textArea.setCaretPosition(m);
                        textArea.select(m, m + textfield.getText().length());
                    } else {
                        if (check2.isSelected()) {// 如果循环
                            m = A.lastIndexOf(B);// 从后面开始找
                            if (m != -1) {
                                textArea.setCaretPosition(m);
                                textArea.select(m, m + textfield.getText().length());
                            } else {
                                JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                } else {// 向下查找
                    m = A.indexOf(B, n);
                    if (m != -1) {
                        textArea.setCaretPosition(m + textfield.getText().length());
                        textArea.select(m, m + textfield.getText().length());
                    } else {
                        if (check2.isSelected()) {// 如果循环
                            m = A.indexOf(B);// 从头开始找
                            if (m != -1) {
                                textArea.setCaretPosition(m + textfield.getText().length());
                                textArea.select(m, m + textfield.getText().length());
                            } else {
                                JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        b2.addActionListener(new ActionListener() {// 取消

            public void actionPerformed(ActionEvent e) {
                dislog.dispose();// 销毁窗体
            }
        });

    }

    /**
     * @Description 替换
     * @author ikun568
     **/
    private void Replace(ActionEvent e) {
        int end;// 结尾的位置
        int start;// 开始的位置
        JDialog d = new JDialog(this, "替换", false);
        Container c = d.getContentPane();
        d.setSize(350, 160);
        d.setLocation(200, 200);
        d.setResizable(false);
        JPanel jpanel = new JPanel();
        final JLabel label1 = new JLabel("查找内容(N)");
        final JTextField fiel = new JTextField(10);// 查找框
        final JButton but1 = new JButton("查找 下一 个(F)");// 对按钮添加事件
        final JLabel label2 = new JLabel("替   换  为(P)");
        final JTextField fiel2 = new JTextField(10);// 替换框
        final JButton but2 = new JButton("替     换    (   R   )");
        final JButton but3 = new JButton("取消");
        final JCheckBox box = new JCheckBox("区分大小写(C)");

        but1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea textArea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textArea = myTextArea.textarea;
                } else textArea = main_textarea;
                StringBuilder str1 = new StringBuilder(textArea.getText());// 获取文本内容
                String str2 = fiel.getText();// 获取查找文本的内容
                String str3 = str1.toString().toUpperCase();// 转为大写
                String str4 = str2.toUpperCase();
                String A, B;
                if (box.isSelected()) {
                    A = str1.toString();
                    B = str2;
                } else {
                    A = str3;
                    B = str4;
                }
                int n = textArea.getCaretPosition();// 获取光标所处位置
                int m = A.indexOf(B, n);// 得到找到文本的位置。或者没有为-1
                if (m != -1) {
                    textArea.setCaretPosition(m + str2.length());
                    textArea.select(m, m + str2.length());
                } else {
                    m = A.indexOf(B);// 从头开始找
                    if (m != -1) {
                        textArea.setCaretPosition(m + str2.length());
                        textArea.select(m, m + str2.length());
                    } else {
                        JOptionPane.showMessageDialog(null, "找不到 “" + str2 + "”");
                    }
                }
            }
        });
        but2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textArea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textArea = myTextArea.textarea;
                } else textArea = main_textarea;
                String str1 = fiel.getText();
                String str2 = fiel2.getText();
                if (textArea.getSelectedText() != null) {// 如果有选中的
                    textArea.replaceRange(str2, textArea.getSelectionStart(), textArea.getSelectionEnd());
                } else {
                    JOptionPane.showMessageDialog(null, "选择内容不能为空！");
                }
            }
        });
        but3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                d.dispose();
            }
        });

        jpanel.add(label1);
        jpanel.add(fiel);
        jpanel.add(but1);
        jpanel.add(label2);
        jpanel.add(fiel2);
        jpanel.add(but2);
        jpanel.add(box);
        jpanel.add(but3);
        c.add(jpanel);
        d.setVisible(true);
        d.setAlwaysOnTop(true);
    }

    public boolean if_changed() {
        return true;
    }//判断当前文件是否已经改变？

    /**
     * @Description 所有监听事件
     * @author ikun568
     **/
    private void all_menu_event() {
        //右键菜单弹出
        frame.addMouseListener(new mouselistener(rightkey_menu));
        main_textarea.addMouseListener(new mouselistener(rightkey_menu));

        //快捷键设置
        withdraw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        withdraw1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        reoperate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
        reoperate1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
        replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
        create_file.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
        open_file.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Event.CTRL_MASK));
        save_file.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        save_another_way.setAccelerator(KeyStroke.getKeyStroke("F12"));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));
        search.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
        searchNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        searchLast.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F3, KeyEvent.SHIFT_MASK));

        main_textarea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InsertContainer insertContainer = Select.getCurrent_Edit_Object();
                if (Select.isText(insertContainer)) {
                    MyTextArea myTextArea = (MyTextArea) insertContainer;
                    myTextArea.set_Unfocus_Color();
                } else {
                    Picture picture = (Picture) insertContainer;
                    if (picture != null)
                        picture.set_Unfocus_Color();
                }
                Select.Current_Edit_Object = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
//
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
        create_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cf();
            }
        });


        save_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sf();
            }
        });

        open_file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                of();
            }
        });

        save_another_way.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saw();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        new_textarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTextArea new_textarea = new MyTextArea(Frame.this, pressx, pressy, defaultwidth, defaultheight);
//                defaultpane.add(new_textarea.panel);
//                defaultpane.add(new_textarea.buttonpanel);
//                frame.add(new_textarea.buttonpanel);
//                frame.add(new_textarea.panel);
//                pane.add(new_textarea.panel);
//                pane.add(new_textarea.buttonpanel);
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
        new_image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Picture new_pic = new Picture(Frame.this, pressx, pressy, 640, 480, null);
            }
        });
        //新增：背景颜色
        background_set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responseToColor();
            }
        });
        //新增：字体
        font_set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responseToFont();
            }
        });
        //新增：字体颜色
        font_color_set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                responseToFontColor();
            }
        });
        //以下四个监听事件为图层控制
        layup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Select.getCurrent_Edit_Object() != null) menuMethod.Layup(Select.getCurrent_Edit_Object());
            }
        });
        laydown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Select.getCurrent_Edit_Object() != null) menuMethod.Laydown(Select.getCurrent_Edit_Object());
            }
        });
        //顶部菜单和右键菜单中的撤销操作
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
        withdraw1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
        //顶部菜单和右键菜单中的重做操作
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
        withdraw1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
        //文本框和图片的移除
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Select.getCurrent_Edit_Object() != null) {
                    menuMethod.remove(Select.getCurrent_Edit_Object());
                    Select.setCurrent_Edit_Object(null);
                }
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                String content = textarea.getSelectedText();// 获得选中的文本
                textarea.replaceSelection("");// 替换选中的文本
            }
        });

        //文本框的背景设置
        background_pic_set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    set_Background();
                } catch (IOException ex) {
                    System.out.println("图片设置失败！");
                }
            }
        });

        // 查找功能的实现
        search.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Find();
            }
        });
        search.setMnemonic('F');

        searchNext.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textArea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textArea = myTextArea.textarea;
                } else textArea = main_textarea;
                StringBuilder areastr = new StringBuilder(textArea.getText());// 获取文本区文本
                String fieldstr = textArea.getSelectedText();// 获取选中的文本
                if (fieldstr != null) {
                    String toupparea = areastr.toString().toUpperCase();// 转为大写，用做区分大小写判断方便查找
                    String touppfield = fieldstr.toUpperCase();
                    String A;// 用做查找的文本域内容
                    String B;// 用作查找的文本框内容

                    if (check1.isSelected()) {// 区分大小写
                        A = areastr.toString();
                        B = fieldstr;
                    } else {// 全部换为大写
                        A = toupparea;
                        B = touppfield;
                    }
                    int n = textArea.getCaretPosition();// 获取光标的位置
                    int m = 0;
                    // 开始查找
                    m = A.indexOf(B, n);
                    if (m != -1) {
                        textArea.setCaretPosition(m + fieldstr.length());
                        textArea.select(m, m + fieldstr.length());
                    } else {
                        if (check2.isSelected()) {// 如果循环
                            m = A.indexOf(B);// 从头开始找
                            if (m != -1) {
                                textArea.setCaretPosition(m + fieldstr.length());
                                textArea.select(m, m + fieldstr.length());
                            } else {
                                JOptionPane.showMessageDialog(null, "找不到 “" + fieldstr + "“", "查找",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "找不到 “" + fieldstr + "“", "查找",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        //查找下一个
        searchNext.setMnemonic('N');
        searchNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        searchLast.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textArea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textArea = myTextArea.textarea;
                } else textArea = main_textarea;
                StringBuilder areastr = new StringBuilder(textArea.getText());// 获取文本区文本
                String fieldstr = textArea.getSelectedText();// 获取文本框文本
                if (fieldstr != null) {
                    String toupparea = areastr.toString().toUpperCase();// 转为大写，用做区分大小写判断方便查找
                    String touppfield = fieldstr.toUpperCase();
                    String A;// 用做查找的文本域内容
                    String B;// 用作查找的文本框内容
                    if (check1.isSelected()) {// 区分大小写
                        A = areastr.toString();
                        B = fieldstr;
                    } else {// 全部换为大写
                        A = toupparea;
                        B = touppfield;
                    }
                    int n = textArea.getCaretPosition();// 获取光标的位置
                    int m = 0;
                    // 开始向上查找
                    if (textArea.getSelectedText() == null) {
                        m = A.lastIndexOf(B, n - 1);
                    } else {
                        m = A.lastIndexOf(B, n - fieldstr.length() - 1);
                    }
                    if (m != -1) {
                        textArea.setCaretPosition(m);
                        textArea.select(m, m + fieldstr.length());
                    } else {
                        if (check1.isSelected()) {// 如果循环
                            m = A.lastIndexOf(B);// 从后面开始找
                            if (m != -1) {
                                textArea.setCaretPosition(m);
                                textArea.select(m, m + fieldstr.length());
                            } else {
                                JOptionPane.showMessageDialog(null, "找不到 “" + fieldstr + "“", "查找",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "找不到 “" + fieldstr + "“", "查找",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        //查找上一个
        searchLast.setMnemonic('V');
        //替换
        replace.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Replace(e);
            }
        });
        replace.setMnemonic('R');
        //帮助
        findHelp.setMnemonic('H');
        findHelp.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // 打开指定网站
                Desktop desk = Desktop.getDesktop();
                try {
                    URI uri = new URI(
                            "https://www.bilibili.com/video/BV1fv411t7zV/?spm_id_from=333.337.search-card.all.click&vd_source=6af5d653581344560c7825d769a35704");
                    desk.browse(uri);
                } catch (MalformedURLException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                } catch (IOException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO 自动生成的 catch 块
                    e1.printStackTrace();
                }

            }
        });

        //关于
        about.setMnemonic('A');
        about.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                About();
            }

        });
        //缩放
        scale.setMnemonic('Z');
        JMenuItem pmen1 = new JMenuItem("放大(l)");
        pmen1.setMnemonic('l');
        pmen1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_MASK));// 设置Ctrl + 加号
        pmen1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                String name = textarea.getFont().getFontName();// 返回字体外观
                int style = textarea.getFont().getStyle();// 获得字体的样式
                int size = textarea.getFont().getSize();// 获得字体的大小
                textarea.setFont(new Font(name, style, size + 1));// 设置字体大小+1
            }
        });

        JMenuItem pmen2 = new JMenuItem("缩小(O)");
        pmen2.setMnemonic('O');
        pmen2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_MASK));// 设置Ctrl + 减号
        pmen2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                String name = textarea.getFont().getFontName();// 返回字体外观
                int style = textarea.getFont().getStyle();// 获得字体的样式
                int size = textarea.getFont().getSize();// 获得字体的大小
                textarea.setFont(new Font(name, style, size - 1));// 设置字体大小-1
            }
        });

        JMenuItem pmen3 = new JMenuItem("恢复默认缩放");
        pmen3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_MASK));// 设置Ctrl + 0
        pmen3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                String name = textarea.getFont().getFontName();// 返回字体外观
                int style = textarea.getFont().getStyle();// 获得字体的样式
                int size = textarea.getFont().getSize();// 获得字体的大小
                textarea.setFont(new Font(name, style, 14));// 默认四号(14)
            }
        });

        scale.add(pmen1);
        scale.add(pmen2);
        scale.add(pmen3);
        //全选
        select_all.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.selectAll();// 全部选中
            }
        });
        select_all.setMnemonic('A');
        select_all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        select_all1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.selectAll();// 全部选中
            }
        });
        select_all1.setMnemonic('A');
        //自动换行优化
        auto_shift_row.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (auto_shift_row.isSelected()) {// 如果选中，则可以自动换行
//                    item2_10.setEnabled(false);
                    main_textarea.setLineWrap(true);
                    main_textarea.setWrapStyleWord(true);
                    for (InsertContainer key : TP_List) {
                        if (Select.isText(key)) {
                            MyTextArea myTextArea = (MyTextArea) key;
                            myTextArea.textarea.setLineWrap(true);
                            myTextArea.textarea.setWrapStyleWord(true);
                        }
                    }
                } else {
//                    item2_10.setEnabled(true);
                    main_textarea.setLineWrap(false);
                    main_textarea.setWrapStyleWord(false);
                    for (InsertContainer key : TP_List) {
                        if (Select.isText(key)) {
                            MyTextArea myTextArea = (MyTextArea) key;
                            myTextArea.textarea.setLineWrap(false);
                            myTextArea.textarea.setWrapStyleWord(false);
                        }
                    }
                }
            }
        });
        auto_shift_row.setMnemonic('W');
        //获取当前日期

        time_date.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Date date = new Date();// 获得当前日期
                /*
                 * 日期格式化SimpleDateFormat h小时，m分钟 y年份 M月份 d天数
                 */
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//				System.out.println(sdf.format(date));
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.append(sdf.format(date));// 追加到文本
            }
        });
        time_date.setMnemonic('D');
        time_date.setAccelerator(KeyStroke.getKeyStroke("F5"));
        //更多设置的提示
        other_set.setMnemonic('O');
        other_set.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Point();
            }
        });

        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.copy();// 复制
            }
        });
        copy.setMnemonic('C');


        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.paste();// 粘贴
            }
        });
        paste.setMnemonic('P');

        cut.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JTextArea textarea;
                if (Select.isText(Select.getCurrent_Edit_Object())) {
                    MyTextArea myTextArea = (MyTextArea) Select.getCurrent_Edit_Object();
                    textarea = myTextArea.textarea;
                } else textarea = main_textarea;
                textarea.cut();// 剪切
            }
        });
        cut.setMnemonic('T');
        init();
        item_of_Statement.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //当不选择状态栏时工具栏不显示
                toolState.setVisible(item_of_Statement.isSelected());  //当选择状态栏时工具栏显示
            }
        });
        JTextArea textArea = main_textarea;
        textArea.addCaretListener(new CaretListener() {  //文本改变监听器
            public void caretUpdate(CaretEvent e) {
                JTextArea manuArea = (JTextArea) e.getSource();
                try {
                    int caretpos = manuArea.getCaretPosition();
                    Frame.linenum = manuArea.getLineOfOffset(caretpos);
                    Frame.columnnum = caretpos - textArea.getLineStartOffset(Frame.linenum);
                    Frame.linenum += 1;
                    Frame.tool_labelline.setText("  第" + Frame.linenum + "行  ");
                    Frame.tool_labelcolumn.setText("  第" + Frame.columnnum + "列  ");
                    Frame.length = textArea.getText().length();
                    Frame.tool_labellength.setText("  一共" + Frame.length + " 字 ");
                } catch (Exception ex) {
                }
            }
        });
    }

    private void Point() {// 未开发功能提示框
        JOptionPane.showMessageDialog(this, "经过1坤月的开发，我们暂且只有以上设置", "提示", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * @Description 关于的内容
     * @author ikun568
     **/
    private void About() {
        JOptionPane.showMessageDialog(this,
                "*********************************************\n" + " 制作者：wfy shj gm lzy \n"
                        + " 展示时间：2023-12-19                           \n" + "    小组名：568ikun                \n"
                        + " 不足之处希望助教能提出意见，谢谢！  \n" + "*********************************************\n",
                "记事本", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * @Description 背景颜色
     * @author ikun568
     **/
    public void responseToColor() {
        if (main_textarea == null) {
        } else {
            JColorChooser colorChooser = new JColorChooser();
            int userOption = JOptionPane.showConfirmDialog(null, colorChooser, "选择背景颜色", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//            JOptionPane.showMessageDialog(null, colorChooser, "选择背景颜色", -1);
            Color color = colorChooser.getColor();
            if (Select.getCurrent_Edit_Object() == null || Select.getCurrent_Edit_Object().type == 1) {
                MyTextArea cur = (MyTextArea) Select.getCurrent_Edit_Object();
                if (cur == null) {
                    if (userOption == JOptionPane.OK_OPTION) {
                        main_textarea.setBackground(color);
                    }
                    frame.getLayeredPane().repaint();
                } else if (userOption == JOptionPane.OK_OPTION)
                    cur.textarea.setBackground(color);
            }
        }
    }

    /**
     * @Description 字体
     * @author ikun568
     **/
    public void responseToFont() {
        if (main_textarea == null) {
        } else {
            FontSetter fontSetter = new FontSetter(main_textarea.getFont());
            fontSetter.showFontDialog(frame);
            Font font = fontSetter.getSelectFont();
            // 将字体设置到聚焦的JTextArea中
            if (Select.getCurrent_Edit_Object() == null || Select.getCurrent_Edit_Object().type == 1) {
                MyTextArea cur = (MyTextArea) Select.getCurrent_Edit_Object();
                if (cur == null) {
                    main_textarea.setFont(font);
                    frame.getLayeredPane().repaint();
                } else cur.textarea.setFont(font);
            }
        }
    }

    /**
     * @Description 字体颜色
     * @author ikun568
     **/
    public void responseToFontColor() {
        if (main_textarea == null) {
        } else {
            JColorChooser colorChooser = new JColorChooser();
            int userOption = JOptionPane.showConfirmDialog(null, colorChooser, "选择字体颜色", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            Color color = colorChooser.getColor();
            if (Select.getCurrent_Edit_Object() == null || Select.getCurrent_Edit_Object().type == 1) {
                MyTextArea cur = (MyTextArea) Select.getCurrent_Edit_Object();
                if (cur == null) {
                    if (userOption == JOptionPane.OK_OPTION) {
                        main_textarea.setForeground(color);
                    }
                    frame.getLayeredPane().repaint();
                } else if (userOption == JOptionPane.OK_OPTION)
                    cur.textarea.setForeground(color);
            }
        }
    }

    /**
     * @Description 设置主文本框背景图片
     * @author ikun568
     **/
    public void set_Background() throws IOException {//设置文本框背景图片
        {
            String picpath = Picture.find_Picture_Path(this);
            if (picpath != null) {
                backgroundPicPath = picpath;
                hold_panel.removeAll();
                frame.remove(hold_panel);
                hold_panel = new JPanel() {
                    {
                        this.setOpaque(false);
                        this.setLayout(new BorderLayout());
                    }

                    public void paintComponent(Graphics g) {
                        ImageIcon imageIcon = new ImageIcon(picpath);
                        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                        super.paintComponent(g);
                    }
                };
                frame.getContentPane().add(hold_panel);
                main_textarea.setOpaque(false);
                pane.setOpaque(false);
                pane.getViewport().setOpaque(false);
                hold_panel.add(pane);
                hold_panel.updateUI();
            }
        }
    }

    /**
     * @Description 方法重载，用于MenuMethod
     * @author ikun568
     **/
    public void set_Background(String picpath) throws IOException {//设置文本框背景图片
        {
            if (picpath != null) {
                backgroundPicPath = picpath;
                hold_panel.removeAll();
                frame.remove(hold_panel);
                hold_panel = new JPanel() {
                    {
                        this.setOpaque(false);
                        this.setLayout(new BorderLayout());
                    }

                    public void paintComponent(Graphics g) {
                        ImageIcon imageIcon = new ImageIcon(picpath);
                        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                        super.paintComponent(g);
                    }
                };
                frame.getContentPane().add(hold_panel);
                main_textarea.setOpaque(false);
                pane.setOpaque(false);
                pane.getViewport().setOpaque(false);
                hold_panel.add(pane);
                hold_panel.updateUI();
            }
        }
    }

    /**
     * @Description 初始化窗口
     * @author ikun568
     **/
    public void init(String name) {
        for (InsertContainer i : TP_List) {
            i.panel.setVisible(false);
            i.buttonpanel_east.setVisible(false);
            i.buttonpanel_north.setVisible(false);
            i.buttonpanel_west.setVisible(false);
            i.buttonpanel_south.setVisible(false);
        }
        TP_List.clear();
        top_pane_number = -1;
        frame.setTitle(name);
        main_textarea.setText("");
        frame.repaint();
    }

    public void upFind_downFind(ActionEvent e) {
        if (e == null) return;
        JTextArea textarea = null;
        String areastr = textarea.getText();// 获取文本区文本
        String fieldstr = textfield.getText();// 获取文本框文本
        String toupparea = areastr.toUpperCase();// 转为大写，用做区分大小写判断方便查找
        String touppfield = fieldstr.toUpperCase();
        String A;// 用做查找的文本域内容
        String B;// 用作查找的文本框内容
        if (check1.isSelected()) {// 区分大小写
            A = areastr;
            B = fieldstr;
        } else {// 全部换为大写
            A = toupparea;
            B = touppfield;
        }
        int n = textarea.getCaretPosition();// 获取光标的位置
        int m = 0;
        if (up.isSelected()) {// 向上查找
            if (textarea.getSelectedText() == null) {
                m = A.lastIndexOf(B, n - 1);
            } else {
                m = A.lastIndexOf(B, n - textfield.getText().length() - 1);
            }
            if (m != -1) {
                textarea.setCaretPosition(m);
                textarea.select(m, m + textfield.getText().length());
            } else {
                if (check2.isSelected()) {// 如果循环
                    m = A.lastIndexOf(B);// 从后面开始找
                    if (m != -1) {
                        textarea.setCaretPosition(m);
                        textarea.select(m, m + textfield.getText().length());
                    } else {
                        JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else {// 向下查找
            m = A.indexOf(B, n);
            if (m != -1) {
                textarea.setCaretPosition(m + textfield.getText().length());
                textarea.select(m, m + textfield.getText().length());
            } else {
                if (check2.isSelected()) {// 如果循环
                    m = A.indexOf(B);// 从头开始找
                    if (m != -1) {
                        textarea.setCaretPosition(m + textfield.getText().length());
                        textarea.select(m, m + textfield.getText().length());
                    } else {
                        JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "找不到 “" + textfield.getText() + "“", "查找",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    /**
     * @author ikun568
     * @Description 右键弹出小菜单
     * @Implements MouseListener
     **/
    static class mouselistener implements MouseListener {
        JPopupMenu jpopupmenu2;  //一个弹出式菜单对象

        mouselistener(JPopupMenu jpopupmenu)   //构造函数
        {
            jpopupmenu2 = jpopupmenu;
        }

        //自己写的一个显示的方法
        public void display(MouseEvent e) {
            if (e.isPopupTrigger()) {
                pressx = e.getX();
                pressy = e.getY();
                jpopupmenu2.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        public void mouseReleased(MouseEvent e) {
            this.display(e);
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseClicked(MouseEvent e) {
        }
    }

    /**
     * @author ikun568
     * @version 1.0
     * @Description 显示时间
     * @Extends Thread
     **/
    class Clock extends Thread {
        public void run() {
            while (true) {
                GregorianCalendar time = new GregorianCalendar();
                int hour = time.get(Calendar.HOUR_OF_DAY);
                int min = time.get(Calendar.MINUTE);
                int second = time.get(Calendar.SECOND);
                Frame.tool_label_time.setText("  Time:" + hour + ":" + min + ":" + second);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    //发生错误时的信息提示
                }
            }
        }
    }

//    public static void main(String[] args) {
//        new Frame();
//    }

}
