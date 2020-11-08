package gestao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import objeto.Tarefa;


public class Gestor {
	
	public List<Tarefa> listaDeTarefas = new ArrayList<>();          //Create arrayList to store all the created tasks
	                                                                    
	
	//List of the Tasks
	public void listar() throws Exception{
		
		String Lista = "";  //Clean the previous "Lista"
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");         //Format the date   
		String partiList = "";
		
		System.out.println("------------Lista de Tarefas----------" );
		
		for(int i = 0; i < listaDeTarefas.size(); i++)              //Loop in listaDeTarefas to see if there any task
    	{ 
			partiList = "";
			for (String s : listaDeTarefas.get(i).getParticipantes())  //add "participantes" to the partiList
			{
				partiList += s ;
			}
			
			Lista += "\n\nId:"+ listaDeTarefas.get(i).getId() + "\nTítulo:" + listaDeTarefas.get(i).getTitulo() +  "\nData:" +
					df.format(listaDeTarefas.get(i).getDataInicio())+ " - " + df.format(listaDeTarefas.get(i).getDataFim())+ "\nParticipantes:" + partiList + "\n";
    	}
		System.out.println(Lista); 
	}
	
	
	//Create New Task
	public void Criar(String titulo, Date dataInicio, Date dataFim, List<String> participantes) throws Exception{  
		
		if(listaDeTarefas.size() == 0) {      //If there ins't any tasks in listaDeTarefas, just add in the list
			listaDeTarefas.add(new Tarefa(1, titulo, dataFim, dataInicio, participantes, false));
		}
		else {
			Tarefa tarefaId = listaDeTarefas.get(listaDeTarefas.size() - 1);  //Find the last created task
			int newId = tarefaId.getId() + 1;                                 //Add 1 to the Id of the new task
			listaDeTarefas.add(new Tarefa(newId, titulo, dataFim, dataInicio, participantes, false));   
		}
		
	}
	
	//Edit Tasks
	public void Editar(int id, String titulo, Date dataInicio, Date dataFim, List<String> participantes) throws Exception{
		
		for(int i = 0; i < listaDeTarefas.size(); i++) {          //Loop to read all the task's list
			
			if(listaDeTarefas.get(i).getId() == id) {            //If the chosen Id is the same of another task already created starts to set again
				listaDeTarefas.get(i).setDataInicio(dataInicio);
				listaDeTarefas.get(i).setDataFim(dataFim);
				listaDeTarefas.get(i).setTitulo(titulo);
				listaDeTarefas.get(i).setParticipantes(participantes);
			}
    	}
	}
	
	//Delete tasks
	public void Apagar(int id) throws Exception{
		
		for(int i = 0; i < listaDeTarefas.size(); i++)  {         //Loop to read all the task's list
    	 
			if(listaDeTarefas.get(i).getId() == id) {
		    	int index = id - 1;                              
		    	listaDeTarefas.remove(index);
		    }
    	}
	}
	
	//Create reminder
	public void Reminder(int id) {
		
		for(int i = 0; i < listaDeTarefas.size(); i++) {          //Loop to read all the task's list 
			
			if(listaDeTarefas.get(i).getId() == id) {           //If the chosen Id is the same of another task already created convert the boolean isReminder to true
				listaDeTarefas.get(i).setIsRemander(true);
			}
    	}
	}
	
