package models;
import java.util.Date;

public class Assessment {
	private double weight;
	private int chest;
	private int thigh;
	private int upperArm;
	private int waist;
	private int hips;
	private String comment;
	private Person trainer;

	/**
	 * Constructor for objects of class Assessment.
	 */
	
	public Assessment(double weight, int chest, int thigh, int upperArm, int waist, 
			int hips, String comment, Person trainer){
		
		this.setWeight(weight);
		this.setChest(chest);
		this.setThigh(thigh);
		this.setUpperArm(upperArm);
		this.setWaist(waist);
		this.setHips(hips);
		this.setComment(comment);
		this.setTrainer(trainer);

		
	}

	/**
	 *
	 * Returns the weight recorded in the assessment
	 *
	 * @return The weight recorded in the assessment
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Updates Weight
	 *
	 * @param weight The weight recorded in the assessment
	 */

	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 *
	 * Returns the chest value recorded in the assessment
	 *
	 * @return The chest value recorded in the assessment
	 */
	public int getChest() {	return chest;}

	/**
	 * Updates chest
	 *
	 * @param chest The chest recorded in the assessment
	 */


	public void setChest(int chest) {
		this.chest = chest;
	}

	/**
	 *
	 * Returns the thigh value recorded in the assessment
	 *
	 * @return The thigh value recorded in the assessment
	 */
	public int getThigh() {
		return thigh;
	}

	/**
	 * Updates thigh
	 *
	 * @param thigh The thigh recorded in the assessment
	 */


	public void setThigh(int thigh) {
		this.thigh = thigh;
	}

	/**
	 *
	 * Returns the Upper Arm value recorded in the assessment
	 *
	 * @return The Upper Arm value recorded in the assessment
	 */
	public int getUpperArm() {
		return upperArm;
	}

	/**
	 * Updates Upper
	 *
	 * @param upperArm The upper arm value recorded in the assessment
	 */

	public void setUpperArm(int upperArm) {
		this.upperArm = upperArm;
	}

	/**
	 *
	 * Returns the waist value recorded in the assessment
	 *
	 * @return The waist value recorded in the assessment
	 */
	public int getWaist() {
		return waist;
	}

	/**
	 * Updates waist
	 *
	 * @param waist The waist value recorded in the assessment
	 */

	public void setWaist(int waist) {
		this.waist = waist;
	}

	/**
	 *
	 * Returns the hips value recorded in the assessment
	 *
	 * @return The hips value recorded in the assessment
	 */
	public int getHips() {
		return hips;
	}

	/**
	 * Updates hips
	 *
	 * @param hips The hips value recorded in the assessment
	 */

	public void setHips(int hips) {
		this.hips = hips;
	}

	/**
	 *
	 * Returns the comment recorded in the assessment
	 *
	 * @return The comment recorded in the assessment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Updates comment
	 *
	 * @param comment The comment recorded in the comment
	 */

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 *
	 * Returns the trainer recorded in the assessment
	 *
	 * @return The trainer recorded in the assessment
	 */
	public Person getTrainer() {
		return trainer;
	}

	/**
	 * Updates trainer
	 *
	 * @param trainer The trainer recorded in the assessment
	 */

	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}

	/**
	 * Returns a user-friendly string representation of the Assessment object
	 *
	 * @return User-friendly String representing the assessment
	 */

	public String toString(){
        return (" Weight:"  + weight
        		+ "kg\n Chest: "+ chest
        		+ "cm\n Thigh: "+ thigh
        		+ "cm\n upperArm: "+ upperArm
        		+ "cm\n Waist: "+ waist
        		+ "cm\n Hips: "+ hips
        		+ "cm\n Comment: "+ comment
        		+ "\n Trainer: "+ trainer.getMemberName());
	}         
	
}
