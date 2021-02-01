package com.example.hamburgeradmin.assemblers;

import com.example.hamburgeradmin.dto.APIDetailsDTO;
import com.example.hamburgeradmin.model.APIDetails;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Yash Dubey
 *
 * This class encapsulates data transfer object for APIDetails entity
 */
@AllArgsConstructor
@Component
public class APIDetailsAssembler implements RepresentationModelAssembler<APIDetails, APIDetailsDTO> {

    public APIDetailsDTO toModel(APIDetails entity) {
        return new APIDetailsDTO(entity.getReqName(), entity.getReqUrl(), entity.getReqTimeStamp(), entity.getReqExecTime());
    }
}

