package Work;

import raven.forms.Login;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Collections;

/**
 * @author ikun568
 * @version 1.0
 * @Description 文件选项
 * 文件操作中用到方法的一个目录
 **/
interface FileOption {

    void save_File(File f, Frame frame) throws IOException;//按指定路径保存

    File find_New_File_Path(Frame frame) throws IOException;//当前文件为默认新建文件时，从文件目录对话框中获得保存路径,并创建文件

    boolean ask_If_Need_Resave(File f, Frame frame);//询问当前已编辑过且已打开的文件是否需要保存,若取消，则返回false

    boolean ask_If_Need_Newsave(File f, Frame frame);//对于当前已编辑过的草稿，询问是否需要保存,若取消，则返回false

    void open_File(File f, Frame frame) throws IOException;//打开指定路径文件

    void Layup(InsertContainer i);

    void Laydown(InsertContainer i);

    void remove(InsertContainer i);
}

/**
 * @author ikun568
 * @version 1.0
 * @Description 菜单方法
 * @Implements FileOption
 **/
public class MenuMethod implements FileOption {
    private final String name = Login.getname();

    /**
     * @param f,frame
     * @return null
     * @Description 保存文件方法，使用文件编码形式，支持用户权限检测
     * @author ikun568
     **/
    public void save_File(File f, Frame frame) throws IOException {
        if (f != null) {
            BufferedWriter bufw = new BufferedWriter(new FileWriter(f));
            String text = Frame.main_textarea.getText();
            // 存储用户信息
            bufw.write("%\n" + name + "\n\n");
            bufw.write("0\n");
            // 存储字体相关
            bufw.write((Frame.main_textarea.getFont().getName()) + "  ");
            bufw.write((Frame.main_textarea.getFont().getStyle()) + "  ");
            bufw.write((Frame.main_textarea.getFont().getSize()) + "  ");
            bufw.write((Frame.main_textarea.getForeground().getRed()) + "  ");
            bufw.write((Frame.main_textarea.getForeground().getGreen()) + "  ");
            bufw.write((Frame.main_textarea.getForeground().getBlue()) + "  ");
            // 背景颜色
            bufw.write((Frame.main_textarea.getBackground().getRed()) + "  ");
            bufw.write((Frame.main_textarea.getBackground().getGreen()) + "  ");
            bufw.write((Frame.main_textarea.getBackground().getBlue()) + "  ");
            // 背景图片
            bufw.write((frame.backgroundPicPath) + "  ");
            // 文字内容
            bufw.write("\n" + text);
            bufw.write("\n\n");
            // 背景图片
            for (InsertContainer i : Frame.TP_List) {
                if (i.type == 1) {
                    MyTextArea cur = (MyTextArea) i;
                    bufw.write("1\n");
                    bufw.write(cur.toString());
                    // 存储字体相关
                    bufw.write((cur.textarea.getFont().getName()) + "  ");
                    bufw.write((cur.textarea.getFont().getStyle()) + "  ");
                    bufw.write((cur.textarea.getFont().getSize()) + "  ");
                    bufw.write((cur.textarea.getForeground().getRed()) + "  ");
                    bufw.write((cur.textarea.getForeground().getGreen()) + "  ");
                    bufw.write((cur.textarea.getForeground().getBlue()) + "  ");
                    // 背景颜色
                    bufw.write((cur.textarea.getBackground().getRed()) + "  ");
                    bufw.write((cur.textarea.getBackground().getGreen()) + "  ");
                    bufw.write((cur.textarea.getBackground().getBlue()) + "  ");

                    bufw.write("\n");
                    bufw.write(cur.textarea.getText() + "\n");
                } else {
                    Picture cur = (Picture) i;
                    bufw.write("2\n");
                    bufw.write(cur.toString());
                    bufw.write(cur.path + '\n');
                }
                bufw.write('\n');
            }
            bufw.close();
        } else {
            JOptionPane.showMessageDialog(null, "保存已取消，原因：没有指定路径", "未完成的保存", JOptionPane.YES_OPTION);
        }

    }

