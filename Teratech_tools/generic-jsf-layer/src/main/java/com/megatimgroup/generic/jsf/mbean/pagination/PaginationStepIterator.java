package com.megatimgroup.generic.jsf.mbean.pagination;

import java.util.Iterator;

public class PaginationStepIterator implements Iterator<PaginationStep> , IPreviousIterator<PaginationStep>{

	private PaginationStep pagination;
	
	public PaginationStepIterator(PaginationStep pagination) {
		// TODO Auto-generated constructor stub
		
		this.pagination = pagination ;
		
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		if(pagination.getStepSize()<=0)
                                  return false;
		//The number of pagination step allready executes
		long nbreofStepExecute = ((pagination.getOffset()+pagination.getStepSize())/pagination.getStepSize());
		
		//the number of availabe step
		long nbreofStep = (pagination.getSize()+1)/pagination.getStepSize();		
                
                if(pagination.getStepSize()>=pagination.getSize()||pagination.getStepSize()!=pagination.getStepSizeCache())
                              return false;
                //System.out.println("PaginationStepIterator.hasNext() ::::::::::: offset : "+pagination.getOffset()+" :::::  stepSize = "+pagination.getStepSize()+" :::::: Size = "+pagination.getSize()+"  ::::: Nbre of Step = "+nbreofStep+" ::: executeStep = "+nbreofStepExecute);
		return (nbreofStepExecute <= nbreofStep) ?  true : false;
	}

	public PaginationStep next() {
		// TODO Auto-generated method stub
		
		// Just do it if there are next
		  if(hasNext()){			
                       
		      if(((pagination.getOffset()+pagination.getStepSize())>pagination.getSize())
                              &&(pagination.getStepSize()==pagination.getStepSizeCache())){
                          pagination.setStepSizeCache(pagination.getStepSize());;
                          
                          pagination.setStepSize(Integer.parseInt(Long.toString(pagination.getSize()))-pagination.getOffset());
                      }
                     //Initialise the offset
                     pagination.setOffset(pagination.getOffset()+pagination.getStepSize()); 
			
		  }
		 
		 //return the pagination
		 return pagination;
	}
	
	public  PaginationStep previous() {
		// TODO Auto-generated method stub
		
		  //Just do it if there are previous
		  if(hasPrevious()){	
			//Initialise the offset
			pagination.setOffset(pagination.getOffset()-pagination.getStepSize());
			//return the pagination
		  }
		  
                  if(pagination.getStepSizeCache()!=pagination.getStepSize())
                      pagination.setStepSize(pagination.getStepSizeCache());
		//return the pagination
		  return pagination;
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		//The number of pagination step allready executes
                if(pagination.getStepSize()!=pagination.getStepSizeCache())
                    return true;
                                
		long nbreofStepExecute = (pagination.getOffset()/(pagination.getStepSize()==0 ? 1 : pagination.getStepSize()));
		
                
		//the number of availabe step
		//long nbreofStep = pagination.getSize()/pagination.getStepSize();
		
		return (nbreofStepExecute > 0) ?  true : false;
	}

	public void remove() {
		// TODO Auto-generated method stub

	}

	

}
