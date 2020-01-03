import java.util.EnumMap;
import java.util.Map;

//a trader implementation
public class TraderImpl implements Trader{

	//class variables that represent the trader's primary grain 
	public Grain primary;

	//the amount of the 4 grains the trader needs 
	public int barleyNeeded;
	public int cornNeeded;
	public int wheatNeeded;
	public int riceNeeded;
	
	//an order that holds the current amounts of grains the trader has
	public Order amounts;
	

	public TraderImpl(Grain prim) {
		this.primary = prim;
		this.barleyNeeded = 0;
		this.cornNeeded = 0;
		this.wheatNeeded = 0;
		this.riceNeeded = 0;
		this.amounts = new Order();
	}

	/** Reports on the total amount of resources held by this Trader.
	 * @return an indication of the amount of each type of grain stocked by this
	 * Trader.
	 */
	public Order getAmountOnHand() {
		return this.amounts;
	}

	/** A request from a brewer.
	 * The caller is blocked until the request can be completely filled.
	 * @param order the number of bushels needed of each type of grain.
	 * @throws InterruptedException if the current thread is interrupted while
	 *            waiting for the order to be filled.
	 */
	public void get(Order order) throws InterruptedException {
		this.barleyNeeded = order.get(Grain.BARLEY);
		this.riceNeeded = order.get(Grain.RICE);
		this.cornNeeded = order.get(Grain.CORN);
		this.wheatNeeded = order.get(Grain.WHEAT);
		if	(this.wheatNeeded <= this.amounts.get(Grain.WHEAT) && 
			 this.riceNeeded <= this.amounts.get(Grain.RICE) &&
			 this.cornNeeded <= this.amounts.get(Grain.CORN) && 
			 this.barleyNeeded <= this.amounts.get(Grain.BARLEY)){
			this.amounts.change(Grain.BARLEY, (-1*order.get(Grain.BARLEY)));
			this.amounts.change(Grain.RICE, (-1*order.get(Grain.RICE)));
			this.amounts.change(Grain.CORN, (-1*order.get(Grain.CORN)));
			this.amounts.change(Grain.WHEAT, (-1*order.get(Grain.WHEAT)));
		} else {
			throw new InterruptedException();
		}

	}

	/** Responds to a swap request from another Trader.
	 * The other Trader calls this method to request a trade of a particular
	 * type of grain for this Trader's specialty.  Delays the caller until this
	 * Trader can complete the swap.
	 * @param what the type of grain the other Trader wants to swap.
	 * @param amt the number of bushels to swap.
	 * @throws InterruptedException if the current thread is interrupted while
	 *            waiting for the swap to succeed.
	 */
	public void swap(Grain what, int amt) throws InterruptedException {
		if (this.amounts.get(primary) < amt){
			throw new InterruptedException(); 
		} else {
			this.amounts.change(what, amt);
			this.amounts.change(primary, (-1*amt));
		}

	}

	/** Accepts a delivery from the supplier.
	 * The supplier calls this method to deliver some of this Trader's
	 * specialty grain.
	 * @param amt the number of bushels of this Trader's specialty
	 *               being delivered.
	 */
	public void deliver(int amt) {
		this.amounts.change(this.primary, amt);
	}

}
