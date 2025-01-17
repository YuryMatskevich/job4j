package ru.job4j.comparator;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Yury Matskevich
 * @since 0.1
 */
public class ListCompareTest {
    @Test
    public void whenLeftAndRightEqualsThenZero() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(0));
    }

    @Test
    public void whenOneOfLIstLessOtherThenMunisOne() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1),
                Arrays.asList(1, 2, 3)
        );
        assertThat(rst, is(-1));
    }

    @Test
    public void whenOneOfLIstLessOtherThenMunisTwo() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2, 3),
                Arrays.asList(1)
        );
        assertThat(rst, is(-2));
    }

    @Test
    public void whenLeftGreatRightThenPlusOne() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 2),
                Arrays.asList(1, 1)
        );
        assertThat(rst, is(1));
    }

    @Test
    public void whenRightGreatLeftThenPlusTwo() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(1, 1),
                Arrays.asList(1, 2)
        );
        assertThat(rst, is(2));
    }

    @Test
    public void whenOneOfLIstLessOtherThenMunisOne2() {
        ListCompare compare = new ListCompare();
        int rst = compare.compare(
                Arrays.asList(10),
                Arrays.asList(1, 2)
        );
        assertThat(rst, is(-1));
    }
}

