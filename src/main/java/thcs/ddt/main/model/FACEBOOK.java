package thcs.ddt.main.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class FACEBOOK {

  public static final String uri = "https://www.facebook.com";
  private String userId;
  private POST post;

  public POST getPost() {
    return post;
  }

  public void setPost(POST post) {
    this.post = post;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  private final Model m = ModelFactory.createDefaultModel();

  public Resource getPROFILE_URI(){
    return m.createResource(uri + "/" + this.getUserId());
  }

  public Resource getPOST_URI(){
    return m.createResource(uri + "/" + getUserId() + "/post/" + post.getId());
  }

  public Property getPOST_VALUE(){
    return m.createProperty(uri + "/" + getUserId() + "/post/" + post.getId() + "#value");
  }

  public Property getPOST_TAG(){
    return m.createProperty(uri + "/" + getUserId() + "/post/" + post.getId() + "#tag");
  }

  public Property getPOST_LOCATION(){
    return m.createProperty(uri + "/" + getUserId() + "/post/" + post.getId() + "#location");
  }

}

