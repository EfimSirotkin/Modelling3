package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {


    public double randomNumber()
    {
        return new Random().nextDouble();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Random numberGenerator = new Random();


        double pi_1 = 0.45;
        double pi_2 = 0.35;
        double p = 0.5;

        String state = "000";
        double randomQuery = 0;
        double randomFirstChannel = 0;
        double randomSecondChannel = 0;

        while(true)
        {
            System.out.flush();
            System.out.println(state);
            Thread.sleep(10);
            randomQuery = numberGenerator.nextDouble();
            randomFirstChannel = numberGenerator.nextDouble();
            randomSecondChannel = numberGenerator.nextDouble();
            if(state.equals("000")) {
                if (randomQuery <= p) {
                    state = "000";
                } else if (randomQuery >= p) {
                    state = "010";
                }
            }

            else if(state.equals("001"))
            {
                if(randomQuery <= p && randomSecondChannel >= pi_2)
                    state = "000";
                else if(randomQuery <= p && randomSecondChannel <= pi_2)
                    state = "001";
                else if(randomQuery >= p && randomSecondChannel >= pi_2)
                    state = "010";
                else if(randomQuery >= p && randomSecondChannel <= pi_2)
                    state = "011";
            }
            else if(state.equals("010"))
            {
                if(randomQuery <= p && randomFirstChannel <= pi_1)
                    state = "010";
                else if(randomQuery <= p && randomFirstChannel >= pi_1)
                    state = "001";
                else if(randomQuery >= p && randomFirstChannel >= pi_1)
                    state = "011";
                else if(randomQuery >= p && randomFirstChannel <= pi_1)
                    state = "110";
            }

            else if(state.equals("011"))
            {
                if((randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2) ||
                        (randomQuery >= p && randomFirstChannel >= pi_1 && randomSecondChannel <= pi_2) ||
                        (randomQuery >= p && randomFirstChannel >= pi_1 && randomSecondChannel >= pi_2))
                    state = "011";
                else if(randomQuery >= p && randomFirstChannel <= pi_1 && randomSecondChannel >= pi_2)
                    state = "110";
                else if(randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel >= pi_2)
                    state = "010";
                else if(randomQuery >= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2)
                    state = "111";
                else if((randomQuery <= p && randomFirstChannel >= pi_1 && randomSecondChannel >= pi_2) ||
                        (randomQuery <= p && randomFirstChannel >= pi_1 && randomSecondChannel <= pi_2))
                    state = "001";
            }
            else if(state.equals("110"))
            {
                if(randomQuery <= p && randomQuery >= pi_1)
                    state = "011";
                else if((randomQuery <= p && randomFirstChannel <= pi_1) || (randomQuery >= p && randomFirstChannel <= pi_1))
                    state = "110";
                else if(randomQuery >= p && randomFirstChannel >= pi_1)
                    state = "111";
            }
            else if(state.equals("111"))
            {
                if     ((randomQuery >= p && randomFirstChannel >= pi_1 && randomSecondChannel >= pi_2) ||
                        (randomQuery >= p && randomFirstChannel >= pi_1 && randomSecondChannel <= pi_2) ||
                        (randomQuery >= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2) ||
                        (randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2))
                    state = "111";
                else if((randomQuery <= p && randomFirstChannel >= pi_1 && randomSecondChannel >= pi_2) ||
                        (randomQuery <= p && randomFirstChannel >= pi_1 && randomSecondChannel<= pi_2))
                    state = "011";
                else if((randomQuery >= p && randomFirstChannel <= pi_1 && randomSecondChannel >= pi_2) ||
                        randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel >= pi_2)
                    state = "110";
            }
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
