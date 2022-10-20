package com.mmg.runner;

import com.arangodb.springframework.core.ArangoOperations;
import com.mmg.entity.Character;
import com.mmg.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;
import java.util.stream.StreamSupport;

@ComponentScan("com.mmg.config")
public class CrudRunner implements CommandLineRunner {

    @Autowired
    private CharacterRepository repository;
    @Autowired
    private ArangoOperations operations;

    @Override
    public void run(String... args) throws Exception {
        // first drop the database so that we can run this multiple times with the same dataset
        operations.dropDatabase();

        // save a single entity in the database
        // there is no need of creating the collection first. This happens automatically
        final Character nedStark = new Character("Ned", "Stark", true, 41);
        repository.save(nedStark);
        // the generated id from the database is set in the original entity
        System.out.printf("Ned Stark saved in the database with id: '%s'%n", nedStark.getId());
        // let's take a look whether we can find Ned Stark in the database
        final Optional<Character> foundNed = repository.findById(nedStark.getId());
        assert foundNed.isPresent();
        System.out.printf("Found %s%n", foundNed.get());
        nedStark.setAlive(false);
        repository.save(nedStark);
        final Optional<Character> deadNed = repository.findById(nedStark.getId());
        assert deadNed.isPresent();
        System.out.printf("The alive flag of persisted Ned Stark is now %s %n", deadNed.get().isAlive());

        Collection<Character> createCharacters = createCharacters();
        System.out.printf("Saved %s additional characters %n", createCharacters.size());
        repository.saveAll(createCharacters);

        Iterable<Character> all = repository.findAll();
        long count = StreamSupport.stream(Spliterators.spliteratorUnknownSize(all.iterator(), 0), false).count();
        System.out.printf("A total of %s characters are persisted in the database", count);

    }

    private Collection<Character> createCharacters() {
        return Arrays.asList(new Character("Robert", "Baratheon", false),
                new Character("Jaime", "Lannister", true, 36), new Character("Catelyn", "Stark", false, 40),
                new Character("Cersei", "Lannister", true, 36), new Character("Daenerys", "Targaryen", true, 16),
                new Character("Jorah", "Mormont", false), new Character("Petyr", "Baelish", false),
                new Character("Viserys", "Targaryen", false), new Character("Jon", "Snow", true, 16),
                new Character("Sansa", "Stark", true, 13), new Character("Arya", "Stark", true, 11),
                new Character("Robb", "Stark", false), new Character("Theon", "Greyjoy", true, 16),
                new Character("Bran", "Stark", true, 10), new Character("Joffrey", "Baratheon", false, 19),
                new Character("Sandor", "Clegane", true), new Character("Tyrion", "Lannister", true, 32),
                new Character("Khal", "Drogo", false), new Character("Tywin", "Lannister", false),
                new Character("Davos", "Seaworth", true, 49), new Character("Samwell", "Tarly", true, 17),
                new Character("Stannis", "Baratheon", false), new Character("Melisandre", null, true),
                new Character("Margaery", "Tyrell", false), new Character("Jeor", "Mormont", false),
                new Character("Bronn", null, true), new Character("Varys", null, true), new Character("Shae", null, false),
                new Character("Talisa", "Maegyr", false), new Character("Gendry", null, false),
                new Character("Ygritte", null, false), new Character("Tormund", "Giantsbane", true),
                new Character("Gilly", null, true), new Character("Brienne", "Tarth", true, 32),
                new Character("Ramsay", "Bolton", true), new Character("Ellaria", "Sand", true),
                new Character("Daario", "Naharis", true), new Character("Missandei", null, true),
                new Character("Tommen", "Baratheon", true), new Character("Jaqen", "H'ghar", true),
                new Character("Roose", "Bolton", true), new Character("The High Sparrow", null, true));
    }
}
