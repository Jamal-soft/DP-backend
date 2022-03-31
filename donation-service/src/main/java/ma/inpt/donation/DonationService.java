package ma.inpt.donation;


import ma.inpt.feignClients.ProjectFeignClient;
import ma.inpt.model.DonationRequestModel;

import ma.inpt.model.DonationResponseModelToCalculateSumOfDonations;
import ma.inpt.model.DonationResponseModelToShowInDashboardOfADonor;
import ma.inpt.model.DonationResponseSumOfDonationsOfADonorPerOrganisation;
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
    @Autowired
    ProjectFeignClient projectFeignClient;

    public List<DonationEntity> getDonationByOrgId(Long id) {
        return donationRepository.findAllByOrgId(id);
    }

    public DonationEntity createDonation(DonationRequestModel donationRequestModel) {
        DonationEntity donationEntity = new DonationEntity();
        donationEntity.setDonorId(donationRequestModel.getDonorId());
        donationEntity.setOrgId(donationRequestModel.getOrgId());
        donationEntity.setAmount(donationRequestModel.getAmount());
        donationEntity.setProjectId(donationRequestModel.getProjectId());
        donationEntity.setDate(new Date());
        donationRepository.save(donationEntity);
        String str = projectFeignClient.updateCurrentBalance(donationRequestModel.getProjectId(),donationRequestModel.getAmount());
        System.out.print(str);
        return donationRepository.save(donationEntity);
    }

    public List<DonationResponseModelToCalculateSumOfDonations> getSomeOfDonationsPerDonors() {
        List list = donationRepository.getSomeOfDonationsPerDonors();

        return list;
    }

    public Long getTotalSomeOfDonations() {
        return donationRepository.getTotalSomeOfDonations();
    }

    public List<DonationResponseModelToShowInDashboardOfADonor> getDonationsOfADonorAndShowItInItsDashboard(Long donorId) {
        return donationRepository.getDonationsOfADonorAndShowItInItsDashboard(donorId);
    }

    public List<DonationResponseSumOfDonationsOfADonorPerOrganisation> getSumOfDonationsOfADonorPerOrganisation(Long donorId) {
        return donationRepository.getSumOfDonationsOfADonorPerOrganisation(donorId);
    }
}
