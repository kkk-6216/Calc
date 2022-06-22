import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum LatinNumbers {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);

    private int trans;
    LatinNumbers(int number) {
        this.trans = number;
    }

    public int getNumber(){
        return trans;
    }

    public static List getReverseSortedValues() {
        return Arrays.stream(values()).sorted(Comparator.comparing((LatinNumbers e) -> e.trans).reversed()).collect(Collectors.toList());
    }

}

