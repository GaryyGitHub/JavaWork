package raven.forms;

/**
 * @author ikun568
 * @version 1.0
 * @Description 工具
 **/
public class Tools {
    static int name_judge(String name) {
        int judge = 1;
        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) <= 'z' && name.charAt(i) >= 'a') || (name.charAt(i) <= 'Z' && name.charAt(i) >= 'A'))) {
                judge = 0;
                break;
            }
        }
        if (name.length() <= 2 || name.length() >= 13) judge = 0;
        return judge;
    }

    static int pwd_judge(String pwd) {
        int judge = 1;
        for (int i = 0; i < pwd.length(); i++) {
            if (!((pwd.charAt(i) <= 'z' && pwd.charAt(i) >= 'a') || (pwd.charAt(i) <= 'Z' && pwd.charAt(i) >= 'A') || (pwd.charAt(i) <= '9' && pwd.charAt(i) >= '0'))) {
                judge = 0;
                break;
            }
        }
        if (pwd.length() <= 5 || pwd.length() >= 13) judge = 0;
        return judge;
    }

    static int pwd_check(String pwd_old, String pwd_new) {
        int judge = 1;
        if (!pwd_old.equals(pwd_new)) judge = 0;
        return judge;
    }
}
