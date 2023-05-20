import java.util.ArrayList;

public class Data {
	private ArrayList<FieldButton> livingNew = new ArrayList<>();
	private ArrayList<FieldButton> deadNew = new ArrayList<>();
	private FieldButton[][] field;
	private FieldButton[][] save;
	private View view;
	
	private boolean l = false;
	private boolean d = false;
	private boolean start = false;
	
	private int interval = 500;
	private int generation = 0;

	public FieldButton[][] getField() {
		return field;
	}

	public void setField(FieldButton[][] field) {
		this.field = field;
	}

	public boolean isD() {
		return d;
	}

	public void setD(boolean d) {
		this.d = d;
	}

	public boolean isL() {
		return l;
	}

	public void setL(boolean l) {
		this.l = l;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public ArrayList<FieldButton> getLivingNew() {
		return livingNew;
	}

	public void setLivingNew(ArrayList<FieldButton> livingNew) {
		this.livingNew = livingNew;
	}

	public ArrayList<FieldButton> getDeadNew() {
		return deadNew;
	}

	public void setDeadNew(ArrayList<FieldButton> deadNew) {
		this.deadNew = deadNew;
	}

	public FieldButton[][] getSave() {
		return save;
	}

	public void setSave(FieldButton[][] save) {
		this.save = save;
	}
}
