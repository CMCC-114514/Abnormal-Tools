package otherFunctions.randomGenerator;

import java.util.Random;

public class Generators {
    static Random rd = new Random();

    public static int randomNum(int min, int max) {
        return rd.nextInt(min, max + 1);
    }

    public static String randomStr(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            builder.append((char) rd.nextInt(33, 127));
        }

        return builder.toString();
    }
}
