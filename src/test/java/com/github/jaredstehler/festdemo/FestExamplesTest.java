package com.github.jaredstehler.festdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.google.common.collect.Lists;

/**
 * Examples of FEST assertions in use
 * 
 * FEST provides assertions for the following data types:
 * 
 * Object
 * String
 * Date
 * Primitives (boolean, int, char, etc.)
 * BigDecimal
 * Iterable
 * Arrays of Object
 * Arrays of primitives
 * Map
 * Throwable
 * File and InputStream
 * BufferedImage
 * 
 * @see https://github.com/alexruiz/fest-assert-2.x/wiki/Using-fest-assertions
 */
public class FestExamplesTest {
    
    @Test
    public void commonAssertions() {
        assertThat("foo").isInstanceOf(String.class);
        assertThat("foo").isNotEqualTo("bar");

        assertThat(new Long(1L)).isEqualTo(Long.valueOf(1l));
        
        List<String> list = Lists.newArrayList("foo", "bar");
        assertThat("foo").isIn(list);
        assertThat("other").isNotIn(list);
    }
    
    @Test
    public void stringAssertions() {
        assertThat("Frodo").startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");
    }
    
    @Test
    public void collectionAssertions() {
        List<String> list = list();
        assertThat(list).hasSize(3)
            .contains("a", "b")
            .doesNotContain("d");
    }
    
    @Test
    public void throwAssertions() {
        try {
            List<String> list = list();
            list.get(3);
          } catch (Exception e) {
            assertThat(e).isInstanceOf(IndexOutOfBoundsException.class)
                         .hasMessage("Index: 3, Size: 3")
                         .hasNoCause();
          }
    }

    @Test
    public void mapAssertions() {
        Map<String, String> map = Maps.newHashMap();
        map.put("a", "b");
        map.put("c", "d");
        
        assertThat(map).hasSize(2)
            .contains(entry("a", "b"), entry("c", "d"))
            .doesNotContain(entry("a", "e"));
    }

    private List<String> list() {
        List<String> list = Lists.newArrayList("a", "b", "c");
        return list;
    }

}

