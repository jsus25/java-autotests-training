package pft.generators;

import pft.model.GroupData;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);
    List<GroupData> groups = generateGroups(count);
    save(groups, file);
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      GroupData group = new GroupData(null, String.format("group %s",i),String.format("header %s",i), String.format("footer %s",i));
      groups.add(group);
    }
    return groups;
  }
  private static void save(List<GroupData> groups, File file) throws IOException {  //IO IOException - добавляется идеей при выборе того,
                                                                                    // как обрабатывать исключения в случае, если не будет возможности открыть файл на запись
    Writer writer = new FileWriter(file);  //открыть файл на запись
    for (GroupData group: groups) {
      writer.write(String.format("%s;%s;%s\n", group.name(), group.header(), group.footer()));
    }
    writer.close();    //обязательно закрыть файл
  }

}