    /**
     * @param file_name,frame
     * @return null
     * @Description 保存文件方法重载
     * @author ikun568
     **/
    public void save_File(String file_name, Frame frame) throws IOException {
        BufferedWriter bufw = new BufferedWriter(new FileWriter(file_name));
        String text = Frame.main_textarea.getText();
        bufw.write("0\n");
        // 存储字体相关
        bufw.write((Frame.main_textarea.getFont().getName()) + "  ");
        bufw.write((Frame.main_textarea.getFont().getStyle()) + "  ");
        bufw.write((Frame.main_textarea.getFont().getSize()) + "  ");
        bufw.write((Frame.main_textarea.getForeground().getRed()) + "  ");
        bufw.write((Frame.main_textarea.getForeground().getGreen()) + "  ");
        bufw.write((Frame.main_textarea.getForeground().getBlue()) + "  ");
        // 背景颜色
        bufw.write((Frame.main_textarea.getBackground().getRed()) + "  ");
        bufw.write((Frame.main_textarea.getBackground().getGreen()) + "  ");
        bufw.write((Frame.main_textarea.getBackground().getBlue()) + "  ");
        // 背景图片
        bufw.write((frame.backgroundPicPath) + "  ");
        // 文字内容
        bufw.write("\n" + text);
        bufw.write("\n\n");
        // 背景图片
        for (InsertContainer i : Frame.TP_List) {
            if (i.type == 1) {
                MyTextArea cur = (MyTextArea) i;
                bufw.write("1\n");
                bufw.write(cur.toString());
                // 存储字体相关
                bufw.write((cur.textarea.getFont().getName()) + "  ");
                bufw.write((cur.textarea.getFont().getStyle()) + "  ");
                bufw.write((cur.textarea.getFont().getSize()) + "  ");
                bufw.write((cur.textarea.getForeground().getRed()) + "  ");
                bufw.write((cur.textarea.getForeground().getGreen()) + "  ");
                bufw.write((cur.textarea.getForeground().getBlue()) + "  ");
                // 背景颜色
                bufw.write((cur.textarea.getBackground().getRed()) + "  ");
                bufw.write((cur.textarea.getBackground().getGreen()) + "  ");
                bufw.write((cur.textarea.getBackground().getBlue()) + "  ");

                bufw.write("\n");
                bufw.write(cur.textarea.getText() + "\n");
            } else {
                Picture cur = (Picture) i;
                bufw.write("2\n");
                bufw.write(cur.toString());
                bufw.write(cur.path + '\n');
            }
            bufw.write('\n');
        }
        bufw.close();
    }

    public File find_New_File_Path(Frame frame) throws IOException {
        frame.saveDia.setVisible(true);
        String dirPath = frame.saveDia.getDirectory();
        String fileName = frame.saveDia.getFile();
        if (dirPath == null || fileName == null)
            return null;
        save_File(dirPath + fileName, frame);
        frame.frame.setTitle(fileName);
        return new File(dirPath, fileName);
    }

