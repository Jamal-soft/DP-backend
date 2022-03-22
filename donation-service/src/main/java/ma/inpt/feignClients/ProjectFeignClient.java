package ma.inpt.feignClients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(url = "http://localhost:8081/organisations", name = "organisation-service"/*, value = "api-gateway"*/)
public interface ProjectFeignClient {
    @PutMapping("/projects/current-balance/update/{projectId}/{amount}")
    //@Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJka2RsanFoc2RrbGpocXNkIiwiZXhwIjoxNjQ4NjczNTUwfQ.2_97j_2b6acKdyvtdttHf3h9d6-8BbCqGjOHBOChSHT15733UdEaDWq1nALD0jlwbPcWiM9o7lVnwvj-iydbuA")
    public String updateCurrentBalance(@PathVariable("projectId") Long projectId, @PathVariable("amount") Long amount);
}
