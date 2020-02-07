package com.example.sampleJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Person process(Person item) throws Exception {

    final String firstName = item.getFirstName().toUpperCase();
    final String lastName = item.getLastName().toUpperCase();

    final Person transformedPerson = new Person(lastName, firstName);

    log.info("Converting (" + item + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }
}