    public boolean ask_If_Need_Resave(File f, Frame frame) {
        switch (JOptionPane.showConfirmDialog(null, "当前文件可能未保存，是否保存？", "未保存的文件", JOptionPane.YES_NO_CANCEL_OPTION)) {
            case JOptionPane.YES_OPTION:
                try {
                    save_File(f, frame);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
                return false;
        }
        return true;
    }

    public boolean ask_If_Need_Newsave(File f, Frame frame) {
        switch (JOptionPane.showConfirmDialog(null, "当前草稿可能未保存，是否保存？", "未保存的草稿", JOptionPane.YES_NO_CANCEL_OPTION)) {
            case JOptionPane.YES_OPTION:
                try {
                    f = find_New_File_Path(frame);
                    save_File(f, frame);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case JOptionPane.NO_OPTION:
                break;
            case JOptionPane.CANCEL_OPTION:
                return false;
        }
        return true;
    }

    /**
     * @param f,frame
     * @return null
     * @Description 打开文件方法
     * @author ikun568
     **/
    public void open_File(File f, Frame frame) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f));

        } catch (FileNotFoundException e) {
            System.out.println("文件打开失败！");
            throw new RuntimeException(e);
        }
        String textline, userName; //文本内容
        String fontName, backPicPath; //字体名称，背景图片路径
        int fontStyle, fontSize; // 字体样式，大小
        int red, green, blue, backred, backgreen, backblue; // 字体RGB，背景RGB
        boolean next = true;
        boolean end = false;
        String[] set;
        char waste;
        int x, y, width, height;
        String buffer;
        try {
            while (!end) {
                int type = br.read();
                switch (type) {
                    case -1:
                        end = true;
                        waste = (char) br.read();
                        break;
                    case '%':
                        waste = (char) br.read();
                        userName = br.readLine();
                        if (!userName.equals(name)) {
                            JOptionPane.showMessageDialog(null, "打开失败，原因：用户无权限", "error", JOptionPane.YES_OPTION);
                            return;
                        }
                        break;
                    case '0':
                        waste = (char) br.read();
                        set = br.readLine().split("  ");

                        fontName = set[0];
                        fontStyle = Integer.valueOf(set[1]);
                        fontSize = Integer.valueOf(set[2]);
                        red = Integer.valueOf(set[3]);
                        green = Integer.valueOf(set[4]);
                        blue = Integer.valueOf(set[5]);
                        backred = Integer.valueOf(set[6]);
                        backgreen = Integer.valueOf(set[7]);
                        backblue = Integer.valueOf(set[8]);
                        backPicPath = set[9];

                        while (!(textline = br.readLine()).isEmpty()) {
                            Frame.main_textarea.append(textline);
                        }
                        Font mainFont = new Font(fontName, fontStyle, fontSize);
                        Color mainTextColor = new Color(red, green, blue);
                        Color mainBackColor = new Color(backred, backgreen, backblue);

                        Frame.main_textarea.setFont(mainFont);
                        Frame.main_textarea.setForeground(mainTextColor);
                        Frame.main_textarea.setBackground(mainBackColor);
                        if (!backPicPath.equals("null"))
                            frame.set_Background(backPicPath);
                        break;
                    case '1':
                        waste = (char) br.read();
                        set = br.readLine().split("  ");

                        x = Integer.valueOf(set[0]);
                        y = Integer.valueOf(set[1]);
                        width = Integer.valueOf(set[2]);
                        height = Integer.valueOf(set[3]);
                        fontName = set[4];
                        fontStyle = Integer.valueOf(set[5]);
                        fontSize = Integer.valueOf(set[6]);
                        red = Integer.valueOf(set[7]);
                        green = Integer.valueOf(set[8]);
                        blue = Integer.valueOf(set[9]);
                        backred = Integer.valueOf(set[10]);
                        backgreen = Integer.valueOf(set[11]);
                        backblue = Integer.valueOf(set[12]);

                        MyTextArea cur = new MyTextArea(frame, x, y, width, height);

                        Font font = new Font(fontName, fontStyle, fontSize);
                        Color textColor = new Color(red, green, blue);
                        Color backColor = new Color(backred, backgreen, backblue);

                        cur.textarea.setFont(font);
                        cur.textarea.setForeground(textColor);
                        cur.textarea.setBackground(backColor);

                        while (!(textline = br.readLine()).isEmpty()) {
                            cur.textarea.append(textline);
                        }
                        break;
                    case '2':
                        waste = (char) br.read();
                        set = br.readLine().split("  ");
                        x = Integer.valueOf(set[0]);
                        y = Integer.valueOf(set[1]);
                        width = Integer.valueOf(set[2]);
                        height = Integer.valueOf(set[3]);
                        textline = set[4];
                        new Picture(frame, x, y, width, height, textline);
                        break;
                    default:

                        break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "文件打开失败！请检查是否为文件形式是否正确", "错误编码方式的文件", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void Layup(InsertContainer i) {
        if (i.ID < 200 && Frame.TP_List.size() > 1) swap_lay(i.ID, i.ID + 1);
        Collections.swap(Frame.TP_List, i.ID, i.ID - 1);
    }

    @Override
    public void Laydown(InsertContainer i) {
        if (i.ID > 0 && Frame.TP_List.size() > 1) swap_lay(i.ID - 1, i.ID);
        Collections.swap(Frame.TP_List, i.ID + 1, i.ID);
    }

    @Override
    public void remove(InsertContainer i) {
        if (Frame.TP_List.size() > 1) {
            for (int k = i.ID + 1; k < Frame.TP_List.size(); k++) {
                Frame.TP_List.get(k).ID--;
            }
        }
        Frame.top_pane_number--;
        Frame.TP_List.remove(i.ID);
        i.buttonpanel_west.setVisible(false);
        i.buttonpanel_east.setVisible(false);
        i.buttonpanel_north.setVisible(false);
        i.buttonpanel_south.setVisible(false);
        i.panel.setVisible(false);
    }

    public void swap_lay(int i, int j) {
        InsertContainer I = Frame.TP_List.get(i);
        InsertContainer J = Frame.TP_List.get(j);
        ++I.ID;
        --J.ID;
        I.belong_pane.getLayeredPane().add(I.buttonpanel_north, Integer.valueOf(I.ID), 0);
        I.belong_pane.getLayeredPane().add(I.buttonpanel_west, Integer.valueOf(I.ID), 1);
        I.belong_pane.getLayeredPane().add(I.buttonpanel_east, Integer.valueOf(I.ID), 2);
        I.belong_pane.getLayeredPane().add(I.buttonpanel_south, Integer.valueOf(I.ID), 3);
        I.belong_pane.getLayeredPane().add(I.panel, Integer.valueOf(I.ID), 4);
        J.belong_pane.getLayeredPane().add(J.buttonpanel_north, Integer.valueOf(J.ID), 0);
        J.belong_pane.getLayeredPane().add(J.buttonpanel_west, Integer.valueOf(J.ID), 1);
        J.belong_pane.getLayeredPane().add(J.buttonpanel_east, Integer.valueOf(J.ID), 2);
        J.belong_pane.getLayeredPane().add(J.buttonpanel_south, Integer.valueOf(J.ID), 3);
        J.belong_pane.getLayeredPane().add(J.panel, Integer.valueOf(J.ID), 4);

    }
}
