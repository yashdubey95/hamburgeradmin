package com.example.hamburgeradmin.services;

import com.example.hamburgeradmin.assemblers.APIDetailsAssembler;
import com.example.hamburgeradmin.dto.APIDetailsDTO;
import com.example.hamburgeradmin.exception.InternalServerErrorException;
import com.example.hamburgeradmin.model.APIDetails;
import com.example.hamburgeradmin.repository.APIDetailsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;

/**
 * @author Yash Dubey
 * <p>
 * This class implements methods to fetch records from the APIDetails entity
 */
@AllArgsConstructor
@Service
@Log4j2
public class APIDetailsServices {

    private final APIDetailsAssembler apiDetailsAssembler;
    private final APIDetailsRepository apiDetailsRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;

    public CollectionModel<APIDetailsDTO> getAllExecTime(String reqName, LocalDate reqTimeStamp, int page, int size) {
        try{
            Pageable paging = PageRequest.of(page, size);
            Page<APIDetails> pageExecutionTimes;

            if(reqName == null && reqTimeStamp == null)
                pageExecutionTimes = apiDetailsRepository.findAll(paging);
            else if(reqTimeStamp == null)
                pageExecutionTimes = apiDetailsRepository.findByReqName(reqName, paging);
            else if(reqName == null)
                pageExecutionTimes = apiDetailsRepository.findByReqTimeStamp(reqTimeStamp, paging);
            else
                pageExecutionTimes = apiDetailsRepository.findByReqNameAndReqTimeStamp(reqName, reqTimeStamp, paging);

            if(! CollectionUtils.isEmpty(pageExecutionTimes.getContent())) return pagedResourcesAssembler.toModel(pageExecutionTimes, apiDetailsAssembler);
            return null;
        } catch (Exception e){
            throw new InternalServerErrorException();
        }

    }
}
