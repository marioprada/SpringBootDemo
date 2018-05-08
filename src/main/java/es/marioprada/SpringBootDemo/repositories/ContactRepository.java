package es.marioprada.SpringBootDemo.repositories;

import es.marioprada.SpringBootDemo.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, String> {
    @Override
    Contact findOne(String id);

    @Override
    void delete(Contact deleted);
}
