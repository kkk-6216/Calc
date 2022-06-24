import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите условию задачи: "); String condition = scanner.nextLine();
        calc(condition);
    }


    public static String calc(String input) throws IOException{

        String[] conditionArray = input.split(" ");
        int firstNumber = 0;
        int secondNumber = 0;
        String operator = "";
        boolean isRim = false;
        boolean than = false;
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
            if (conditionArray[0].matches("-?[0-9]+") && conditionArray[2].matches("-?[0-9]+")) {
                firstNumber += Integer.parseInt(conditionArray[0]);
                secondNumber += Integer.parseInt(conditionArray[2]);
                if ((firstNumber < 1 || firstNumber > 10) || (secondNumber < 1 || secondNumber > 10)){
                    try{
                        than = true;
                        throw new IOException();
                    }catch (IOException e){
                        System.out.println("Принимаеться на вход числа от 1 до 10 включительно, не более");
                    }
                }

            } else if (conditionArray[0].matches("[IVXLCDM]+") && conditionArray[2].matches("[IVXLCDM]+")) {
                isRim = true;
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
                if ((result1 < 1 || result1 > 10) || (result2 < 1 || result2 > 10)){
                    try{
                        than = true;
                        throw new IOException();
                    }catch (IOException e){
                        System.out.println("Принимаеться на вход числа от 1 до 10 включительно, не более");
                    }
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
        if (!than){
            switch (operator) {
                case "+" -> result += firstNumber + secondNumber;
                case "-" -> result += firstNumber - secondNumber;
                case "*" -> result += firstNumber * secondNumber;
                case "/" -> result += firstNumber / secondNumber;
            }
        }


        int resultCopy = result;
        // Конвертация число от арабского до римского, ...

        List romanNumerals = LatinNumbers.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((result > 0) && (i < romanNumerals.size())) {
            LatinNumbers currentSymbol = (LatinNumbers) romanNumerals.get(i);
            if (currentSymbol.getNumber() <= result) {
                sb.append(currentSymbol.name());
                result -= currentSymbol.getNumber();
            } else {
                i++;
            }
        }

        String finalResult = "";
        if (isRim){
            if (resultCopy == 0){
                throw new IOException();
            }else {
                finalResult += sb.toString();
            }
        } else {
            finalResult += Integer.toString(resultCopy);
        }
        System.out.println(finalResult);
        return finalResult;
    }
}
