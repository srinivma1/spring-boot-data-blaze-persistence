package com.blazebit.spring.data.test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import com.blazebit.persistence.integration.view.spring.EnableEntityViews;
import com.blazebit.persistence.spring.data.impl.repository.BlazePersistenceRepositoryFactoryBean;
import com.blazebit.spring.data.test.model.Husband;
import com.blazebit.spring.data.test.model.Wife;
import com.blazebit.spring.data.test.repository.WifeRepository;
import com.blazebit.spring.data.test.view.WifeSimpleView;



@SpringBootApplication
@EnableEntityViews(basePackages = { "com.blazebit.spring.data.test.view"})
@EnableJpaRepositories(
       basePackages = "com.blazebit.spring.data.test.repository",
       repositoryFactoryBeanClass = BlazePersistenceRepositoryFactoryBean.class)

public class SpringJpaOneToOneApplication implements CommandLineRunner{	
	
	@Autowired
    WifeRepository wifeRepository;

     
    
   

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaOneToOneApplication.class, args);
	}

	@Override
    public void run(String... arg0) throws Exception {
    	//deleteData();
    	saveData();
    	showData();
    }
    
   
    
    @Transactional
    private void saveData(){
    	// Store a wife to DB
        Wife lisa = new Wife("Lisa", new Husband("David"));
      wifeRepository.save(lisa);

        // Store list wifes to DB
        Wife mary = new Wife("Mary", new Husband("Peter"));
 
        Wife lauren = new Wife("Lauren", new Husband("Phillip"));
        Wife lauren1 = new Wife("Lauren1", new Husband("Phillip1"));
        Wife lauren2 = new Wife("Lauren2", new Husband("Phillip2"));
        Wife lauren3 = new Wife("Lauren3", new Husband("Phillip3"));
        Wife lauren4 = new Wife("Lauren4", new Husband("Phillip4"));
        Wife lauren5 = new Wife("Lauren5", new Husband("Phillip5"));
        Wife lauren6 = new Wife("Lauren6", new Husband("Phillip6"));
        Wife lauren7 = new Wife("Lauren7", new Husband("Phillip7"));
        wifeRepository.save(Arrays.asList(mary,lauren,lauren1,lauren2,lauren3,lauren4,lauren5,lauren6,lauren7));
       
    }
    
    @Transactional(readOnly=true)
    private void showData(){
    	// get All data
    	
    	Pageable p = new PageRequest(0, 10,Direction.DESC,"id");
    
    	Slice<WifeSimpleView> wifes = wifeRepository.findByIdNotNull(p);
       // List<Husband> husbands = husbandRepository.findAll();
         
        System.out.println("===================Wifes:==================");
        wifes.forEach(System.out::println);
         
        System.out.println("===================Husbands:==================");
        //husbands.forEach(System.out::println);
    }
}