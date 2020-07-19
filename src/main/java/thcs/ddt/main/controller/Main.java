package thcs.ddt.main.controller;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import thcs.ddt.main.model.FACEBOOK;

import java.io.*;

@Controller
@RequestMapping("/")
public class Main {

  @GetMapping("/getAll")
  public ModelAndView getAll(ModelMap modelMap) {

    /*Model model = createJena();
    createFileRDF(model);
    stardogController.loadRdfXmlFile("thcs");*/
    return new ModelAndView("list", modelMap);
  }

  public void createFileRDF(Model model) {
    try (OutputStream out = new FileOutputStream("test.rdf")) {
      RDFDataMgr.write(out, model, Lang.RDFXML);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Model createJena() {
    String gender = "Male";

    String schoolURI = "https://portal.ptit.edu.vn/";
    String selfURI = "/dth512";
    String firstName = "Thang";
    String lastName = "Du";
    String familyName = "Duc";
    String nickName = "Maxi";
    String selfAge = "23";
    String selfEmail = "mailto:duthang98@gmail.com";
    String selfPhone = "0962774423";
    String selfGEO = "geo:21.062551, 105.797786";

    String post1 = "a";
    String post1Value = "Day la status a";
    String post1Location = "geo:21.062561, 102.797786";

    String post2 = "b";
    String post2Value = "Day la status a";
    String post2Location = "geo:21.062561, 102.7913786";

    String post3 = "c";
    String post3Value = "Day la status a";
    String post3Location = "geo:218.062561, 102.797786";

    String friend1 = "/friend-1";
    String firstNameF1 = "Mot";
    String lastNameF1 = "Nguyen";
    String familyNameF1 = "Van";
    String nickNameF1 = "NVM";
    String selfAgeF1 = "22";
    String selfEmailF1 = "mailto:nvm@gmail.com";
    String selfPhoneF1 = "00999000990";
    String selfGEOF1 = "geo:21.062561, 105.797786";

    String friend2 = "/friend-2";
    String firstNameF2 = "Hai";
    String lastNameF2 = "Nguyen";
    String familyNameF2 = "Van";
    String nickNameF2 = "NVH";
    String selfAgeF2 = "24";
    String selfEmailF2 = "mailto:nvh@gmail.com";
    String selfPhoneF2 = "0088877662";
    String selfGEOF2 = "geo:21.061551, 105.797786";

    String friend3 = "/friend-3";
    String firstNameF3 = "Ba";
    String lastNameF3 = "Nguyen";
    String familyNameF3 = "Van";
    String nickNameF3 = "NVB";
    String selfAgeF3 = "21";
    String selfEmailF3 = "mailto:nvb@gmail.com";
    String selfPhoneF3 = "0112321123";
    String selfGEOF3 = "geo:21.032551, 105.797786";

    Model model = ModelFactory.createDefaultModel();
    Model modelF1 = ModelFactory.createDefaultModel();
    Model modelF2 = ModelFactory.createDefaultModel();
    Model modelF3 = ModelFactory.createDefaultModel();
    Model last = ModelFactory.createDefaultModel();

    Resource mySelf = model.createResource(getURI(selfURI));
    Resource friend_1 = modelF1.createResource(getURI(friend1));
    Resource friend_2 = modelF2.createResource(getURI(friend2));
    Resource friend_3 = modelF3.createResource(getURI(friend3));

    model.add(mySelf, RDF.type, FOAF.Person);
    modelF1.add(friend_1, RDF.type, FOAF.Person);
    modelF2.add(friend_2, RDF.type, FOAF.Person);
    modelF3.add(friend_3, RDF.type, FOAF.Person);

    mySelf.addProperty(FOAF.name, model.createResource()
            .addProperty(FOAF.firstName, firstName)
            .addProperty(FOAF.givenName, model.createResource()
                    .addProperty(FOAF.lastName, lastName)
                    .addProperty(FOAF.familyName, familyName)))
            .addProperty(FOAF.age, selfAge)
            .addProperty(FOAF.phone, selfPhone)
            .addProperty(FOAF.nick, nickName)
            .addProperty(FOAF.gender, gender)
            .addProperty(FOAF.made, model.createResource(getPhotoURI(post1))
                    .addProperty(VCARD.PRODID, post1)
                    .addProperty(FOAF.maker, mySelf)
                    .addProperty(FACEBOOK.POST_VALUE, post1Value)
                    .addProperty(FACEBOOK.POST_LOCATION, model.createResource().addProperty(VCARD.GEO, model.createResource(post1Location)))
                    .addProperty(FACEBOOK.POST_TAG, friend_1)
            )
            .addProperty(FOAF.made, model.createResource(getPhotoURI(post2))
                    .addProperty(VCARD.PRODID, post2)
                    .addProperty(FOAF.maker, mySelf)
                    .addProperty(FACEBOOK.POST_VALUE, post2Value)
                    .addProperty(FACEBOOK.POST_LOCATION, model.createResource().addProperty(VCARD.GEO, model.createResource(post2Location)))
                    .addProperty(FACEBOOK.POST_TAG, friend_1)
            )
            .addProperty(FOAF.made, model.createResource(getPhotoURI(post3))
                    .addProperty(VCARD.PRODID, post3)
                    .addProperty(FOAF.maker, mySelf)
                    .addProperty(FACEBOOK.POST_VALUE, post3Value)
                    .addProperty(FACEBOOK.POST_LOCATION, model.createResource().addProperty(VCARD.GEO, model.createResource(post3Location)))
                    .addProperty(FACEBOOK.POST_TAG, friend_2)
            )
            .addProperty(FOAF.based_near, model.createResource().addProperty(VCARD.GEO, model.createResource(selfGEO)))
            .addProperty(FOAF.schoolHomepage, ResourceFactory.createResource(schoolURI))
            .addProperty(FOAF.mbox, ResourceFactory.createResource(selfEmail))
    ;

    last.add(mySelf, FOAF.knows, friend_1
            .addProperty(FOAF.name, modelF1.createResource()
                    .addProperty(FOAF.firstName, firstNameF1)
                    .addProperty(FOAF.givenName, modelF1.createResource()
                            .addProperty(FOAF.lastName, lastNameF1)
                            .addProperty(FOAF.familyName, familyNameF1)))
            .addProperty(FOAF.age, selfAgeF1)
            .addProperty(FOAF.phone, selfPhoneF1)
            .addProperty(FOAF.nick, nickNameF1)
            .addProperty(FOAF.gender, gender)
            .addProperty(FOAF.based_near, model.createResource().addProperty(VCARD.GEO, model.createResource(selfGEOF1)))
            .addProperty(FOAF.schoolHomepage, ResourceFactory.createResource(schoolURI))
            .addProperty(FOAF.mbox, ResourceFactory.createResource(selfEmailF1)));

    last.add(mySelf, FOAF.knows, friend_2
            .addProperty(FOAF.name, modelF2.createResource()
                    .addProperty(FOAF.firstName, firstNameF2)
                    .addProperty(FOAF.givenName, modelF2.createResource()
                            .addProperty(FOAF.lastName, lastNameF2)
                            .addProperty(FOAF.familyName, familyNameF2)))
            .addProperty(FOAF.age, selfAgeF2)
            .addProperty(FOAF.phone, selfPhoneF2)
            .addProperty(FOAF.nick, nickNameF2)
            .addProperty(FOAF.gender, gender)
            .addProperty(FOAF.based_near, model.createResource().addProperty(VCARD.GEO, model.createResource(selfGEOF2)))
            .addProperty(FOAF.schoolHomepage, ResourceFactory.createResource(schoolURI))
            .addProperty(FOAF.mbox, ResourceFactory.createResource(selfEmailF2)));

    last.add(mySelf, FOAF.knows, friend_3
            .addProperty(FOAF.name, modelF3.createResource()
                    .addProperty(FOAF.firstName, firstNameF3)
                    .addProperty(FOAF.givenName, modelF3.createResource()
                            .addProperty(FOAF.lastName, lastNameF3)
                            .addProperty(FOAF.familyName, familyNameF3)))
            .addProperty(FOAF.age, selfAgeF3)
            .addProperty(FOAF.phone, selfPhoneF3)
            .addProperty(FOAF.nick, nickNameF3)
            .addProperty(FOAF.gender, gender)
            .addProperty(FOAF.based_near, model.createResource().addProperty(VCARD.GEO, model.createResource(selfGEOF3)))
            .addProperty(FOAF.schoolHomepage, ResourceFactory.createResource(schoolURI))
            .addProperty(FOAF.mbox, ResourceFactory.createResource(selfEmailF3)));

    last.add(model);
    last.add(modelF1);
    last.add(modelF2);
    last.add(modelF3);

    return last;
  }

  private String getURI(String id) {
    return FACEBOOK.uri + id;
  }

  private String getPhotoURI(String id) {
    return FACEBOOK.POST_URI + id;
  }
}
