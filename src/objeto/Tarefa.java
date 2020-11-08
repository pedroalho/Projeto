package objeto;

import java.util.Date;
import java.util.List;

public class Tarefa {
	private int id;
	private Date dataInicio = new Date();
	private Date dataFim = new Date();             //Create object and its properties (id, dataInicio, dataFim, titulo, participantes and isReminder).
	private String titulo;
	private  List<String> participantes;
	private Boolean isRemander;
	
	public Tarefa(int id,  String titulo, Date dataInicio, Date dataFim, List<String> participantes, Boolean isRemander) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.titulo = titulo;
		this.participantes = participantes;
		this.isRemander = isRemander;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<String> participantes) {
		this.participantes = participantes;
	}
	
	public Boolean getIsRemander() {
		return isRemander;
	}

	public void setIsRemander(Boolean isRemander) {
		this.isRemander = isRemander;
	}

	

	
	
}
