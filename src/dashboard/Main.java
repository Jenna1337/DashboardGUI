package dashboard;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		Dashboard dash = new Dashboard();
		dash.setVisible(true);
		/*
		Thread.sleep(10000);
		dash.destroy();
		System.gc();
		/*//*/
		Thread.sleep(1);
		benchmark();/**/
	}
	@SuppressWarnings("unused")
	private static void benchmark() throws Exception
	{
		java.io.File f=new java.io.File("src/benchmarktest.txt");
		if(!f.exists())
			System.err.println("Can't find benchmark file");
		if(f.canWrite())
		{
			@SuppressWarnings("resource")
			final java.io.BufferedWriter writer=new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(f)));
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
				public void run(){
					try
					{
						writer.close();
					}
					catch (Exception e)
					{
					}
				}
			}));
			while(Thread.activeCount()>1)
			{
				//System.out.println("Free: "+Runtime.getRuntime().freeMemory());
				//System.out.println("Total:"+Runtime.getRuntime().totalMemory());
				try
				{
					long usedmemory = java.lang.management.ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
					writer.write(""+usedmemory+"\n");
					writer.flush();
				}
				catch(java.io.IOException ioe)
				{
					
				}
				Thread.sleep(60000);
			}
		}
	}
}
