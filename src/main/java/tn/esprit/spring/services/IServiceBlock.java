package tn.esprit.spring.services;

import tn.esprit.spring.entity.Block;
import tn.esprit.spring.entity.Classroom;

import java.util.List;

public interface IServiceBlock {
    public void addBlock(Block block);
    public void updateBlock(Block block);
    public void deleteBlock(int id);
    public List<Block> displayBlock();
}
