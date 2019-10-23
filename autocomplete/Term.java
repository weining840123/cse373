package autocomplete;


public class Term implements Comparable<Term> {
    private final String query;
    private final long weight;

    /**
     * Initializes a term with the given query string and weight.
     * @throws IllegalArgumentException if query is null or weight is negative
     */
    @SuppressWarnings("checkstyle:EmptyBlock")
    public Term(String query, long weight) {
        if (query == null) {
            throw new IllegalArgumentException("query can't be null");
        }
        else if (weight < 0) {
            throw new IllegalArgumentException("weight can't be negative");
        }

        this.query = query;
        this.weight = weight;

    }

    /**
     * Compares the two terms in lexicographic order by query.
     * @throws NullPointerException if the specified object is null
     * compareTo: if former smaller than later, will return negative(not change the order) so
     * it will be ascending order
     */
    public int compareTo(Term that) {
        if (that.query == null) {
            throw new NullPointerException("query can't be null");
        }
        int cmp = this.query.compareTo(that.query);
        // System.out.println(cmp);
        if (cmp <= -1) {
            return -1;
        }
        else if (cmp >= 1) {
            return 1;
        }
        else {
            return 0;
        }

        /*
        return this.query.compareTo(that.query);
        */
    }

    /** Compares to another term, in descending order by weight. */
    public int compareToByReverseWeightOrder(Term that) {
        Long w1 = this.weight;
        Long w2 = that.weight;

        int cmp = w1.compareTo(w2);
        if (cmp <= -1) {
            return 1;
        }
        else if (cmp >= 1) {
            return -1;
        }
        else {
            return 0;
        }

        /*
        return this.weight.compareToByReverseWeightOrder(that.weight);
        */

        /*
        return (int) (this.weight - that.weight)
        */


    }

    /*
    private static class compareToByReverseWeightOrder implements Comparator<Term> {
        @Override
        public int compare(Term t1, Term t2) {
            Long w1 = t1.weight;
            Long w2 = t2.weight;

            int cmp = w1.compareTo(w2);
            if (cmp <= -1) return 1;
            else if (cmp >= 1) return -1;
            else return 0;
        }
    }

    */



    /**
     * Compares to another term in lexicographic order, but using only the first r characters
     * of each query. If r is greater than the length of any term's query, compares using the
     * term's full query.
     * @throws IllegalArgumentException if r < 0
     */
    public int compareToByPrefixOrder(Term that, int r) {
        if (r < 0) {
            throw new IllegalArgumentException("r can't be negative");
        }

        String query1Prefix = this.queryPrefix(r);
        String query2Prefix = that.queryPrefix(r);

        return query1Prefix.compareTo(query2Prefix);
        /*
        int len1 = this.query.length();
        int len2 = that.query.length();

        if (len1 >= r && len2 >= r) {
            String s1 = this.query.toLowerCase().substring(0, r);
            String s2 = that.query.toLowerCase().substring(0, r);

            int cmp = s1.compareTo(s2);
            if (cmp <= -1) return -1;  // if former smaller than later, will return negative not change order
            else if (cmp >= 1) return 1; // former larger than later, will return positive change order
            else return 0;
        }
        else if (len1 < r && len2 >= r) {
            String s1 = this.query.toLowerCase();
            String s2 = that.query.toLowerCase().substring(0, r);

            int cmp = s1.compareTo(s2);
            if (cmp <= -1) return -1;  // if former smaller than later, will return negative not change order
            else if (cmp >= 1) return 1; // former larger than later, will return positive change order
            else return 0;
        }
        else if (len2 < r && len1 >= r) {
            String s1 = this.query.toLowerCase().substring(0, r);
            String s2 = that.query.toLowerCase();

            int cmp = s1.compareTo(s2);
            if (cmp <= -1) return -1;  // if former smaller than later, will return negative not change order
            else if (cmp >= 1) return 1; // former larger than later, will return positive change order
            else return 0;
        }
        else {
            String s1 = this.query.toLowerCase();
            String s2 = that.query.toLowerCase();

            int cmp = s1.compareTo(s2);
            if (cmp <= -1) return -1;  // if former smaller than later, will return negative not change order
            else if (cmp >= 1) return 1; // former larger than later, will return positive change order
            else return 0;
        }

        */

        /*
        return this.query.compareToByPrefixOrder(that.query);
        */

    }

    /*
    private static class compareToByPrefixOrder implements Comparator<Term> {
        private int r;

        public compareToByPrefixOrder(int r) {
            this.r = r;
        }
        @Override
        public int compare(Term t1, Term t2) {
            int r1 = t1.query.length() < r ? t1.query.length() : r;
            int r2 = t2.query.length() < r ? tw.query.length() : r;

            return t1.query.substring(0, r1).compareTo(t2.query.substring(0, r2));
        }
    }

    */

    /** Returns this term's query. */
    public String query() {
        return this.query;

    }

    /**
     * Returns the first r characters of this query.
     * If r is greater than the length of the query, returns the entire query.
     * @throws IllegalArgumentException if r < 0
     */
    public String queryPrefix(int r) {
        if (r < 0) {
            throw new IllegalArgumentException("r can't be negative");
        }
        int lenQ = this.query.length();
        if (lenQ < r) {
            return this.query;
        }
        else {
            return this.query.substring(0, r);
        }

    }

    /** Returns this term's weight. */
    public long weight() {
        return this.weight;

    }
}
