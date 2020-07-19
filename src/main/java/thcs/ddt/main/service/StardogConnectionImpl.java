package thcs.ddt.main.service;

import com.stardog.stark.query.SelectQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thcs.ddt.main.model.RDFForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StardogConnectionImpl {

  @Autowired
  private StardogDataService dataService = new StardogDataService();

  public List<RDFForm> getAll(String dbName, String parameter) {
    String sparql = "SELECT ?s ?p ?o WHERE { ?s ?p ?o }";
    SelectQueryResult result = dataService.executeSelectQuery(dbName, sparql);

    List<RDFForm> rdfForms = new ArrayList<>();

    result.stream().forEach(bindings -> rdfForms.add(new RDFForm(
            Optional.of(bindings.get("s").toString()),
            Optional.of(bindings.get("p").toString()),
            Optional.of(regexString(bindings.get("o").toString()))))
    );

    if (!parameter.isEmpty() || parameter != "") {
      String getOne = "SELECT ?s ?p ?o WHERE { <" + parameter + "> ?p ?o }";
      SelectQueryResult resultOne = dataService.executeSelectQuery(dbName, getOne);

      List<RDFForm> rdfFormsOne = new ArrayList<>();

      resultOne.stream().forEach(bindings -> rdfFormsOne.add(new RDFForm(
              Optional.of(parameter),
              Optional.of(bindings.get("p").toString()),
              Optional.of(regexString(bindings.get("o").toString()))))
      );

      List<RDFForm> rdfFormsOut = new ArrayList<>();

      for (RDFForm rdfForm : rdfFormsOne
      ) {
        if (!rdfForm.getObject().startsWith("_:bnode")) {
          rdfFormsOut.add(rdfForm);
        } else {
          for (int i = 0; i < rdfForms.size(); i++) {
            if (rdfForm.getObject().equals(rdfForms.get(i).getSubject())) {
              rdfFormsOut.add(new RDFForm(
                      Optional.of(rdfForm.getSubject()),
                      Optional.of(rdfForms.get(i).getPredict()),
                      Optional.of(rdfForms.get(i).getObject())));
            }
          }
        }
      }

      List<RDFForm> rdfFormsLast = new ArrayList<>();

      for (RDFForm rdfForm : rdfFormsOut
      ) {
        if (!rdfForm.getObject().startsWith("_:bnode")) {
          rdfFormsLast.add(rdfForm);
        } else {
          for (int i = 0; i < rdfForms.size(); i++) {
            if (rdfForm.getObject().equals(rdfForms.get(i).getSubject())) {
              rdfFormsLast.add(new RDFForm(
                      Optional.of(rdfForm.getSubject()),
                      Optional.of(rdfForms.get(i).getPredict()),
                      Optional.of(rdfForms.get(i).getObject())));
            }
          }
        }
      }
      return rdfFormsLast.stream().filter(rdfForm -> !"http://www.w3.org/2002/07/owl#Thing".equals(rdfForm.getObject())).collect(Collectors.toList());
    } else
      return rdfForms.stream().filter(rdfForm -> !"http://www.w3.org/2002/07/owl#Thing".equals(rdfForm.getObject())).collect(Collectors.toList());
  }

  private String regexString(String text) {
    String resultStr = "";
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != 34 && text.charAt(i) != 94) {
        resultStr = resultStr + text.charAt(i);
      }
    }
    return resultStr;
  }
}
