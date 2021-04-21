import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Elevator{

    public static void main(String[] args) {



        Elevator e = new Elevator();
        e.ask();


    }

    //переменные




    Scanner intel = new Scanner(System.in);
    final int maxFloor = 20;
    final int minFloor = 0;
    int curFloor = 1;
    int desFloor = 0;
    ArrayList<Object> log = new ArrayList<Object>();

    //функции
    void logging(Object text){
        log.add(text+" time: "+ LocalTime.now()+"\r\n");

        Path logFile = Paths.get("log.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8)) {
            writer.write(String.valueOf(log));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    void display(Object o){
        System.out.println(o);
        logging(o);

    }

    void ask(Object o){
        System.out.println(o);
    }

    void delay(int ms){
        try{
            Thread.sleep(ms);
        } catch (Exception e) {}
    }

    void goUp() {
        while (curFloor++ < desFloor) {
            display("Going up... current Floor " + curFloor);
            delay(600);
        }
    }

    void goDown(){
    while(curFloor-->desFloor){
        display("Going down... current Floor  " + curFloor);
        delay(600);
    }
    }

        void ask() {

            ask("Enter the floor: ");
            while (!intel.hasNextInt() && curFloor>1) {
                display("Emergency, going down to 1st floor....");
                desFloor=1;
                goDown();
            }


                desFloor = intel.nextInt();

                if (desFloor < minFloor || desFloor > maxFloor || desFloor == curFloor) {
                    display("Error, beyound the range");
                    ask();
                } else {
                    display("doors are closing....");
                    delay(600);
                    if (desFloor < curFloor) {
                        goDown();
                    } else if (desFloor > curFloor) {
                        goUp();

                    }
                }
                ask();
            }



}