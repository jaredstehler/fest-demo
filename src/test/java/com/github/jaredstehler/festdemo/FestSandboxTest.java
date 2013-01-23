package com.github.jaredstehler.festdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.atIndex;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.fest.assertions.api.Assertions.filter;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.fest.assertions.core.Condition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;

/**
 * Playing with the FEST assertions
 * 
 * @see https://github.com/joel-costigliola/fest-examples/tree/master/src/main/java/org/fest/assertions/examples
 */
public class FestSandboxTest {
    
    private Person jared;
    private Person fred;
    private Person alice;
    
    private Superhero spiderman;
    
    private List<Person> men;
    private List<Person> women;
    
    private List<Person> people;
    
    @BeforeMethod(alwaysRun=true)
    public void setup() {
        jared = new Person("Jared Stehler", "jared@foo.com", 22, new Hobby("bikes"), Lists.newArrayList("red", "dude"));
        fred = new Person("Frederick Jackson", "fred@foo.com", 22, new Hobby("cards"), Lists.newArrayList("fred", "freddie")); 
        alice = new Person("Alice Carroll", "alice@bar.com", 65, new Hobby("bikes"), Lists.newArrayList("allie"));

        spiderman = new Superhero("Peter Parker", "spidey@foo.com", 9, new Hobby("bugs"), Lists.newArrayList("spidey", "webslinger"));
        
        men = Lists.newArrayList(jared, fred);
        women = Lists.newArrayList(alice);
        
        people = Lists.newArrayList(jared, fred, alice, spiderman);
    }

    @Test
    public void basics() {
        assertThat(jared).isLenientEqualsToByAcceptingFields(fred, "favoriteNumber");
        assertThat(spiderman).isInstanceOf(Person.class).isNotExactlyInstanceOf(Person.class);

        Person p1 = new Person("frank", null, 0, null, null);
        Person p2 = new Person("frank", null, 0, null, null);
        assertThat(p2).isLenientEqualsToByIgnoringNullFields(p1);
    }
    
    @Test
    public void strings() {
        assertThat("foobar").matches(".*bar").endsWith("bar").containsOnlyOnce("oo");
    }
    
    @Test
    public void collections() {
        assertThat(extractProperty("email").from(men)).containsOnly("jared@foo.com", "fred@foo.com");
        assertThat(filter(people).with("hobby.name").equalsTo("bikes").get()).contains(jared, alice);
    }

    @Test
    public void lists() {
        assertThat(men).isSubsetOf(people);
        assertThat(women).isNotEqualTo(men);
        
        Comparator<Person> favNumComparator = new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return Integer.valueOf(p1.getFavoriteNumber()).compareTo(p2.getFavoriteNumber());
            }
        };
        
        assertThat(men).isSortedAccordingTo(favNumComparator);  
        
        assertThat(men).has(new Condition<Person>() {
            @Override
            public boolean matches(Person value) {
                return value.getName().equals("Jared Stehler");
            }
        }, atIndex(0));
    }
    
    @Test
    public void betterExceptionAssertions() {
        try {
            throw new IllegalArgumentException("this is an error: foobar");
        } catch(IllegalArgumentException e) {
            assertThat(e).hasNoCause().hasMessageContaining("foobar");
        }
    }
 
    
    @Test
    public void dates() {
        Date updatedOn = new Date();
        assertThat(updatedOn).isCloseTo(new Date(), 100);
        assertThat(updatedOn).isAfter("2010-04-23");
        assertThat(updatedOn)
            .isInSameYearAs(new Date())
            .isInSameDayAs(new Date())
            .isInSameMonthAs("2013-01-02");
    }
    
}
