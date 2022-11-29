package com.jin.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class ExceptionController {

  //@ControllerAdvice
  //모든 controller에서 발생하는 exception을 이 친구가 처리함

  //개별 Controller에서 @ExceptionHandler 를 이용할 수도 있지만
  //(1)일괄 적용 (2)유지보수의 용이성을 위해 Exception Controller를 사용했다.


  private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);


  //{}안의 Exception을 처리하는 method(단일/다수 모두 가능)
  //원래는 400, 500 등등 에러 코드 별로, Exception을 나눠서 선언해주는 것 같다.(SQLException.class, NullPointerException.class 등등...)
  //각 코드 마다 return 할 HttpStatus를 다르게 해서 return할 수도 있지만
  //예) return new ResponseEntity<>("ExceptionController: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  //여기서는 그냥 error page로 return 한다.(redirect XXX)
  //에러가 발생하면 이 코드에서 catch하기 때문에 web.xml에 설정해놓은 error page Mapping이 적용 안되는 듯??
  //(이 친구가 우선 순위를 가진 듯????)
  //그래서 mapping에 주의를 기울여야함
  @ExceptionHandler({Exception.class})
  public Exception exceptionAll(Exception e) {

    logger.error("ExceptionController: " + e.getMessage());
    e.printStackTrace();

    return e;
  }
}
