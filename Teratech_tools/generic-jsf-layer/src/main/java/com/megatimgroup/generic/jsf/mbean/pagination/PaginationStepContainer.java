/**
 * 
 */
package com.megatimgroup.generic.jsf.mbean.pagination;


/**
 * Interface reprentant le container des Iterateur pour PaginationStep
 * @author Bekondo Kangue Dieunedort <bekondo_dieu@yahoo.fr / tel:695427874>
 *
 */
public interface PaginationStepContainer {

	/**
	 * Return  the iterator for manager PaginationStep
	 * @return
	 */
	  public PaginationStepIterator iterator();
}
