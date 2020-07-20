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
import thcs.ddt.main.model.POST;

import java.io.*;

@Controller
@RequestMapping("/")
public class Main {

  @GetMapping("/getAll")
  public ModelAndView getAll(ModelMap modelMap) {

    StardogController stardogController = new StardogController();

    Model model = createJena();
    createFileRDF(model);
    stardogController.loadRdfXmlFile("thcs");
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
    String selfURI = "dth512";
    String firstName = "Thang";
    String lastName = "Du";
    String familyName = "Duc";
    String nickName = "Maxi";
    String selfAge = "23";
    String selfEmail = "mailto:duthang98@gmail.com";
    String selfPhone = "0962774423";
    String selfGEO = "geo:21.044448, 105.794018";

    String friend1 = "truong.nexo";
    String firstNameF1 = "Trường";
    String lastNameF1 = "Nguyễn";
    String familyNameF1 = "Vân";
    String nickNameF1 = "nexo";
    String selfAgeF1 = "22";
    String selfEmailF1 = "mailto:nvt@gmail.com";
    String selfPhoneF1 = "00999000990";
    String selfGEOF1 = "geo:20.984180, 105.854459";

    String friend2 = "samnv.1998";
    String firstNameF2 = "Sâm";
    String lastNameF2 = "Nguyễn";
    String familyNameF2 = "Văn";
    String nickNameF2 = "NVS";
    String selfAgeF2 = "23";
    String selfEmailF2 = "mailto:nvs@gmail.com";
    String selfPhoneF2 = "0088877662";
    String selfGEOF2 = "geo:20.988730, 105.778862";

    String friend3 = "cong.thieu.92";
    String firstNameF3 = "Công";
    String lastNameF3 = "Thiều";
    String familyNameF3 = "Ngọc";
    String nickNameF3 = "TNC";
    String selfAgeF3 = "22";
    String selfEmailF3 = "mailto:tnc@gmail.com";
    String selfPhoneF3 = "0112321123";
    String selfGEOF3 = "geo:20.971435, 105.792656";

    Model model = ModelFactory.createDefaultModel();
    Model modelF1 = ModelFactory.createDefaultModel();
    Model modelF2 = ModelFactory.createDefaultModel();
    Model modelF3 = ModelFactory.createDefaultModel();
    Model last = ModelFactory.createDefaultModel();

    FACEBOOK facebookSelf = new FACEBOOK();
    facebookSelf.setUserId(selfURI);
    facebookSelf.setPost(new POST("1219190075090105", "hi ae", "geo:21.054428, 105.811305"));

    FACEBOOK facebook1 = new FACEBOOK();
    facebook1.setUserId(friend1);

    FACEBOOK facebook2 = new FACEBOOK();
    facebook2.setUserId(friend2);

    FACEBOOK facebook3 = new FACEBOOK();
    facebook3.setUserId(friend3);

    Resource mySelf = model.createResource(facebookSelf.getPROFILE_URI().toString());
    Resource friend_1 = modelF1.createResource(facebook1.getPROFILE_URI().toString());
    Resource friend_2 = modelF2.createResource(facebook2.getPROFILE_URI().toString());
    Resource friend_3 = modelF3.createResource(facebook3.getPROFILE_URI().toString());

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
            .addProperty(FOAF.based_near, model.createResource().addProperty(VCARD.GEO, model.createResource(selfGEO)))
            .addProperty(FOAF.schoolHomepage, ResourceFactory.createResource(schoolURI))
            .addProperty(FOAF.mbox, ResourceFactory.createResource(selfEmail));

    mySelf.addProperty(FOAF.made, model.createResource(facebookSelf.getPOST_URI().toString())
            .addProperty(VCARD.PRODID, facebookSelf.getPost().getId())
            .addProperty(FOAF.maker, mySelf)
            .addProperty(facebookSelf.getPOST_VALUE(), facebookSelf.getPost().getValue())
            .addProperty(facebookSelf.getPOST_LOCATION(), model.createResource().addProperty(VCARD.GEO, model.createResource(facebookSelf.getPost().getLocation())))
            .addProperty(facebookSelf.getPOST_TAG(), friend_1)
    );


    facebookSelf.setPost(new POST("1219190241756755", "hô nay tôi buồn •••• Ị", "geo:20.980859, 105.787993"));
    mySelf.addProperty(FOAF.made, model.createResource(facebookSelf.getPOST_URI().toString())
            .addProperty(VCARD.PRODID, facebookSelf.getPost().getId())
            .addProperty(FOAF.maker, mySelf)
            .addProperty(facebookSelf.getPOST_VALUE(), facebookSelf.getPost().getValue())
            .addProperty(facebookSelf.getPOST_LOCATION(), model.createResource().addProperty(VCARD.GEO, model.createResource(facebookSelf.getPost().getLocation())))
            .addProperty(facebookSelf.getPOST_TAG(), friend_1)
    );

    facebookSelf.setPost(new POST("1219190242356755", "Xin chao các bạnn", "geo:218.062561, 102.797786"));

    mySelf.addProperty(FOAF.made, model.createResource(facebookSelf.getPOST_URI().toString())
            .addProperty(VCARD.PRODID, facebookSelf.getPost().getId())
            .addProperty(FOAF.maker, mySelf)
            .addProperty(facebookSelf.getPOST_VALUE(), facebookSelf.getPost().getValue())
            .addProperty(facebookSelf.getPOST_LOCATION(), model.createResource().addProperty(VCARD.GEO, model.createResource(facebookSelf.getPost().getLocation())))
            .addProperty(facebookSelf.getPOST_TAG(), friend_2)
    )

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
}
