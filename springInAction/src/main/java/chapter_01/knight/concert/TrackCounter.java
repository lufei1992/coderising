package chapter_01.knight.concert;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class TrackCounter {
	
	private Map<Integer,Integer> trackCounts = new HashMap<>();
	
	// 通知 playTrack 方法
	@Pointcut("execution(* soundsystem.CompactDisc.playTrack(int)) "
			+ "&& args(trackNumber)")
	public void trackPlayed(int trackNumber){}
	
	// 在播放前，为该磁道计数
	@Before("trackPlayed(trackNumber)")
	public void countTrack(int trackNumber){
		int currentCount = getPlayCount(trackNumber);
		trackCounts.put(trackNumber, currentCount+1);
	}
	
	public int getPlayCount(int trackNumber){
		return trackCounts.containsKey(trackNumber) 
				? trackCounts.get(trackNumber):0;
	}
}
