/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.generic.jsf.layer.mbean.processor;

import com.megatim.common.annotations.OrderType;


/**
 *
 * @author DEV_4
 */
public class OrderByItem implements Comparable<OrderByItem>{
		
		/**
		 * Champ sur le quel porte le critere d'ordre
		 */
		private String fieldName ;
		/**
		 * Ordre
		 */
		private OrderType type ;
		
		/**
		 * Rang dans le map
		 */
		private short rang ;

		
		/**
		 * Constructeur par defaut
		
		
		public OrderByItem() {
			super();
			// TODO Auto-generated constructor stub
		}

      */


		public OrderByItem(String fieldName, OrderType type, short rang) {
			super();
			this.fieldName = fieldName;
			this.type = type;
			this.rang = rang;
		}




		public int compareTo(OrderByItem arg0) {
			// TODO Auto-generated method stub
			
			Integer _value = Integer.valueOf(rang);
                        
                        Integer value_ =Integer.valueOf(arg0.rang);
                        
                        return _value.compareTo(value_);
		}




		public String getFieldName() {
			return fieldName;
		}




		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}




		public OrderType getType() {
			return type;
		}




		public void setType(OrderType type) {
			this.type = type;
		}




		public short getRang() {
			return rang;
		}




		public void setRang(short rang) {
			this.rang = rang;
		}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderByItem other = (OrderByItem) obj;
        if ((this.fieldName == null) ? (other.fieldName != null) : !this.fieldName.equals(other.fieldName)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.rang != other.rang) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "OrderByItem{" + "fieldName=" + fieldName + ", type=" + type + ", rang=" + rang + '}';
    }
		
		
}
