package es.masmovil.phonecatalog.config.changelogs;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import es.masmovil.phonecatalog.entity.Phone;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.math.BigDecimal;
import java.net.URI;

@ChangeLog
public class MongodbMigration {

    private static final String MIGRATION_USER = "migrationUser";

    @ChangeSet(order = "001", id = "initDb", author = MIGRATION_USER)
    public void initDb(final MongoTemplate db) {
        final Phone samsungGalaxyS8 = new Phone();
        samsungGalaxyS8.setName("Samsung Galaxy S8");
        samsungGalaxyS8.setDescription("Unlocked 64GB - US Version (Midnight Black) - US Warranty");
        samsungGalaxyS8.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/61tpPLeyBBL._SX679_.jpg"));
        samsungGalaxyS8.setPrice(BigDecimal.valueOf(499.99));
        db.save(samsungGalaxyS8);

        final Phone essentialPhone = new Phone();
        essentialPhone.setName("Essential Phone");
        essentialPhone.setDescription("128 GB Unlocked with Full Display, Dual Camera – Black Moon");
        essentialPhone.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/81OvOMun5zL._SX569_.jpg"));
        essentialPhone.setPrice(BigDecimal.valueOf(339.99));
        db.save(essentialPhone);

        final Phone samsungGalaxyS9 = new Phone();
        samsungGalaxyS9.setName("Samsung Galaxy S9");
        samsungGalaxyS9.setDescription("Unlocked Smartphone - Midnight Black - US Warranty");
        samsungGalaxyS9.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/81%2Bh9mpyQmL._SX679_.jpg"));
        samsungGalaxyS9.setPrice(BigDecimal.valueOf(535.99));
        db.save(samsungGalaxyS9);

        final Phone appleiPhone7Plus = new Phone();
        appleiPhone7Plus.setName("Apple iPhone 7 Plus");
        appleiPhone7Plus.setDescription("Fully Unlocked, 128GB (Certified Refurbished)");
        appleiPhone7Plus.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/71qoijN2JuL._SX679_.jpg"));
        appleiPhone7Plus.setPrice(BigDecimal.valueOf(499.99));
        db.save(appleiPhone7Plus);

        final Phone appleiPhone6sPlus = new Phone();
        appleiPhone6sPlus.setName("Apple iPhone 6s Plus");
        appleiPhone6sPlus.setDescription("Fully Unlocked, 64GB - Space Gray (Certified Refurbished)");
        appleiPhone6sPlus.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/61ffLa3YobL._SX679_.jpg"));
        appleiPhone6sPlus.setPrice(BigDecimal.valueOf(329.99));
        db.save(appleiPhone6sPlus);

        final Phone nokia3220b = new Phone();
        nokia3220b.setName("Nokia 3220b");
        nokia3220b.setDescription("GSM Unlocked");
        nokia3220b.setImage(URI.create("https://images-na.ssl-images-amazon.com/images/I/51t4YswhY0L.jpg"));
        nokia3220b.setPrice(BigDecimal.valueOf(23.99));
        db.save(nokia3220b);
    }

}
