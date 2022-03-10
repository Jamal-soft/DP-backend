package ma.inpt.donation;

import ma.inpt.donor.DonorEntity;
import ma.inpt.model.DonationRequestModel;
import ma.inpt.model.DonorModelResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DonationService {
    @Autowired
    DonationRepository donationRepository;

    public List<DonationEntity> getDonationByOrgId(Long id) {
        return donationRepository.findAllByOrgId(id);
    }

    public DonationEntity createDonation(DonationRequestModel donationRequestModel) {
        ModelMapper modelMapper  = new ModelMapper();
        DonationEntity donationEntity = modelMapper.map(donationRequestModel,DonationEntity.class);
        donationEntity.setDate(new Date());
        DonationEntity donationEntity1 = donationRepository.save(donationEntity);
        return donationEntity1;
    }
}
