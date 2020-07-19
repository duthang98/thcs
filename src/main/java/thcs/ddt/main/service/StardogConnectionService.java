package thcs.ddt.main.service;

import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.ConnectionConfiguration;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StardogConnectionService {
    @Value("${stardog.useEmbeddedServer}")
    private Boolean useEmbeddedServer;
    private String remoteServer = "http://localhost:5820";
    private String user = "admin";
    private String password = "admin";

    public final AdminConnection getAdminConnection() {
        if (Boolean.TRUE.equals(useEmbeddedServer)) {
            return AdminConnectionConfiguration
                    .toEmbeddedServer()
                    .credentials(user, password)
                    .connect();
        } else {
            return AdminConnectionConfiguration
                    .toServer(remoteServer)
                    .credentials(user, password)
                    .connect();
        }
    }

    public final Connection getConnection(String dbName) {
        final ConnectionConfiguration connConfig = ConnectionConfiguration
                .to(dbName)
                .credentials(user, password);
        if (!Boolean.TRUE.equals(useEmbeddedServer)) {
            connConfig.server(remoteServer)
                    .reasoning(true);
        }
        return connConfig.connect();
    }
}
