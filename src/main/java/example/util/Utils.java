package example.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    public static void block() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        try {
            while ((s = in.readLine()) != null && s.length() != 0)
                Thread.sleep(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int roundUpTo100(int n) {
        int rem = n % 100;

        if (rem == 0)
            return n;

        return n + (100 - rem);
    }

    public static byte[] toBytes(int i)
    {
        byte[] result = new byte[4];

        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i /*>> 0*/);

        return result;
    }

    public static int toInt(byte[] b)
    {
        int result = 0;

        result |= (((int)b[0]) << 24);
        result |= (((int)b[1]) << 16);
        result |= (((int)b[2]) << 8);
        result |= b[3];

        return result;
    }

}
