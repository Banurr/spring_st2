package bitlab.spring.sprint2.services;

import bitlab.spring.sprint2.models.ApplicationRequest;
import bitlab.spring.sprint2.repositories.appreqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationRequestService {

    @Autowired
    private appreqRepository appreqRepository;

    public List<ApplicationRequest> allRequests(){
        return appreqRepository.findAll();
    }

    public void addRequest(ApplicationRequest applicationRequest){
        appreqRepository.save(applicationRequest);
    }

    public ApplicationRequest getAppRequest(Long id){
         return appreqRepository.findById(id).orElse(null);
    }

    public List<ApplicationRequest> filteredRequests(boolean type){
        return appreqRepository.getFiltered(type);
    }

    public void deleteRequest(Long id){
        appreqRepository.deleteById(id);
    }
    public void processRequest(Long id){
        appreqRepository.editRequest(id);
    }

}
