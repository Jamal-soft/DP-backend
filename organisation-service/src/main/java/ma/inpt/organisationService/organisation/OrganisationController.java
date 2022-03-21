package ma.inpt.organisationService.organisation;

import ma.inpt.organisationService.organisation.entity.Organisation;
import ma.inpt.organisationService.organisation.model.response.OrganisationResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganisationController {
    @Autowired
    OrganisationService organisationService;


    @GetMapping(path = "/organisations")
    public List<OrganisationResponseModel> getAllOrganisations(){
         return organisationService.getAllOrganisations();
    }

    @GetMapping(path = "/organisations/{id}")
    public OrganisationResponseModel getOrganisationById(@PathVariable Long id){
         return organisationService.getOrganisationById(id);
    }

    @GetMapping(path = "/organisations/verified")
    public List<OrganisationResponseModel> getOrganisationsVerified(){
        return organisationService.getOrganisationsVerified();
    }

    @GetMapping(path = "/organisations/notverified")
    public List<OrganisationResponseModel> getOrganisationsNotVerified(){
         return organisationService.getOrganisationsNotVerified();
    }
    @DeleteMapping (path = "/organisations/{orgId}")
    public String deleteOrganisationById(@PathVariable(name = "orgId") Long orgId){
        return organisationService.deleteOrganisationById(orgId);
    }
    @PatchMapping  (path = "/organisations/{orgId}")
    public String acceptOrganisation(@PathVariable(name = "orgId") Long orgId){
        return organisationService.acceptOrganisation(orgId);
    }


}
