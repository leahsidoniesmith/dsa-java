package edu.emory.cs.algebraic;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LongIntegerQuizTest {
    @Test
    public void test0() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("0"));
        assertEquals("123", a.toString());
    }

    @Test
    public void test2() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("-0"));
        assertEquals("123", a.toString());
    }

    @Test
    public void test3() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("-123"));
        assertEquals("0", a.toString());
    }

    @Test
    public void test4() {
        LongInteger a = new LongIntegerQuiz("-123");
        a.add(new LongIntegerQuiz("123"));
        assertEquals("-0", a.toString());
    }

    @Test
    public void test5() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("-122"));
        assertEquals("1", a.toString());
    }

    @Test
    public void test6() {
        LongInteger a = new LongIntegerQuiz("-123");
        a.add(new LongIntegerQuiz("122"));
        assertEquals("-1", a.toString());
    }

    @Test
    public void test7() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("-124"));
        assertEquals("-1", a.toString());
    }

    @Test
    public void test8() {
        LongInteger a = new LongIntegerQuiz("-123");
        a.add(new LongIntegerQuiz("124"));
        assertEquals("1", a.toString());
    }

    @Test
    public void test9() {
        LongInteger a = new LongIntegerQuiz("123");
        a.add(new LongIntegerQuiz("-45678"));
        assertEquals("-45555", a.toString());
    }

    @Test
    public void test10() {
        LongInteger a = new LongIntegerQuiz("-12345");
        a.add(new LongIntegerQuiz("678"));
        assertEquals("-11667", a.toString());
    }

    @Test
    public void test11() {
        LongInteger a = new LongIntegerQuiz("-1111111111111111111111111");
        a.add(new LongIntegerQuiz("9999999999999999999999999"));
        assertEquals("8888888888888888888888888", a.toString());
    }

    @Test
    public void test12() {
        LongInteger a = new LongIntegerQuiz("-0000028");
        a.add(new LongIntegerQuiz("000007"));
        assertEquals("-21", a.toString());
    }

    @Test
    public void test13() {
        LongInteger a = new LongIntegerQuiz("21000");
        a.add(new LongIntegerQuiz("-1000"));
        assertEquals("20000", a.toString());
    }
}