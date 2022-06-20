import java.io.IOException;
// import java.util.Scanner;

public class Main {
    public static String calc(String input) throws IOException{

        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Введите условию задачи: "); String condition = scanner.nextLine();

        String[] conditionArray = input.split(" ");
        int firstNumber = 0;
        int secondNumber = 0;
        String operator = "";

        if (conditionArray.length == 1 || conditionArray.length == 2){
            try{
                throw new IOException();
            } catch (IOException e){
                System.out.println("Строка не является математической операцией");
            }
        } else if (conditionArray.length != 3){
            try {
                throw new IOException();
            } catch (IOException e){
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else {
            operator += conditionArray[1];
            if (conditionArray[0].matches("[0-9]+") && conditionArray[2].matches("[0-9]+")) {
                firstNumber += Integer.parseInt(conditionArray[0]);
                secondNumber += Integer.parseInt(conditionArray[2]);
                System.out.println("Удачное выполнение, арабские числа");
            } else if (conditionArray[0].matches("[IVXLCDM]+") && conditionArray[2].matches("[IVXLCDM]+")) {
                
                String firstRimNumber = conditionArray[0];
                String secondRimNumber = conditionArray[2];

                int result1 = 0;
                int prev = 0;

                for (int i = firstRimNumber.length() - 1; i >= 0; i--){
                    int curr = LatinNumbers.valueOf(String.valueOf(firstRimNumber.charAt(i))).getNumber();
                    if (curr < prev){
                        result1 -= curr;
                    }else{
                        result1 += curr;
                    }
                    prev = curr;
                }

                int result2 = 0;
                int prev2 = 0;
                for (int i = secondRimNumber.length() - 1; i >= 0; i--){
                    int curr = LatinNumbers.valueOf(String.valueOf(secondRimNumber.charAt(i))).getNumber();
                    if (curr < prev2){
                        result2 -= curr;
                    }else{
                        result2 += curr;
                    }
                    prev2 = curr;
                }
                if (result1 < result2){
                    try{
                        throw new IOException();
                    }catch (IOException e){
                        System.out.println("B римской системе нет отрицательных чисел");
                    }
                }else{
                    firstNumber += result1;
                    secondNumber += result2;
                }

            } else {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Используются одновременно разные системы счисления");
                }
            }

        }
        int result = 0;
        switch (operator) {
            case "+" -> result += firstNumber + secondNumber;
            case "-" -> result += firstNumber - secondNumber;
            case "*" -> result += firstNumber * secondNumber;
            case "/" -> result += firstNumber / secondNumber;
        }

        // Конвертация число от арабского до римского, ...

        String finalResult = Integer.toString(result);
        System.out.println(finalResult);
        return finalResult;
    }
}
