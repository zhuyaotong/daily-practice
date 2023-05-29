package com.github.zhuyaotong.gcdemo;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GcTestController {

  private Queue<Greeting> objCache = new ConcurrentLinkedDeque<>();

  @RequestMapping("/greeting")
  public Greeting greeting() {
    Greeting greeting = new Greeting("Hello World!");

    if (objCache.size() >= 200000) {
      objCache.clear();

    } else {
      objCache.add(greeting);
    }

    return greeting;
  }
}

@Data
@AllArgsConstructor
class Greeting {
  private String message;
}
