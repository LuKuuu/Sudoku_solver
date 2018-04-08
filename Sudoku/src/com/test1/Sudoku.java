package com.test1;

import java.io.*;
import java.util.*;

public class Sudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Table t=new Table();
		t.setCell();
		//t.printCell();
		t.solveCells();
		t.listEmptyCells();
		t.tryValue();
		
		t.printCell();
		
		
		
		
		

	}

}




class Table
{
	Cell[][] cell= new Cell[9][9]; 
	Cell[] empty_cells;
	
	public Table()
	{
		
	}
	
	public void setCell()

	
	{
		System.out.println("please input the number with a space between each, input an zero if it is needed to be solved");
		
		InputStreamReader isr2=new InputStreamReader(System.in);
		BufferedReader br2=new BufferedReader(isr2);
		
		String[] row =new String[9];
		
		try {
			
			for(int i=0; i<9;i++)
			{
				row[i]=br2.readLine();
				String[] rowString=row[i].split("(?!^)");
				
				
				
				int k=0;
				for(int j=0;j<rowString.length;j++)
				{
					//System.out.println(rowString[j]);
					
					if(!rowString[j].equals(" "))
					{
						cell[i][k]=new Cell(i, k, Integer.parseInt(rowString[j]));
						//System.out.println(cell[i][k].value);
						//System.out.println("added");
						
						k++;
						
					}
					
				}
				k=0;
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void printCell()
	{
		System.out.println();
		for(int i=0; i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				System.out.print(cell[i][j].value+" ");
			}
			System.out.println();
		}
	}
	
	public void solveCell(Cell cell_in_process)
	{
		
		
		
		
		
		if(!cell_in_process.confirmed)
		{
			
			while(cell_in_process.possible_value.size()!=0)
			{
				cell_in_process.possible_value.remove(0);
			}
			
			
		
			for(int i=0;i<9;i++)
			{
				cell_in_process.possible_value.add(i+1);

			}
			
			int impossible_value=0; 
			for(int i=0; i<9; i++)
			{
				for(int j=0;j<9 ;j++)
				{
					if(cell[i][j].x==cell_in_process.x || cell[i][j].y==cell_in_process.y || cell[i][j].block==cell_in_process.block)
					{
						impossible_value=cell[i][j].value;
						for(int k=0;k<cell_in_process.possible_value.size();k++)
						{
							if(cell_in_process.possible_value.get(k)==impossible_value)
							{
								cell_in_process.possible_value.remove(k);
							}
								
						}
					}
				} 
			}
			
			//System.out.print("possible values for cell("+(cell_in_process.x+1)+","+(cell_in_process.y+1)+") is ");
			
			for(int i=0;i<cell_in_process.possible_value.size();i++)
			{
				//System.out.print(cell_in_process.possible_value.get(i)+" ");
				
			}
			//System.out.println();
		}
	}

	public void solveCells()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				this.solveCell(this.cell[i][j]);
			}
		}
	}
	
	
	
	public void listEmptyCells()
	{
		int empty_number=0;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(this.cell[i][j].confirmed)
				{
					//System.out.println("is confirmed");
				}
				else
				{
					empty_number++;
				}
					
			}
		}
		System.out.println("there are "+empty_number+" empty cells");
		
		empty_cells=new Cell[empty_number];
		
		int k=0;
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(this.cell[i][j].confirmed)
				{
					//System.out.println("is confirmed");
				}
				else
				{
					empty_cells[k]=cell[i][j];
					k++;
				}
					
			}
		}
		
	}


	public void tryValue()
	{
		
		int i=0;
		
		
		this.solveCell(empty_cells[0]);

		
		while(empty_cells[empty_cells.length-1].value==0)
		{
			
			//System.out.println("unfinished");
			
			
			if(empty_cells[i].possible_value.size()==0)
			{
				empty_cells[i].value=0;
				
				i--;
				
				empty_cells[i].possible_value.remove(0);				


			}
			else
			{
				empty_cells[i].value=empty_cells[i].possible_value.get(0);
				i++;
				
				if(i<empty_cells.length)
				{
					this.solveCell(empty_cells[i]);

				}		


			}			
			
			
		}
	}
	
}



class Cell
{
	int x;
	int y;
	int block;
	int value;
	boolean confirmed=false;
	ArrayList<Integer> possible_value =new ArrayList<Integer>();
	
	
	public Cell(int x, int y, int value)
	{
		
		this.x=x;
		this.y=y;
		this.value=value;
		
		
		if(0<=x && x<3)
		{
			if(0<=y && y<3)
			{
				this.block=1;
			}
			else if(3<=y && y<6)
			{
				this.block=4;
			}
			else if(6<=y && y<9)
			{
				this.block=7;
			}
			else
			{
				System.out.println("value error");
			}
		}
		else if(3<=x && x<6)
		{
			if(0<=y && y<3)
			{
				this.block=2;
			}
			else if(3<=y && y<6)
			{
				this.block=5;
			}
			else if(6<=y && y<9)
			{
				this.block=8;
			}
			else
			{
				System.out.println("value error");
			}
		}
		
		else if(6<=x && x<9)
		{
			if(0<=y && y<3)
			{
				this.block=3;
			}
			else if(3<=y && y<6)
			{
				this.block=6;
			}
			else if(6<=y && y<9)
			{
				this.block=9;
			}
			else
			{
				System.out.println("value error");
			}
		}
		
		else
		{
			System.out.println("value error");
		}
		
		if(value!=0)
		{
			this.confirmed=true;
			this.possible_value.add(value);
		}
		
		
		
	}
}
