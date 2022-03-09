package ma.inpt.organisationService.organisation;

import ma.inpt.organisationService.organisation.entity.Organisation;
import ma.inpt.organisationService.organisation.model.request.OrganisationCreateRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {
    @Autowired
    OrganisationRepository organisationRepository;

    public List<Organisation> getAllOrganisations() {
        return organisationRepository.findAll();

    }

    public Organisation createOrganisation(OrganisationCreateRequestModel organisationCreateRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        Organisation organisation = modelMapper.map(organisationCreateRequestModel, Organisation.class);
        Organisation organisation1 = null;

        try{
            organisation1 = organisationRepository.save(organisation);
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
        return organisation1;

    }

    public Organisation getOrganisationById(Long id) {
        Organisation organisation = organisationRepository.findById(id).get();
        return organisation;
    }
    public List<Organisation> getOrganisationsVerified() {
        List<Organisation> organisations = organisationRepository.findByVerified(true);
        return organisations;
    }

    public List<Organisation> getOrganisationsNotVerified() {
        List<Organisation> organisations = organisationRepository.findByVerified(false);
        return organisations;
    }


}
