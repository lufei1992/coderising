package chapter_01.knight.sia.config;

import chapter_01.knight.sia.BraveKnight;
import chapter_01.knight.sia.Knight;
import chapter_01.knight.sia.Quest;
import chapter_01.knight.sia.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KnightConfig {

  @Bean
  public Knight knight() {
    return new BraveKnight(quest());
  }
  
  @Bean
  public Quest quest() {
    return new SlayDragonQuest(System.out);
  }

}
/*
>
![](https://github.com/lu666666/notebooks/tree/master/java/spring/sping_in_action/01/pic/04.png)
>
*/
