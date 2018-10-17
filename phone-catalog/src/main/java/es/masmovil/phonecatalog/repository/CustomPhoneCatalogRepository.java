package es.masmovil.phonecatalog.repository;

import org.springframework.data.mongodb.core.MongoOperations;

public class CustomPhoneCatalogRepository {

    private final MongoOperations mongoOperations;

    public CustomPhoneCatalogRepository(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }



}