	//Check the reminders
	public void CheckReminder() {
		String stringRemander = "";         //Clean the previous reminders
		
		System.out.println("----------Reminders----------\n");
		
		for(int i = 0; i < listaDeTarefas.size(); i++) {
			
			Date date = new Date(System.currentTimeMillis());    //to get the current date 
			
			if(listaDeTarefas.get(i).getIsRemander() == true) {   //If the boolean isReminder is true starts the calculations of time
				
				long diff = listaDeTarefas.get(i).getDataInicio().getTime() - date.getTime();  //Calculations of time
				int diffDays = (int) (diff / (24 * 60 * 60 * 1000));         //Calculation with days, hours, minutes and milliseconds.                   
				
				if(diffDays == 0) {  //if the time difference is zero the task is on the current day 
					stringRemander += "\n" + listaDeTarefas.get(i).getTitulo()+ "- Hoje \n";
				}
				else if(diffDays < 0) {  //If the task's date has already passed
					long newDiff = date.getTime() - listaDeTarefas.get(i).getDataInicio().getTime();
					int newDiffDays = (int) (newDiff / (24 * 60 * 60 * 1000));
					stringRemander += "\n" + listaDeTarefas.get(i).getTitulo()+ "- Já passou " + newDiffDays + " dias \n" ;
					
				}else if(diffDays > 0){  //If there are still days to the task's date
					stringRemander += "\n" + listaDeTarefas.get(i).getTitulo()+ "- Falta " + diffDays + " dias \n" ;
				}
				
			}
    	}
		
		System.out.println(stringRemander);
	}
	
	

	
	public void Menu(){
		String id;
		Date dataInicio;
		Date dataFim;                               //Declaration of variables
		String titulo;
		String listParticipantes; 
		List<String> participantes;
		
		String menu = JOptionPane.showInputDialog("Escolha uma opção:\n 1- Ver lista de tarefas\n 2- Criar nova tarefa\n 3- Editar tarefa\n 4- Apagar tarefa\n 5- Acrescentar reminder\n 6- Verificar reminders");
		
		switch(menu)   //allows a variable to be compared to a list of values
        {    
            case "1": //See List of Tasks 
                try {
            		listar();
            		Menu();
        		}
        		catch(Exception e) {
        		  //  Block of code to handle errors
        		}
            	
                break;
            
            
            case "2": //Create new task
        		try {
            		titulo = JOptionPane.showInputDialog("Escolha o título da tarefa");
            		
            		String retorno  = JOptionPane.showInputDialog(null, "Data de Início: (dd/MM/yyyy)","Data",JOptionPane.OK_OPTION);
                	dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(retorno);  //Convert date for a SimleDateFormat
                	
                	String retorno2  = JOptionPane.showInputDialog(null, "Data de Fim: (dd/MM/yyyy) ","Data",JOptionPane.OK_OPTION);
            		dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(retorno2);
            		
            		listParticipantes = JOptionPane.showInputDialog("Escolha os participantes(separe por ,)");
            		participantes = Arrays.asList(listParticipantes.split("\\s+"));  //Split the string by one or more space to convert the words for a form of a String array
            		
        			Criar(titulo, dataInicio, dataFim, participantes);
        			Menu();
        		}
        		catch(Exception e) {
        			System.out.println(e);
        		}
                break;  
            
            
            case "3":  //Edit task
            	try {
            		id = JOptionPane.showInputDialog("Escolha o id da tarefa");
            		Tarefa tarefaNew = listaDeTarefas.get(Integer.parseInt(id)-1);     //Convert the Id string to integer
            		
            		titulo = JOptionPane.showInputDialog("Escolha o título da tarefa", tarefaNew.getTitulo());   //Get new "título"
            		
            		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");    		
            		
            		String retorno  = (String)JOptionPane.showInputDialog(null, "Data de Inicio: (dd/MM/yyyy)", 
                            "Data", JOptionPane.QUESTION_MESSAGE, null, null,df.format(tarefaNew.getDataInicio()));  //Show the previous date
                	dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(retorno);       	//Convert date for a SimleDateFormat
                	
                	String retorno2  = (String)JOptionPane.showInputDialog(null, "Data de Fim: (dd/MM/yyyy)", 
                            "Data", JOptionPane.QUESTION_MESSAGE, null, null,df.format(tarefaNew.getDataFim()));
            		dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(retorno2);
            		
            		String partiList = "";   //Clean previous partiList
            		for (String s : tarefaNew.getParticipantes())    //add  new "participantes" to the partiList
        			{
        				partiList += s ;
        			}
            		listParticipantes = JOptionPane.showInputDialog("Escolha os participantes(separe por ,)", partiList);
            		participantes = Arrays.asList(listParticipantes.split("\\s+"));
            		
            		Editar(Integer.parseInt(id), titulo, dataInicio, dataFim, participantes);
            		Menu();
        		}
        		catch(Exception e) {
        		  //  Block of code to handle errors
        		}
            	
                break;
            
            
            case "4":  //Delete tasks
            	try {
            		id = JOptionPane.showInputDialog("Escolha o id da tarefa para apagar");
            		Tarefa tarefaNew = listaDeTarefas.get(Integer.parseInt(id)-1);        //Convert the Id string to Integer
                	
            		Apagar(tarefaNew.getId());
                	
                	Menu();
        		}
        		catch(Exception e) {
        		  //  Block of code to handle errors
        		}
            	
            	break;
            
            
            case "5":  //Create reminder
            	id = JOptionPane.showInputDialog("Escolha o id da tarefa");
        		Tarefa tarefaNew = listaDeTarefas.get(Integer.parseInt(id)-1);      //Convert the Id string to Integer
        		Reminder(tarefaNew.getId());
        		Menu();
            	
        		break;
            
            
            case "6":  //Check reminders
            	
            	CheckReminder();
            	Menu();
            	break;
            
            
            default: //In case of an nonexistent number of the menu
                System.out.println("no match"); 
        }
	}	
}

