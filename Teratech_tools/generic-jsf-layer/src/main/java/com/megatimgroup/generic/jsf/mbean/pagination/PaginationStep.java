package com.megatimgroup.generic.jsf.mbean.pagination;

import java.util.Iterator;

public class PaginationStep implements PaginationStepContainer{

	/**
	 * Begin of the pagination
	 */
	private int offset = 0 ;
	
	/**
	 * Size of the total seach result
	 */
	private long size = 0;
	
	/**
	 * The size of the page
	 */
	private int stepSize = 1 ;

	private int stepSizeCache = 1;
	
	/**
	 * Constructeur par defaut
	 */
	public PaginationStep() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructeur initialisant la taille d'un pas 
	 * @param size
	 * @param stepSize
	 */
	public PaginationStep(int stepSize) {
		super();
		
		this.stepSize = stepSize;
                this.stepSizeCache = stepSize;
	}

	/**
	 * Constructteur initialisant la taille d'un pas et la taille max des données a charger
	 * @param size
	 * @param stepSize
	 */
	public PaginationStep(int size, int stepSize) {
		super();
		this.size = size;
		this.stepSize = stepSize;
                this.stepSizeCache = stepSize;
	}
	
	public int getOffset() {
		return offset;
	}
	

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getStepSize() {
		return stepSize;
	}

	public void setStepSize(int stepSize) {
		this.stepSize = stepSize;
                
	}

    public int getStepSizeCache() {
        return stepSizeCache;
    }

    public void setStepSizeCache(int stepSizeCache) {
        this.stepSizeCache = stepSizeCache;
    }

        
	public PaginationStepIterator iterator() {
		// TODO Auto-generated method stub
		return new PaginationStepIterator(this);
	}
	
	
}
