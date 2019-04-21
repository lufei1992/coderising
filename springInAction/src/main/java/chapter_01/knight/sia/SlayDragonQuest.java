package chapter_01.knight.sia;

import java.io.PrintStream;

/**
 *  SlayDragonQuest 是要注入到  BraveKnight 中的 Quest实现
 */
public class SlayDragonQuest implements Quest {

    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }

}
