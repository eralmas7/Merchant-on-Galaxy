package com.galaxy.merchant.utils;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

public class GalaxyUtilsTest extends TestCase {

    @Test
    public void testConcatenateMethod() {
        String[] strings = {"a", "b", "c"};
        Assert.assertEquals("a|b|c", GalaxyUtils.concatenate('|', strings));
    }

    @Test
    public void testConcatenateMethodWithEmptyArray() {
        String[] strings = {};
        Assert.assertEquals("", GalaxyUtils.concatenate(' ', strings));
    }

    @Test
    public void testConcatenateMethodWithNullArray() {
        String[] strings = null;
        Assert.assertEquals("", GalaxyUtils.concatenate(' ', strings));
    }

    @Test
    public void testConcatenateMethodWithStrings() {
        String[] strings = {"a", "b", "c"};
        Assert.assertEquals("abc", GalaxyUtils.concatenate(strings));
    }

    @Test
    public void testConcatenateMethodWithStringEmptyArray() {
        String[] strings = {};
        Assert.assertEquals("", GalaxyUtils.concatenate(strings));
    }

    @Test
    public void testConcatenateMethodWithStringNullArray() {
        String[] strings = null;
        Assert.assertEquals("", GalaxyUtils.concatenate(strings));
    }
}
