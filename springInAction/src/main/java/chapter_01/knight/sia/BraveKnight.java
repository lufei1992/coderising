package chapter_01.knight.sia;

/**
 * BraveKnight可以接收任何赋予他的探险任务
 */
public class BraveKnight implements Knight {
    private Quest quest;
    // Quest被注入进来
    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
