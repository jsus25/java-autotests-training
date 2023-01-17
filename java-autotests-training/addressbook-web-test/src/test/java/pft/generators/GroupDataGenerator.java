package pft.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import pft.model.GroupData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    String format = args[1].substring(args[1].lastIndexOf(".") + 1);
    List<GroupData> groups = generateGroups(count);
    switch (format) {
      case "csv" -> saveAsCsv(groups, file);
      case "xml" -> saveAsXml(groups, file);
      case "json" -> saveAsJson(groups, file);
      default -> System.out.println("Unknown file formal");
    }
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      GroupData group = new GroupData(null, String.format("group %s",i),String.format("header %s",i), String.format("footer %s",i));
      groups.add(group);
    }
    return groups;
  }
  private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {  //IO IOException - добавляется идеей при выборе того,
                                                                                    // как обрабатывать исключения в случае, если не будет возможности открыть файл на запись
    Writer writer = new FileWriter(file);  //открыть файл на запись
    for (GroupData group: groups) {
      writer.write(String.format("%s;%s;%s\n", group.name(), group.header(), group.footer()));
    }
    writer.close();    //обязательно закрыть файл
  }
  private static void saveAsJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(groups);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.alias("group", GroupData.class);   //необязательное определение названия полей, чтобы красиво называлось
    String xml = xStream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }


}
