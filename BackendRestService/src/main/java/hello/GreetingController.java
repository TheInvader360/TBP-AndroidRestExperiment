package hello;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GreetingController {

  private static final Logger log = Logger.getLogger(GreetingController.class.getName());
  private static final String template = "Hello, %s!";
  private AtomicLong counter = new AtomicLong();
  private ObjectMapper mapper = new ObjectMapper();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name, HttpServletRequest request) throws JsonProcessingException {
    Greeting greeting = new Greeting();
    greeting.setId(String.valueOf(counter.incrementAndGet()));
    greeting.setContent(String.format(template, name));
    log.debug("Request:" + Utils.getUrl(request) + " Response:" + mapper.writeValueAsString(greeting));
    return greeting;
  }

}
