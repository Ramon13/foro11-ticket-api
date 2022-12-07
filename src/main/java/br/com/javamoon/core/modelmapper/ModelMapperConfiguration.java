package br.com.javamoon.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.javamoon.api.model.input.TicketMultipartInput;
import br.com.javamoon.domain.model.Comment;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        skipImagesMapping(mapper);
        
        return mapper;
    }
    
    private void skipImagesMapping(ModelMapper mapper) {
    	mapper.addMappings(new PropertyMap<TicketMultipartInput, Comment>() {
			@Override
			protected void configure() {
				skip(destination.getImages());
			}
		});
    }
}
