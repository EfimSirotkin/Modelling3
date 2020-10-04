package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Main extends Application {


    public double getScaledValue(double sourceNumber, int scale)
    {
        BigDecimal bd = new BigDecimal(sourceNumber);
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        Random numberGenerator = new Random();

        int countTicks = 1000000;
        int totalQuery = 0;

        double pi_1 = 0.45;
        double pi_2 = 0.35;
        double p = 0.5;


        double Q2 = 0;
        double A = 0;
        double Q = 0;
        double P_otk = 0;
        double L_c = 0;
        double L_och = 0;
        double W_c = 0;
        double W_och = 0;
        double K_channel_1 = 0;
        double K_channel_2 = 0;

        String state = "000";
        double randomQuery = 0;
        double randomFirstChannel = 0;
        double randomSecondChannel = 0;


        int A_counter = 0;
        int B_counter = 0;
        int C_counter = 0;
        int D_counter = 0;
        int E_counter = 0;
        int F_counter = 0;

        int counter = 0;

        while(counter < countTicks)
        {
            System.out.flush();
            System.out.println(state);
            randomQuery = getScaledValue(numberGenerator.nextDouble(), 4);
            randomFirstChannel = getScaledValue(numberGenerator.nextDouble(), 4);
            randomSecondChannel = getScaledValue(numberGenerator.nextDouble(), 4);

            if(state.equals("000")) {
                if (randomQuery <= p) {
                    state = "000";
                } else if (randomQuery > p) {
                    totalQuery++;
                    state = "010";
                }
                A_counter++;

            }

            else if(state.equals("001"))
            {
                K_channel_2++;
                B_counter++;
                L_c++;


                if(randomQuery <= p && randomSecondChannel > pi_2)
                    state = "000";
                else if(randomQuery <= p && randomSecondChannel <= pi_2)
                    state = "001";
                else if(randomQuery > p && randomSecondChannel > pi_2) {
                    state = "010";
                }
                else if(randomQuery > p && randomSecondChannel <= pi_2)
                    state = "011";

                if(randomSecondChannel >= pi_2) {
                    A++;
                }
                if(randomQuery > p)
                    totalQuery++;
            }
            else if(state.equals("010"))
            {
                K_channel_1++;
                C_counter++;
                L_c++;


                if(randomQuery <= p && randomFirstChannel <= pi_1)
                    state = "010";
                else if(randomQuery <= p && randomFirstChannel > pi_1)
                    state = "001";
                else if(randomQuery > p && randomFirstChannel > pi_1)
                    state = "011";
                else if(randomQuery > p && randomFirstChannel <= pi_1)
                    state = "110";

                if(randomQuery > p)
                    totalQuery++;

            }

            else if(state.equals("011"))
            {
                K_channel_1++;
                K_channel_2++;
                D_counter++;
                L_c += 2;

                if(randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2)
                    state = "011";
                else if(randomQuery > p && randomFirstChannel > pi_1 && randomSecondChannel > pi_2)
                    state = "011";
                else if(randomQuery > p && randomFirstChannel > pi_1 && randomSecondChannel <= pi_2)
                    state = "011";
                else if(randomQuery > p && randomFirstChannel <= pi_1 && randomSecondChannel > pi_2)
                    state = "110";
                else if(randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel > pi_2)
                    state = "010";
                else if(randomQuery > p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2)
                    state = "111";
                else if(randomQuery <= p && randomFirstChannel > pi_1 && randomSecondChannel > pi_2)
                    state = "001";
                else if(randomQuery <= p && randomFirstChannel > pi_1 && randomSecondChannel <= pi_2)
                    state = "001";

                if(randomFirstChannel >= pi_1 && randomSecondChannel <= pi_2) {
                    P_otk++;

                }

                if(randomSecondChannel > pi_2) {
                    A++;
                }

                if(randomQuery > p)
                    totalQuery++;


            }
            else if(state.equals("110"))
            {
                K_channel_1++;
                E_counter++;
                L_c += 2;
                L_och++;

                if(randomQuery <= p && randomFirstChannel > pi_1)
                    state = "011";
                else if((randomQuery <= p && randomFirstChannel <= pi_1))
                    state = "110";
                else if((randomQuery > p && randomFirstChannel <= pi_1))
                    state = "110";
                else if(randomQuery > p && randomFirstChannel > pi_1)
                    state = "111";

                if(randomFirstChannel < pi_1) {
                    P_otk++;
                }
                if(randomQuery > p)
                    totalQuery++;


            }
            else if(state.equals("111"))
            {
                K_channel_1++;
                K_channel_2++;
                F_counter++;
                L_c += 3;
                L_och++;

                if(randomQuery > p && randomFirstChannel > pi_1 && randomSecondChannel > pi_2)
                    state = "111";
                else if(randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2)
                    state = "111";
                else if(randomQuery > p && randomFirstChannel > pi_1 && randomSecondChannel <= pi_2)
                    state = "111";
                else if(randomQuery > p && randomFirstChannel <= pi_1 && randomSecondChannel <= pi_2)
                    state = "111";
                else if(randomQuery <= p && randomFirstChannel > pi_1 && randomSecondChannel > pi_2)
                    state = "011";
                else if(randomQuery <= p && randomFirstChannel > pi_1 && randomSecondChannel <= pi_2)
                    state = "011";
                else if(randomQuery <= p && randomFirstChannel <= pi_1 && randomSecondChannel > pi_2)
                    state = "110";
                else if(randomQuery > p && randomFirstChannel <= pi_1 && randomSecondChannel > pi_2)
                    state = "110";

                if(randomFirstChannel > pi_1 && randomSecondChannel <= pi_2) {
                    P_otk++;

                }

                if(randomFirstChannel <= pi_1) {
                    P_otk++;

                }

                if(randomSecondChannel > pi_2) {
                    A++;
                }
                if(randomQuery > p)
                    totalQuery++;


            }

            ++counter;
        }

        P_otk = (totalQuery - A)/ totalQuery;
        Q2 = 1 - P_otk;
        A /= countTicks;
        L_c /= countTicks;
        L_och /= countTicks;
        W_c = L_c / p;
        W_och = L_och / p;
        K_channel_1 /= countTicks;
        K_channel_2 /= countTicks;

        System.out.println("(А): " + A);
        System.out.println("(Q): "+ Q2);

        System.out.println("(Pотк): " + P_otk);

        System.out.println("(Lоч): " + L_och);
        System.out.println("(Lcр): " + L_c);
        System.out.println("(Wоч): " + W_och);
        System.out.println("(Wc): " + W_c);

        System.out.println("(K1): " + K_channel_1);
        System.out.println("(K2): " + K_channel_2);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
