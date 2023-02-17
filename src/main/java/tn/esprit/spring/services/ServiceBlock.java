package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.repositories.BlockRepository;
import tn.esprit.spring.repositories.ClassroomRepository;

import java.util.List;

@Service
public class ServiceBlock implements IServiceBlock {
    @Autowired
    private BlockRepository blockRepository;
    @Override
    public void addBlock(Block block) {blockRepository.save(block);}

    @Override
    public void updateBlock(Block block) { blockRepository.save(block);}

    @Override
    public void deleteBlock(int id) { blockRepository.deleteById(id);}

    @Override
    public List<Block> displayBlock() { return blockRepository.findAll();

    }
}
