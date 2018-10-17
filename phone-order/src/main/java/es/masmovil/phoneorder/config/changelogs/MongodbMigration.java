package es.masmovil.phoneorder.config.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog
public class MongodbMigration {

    private static final String MIGRATION_USER = "migrationUser";

    @ChangeSet(order = "001", id = "initDb", author = MIGRATION_USER)
    public void initDb(final MongoTemplate db) {

    }

}
