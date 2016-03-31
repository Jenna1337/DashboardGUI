package dashboard;

import java.io.File;

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
		File f=new File("src/benchmarktest.txt");
		//		if(f.canRead())
		//		{
		//			java.io.BufferedReader reader=new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(f)));
		//			reader.close();
		//		}
		if(!f.exists())
			System.err.println("Can't find benchmark file");
		if(f.canWrite())
		{
			@SuppressWarnings("resource")
			java.io.BufferedWriter writer=new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(f)));
			while(Thread.activeCount()>1)
			{
				//System.out.println("Free: "+Runtime.getRuntime().freeMemory());
				//System.out.println("Total:"+Runtime.getRuntime().totalMemory());
				try
				{
					writer.write(""+(Runtime.getRuntime().maxMemory()-Runtime.getRuntime().freeMemory())+"\n");
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
