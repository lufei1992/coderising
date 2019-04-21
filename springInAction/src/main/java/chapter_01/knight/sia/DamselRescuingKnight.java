package chapter_01.knight.sia;

/**
 * RescueDamselQuest 只能执行 RescueDamselQuest 探险任务
 */
public class DamselRescuingKnight implements Knight {

    private RescueDamselQuest quest;

    public DamselRescuingKnight() {
        this.quest = new RescueDamselQuest();
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
