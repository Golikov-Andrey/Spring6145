package org.belotelov.hw4.service;

import lombok.AllArgsConstructor;
import org.belotelov.hw4.aspects.TrackUserAction;
import org.belotelov.hw4.model.Person;
import org.belotelov.hw4.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @TrackUserAction
    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }
    @TrackUserAction
    public Person getOne(int id) { return personRepository.getOne(id); }
    @TrackUserAction
    public void addPerson(Person person) {
        personRepository.save(person);
    }
    @TrackUserAction
    public void deletePersonById(int id) { personRepository.deleteById(id); }
    @TrackUserAction
    public Person updatePerson(Person person) { return personRepository.update(person); }
}
