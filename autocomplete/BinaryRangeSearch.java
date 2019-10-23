package autocomplete;

// import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;



public class BinaryRangeSearch implements Autocomplete {
    private final int length;
    private final Term[] termsCopy;
    // private Term[] matchs;
    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public BinaryRangeSearch(Term[] terms) {
        if (terms == null || Arrays.asList(terms).contains(null)) {
            throw new IllegalArgumentException("terms can't be null");
        }
        termsCopy = new Term[terms.length];
        this.length = terms.length;
        for (int i = 0; i < terms.length; i++) {
            termsCopy[i] = terms[i];
        }
        quickSort(termsCopy, 0, terms.length - 1);
        // Arrays.sort(termsCopy, TermComparators.byLexicographicOrder());
    }
    private void quickSort(Term[] terms, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;
        Term pivot = terms[start + (end - start) / 2];

        while (left <= right) {
            while (left <= right && terms[left].query().compareTo(pivot.query()) <= -1) {
                left++;
            }
            while (left <= right && terms[right].query().compareTo(pivot.query()) >= 1) {
                right--;
            }
            if (left <= right) {
                Term temp = terms[left];
                terms[left] = terms[right];
                terms[right] = temp;
                left++;
                right--;
            }
        }
        quickSort(terms, start, right);
        quickSort(terms, left, end);
    }

    // return the index of the first term that equals the search prefix, return -1 if no such prefix
    private static <Term> int firstIndexOf(Term[] all, Term prefix, Comparator<Term> comparator) {
        if (all.length <= 0) {
            return -1;
        }

        int min = 0;
        int max = all.length - 1;
        int mid;

        while (min <= max) {
            mid = min + (max - min) / 2;
            if (comparator.compare(all[mid], prefix) < 0) {
                min = mid + 1;
            }
            else if (comparator.compare(all[mid], prefix) > 0) {
                max = mid - 1;
            }
            else {
                if (mid == 0) {
                    return mid;
                }
                else if (comparator.compare(all[mid - 1], prefix) < 0) {
                    return mid;
                }
                else {
                    max = mid - 1;
                }
            }
        }
        return -1;
    }

    private static <Term> int lastIndexOf(Term[] all, Term prefix, Comparator<Term> comparator) {
        if (all.length <= 0) {
            return -1;
        }

        int min = 0;
        int max = all.length - 1;
        int mid;
        while (min <= max) {
            mid = min + (max - min) / 2;
            if (comparator.compare(all[mid], prefix) < 0) {
                min = mid + 1;
            }
            else if (comparator.compare(all[mid], prefix) > 0) {
                max = mid - 1;
            }
            else {
                if (mid == all.length - 1) {  // is this the last pos in the array?
                    return mid;
                }
                else if (comparator.compare(all[mid + 1], prefix) > 0) {
                    return mid;
                }
                else {
                    min = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix can't be null");
        }

        int pLen = prefix.length();
        int first1 = BinaryRangeSearch.firstIndexOf(termsCopy, new Term(prefix, 0),
                TermComparators.byPrefixOrder(pLen));
        int last1 = BinaryRangeSearch.lastIndexOf(termsCopy, new Term(prefix, 0),
                TermComparators.byPrefixOrder(pLen));
        int numOfMatch = last1 - first1 + 1;
        Term[] allMatch = new Term[numOfMatch];

        if (first1 != -1 && last1 != -1) {
            for (int i = 0; i < numOfMatch; i++) {
                allMatch[i] = termsCopy[first1 + i];
            }
        }

        Arrays.sort(allMatch, TermComparators.byReverseWeightOrder());
        return allMatch;
    }
}
