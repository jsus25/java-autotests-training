package pft.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pft.model.ContactData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    String format = args[1].substring(args[1].lastIndexOf(".") + 1);  //определяем формат файла с данными
    List<ContactData> contacts = generateContacts(count);
    switch (format) {
      case "xml" -> saveAsXml(contacts, file);
      case "json" -> saveAsJson(contacts, file);
      default -> System.out.println("Unknown file formal");
    }
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      ContactData contact = new ContactData(String.format("Ivan%s", i), String.format("Balabanov%s", i),
              null, null, null, String.format("8956784573%s", i),
              String.format("+7(913)098 54-5%s", i), null, null);
      contacts.add(contact);
    }
    return contacts;
  }

  private static void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try ( Writer writer = new FileWriter(file)) {   //обернуто в try - для автоматического закрытия файла
      writer.write(json);
    }
  }

  private static void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xStream = new XStream();
//    xStream.alias("contact", ContactData.class);   //необязательное определение названия полей, чтобы красиво называлось
    String xml = xStream.toXML(contacts);
    try ( Writer writer = new FileWriter(file)) {   //обернуто в try - для автоматического закрытия файла
      writer.write(xml);
    }
  }


}
