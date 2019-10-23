package deques.palindrome;

import deques.Deque;
import deques.LinkedDeque;


public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new LinkedDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        Deque<Character> deque = wordToDeque(word);
        while (length > 1) {
            if (!deque.removeFirst().equals(deque.removeLast())) {
                return false;
            }
            length -= 2;

        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        Deque<Character> deque = wordToDeque(word);
        while (length > 1) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
            length -= 2;
        }
        return true;
    }
}
