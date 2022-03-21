package ma.inpt.donation;


import ma.inpt.model.DonationRequestModel;

import ma.inpt.model.DonationResponseModelToCalculateSumOfDonations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //DonationEntity donationEntity = modelMapper.map(donationRequestModel,DonationEntity.class);
        DonationEntity donationEntity = new DonationEntity();
        donationEntity.setDonorId(donationRequestModel.getDonorId());
        donationEntity.setOrgId(donationRequestModel.getOrgId());
        donationEntity.setAmount(donationRequestModel.getAmount());
        donationEntity.setProjectId(donationRequestModel.getProjectId());
        donationEntity.setDate(new Date());
        donationRepository.save(donationEntity);
        return donationRepository.save(donationEntity);
    }

    public List<DonationResponseModelToCalculateSumOfDonations> getSomeOfDonationsPerDonors() {
        List list = donationRepository.getSomeOfDonationsPerDonors();

        return list;
    }

    public Long getTotalSomeOfDonations() {
        return donationRepository.getTotalSomeOfDonations();
    }
}
