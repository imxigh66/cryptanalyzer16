//package com.javarush.cryptanalyzerGirls.iablocova.view;
//
//import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
//
//public class GUIView implements View{
//    @Override
//    public String[] getParameters() {
//        return new String[0];
//    }
//
//    @Override
//    public void printResult(Result result) {
//    }
//
//    @Override
//    public boolean repeat() {
//        return false;
//    }
//}

package com.javarush.cryptanalyzerGirls.iablocova.view;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import static com.javarush.cryptanalyzerGirls.iablocova.Swing.Swing;

public class GUIView implements View{
    private JTextField encryptField;
    private JTextField keyField;
    private JTextField statusField;
    private JTextField dictionaryField;
    private JFrame frame;

    public GUIView(){
        Swing();
    }
    // Конструктор
    public GUIView(JTextField encryptField, JTextField keyField, JTextField statusField, JTextField dictionaryField,JFrame frame) {
        this.encryptField = encryptField;
        this.keyField = keyField;
        this.statusField = statusField;
        this.dictionaryField = dictionaryField;
        this.frame = frame;
    }

    @Override
    public String[] getParameters() {
        // Возвращает параметры для операций шифрования/дешифрования
        return new String[] {
                encryptField.getText(),
                keyField.getText(),
                dictionaryField.getText()
        };
    }

    @Override
    public void printResult(Result result) {
        // Выводит результат в статусное поле
        if (result.getResultCode() == ResultCode.OK) {
            statusField.setText("Успешно выполнено");
        } else {
            statusField.setText("Ошибка: " + result.getErrorMessage());
        }
    }
    public void repeatOperation() {
        // Сброс состояния элементов интерфейса
        encryptField.setText("");
        keyField.setText("");
        statusField.setText("Ожидание операции...");
        dictionaryField.setText("");
        // Сброс других элементов, если это необходимо
        // ...
        frame.repaint();
    }
    @Override
    public boolean repeat() {
        int dialogResult = JOptionPane.showConfirmDialog(frame, "Повторить операцию?", "Повтор", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            repeatOperation(); // Сбросить состояние интерфейса
            return true; // Вернуть true, если нужно повторить операцию
        } else {
            return false; // Вернуть false, если операцию повторять не нужно
        }
    }

}