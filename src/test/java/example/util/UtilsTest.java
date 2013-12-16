package example.util;

import example.cli.CLIOptionParser;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void testRoundUpTo100() {
        int param = 987;
        int res = Utils.roundUpTo100(param);
        Assert.assertEquals(1000, res);

        Assert.assertEquals(1000, Utils.roundUpTo100(1000));

    }

    @Test
    public void testToInt() {
        int a = Utils.toInt(new byte[] {0, 1, 2, 3});
        Assert.assertEquals(66051, a);
        a = Utils.toInt(new byte[] {1,1,1,1});
        Assert.assertEquals(16843009, a);
    }

    @Test
    public void testIntConversion()
    {
        Assert.assertEquals(5, Utils.toInt(Utils.toBytes(5)));
    }

}
