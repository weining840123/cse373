package autocomplete;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TermTest {
    @Test
    public void testSimpleCompareTo() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("me", 0);
        assertTrue(a.compareTo(b) < 0); // "autocomplete" < "me" cause "a" smaller than "m" by 12
        System.out.println(a.compareTo(b));
    }

    @Test
    public void myTestSimpleCompareTo1() {
        Term a = new Term("AqO", 0);
        Term b = new Term("app", 0);
        assertTrue(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b));

    }
    @Test
    public void myTestSimpleCompareTo2() {
        Term a = new Term("Apq", 0);
        Term b = new Term("App", 0);
        assertFalse(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b));

    }
    @Test
    public void myTestSimpleCompareTo3() {
        Term a = new Term("Apq", 0);
        Term b = new Term("Bpp", 0);
        assertTrue(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b));

    }
    @Test
    public void myTestSimpleCompareTo4() {
        Term a = new Term("bppl", 0);
        Term b = new Term("app", 0);
        assertFalse(a.compareTo(b) < 0);
        System.out.println(a.compareTo(b));

    }

    @Test
    public void myTestCompareToByPrefixOrder5() {
        Term a = new Term("Apql", 0);
        Term b = new Term("apq", 0);
        Term c = new Term("bcd", 0);
        Term d = new Term("abcd", 0);
        assertTrue(a.compareToByPrefixOrder(b, 3) < 0);
        assertFalse(c.compareToByPrefixOrder(d, 3) < 0);

    }
}
