package Work;

/**
 * @author ikun568
 * @version 1.0
 * @Description 焦点问题
 **/
public class Select {
    public static InsertContainer Current_Edit_Object;

    public static boolean isText(InsertContainer insertContainer) {

        return insertContainer instanceof MyTextArea;
    }

    public static InsertContainer getCurrent_Edit_Object() {
        return Current_Edit_Object;
    }

    public static void setCurrent_Edit_Object(InsertContainer current_Edit_Object) {
        Current_Edit_Object = current_Edit_Object;
    }

    public static void select_current(InsertContainer insertContainer) {
        if (isText(insertContainer)) {
            InsertContainer preInsertContainer = Select.getCurrent_Edit_Object();
            if (isText(preInsertContainer)) {
                MyTextArea myTextArea = (MyTextArea) preInsertContainer;
                myTextArea.set_Unfocus_Color();
            } else {
                Picture picture = (Picture) preInsertContainer;
                if (picture != null)
                    picture.set_Unfocus_Color();
            }
            Select.Current_Edit_Object = insertContainer;
            MyTextArea myTextArea = (MyTextArea) insertContainer;
            myTextArea.set_Focus_Color();
        } else {
            InsertContainer preInsertContainer = Select.getCurrent_Edit_Object();
            if (isText(preInsertContainer)) {
                MyTextArea myTextArea = (MyTextArea) preInsertContainer;
                myTextArea.set_Unfocus_Color();
            } else {
                Picture picture = (Picture) preInsertContainer;
                if (picture != null)
                    picture.set_Unfocus_Color();
            }
            Select.Current_Edit_Object = insertContainer;
            Picture picture = (Picture) insertContainer;
            picture.set_Focus_Color();
        }
    }
}
