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
		this.setTrainer(trainer);

		
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getChest() {
		return chest;
	}

	public void setChest(int chest) {
		this.chest = chest;
	}

	public int getThigh() {
		return thigh;
	}

	public void setThigh(int thigh) {
		this.thigh = thigh;
	}

	public int getUpperArm() {
		return upperArm;
	}

	public void setUpperArm(int upperArm) {
		this.upperArm = upperArm;
	}

	public int getWaist() {
		return waist;
	}

	public void setWaist(int waist) {
		this.waist = waist;
	}

	public int getHips() {
		return hips;
	}

	public void setHips(int hips) {
		this.hips = hips;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Person getTrainer() {
		return trainer;
	}

	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}
	


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
