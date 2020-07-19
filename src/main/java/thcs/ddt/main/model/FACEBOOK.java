package thcs.ddt.main.model;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class FACEBOOK {

  public static final String uri ="https://www.facebook.com";

  public static String getURI() {
    return uri;
  }

  private static final Model m = ModelFactory.createDefaultModel();

  public static final Resource POST_URI = m.createResource("https://www.facebook.com/photo.php?fbid=" );
  public static final Property POST_VALUE = m.createProperty("https://www.facebook.com#value" );
  public static final Property POST_TAG = m.createProperty("https://www.facebook.com#tag" );
  public static final Property POST_LOCATION = m.createProperty("https://www.facebook.com#location" );

}

