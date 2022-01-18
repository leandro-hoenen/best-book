package ch.fhnw.acrm.business.service;

import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.VideoGame;
import ch.fhnw.acrm.data.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class VideoGameService {
    @Autowired
    private VideoGameRepository videoGameRepository;
    @Autowired
    private AgentService agentService;

    public VideoGame addVideoGame(VideoGame videoGame) {
        return videoGameRepository.save(videoGame);
    }

    public List<VideoGame> getMyVideoGames() {
        return videoGameRepository.findAll();
    }

    public void deleteVideoGame(Long videoGameId) {
        videoGameRepository.deleteById(videoGameId);
    }

//    public VideoGame editEdit(@Valid VideoGame videoGame) throws Exception {
//        if (customer.getId() == null) {
//            if (customerRepository.findByMobile(customer.getMobile()) == null) {
//                customer.setAgent(agentService.getCurrentAgent());
//                return customerRepository.save(customer);
//            }
//            throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
//        }
//        if (customerRepository.findByMobileAndIdNot(customer.getMobile(), customer.getId()) == null) {
//            if (customer.getAgent() == null) {
//                customer.setAgent(agentService.getCurrentAgent());
//            }
//            return customerRepository.save(customer);
//        }
//        throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
//    }

    // TODO: implement edit videoGame
}
