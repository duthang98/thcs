package thcs.ddt.main.model;

import java.util.Optional;

public class RDFForm {

  private String subject;
  private String predict;
  private String object;

  public RDFForm(Optional<String> subject, Optional<String> predict, Optional<String> object) {
    this.subject = subject.orElse("none");
    this.predict = predict.orElse("none");
    this.object = object.orElse("none");
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getPredict() {
    return predict;
  }

  public void setPredict(String predict) {
    this.predict = predict;
  }

  public String getObject() {
    return object;
  }

  public void setObject(String object) {
    this.object = object;
  }

}
