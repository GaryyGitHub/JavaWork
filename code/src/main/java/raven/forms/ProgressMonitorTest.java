package raven.forms;

import javax.swing.*;

/**
 * @author ikun568
 * @version 1.0
 * @Description ProgressMonitorTest
 * ProgressMonitor的用法与ProgressBar相似 , ProgressMonitor可以直接创建一个进度对话框
 * 提供的构造方法:
 * public ProgressMonitor(Component parentComponent,Object message,String note,
 * int min,int max):
 * parentComponent: 对话框的父组件
 * message: 对话框的描述信息
 * note: 进度的提示信息
 * min: 进度条的最小值
 * max: 进度条的最大值
 **/
public class ProgressMonitorTest {
    Timer timer;

    static public void test() {
        new ProgressMonitorTest().init();
    }
    //定义线程任务,模拟相关任务

    public void init() {
        ProgressMonitor monitor = new ProgressMonitor(null, "沸羊羊拼命加载中", "别催了~", 0, 100);

        SimulaterActivity simulaterActivity = new SimulaterActivity(100);
        new Thread(simulaterActivity).start();

        //设置定时任务

        timer = new Timer(100, e -> {
            //读取当前任务量,修改进度
            int current = simulaterActivity.getCurrent();
            monitor.setProgress(current);
            //判断用户是否点击了取消按钮,停止定时任务,关闭对话框,退出程序
            if (monitor.isCanceled()) {
                timer.stop();
                monitor.close();
                simulaterActivity.judge = 2;
                System.exit(0);
            }
            if (current == 100) {
                timer.stop();
                simulaterActivity.judge = 1;
                new LoginSuccess();
            }
        });
        timer.start();
    }

    private static class SimulaterActivity implements Runnable {
        //内存可见
        private volatile int current = 0;
        private int amount;
        private int judge = 0;

        public SimulaterActivity(int amount) {
            this.amount = amount;
        }

        public int getJudge() {
            return judge;
        }

        public void setJudge(int judge) {
            this.judge = judge;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public void run() {
            //通过循环,不断的修改current的值,模拟任务我完成量
            while (current < amount) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                current++;
            }
        }
    }
}
