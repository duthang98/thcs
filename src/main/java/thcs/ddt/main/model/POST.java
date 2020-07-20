package thcs.ddt.main.model;

public class POST {
  private String id;
  private String value;
  private String location;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public POST(String id, String value, String location) {
    this.id = id;
    this.value = value;
    this.location = location;
  }
}
