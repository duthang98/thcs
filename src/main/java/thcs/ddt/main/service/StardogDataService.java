package thcs.ddt.main.service;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.SelectQuery;
import com.complexible.stardog.api.admin.AdminConnection;
import com.stardog.stark.query.SelectQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;


@Service
public class StardogDataService {

  @Autowired
  private StardogConnectionService connectionService = new StardogConnectionService();

  public void createDb(String dbName) {
    AdminConnection aAdminConnection = connectionService.getAdminConnection();
    if (aAdminConnection.list().contains(dbName)) {
      aAdminConnection.drop(dbName);
    }
    aAdminConnection.newDatabase(dbName).create();

  }

  public void loadDataset(String dbName, String... fileNames) {
    Connection conn = connectionService.getConnection(dbName);
    conn.begin();
    for (String fileName : fileNames) {
      conn.add().io().file(Paths.get(getFilePath(fileName)));
    }
    conn.commit();
  }

  public SelectQueryResult executeSelectQuery(String dbName, String sparql) {
    Connection conn = connectionService.getConnection(dbName);
    SelectQuery query = conn.select(sparql);
    return query.execute();

  }

  public void executeUpdateQuery(String dbName, String sparql) {
    Connection conn = connectionService.getConnection(dbName);
    conn.begin();
    conn.update(sparql).execute();
    conn.commit();
  }

  private String getFilePath(String fileName) {
    return fileName;
  }
}


