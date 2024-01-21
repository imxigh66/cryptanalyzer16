package com.javarush.cryptanalyzerGirls.iablocova.view;
import com.javarush.cryptanalyzerGirls.iablocova.repository.ResultCode;
import com.javarush.cryptanalyzerGirls.iablocova.entity.Result;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
public class GUIView implements View{
    private JTextField encryptField;// Поле для пути к файлу для шифрования
    private JTextField decryptField;// Поле для пути к файлу для дешифрования
    private JTextField keyField;// Поле для ключа
    private JTextField statusField;//Поле для статуса операции
    private JTextField dictionaryField;// Поле для пути к файлу словаря
    private JFrame frame;// Главное окно приложения
    // Конструктор
    public GUIView(){

    }

    public GUIView(JTextField encryptField, JTextField decryptField, JTextField keyField, JTextField statusField, JTextField dictionaryField,JFrame frame) {
        this.encryptField = encryptField;
        this.decryptField = decryptField;
        this.keyField = keyField;
        this.statusField = statusField;
        this.dictionaryField = dictionaryField;
        this.frame = frame;
    }
    // Переменная для отслеживания текущей операции (шифрование/дешифрование)
    private String currentOperation = "none"; // "encrypt" или "decrypt"

    // Устанавливаем текущую операцию
    public void setCurrentOperation(String operation) {
        this.currentOperation = operation;
    }
    private String currentMode = "none"; // Начальное значение

    // Метод для установки текущего режима
    public void setCurrentMode(String mode) {
        this.currentMode = mode;
    }
    @Override
    public String[] getParameters() {
        String sourceFilePath;
        String key = keyField.getText(); // Ключ
        String dictionaryPath = dictionaryField.getText(); // Путь к словарю
        String targetFilePath; // Путь к целевому файлу

        // Определяем путь к исходному файлу в зависимости от режима
        if (currentMode.equals("1")) {
            sourceFilePath = encryptField.getText(); // Путь к файлу для шифрования
        } else {
            sourceFilePath = decryptField.getText(); // Путь к файлу для дешифрования
        }

        // Запрашиваем пользователя о пути к целевому файлу
        targetFilePath = JOptionPane.showInputDialog(frame, "Введите путь к целевому файлу:");
        if (currentMode.equals("4")) {
            key="0";
            dictionaryPath = "1"; // Для статистического анализа
        } else {
            key="1";
            dictionaryPath = "0"; // Для остальных режимов
        }

        // Возвращаем массив параметров
        return new String[] {
                currentMode, // Текущий режим работы
                sourceFilePath, // Путь к исходному файлу
                key, // Ключ
                targetFilePath, // Путь к целевому файлу
                dictionaryPath  // Путь к словарю
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
