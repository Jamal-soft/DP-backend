package ma.inpt.organisationService.organisation;

import ma.inpt.organisationService.organisation.entity.Organisation;
import ma.inpt.organisationService.organisation.model.request.OrganisationCreateRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganisationController {
    @Autowired
    OrganisationService organisationService;


    @GetMapping(path = "/organisations")
    public List<Organisation> getAllOrganisations(){
         return organisationService.getAllOrganisations();
    }

    @GetMapping(path = "/organisations/{id}")
    public Organisation getOrganisationById(@PathVariable Long id){
         return organisationService.getOrganisationById(id);
    }


    @PostMapping(path = "/organisations")
    public Organisation createOrganisation(@RequestBody OrganisationCreateRequestModel organisationCreateRequestModel){
         return organisationService.createOrganisation(organisationCreateRequestModel);
    }


    @GetMapping(path = "/organisations/verified")
    public List<Organisation> getOrganisationsVerified(){
        return organisationService.getOrganisationsVerified();
    }

    @GetMapping(path = "/organisations/notverified")
    public List<Organisation> getOrganisationsNotVerified(){
         return organisationService.getOrganisationsNotVerified();
    }


}
