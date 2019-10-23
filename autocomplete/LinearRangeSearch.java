package autocomplete;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LinearRangeSearch implements Autocomplete {
    // private Term[] all = new Term[];
    private List<Term> all = new ArrayList<>();


    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public LinearRangeSearch(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms can't be null");
        }

        for (int i = 0; i < terms.length; i++) {
            this.all.add(terms[i]);
        }





    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix can't be null");
        }
        int r = prefix.length();
        List<Term> results = new ArrayList<>();
        for (Term s : this.all) {
            if (s.queryPrefix(r).equals(prefix)) {
                results.add(s);
            }
        }
        int lenOfMatch = results.size();
        Term[] matchTerm = new Term[lenOfMatch];
        for (int i = 0; i < lenOfMatch; i++) {
            matchTerm[i] = results.get(i);
        }
        Arrays.sort(matchTerm, TermComparators.byLexicographicOrder());
        Arrays.sort(matchTerm, TermComparators.byReverseWeightOrder());


        return matchTerm;
    }
}

