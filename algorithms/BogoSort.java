package main.com.acscooter.algorithms;

import java.util.List;
import java.util.Random;

/**
 * @author      Antares Chen
 * @since       2015-07-28
 *
 * The Jargon File calls, Bogo Sort "perversely awful" but I like to think of Bogo Sort as poorly misunderstood because
 * it represents a stark trade-off between elegance and speed. I say Bogo Sort is elegant because its implemented in
 * only two lines: while the list is not sorted, shuffle the list and try again. Surely there is elegance in that right
 * ... right?
 *
 * In the best case, this is a linear time sorting algorithm (the list is already sorted) but in the worst case it
 * possesses a O((n + 1)!). But wait wouldn't this just never terminate? I mean how do we even know that shuffling will
 * lead to a sorted array. The simple answer would be to reference the infinite monkey theorem, but the actual answer
 * is far cooler in my opinion. At its basis, Bogo sort is a randomized algorithm and so it would make sense to think of
 * termination probabilistically through expected values. That is, what is the expectation that Bogo Sort's running time
 * is finite? Our goal is to prove that the expectation is positive which can be done with quick work from Markov's
 * inequality. You can read the whole thing <a href="http://www.hermann-gruber.com/pdf/fun07-final.html">here</>. It's a
 * pretty cool paper with all sorts of probability and algebras.
 *
 * The below implementation of Bogo Sort actually has a little extra. Instead of calling Java STL's array shuffle, I
 * decided to implement a standard Fisher-Yates Shuffle (further tweaked by Knuth to allow for easier programming).
 * Roughly speaking, the Fisher-Yates shuffle works by iterating through each element and potentially swapping it with a
 * random element after it. This is mathematically proven to give each possible permutation of the array an equal
 * probability of occurrence.
 */
public class BogoSort extends Sort
{
    /**
     * Bogo sort algorithm implementation. A small optimization step is added where if the list is of size one, the
     * algorithm will ignore the array since it is trivially sorted.
     * @param list
     * @param <T>
     * @return
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list)
    {
        if (list.size() > 1)
            while (! isSorted(list))
                list = shuffle(list);

        return list;
    }

    /**
     * Standard Fisher-Yates shuffle with some added tweaks.
     * @param list
     * @param <T>
     * @return shuffled array
     */
    public <T> List<T> shuffle(List<T> list)
    {
        for (int i = 0; i < list.size() - 1; i ++)
        {
            Random rngGods = new Random(133713371337L);
            int random = i + rngGods.nextInt(list.size() - i);

            T temp = list.get(random);
            list.set(random, list.get(i));
            list.set(i, temp);
        }

        return list;
    }

    protected <T extends Comparable<T>> boolean isSorted(List<T> list)
    {
        for (int i = 1; i < list.size(); i ++)
            if (list.get(i).compareTo(list.get(i - 1)) < 0)
                return false;
        return true;
    }
}
