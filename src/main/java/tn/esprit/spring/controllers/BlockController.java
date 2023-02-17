package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Block;
import tn.esprit.spring.services.IServiceBlock;

import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private IServiceBlock serviceBlock;
    @PostMapping("/saveBlock")
    public void saveBlock(@RequestBody Block block){serviceBlock.addBlock(block);
    }
    @PutMapping("/updateBlock")
    public void updateBlock(@RequestBody Block block){serviceBlock.updateBlock(block);
    }
    @DeleteMapping("/deleteBlock/{id}")
    public void updateBlock(@PathVariable int id){
        serviceBlock.deleteBlock(id);
    }
    @GetMapping("/findAll")
    public List<Block> findAll(){
        return serviceBlock.displayBlock();
    }
}
