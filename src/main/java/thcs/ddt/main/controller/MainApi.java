package thcs.ddt.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thcs.ddt.main.model.RDFForm;
import thcs.ddt.main.service.StardogConnectionImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MainApi {

  @Autowired
  private StardogConnectionImpl stardogConnection = new StardogConnectionImpl();

  @GetMapping("/getAll")
  List<RDFForm> getAll(@RequestParam(name = "subject") String parameter) {
    return stardogConnection.getAll("thcs", parameter);
  }
}
