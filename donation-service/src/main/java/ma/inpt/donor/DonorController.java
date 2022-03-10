package ma.inpt.donor;

import ma.inpt.model.DonorModelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DonorController {
    @Autowired
    DonorService donorService;

    @GetMapping("/donors")
    public List<DonorModelResponse> getDonors(){
        return donorService.getDonors();
    }

    @GetMapping("/donors/{id}")
    public DonorModelResponse getDonorById(@PathVariable Long id){
        return donorService.getDonorById(id);
    }

}
