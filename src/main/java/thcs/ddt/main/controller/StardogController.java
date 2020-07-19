package thcs.ddt.main.controller;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import thcs.ddt.main.service.StardogDataService;

@RestController
public class StardogController {

    @Autowired
    private StardogDataService dataService = new StardogDataService();

    @RequestMapping(value = "/load-rdfxml", produces = "text/plain")
    public String loadRdfXmlFile(@RequestParam(value = "dbName", defaultValue = "rdfxml-db") final String dbName) {
        return loadDataset(dbName,"test.rdf");
    }


    private String loadDataset(String dbName, String... fileNames) {
        String result;

        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            dataService.createDb(dbName);
            outputStream.write(String.format("Successfully created database '%s'.\n\n", dbName).getBytes());

            dataService.loadDataset(dbName, fileNames);
            outputStream.write(String.format("Loaded file '%s' to database '%s'\n\n",
                    StringUtils.join(fileNames, ","), dbName).getBytes());

            result = outputStream.toString();

        } catch (Exception e) {
            result = "Exception found loading dataset into Stardog: " + e.getMessage();
        }

        return result;
    }
}
