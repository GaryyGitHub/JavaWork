package Work;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author ikun568
 * @version 1.0
 * @Description 字体设置
 * @Extends JDialog
 **/
public class FontSetter extends JDialog {
    public static final int CANCEL_CODE = 0; //选择“取消”按钮的返回值
    public static final int APPROVE_CODE = 1; //选择“确定”按钮的返回值
    private static final String CHINESE_PREVIEW = "助教老师辛苦啦~"; //中文预览
    private static final String ENGLISH_PREVIEW = "Drink Some Java~"; //英文预览
    private static final String NUMBER_PREVIEW = "2221"; //数字预览
    private final String[] styleArray = {"常规", "粗体", "斜体", "粗体+斜体"};  //所有样式
    private final String[] sizeArray = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72", "初号", "小初", "一号", "小一", "二号", "小二", "三号", "小三", "四号", "小四", "五号", "小五", "六号", "小六", "七号", "八号"}; //所有预设字体大小
    private final float[] sizeIntArray = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 42, 36, 26, 24, 22, 18, 16, 15, 14, 12, 10.5F, 9, 7.5F, 6.5F, 5.5F, 5};
    private int returnValue = CANCEL_CODE;  //返回的数值，默认取消
    private Font font = null;
    private Box mainBox = null;  //字体选择器组件容器
    private JTextField fontText = null;  //字体文本框
    private JTextField styleText = null; //样式文本框
    private JTextField sizeText = null; //文字大小文本框
    private JTextField previewText = null; //预览文本框
    private JRadioButton ChineseButton = null; //中文预览
    private JRadioButton EnglishButton = null; //英文预览
    private JRadioButton NumberButton = null; //数字预览
    private JList fontList = null; //字体选择框
    private JList styleList = null; //样式选择器
    private JList sizeList = null; //文字大小选择器
    private JButton approveButton = null;  //确定按钮
    private JButton cancelButton = null;  //取消按钮
    private String[] fontArray = null; //所有字体, 在init()中获取系统字体


    // 构造字体选择模块
    public FontSetter() {
        new Font("宋体", Font.PLAIN, 12);
    }

    //使用给定的预设字体构造一个字体选择器
    public FontSetter(Font font) {
        setTitle("字体选择窗口");
        this.font = font;
        // 初始化
        init();
        //添加监听器
        addListener();
        //按照预设字体显示
        setUp();
        setModal(true);
        //自适应大小
        pack();
    }

    // 将设置尺寸的功能封装为方法
    private void SetSize(JComponent component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
    }

    // 初始化
    private void init() {
        // 获取系统字体
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontArray = graphics.getAvailableFontFamilyNames();
        // 主容器
        mainBox = Box.createVerticalBox();
        mainBox.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        fontText = new JTextField();
        fontText.setEditable(false);
        fontText.setBackground(Color.WHITE);
        styleText = new JTextField();
        styleText.setEditable(false);
        styleText.setBackground(Color.WHITE);

        sizeText = new JTextField("20");

        previewText = new JTextField(100);
        previewText.setHorizontalAlignment(JTextField.CENTER);
        previewText.setEditable(false);
        previewText.setBackground(Color.WHITE);

        ChineseButton = new JRadioButton("中文预览", true);//默认预览中文
        EnglishButton = new JRadioButton("英文预览");
        NumberButton = new JRadioButton("数字预览");

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(ChineseButton);
        bgroup.add(EnglishButton);
        bgroup.add(NumberButton);

        fontList = new JList(fontArray);
        styleList = new JList(styleArray);
        sizeList = new JList(sizeArray);

        approveButton = new JButton("确定");
        cancelButton = new JButton("取消");

        Box box1 = Box.createHorizontalBox();
        JLabel l1 = new JLabel("字体：");
        JLabel l2 = new JLabel("字形：");
        JLabel l3 = new JLabel("字号：");

        SetSize(l1, 210, 20);
        SetSize(l2, 95, 20);
        SetSize(l3, 90, 20);
        box1.add(l1);
        box1.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔
        box1.add(l2);
        box1.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔
        box1.add(l3);
        box1.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔

        Box box2 = Box.createHorizontalBox();
        SetSize(fontText, 210, 25);
        box2.add(fontText);
        box2.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔

        SetSize(styleText, 100, 25);
        box2.add(styleText);
        box2.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔

        SetSize(sizeText, 95, 25);
        box2.add(sizeText);

        Box box3 = Box.createHorizontalBox();
        JScrollPane scroll1 = new JScrollPane(fontList); //字体滑动组件
        SetSize(scroll1, 210, 160);
        box3.add(scroll1);
        box3.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔

        JScrollPane scroll2 = new JScrollPane(styleList);
        SetSize(scroll2, 100, 160);
        box3.add(scroll2);
        box3.add(Box.createHorizontalStrut(15)); //左右部件之间的中间间隔

        JScrollPane scroll3 = new JScrollPane(sizeList);
        SetSize(scroll3, 95, 160);
        box3.add(scroll3);

        Box box4 = Box.createHorizontalBox();
        Box box5 = Box.createVerticalBox();
        JPanel box6 = new JPanel(new BorderLayout()); //边框布局管理器

        box5.setBorder(BorderFactory.createTitledBorder("显示模式"));
        box6.setBorder(BorderFactory.createTitledBorder("字体预览"));
        box5.add(ChineseButton);
        box5.add(EnglishButton);
        box5.add(NumberButton);

        SetSize(box5, 150, 150);
        box6.add(previewText);
        SetSize(box6, 350, 150);
        box4.add(box5);
        box4.add(Box.createHorizontalStrut(25));
        box4.add(box6);

        Box box7 = Box.createHorizontalBox();
        box7.add(Box.createHorizontalGlue());
        box7.add(approveButton);
        box7.add(Box.createHorizontalStrut(25));
        box7.add(cancelButton);
        box7.add(Box.createHorizontalStrut(25));

        mainBox.add(box1);
        mainBox.add(box2);
        mainBox.add(box3);
        mainBox.add(Box.createVerticalStrut(25));
        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(25));
        mainBox.add(box7);
        getContentPane().add(mainBox);
    }

    // 按照 预设字体显示
    private void setUp() {
        String fontName = font.getFamily(); //字体名称
        int fontStyle = font.getStyle();//PLAIN, BOLD, ITALIC, or BOLD+ITALIC
        int fontSize = font.getSize();//字号大小
        //如果预设的文字大小在选择列表中，则通过选择列表中的某项进行设值，否则直接将预设文字大小写入文本框
        //todo: maybe we can change a bit here!
        boolean inArray = false;
        for (String str : sizeArray) {
            if (str.equals(String.valueOf(fontSize))) {
                inArray = true;
                break;
            }
        }
        if (inArray) {
            //选择文字大小列表中的某项
            sizeList.setSelectedValue(String.valueOf(fontSize), true);
        } else {
            sizeText.setText(String.valueOf(fontSize));
        }
        //选择字体列表中的某项
        fontList.setSelectedValue(fontName, true);
        //选择样式列表中的某项
        styleList.setSelectedIndex(fontStyle);
        //预览默认显示中文字符
        ChineseButton.doClick();
        //显示预览
        setPreview();
    }

    //添加所需的事件监听器
    private void addListener() {
        sizeText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setPreview();
            }

            @Override
            public void focusLost(FocusEvent e) {
                sizeText.selectAll();
            }
        });

        fontList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    fontText.setText(String.valueOf(fontList.getSelectedValue()));
                    //设置预览
                    setPreview();
                }
            }
        });
        styleList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    styleText.setText(String.valueOf(styleList.getSelectedValue()));
                    setPreview();
                }
            }
        });
        sizeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (!sizeText.isFocusOwner()) {
                        sizeText.setText(String.valueOf(sizeList.getSelectedValue()));
                    }
                    //设置预览
                    setPreview();
                }
            }
        });

        //编码监听器
        EncodeAction ea = new EncodeAction();
        ChineseButton.addActionListener(ea);
        EnglishButton.addActionListener(ea);
        NumberButton.addActionListener(ea);
        //确定按钮的事件监听
        approveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //组合字体
                font = groupFont();
                //设置返回值
                returnValue = APPROVE_CODE;
                //关闭窗口
                disposeDialog();
            }
        });

        //取消按钮事件监听
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disposeDialog();
            }
        });

    }

    public final int showFontDialog(JFrame owner) {
        setLocationRelativeTo(owner);
        setVisible(true);
        return returnValue;
    }

    public final Font getSelectFont() {
        return font;
    }

    private void disposeDialog() {
        FontSetter.this.removeAll();
        FontSetter.this.dispose();
    }

    //显示错误信息
    private void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "错误", JOptionPane.ERROR_MESSAGE);
    }

    private void setPreview() {
        Font f = groupFont();
        previewText.setFont(f);
    }

    //按照用户选择组合字体
    private Font groupFont() {
        String fontName = fontText.getText();
        int fontStyle = styleList.getSelectedIndex();
        String sizeStr = sizeText.getText().trim();
        //如果没有输入
        if (sizeStr.length() == 0) {
            showErrorDialog("字体大小必须是正整数数值！");
            return null;
        }
        float fontSize = 0;
        //通过循环对比文字大小输入是否在现有列表内
        for (int i = 0; i < sizeArray.length; i++) {
            if (sizeStr.equals(sizeArray[i])) {
                fontSize = sizeIntArray[i];
                break;
            }
        }
        //没有在列表内
        if (fontSize == 0) {
            try {
                fontSize = Integer.parseInt(sizeStr);
                if (fontSize < 1) {
                    showErrorDialog("字体大小必须是正整数数值！");
                    return null;
                }
            } catch (NumberFormatException nfe) {
                showErrorDialog("字体大小必须是正整数数值！");
                return null;
            }
        }
        return new Font(fontName, fontStyle, (int) fontSize);
    }

    //编码选择事件的监听动作
    class EncodeAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(ChineseButton)) {
                previewText.setText(CHINESE_PREVIEW);
            } else if (e.getSource().equals(EnglishButton)) {
                previewText.setText(ENGLISH_PREVIEW);
            } else {
                previewText.setText(NUMBER_PREVIEW);
            }
        }
    }
}
