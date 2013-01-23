package com.github.jaredstehler.festdemo;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;
import static org.fest.assertions.api.Assertions.filter;

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
        jared = new Person("Jared Stehler", "jared@foo.com", 22, new Hobby("stamps"), Lists.newArrayList("red", "dude"));
        fred = new Person("Frederick Jackson", "fred@foo.com", 22, new Hobby("cards"), Lists.newArrayList("fred", "freddie")); 
        alice = new Person("Alice Carroll", "alice@bar.com", 65, new Hobby("bikes"), Lists.newArrayList("allie"));

        spiderman = new Superhero("Peter Parker", "spidey@foo.com", 9, new Hobby("bugs"), Lists.newArrayList("spidey", "webslinger"));
        
        men = Lists.newArrayList(jared, fred);
        women = Lists.newArrayList(alice);
        
        people = Lists.newArrayList(jared, fred, alice, spiderman);
    }
    

}
