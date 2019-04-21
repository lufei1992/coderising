package chapter_01.sia;

import java.io.PrintStream;

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
    return new SlayDragonQuest(stream());
  }

  @Bean
  public PrintStream stream() {
    return new FakePrintStream();
  }

}
