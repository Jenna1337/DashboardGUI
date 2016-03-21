import display.clock.TimeKeeper;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		TimeKeeper keep = new TimeKeeper();
		for(;;){
			System.out.println(keep);
			Thread.sleep((long)1000);
		}
	}
}
