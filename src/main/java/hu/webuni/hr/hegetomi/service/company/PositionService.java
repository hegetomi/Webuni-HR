package hu.webuni.hr.hegetomi.service.company;

import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Transactional
    public Position save(Position position){
        return positionRepository.save(position);
    }

}
