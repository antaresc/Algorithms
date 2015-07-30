package main.com.acscooter.algorithms;

import main.com.acscooter.datastructures.LinkedList;
import java.util.List;

/**
 * @author      Antares Chen
 * @since       2015-07-29
 *
 * Have you ever thought, "Gee, Bogosort is way too efficient for my application." Me too; many times in fact. Luckily,
 * DangerMouse, online blogger, has posted a revolutionary solution to this issue known as bogobogosort which can be
 * found <a href="http://www.dangermouse.net/esoteric/bogobogosort.html">here</>.
 *
 * The issue with Bogosort is that it ignores how to check if the array is sorted allowing for needless optimiazation
 * that can dramatically speed up its runtime. Bogobogosort specifically checks if the array is sorted recursively
 * because as anyone who knows anything about computer science will tell you, recursion is always pretty chill. Given an
 * array of N elements, a copy is first made, then the first N-1 elements in the copy are sorted via bogosort. After
 * sorting, if the Nth element in the copy array is greater than or equal to the element at N-1, then the copy is
 * sorted, else reshuffle the copy and resort the first N-1 elements. If the copy is sorted, then check if the original
 * list is equal to the copy. If true then the list is sorted.
 *
 * There are competing ideas amongst computer scientists regarding the average time complexity of Bogobogosort. The
 * initial belief was that it ran O(n! ^ (n!)), but further analysis has shown that this is not a tight bound. More
 * recently it is believe that Bogobogosort runs on the order of O(n! ^ (n - k)). There has not been a concrete answer
 * yet but the analysis would most likely be similar to that given by Gruber et. al. And with regards to best case
 * scenario, it would still require a certain amount of analysis, probably involving Master's theorem or something.
 * Interesting follow up questions nonetheless!
 */
public class BogoBogoSort extends BogoSort
{
    /**
     * Simple implementation of the recursive algorithm that checks if the array is sorted. Because of object
     * orientation, we can simply override the isSorted method without changing anything else in super class BogoSort.
     * @param list
     * @param <T>
     * @return
     */
    @Override
    protected <T extends Comparable<T>> boolean isSorted(List<T> list)
    {
        List<T> copy = new LinkedList<>(list);
        List<T> subList;
        do {
            copy = shuffle(copy);
            subList = copy.subList(0, copy.size() - 1);
            sort(subList);
        } while (copy.get(copy.size() - 1).compareTo(subList.get(subList.size() - 1)) >= 0);

        return copy.equals(list);
    }
}
