package com.example.hamburgeradmin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.hamburgeradmin.model.APIDetails;
import com.example.hamburgeradmin.repository.InterceptorDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

/**
 * @author Yash Dubey
 * <p>
 * This class encapsulates the implementation of a Request Interceptor
 */
@AllArgsConstructor
@Component
@Log4j2
public class ExecutionTimeInterceptor implements HandlerInterceptor  {

    private InterceptorDataRepository interceptorDataRepository;
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        LocalDate timeStamp = (LocalDate) request.getAttribute("timeStamp");
        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime);
        log.info("End time: {}", endTime);
        log.info("TimeStamp {}", timeStamp);
        log.info("Request Name: {}", request.getMethod());
        log.info("Request URL: {}", request.getRequestURL().toString());
        log.info("Total Time Taken: {} ms", time);

        APIDetails apiDetails = new APIDetails();
        apiDetails.setReqName(request.getMethod());
        apiDetails.setReqUrl(request.getRequestURL().toString());
        apiDetails.setReqTimeStamp(timeStamp);
        apiDetails.setReqExecTime(time);
        interceptorDataRepository.save(apiDetails);

    }


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {

        long startTime = System.currentTimeMillis();
        LocalDate timeStamp = LocalDate.now();
        request.setAttribute("timeStamp", timeStamp);
        request.setAttribute("startTime", startTime);
        return true;
    }

}
