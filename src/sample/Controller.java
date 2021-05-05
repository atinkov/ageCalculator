package sample;

import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static java.lang.Math.abs;

public class Controller {
    double hisAgeResult, herAgeResult, ageSumm, ageDiff;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date hisBirthDate, herBirthDate;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField yourBirthDateField;

    @FXML
    private Label yourBirthDateLabel;

    @FXML
    private TextField herBirthDateField;

    @FXML
    private Label herBirthDateLabel;

    @FXML
    private TextField ageDiffField;

    @FXML
    private TextField ageSummField;

    @FXML
    private Button calcButton;

    @FXML
    private TextField yourAgeField;

    @FXML
    private TextField herAgeField;

    @FXML
    void initialize() {
        calcButton.setOnAction(event -> {
            Date hisBirthDate = null;
            Date herBirthDate = null;
            try {
                hisBirthDate = dateFormat.parse(yourBirthDateField.getText().trim());
                herBirthDate = dateFormat.parse(herBirthDateField.getText().trim());
            } catch (ParseException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Одно или несколько полей с данными не заполнены или заполнены некорректно." +"\n"+ "Введите даты в поля \"Дата твоего рождения\" и \"Дата её рождения\" " +
                        "или значения возрастов (полных лет) в поля \"Твой возраст\" и \"Её возраст\" соответственно и проверте корректность вводимых данных");
                alert.showAndWait();
            }
            System.out.println("Твоя дата рождения: " + dateFormat.format(hisBirthDate) + " Eе дата рождения: " + dateFormat.format(herBirthDate));

            long diffInHours = hisBirthDate.getTime() - herBirthDate.getTime();
            /*long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);*/
            long diffInYears = TimeUnit.MILLISECONDS.toDays(diffInHours) / 365;
            long diffInMonth = TimeUnit.MILLISECONDS.toDays(diffInHours) / 30;
            ageDiffField.setText("Разница в возрасте - лет: " + abs(diffInYears) + "б месяца" +abs(diffInMonth));
            long summInHours = hisBirthDate.getTime() + herBirthDate.getTime();
            long summInYears = ((TimeUnit.MILLISECONDS.toDays(summInHours)) / 365);
            ageDiffField.setText("Разница в возрасте - лет: " + abs(diffInYears) +
                    "; месяцев: " + abs(diffInMonth));
            ageSummField.setText("Сумма возрастов - " + summInYears + " лет");
        });
    }
}
