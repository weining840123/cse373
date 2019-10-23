package deques.palindrome;

import deques.Deque;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testMyIspalindrome() {
        assertTrue(palindrome.isPalindrome("yajpoopjay"));
        assertFalse(palindrome.isPalindrome("oosjwjix"));
        assertTrue(palindrome.isPalindrome("abcpcba"));
        assertFalse(palindrome.isPalindrome("abcpcbA"));

    }

    static CharacterComparator obo = new OffByOne();
    @Test
    public void testMyIspalindrome2() {
        assertFalse(palindrome.isPalindrome("yajpoopjay", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertFalse(palindrome.isPalindrome("flakE", obo));
        assertFalse(palindrome.isPalindrome("flak>", obo));

    }
}
