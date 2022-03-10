package ma.inpt.donor;

import ma.inpt.model.DonorModelResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonorService {
    @Autowired
    DonorRepository donorRepository;

    public List<DonorModelResponse> getDonors() {
        ModelMapper modelMapper  = new ModelMapper();
        List<DonorEntity> donorsEntity =  donorRepository.findAll();
        List<DonorModelResponse> donorModelResponse = new ArrayList<>();

        donorsEntity.forEach(p ->{
            donorModelResponse.add(modelMapper.map(p,DonorModelResponse.class));
        });
    return donorModelResponse;
    }

    public DonorModelResponse getDonorById(Long id) {
        ModelMapper modelMapper  = new ModelMapper();
        DonorEntity donorEntity = donorRepository.findById(id).get();
        DonorModelResponse donorModelResponse = modelMapper.map(donorEntity,DonorModelResponse.class);
        return donorModelResponse;

    }
}
