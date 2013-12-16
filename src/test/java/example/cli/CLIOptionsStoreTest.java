package example.cli;

import org.junit.Assert;
import org.junit.Test;

public class CLIOptionsStoreTest {

    @Test
    public void testCreateCLIOptionsStore() {
        CLIOptionsStore c = new CLIOptionsStore();
        Assert.assertEquals(0, c.size());
    }

    @Test
    public void testAddOption() {
        CLIOptionsStore c = new CLIOptionsStore();

        c.addOption("client", "127.0.0.1");
        Assert.assertEquals(1, c.size());
    }

    @Test
    public void testGetValue() {
        CLIOptionsStore c = new CLIOptionsStore();

        Assert.assertNull(c.getValue("something"));

        c.addOption("alma", new Integer(1));
        Assert.assertEquals(new Integer(1), c.getValue("alma"));

        c.addOption("korte", "szilva");
        Assert.assertEquals("szilva", c.getValue("korte"));
        Assert.assertEquals(2, c.size());
    }

    @Test
    public void testGetDefaultValue() {

        CLIOptionsStore c = new CLIOptionsStore();

        Assert.assertNull(c.getValue("something"));
        Assert.assertEquals(Boolean.TRUE, c.getValue("something", Boolean.TRUE));
        Assert.assertEquals(new Integer(123), c.getValue("dsdf", new Integer(123)));
    }

    @Test
    public void testGetIntValue() {
        CLIOptionsStore c = new CLIOptionsStore();

        c.addOption("name", new Integer(2));
        Assert.assertEquals(2, c.getIntValue("name"));
    }

    @Test
    public void testGetDefaultIntValue() {
        CLIOptionsStore c = new CLIOptionsStore();

        Assert.assertEquals(2, c.getIntValue("name", new Integer(2)));
    }

    @Test
    public void testGetStringValue() {
        CLIOptionsStore c = new CLIOptionsStore();

        c.addOption("name", "value");
        Assert.assertEquals("value", c.getStringValue("name"));
    }

    @Test
    public void testGetDefaultStringValue() {
        CLIOptionsStore c = new CLIOptionsStore();

        Assert.assertEquals("value", c.getStringValue("name", "value"));
    }

    @Test
    public void testGetBooleanValue() {
        CLIOptionsStore c = new CLIOptionsStore();
        Boolean b = Boolean.FALSE;

        c.addOption("name", b);
        Assert.assertEquals(b, c.getBooleanValue("name"));
    }

    @Test
    public void testGetDefaultBooleanValue() {
        CLIOptionsStore c = new CLIOptionsStore();
        Boolean b = Boolean.FALSE;

        Assert.assertEquals(b, c.getBooleanValue("name", b));
    }
}
