package com.antonovweb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class ConsoleApplicationTest {
    @Autowired
    private Shell shell;

    private Object shellWrapper(String rawText) {
        return shell.evaluate(() -> rawText);
    }

    @Before
    public void clear() {
        Input input = () -> "clearstack";
        shell.evaluate(input);
    }

    @Test
    public void inputNumberTest() {
        Assert.assertEquals("100.00", shellWrapper("exec 100"));
    }

    @Test
    public void inputNotNumberAndNotOperationTest() {
        Assert.assertEquals("Error during calculation", shellWrapper("exec 10xz"));
    }

    @Test
    public void inputAdditionForEmptyStackTest() {
        Assert.assertEquals("Error during calculation", shellWrapper("exec +"));
    }

    @Test
    public void checkAdditionTest() {
        Assert.assertEquals("100.00", shellWrapper("exec 100"));
        Assert.assertEquals("200.00", shellWrapper("exec 200"));
        Assert.assertEquals("300.00", shellWrapper("exec +"));
    }

    @Test
    public void checkSubtractionTest() {
        Assert.assertEquals("50.00", shellWrapper("exec 50"));
        Assert.assertEquals("100.00", shellWrapper("exec 100"));
        Assert.assertEquals("-50.00", shellWrapper("exec -"));
    }

    @Test
    public void checkMultiplicationTest() {
        Assert.assertEquals("2.00", shellWrapper("exec 2"));
        Assert.assertEquals("3.00", shellWrapper("exec 3"));
        Assert.assertEquals("6.00", shellWrapper("exec *"));
    }

    @Test
    public void checkDivisionTest() {
        Assert.assertEquals("10.00", shellWrapper("exec 10"));
        Assert.assertEquals("3.00", shellWrapper("exec 3"));
        Assert.assertEquals("3.33", shellWrapper("exec /"));
    }
}