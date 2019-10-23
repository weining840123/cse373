package deques.palindrome;

import org.junit.Test;
import static org.junit.Assert.*;

public class OffByOneTest {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void myOffByOneTest() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('a', '%'));
        assertFalse(offByOne.equalChars(']', '-'));
        assertFalse(offByOne.equalChars(']', 'à'));
        assertFalse(offByOne.equalChars('1', 'à'));
        assertFalse(offByOne.equalChars('?', 'à'));
        assertTrue(offByOne.equalChars('$', '#'));
    }

}
