package pft.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pft.model.ContactData;

import java.util.List;

public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testHbConnection() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00 00:00:00'", ContactData.class ).list();
    session.getTransaction().commit();
    session.close();
    for (ContactData contact : result) {
      System.out.println(contact);
      System.out.println(contact.getGroups());
    }
  }

  @Test
  public void testHbAddGroupToDb() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
  //  session.save( new GroupData(300,"group88", "header88", "footer88"));
  //  session.createSQLQuery("INSERT INTO `group_list` (`domain_id`, `group_id`, `group_parent_id`, `created`, `modified`, `deprecated`, `group_name`, `group_header`, `group_footer`) VALUES ('0', NULL, NULL, NULL, NULL, '', 'g3', 'h3', 'f3')");
    session.getTransaction().commit();
    session.close();
  }

//     session.save( new ContactData("Juliett", "Suslenkova", "Corporation", null, null, "89567845736", null, null));
}
