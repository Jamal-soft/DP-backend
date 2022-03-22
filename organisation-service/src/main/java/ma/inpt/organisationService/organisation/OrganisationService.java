package ma.inpt.organisationService.organisation;

import ma.inpt.organisationService.model.request.OrganisationUpdateRequestModel;
import ma.inpt.organisationService.organisation.entity.Organisation;
import ma.inpt.organisationService.organisation.model.response.OrganisationResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganisationService {
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<OrganisationResponseModel> getAllOrganisations() {
        ModelMapper modelMapper = new ModelMapper();
        List<OrganisationResponseModel> organisationResponseModels = new ArrayList<>();
        List<Organisation> organisation = organisationRepository.findAll();
        organisation.forEach(p->{
            organisationResponseModels.add(modelMapper.map(p,OrganisationResponseModel.class));
        });
        return organisationResponseModels;

    }


    public OrganisationResponseModel getOrganisationById(Long id) {
        Organisation organisation = organisationRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(organisation,OrganisationResponseModel.class);


    }
    public List<OrganisationResponseModel> getOrganisationsVerified() {
        List<Organisation> organisations = organisationRepository.findByVerified(true);
        ModelMapper modelMapper = new ModelMapper();
        List<OrganisationResponseModel> organisationResponseModels = new ArrayList<>();
        organisations.forEach(p->{
            organisationResponseModels.add(modelMapper.map(p,OrganisationResponseModel.class));
        });
        return organisationResponseModels;
    }

    public List<OrganisationResponseModel> getOrganisationsNotVerified() {
        List<Organisation> organisations = organisationRepository.findByVerified(false);
        ModelMapper modelMapper = new ModelMapper();
        List<OrganisationResponseModel> organisationResponseModels = new ArrayList<>();
        organisations.forEach(p->{
            organisationResponseModels.add(modelMapper.map(p,OrganisationResponseModel.class));
        });
        return organisationResponseModels;
    }


    public String deleteOrganisationById(Long orgId) {
        try {
            organisationRepository.deleteById(orgId);
        }catch (Exception e){
            return e.getMessage();
        }
        return "organisation with id"+ orgId +" deleted succesfully";
    }

    public String acceptOrganisation(Long orgId) {
        try {
            Organisation org = organisationRepository.findById(orgId).get();
            org.setVerified(true);
            organisationRepository.save(org);
        }catch (Exception e){
            return e.getMessage();
        }
        return "organisation with id "+orgId + " accepted";
    }

    public String updateOrganisation(Long orgId, OrganisationUpdateRequestModel organisationUpdateRequestModel) {
        Organisation organisation = organisationRepository.findById(orgId).get();
        if (organisation!=null){
            if (organisationUpdateRequestModel.getName()!=null)
            organisation.setName(organisationUpdateRequestModel.getName());
            if (organisation.getCategory()!=null)
            organisation.setCategory(organisation.getCategory());
            if (organisationUpdateRequestModel.getDescription()!=null)
            organisation.setDescription(organisationUpdateRequestModel.getDescription());
            if (organisationUpdateRequestModel.getLocation()!=null)
            organisation.setLocation(organisationUpdateRequestModel.getLocation());
            if (organisationUpdateRequestModel.getPhoneNumber()!=null)
            organisation.setPhoneNumber(organisationUpdateRequestModel.getPhoneNumber());
            if (organisationUpdateRequestModel.getPassword()!=null){
                organisation.setEncryptedPassword(bCryptPasswordEncoder.encode(organisationUpdateRequestModel.getPassword()));
            }
            organisationRepository.save(organisation);
            return "organisation succesfully updated";

        }
        return "organisation not found";

    }
}
