package chapter_01.sia;
import static org.mockito.Mockito.*;

import chapter_01.knight.sia.BraveKnight;
import chapter_01.knight.sia.Quest;
import org.junit.Test;

public class BraveKnightTest {

  @Test
  public void knightShouldEmbarkOnQuest() {
	// 创建   mockQuest
    Quest mockQuest = mock(Quest.class);
    // 注入 mockQuest
    BraveKnight knight = new BraveKnight(mockQuest);
    knight.embarkOnQuest();
    verify(mockQuest, times(1)).embark();
    System.out.println("knight");
  }

}
