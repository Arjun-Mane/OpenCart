package uitilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Data_Provider 
{
	@DataProvider(name="logindata")
	public String[][] GetData() throws IOException
	{
		String path=".\\TestData\\Data_For_Testing.xlsx";
		
		XL_Utility_File xlfile=new XL_Utility_File(path);
		
		int trow=xlfile.GetRowCount("Sheet1");
		int tcell=xlfile.GetCellCount("Sheet1", 1);
	
		String logindata[][]= new String[trow][tcell];
		
		for(int i=1;i<=trow;i++)
		{
			for(int j=0;j<tcell;j++)
			{
				logindata[i-1][j]= xlfile.GetCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
}
