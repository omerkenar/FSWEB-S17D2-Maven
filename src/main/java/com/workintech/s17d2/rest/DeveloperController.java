package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.DeveloperFactory;
import com.workintech.s17d2.model.JuniorDeveloper;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    public Map<Integer, Developer> developers;

    private Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable){
        this.taxable=taxable;
    }

    @PostConstruct
    public void init(){
        this.developers=new HashMap<>();
        this.developers.put(1,new JuniorDeveloper(1, "ömer", 17002.12));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer){
        Developer createdDeveloper = DeveloperFactory.creatDeveloper(developer, taxable);

        if (Objects.nonNull(createdDeveloper)){
            developers.put(createdDeveloper.getId(), createdDeveloper);
        }

        return new DeveloperResponse(createdDeveloper, HttpStatus.CREATED.value(), "create işlemi başaralı...");
    }

    @GetMapping
    public List<Developer> getAll(){
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable("id") int id){
        Developer foundDeveloper = this.developers.get(id);
        if (foundDeveloper == null){
            return new DeveloperResponse(null, HttpStatus.NOT_FOUND.value(), id + " id kayıtlarda bulunamadı...");
        }

        return new DeveloperResponse(foundDeveloper, HttpStatus.OK.value(), id + " id kayıtlarda bulunmuştur.");
    }

    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable("id") int id , @RequestBody Developer developer){
        developer.setId(id);

        Developer newDeveloper = DeveloperFactory.creatDeveloper(developer,taxable);

        this.developers.put(id, newDeveloper);

        return new DeveloperResponse(newDeveloper, HttpStatus.OK.value(), id + " id'li kayıt başarılıdır.");
    }

    @DeleteMapping("/{id}")
    public DeveloperResponse delete(@PathVariable("id") int id){
        Developer removedDeveloper = this.developers.get(id);

        this.developers.remove(id);

        return new DeveloperResponse(removedDeveloper, HttpStatus.NO_CONTENT.value(), id + "id'li kayıt silinmiştir.");
    }

}
