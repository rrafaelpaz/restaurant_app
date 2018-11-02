package selecao;

public class CriarDados {
	
	public Funcionario[] criarFuncionarios(){
		
		Funcionario f1 = new Funcionario();
		f1.setNome("Kadu");
		
		Funcionario f2 = new Funcionario();
		f2.setNome("Rafael");
		
		Funcionario f3 = new Funcionario();
		f3.setNome("Sales");
		
		Funcionario f4 = new Funcionario();
		f4.setNome("Priscila");
		
		Funcionario f5 = new Funcionario();
		f5.setNome("Fernando");
		
		Funcionario f6 = new Funcionario();
		f6.setNome("Francisco");
	
		Funcionario f7 = new Funcionario();
		f7.setNome("Jo‹o");
	
		Funcionario f8 = new Funcionario();
		f8.setNome("Maria");
		
		Funcionario f9 = new Funcionario();
		f9.setNome("Ricardo");
	
		Funcionario f10 = new Funcionario();
		f10.setNome("Mateus");
		
		return new Funcionario[] {f1,f2,f3,f4,f5,f6,f7,f8,f9,f10};
	}
	
	public DiaEscala[] criarTurnosComPreferenciaEREstricao(Funcionario[] funcionarios){
		
		//* segunda
		Turno ts1 = new Turno();
		ts1.setDiaSemana("Segunda");
		ts1.setHorario("8:00 - 12:00");
		
		Turno ts2 = new Turno();
		ts2.setDiaSemana("Segunda");
		ts2.setHorario("13:00 - 16:00");
		
		Turno ts3 = new Turno();
		ts3.setDiaSemana("Segunda");
		ts3.setHorario("17:00 - 23:00");
		
		//* cria os turnos pra adicionar
		Turno[] turnos1 = new Turno[3];
		turnos1[0] = ts1;
		turnos1[1] = ts2;
		turnos1[2] = ts3;
		
		DiaEscala dia1 = new DiaEscala();
		dia1.setNum(1);
		
		TurnoDia[] arrayTurnoDia1 = new TurnoDia[3];
		
		for(int i = 0; i < turnos1.length; i++){
			
			//* recupera o turno
			Turno turno = turnos1[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia1[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia1.setArrayTurnoDia(arrayTurnoDia1);
		
		
		//* terca
		Turno tt1 = new Turno();
		tt1.setDiaSemana("Tera");
		tt1.setHorario("8:00 - 12:00");
				
		Turno tt2 = new Turno();
		tt2.setDiaSemana("Tera");
		tt2.setHorario("13:00 - 16:00");
				
		Turno tt3 = new Turno();
		tt3.setDiaSemana("Tera");
		tt3.setHorario("17:00 - 23:00");
	
		//* adiciona os turnos
		Turno[] turnos2 = new Turno[3];
		turnos2[0] = tt1;
		turnos2[1] = tt2;
		turnos2[2] = tt3;
		
		DiaEscala dia2 = new DiaEscala();
		dia2.setNum(2);
		
		TurnoDia[] arrayTurnoDia2 = new TurnoDia[3];
		
		for(int i = 0; i < turnos2.length; i++){
			
			//* recupera o turno
			Turno turno = turnos2[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia2[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia2.setArrayTurnoDia(arrayTurnoDia2);
		
		//* quarta
		Turno tqa1 = new Turno();
		tqa1.setDiaSemana("Quarta");
		tqa1.setHorario("8:00 - 12:00");
						
		Turno tqa2 = new Turno();
		tqa2.setDiaSemana("Quarta");
		tqa2.setHorario("13:00 - 16:00");
						
		Turno tqa3 = new Turno();
		tqa3.setDiaSemana("Quarta");
		tqa3.setHorario("17:00 - 23:00");
		

		//* cria os turnos pra adicionar
		Turno[] turnos3 = new Turno[3];
		turnos3[0] = tqa1;
		turnos3[1] = tqa2;
		turnos3[2] = tqa3;
		
		DiaEscala dia3 = new DiaEscala();
		dia3.setNum(3);
		
		TurnoDia[] arrayTurnoDia3 = new TurnoDia[3];
		
		for(int i = 0; i < turnos3.length; i++){
			
			//* recupera o turno
			Turno turno = turnos3[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia3[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia3.setArrayTurnoDia(arrayTurnoDia3);
				
								
		//* quinta
		Turno tqq1 = new Turno();
		tqq1.setDiaSemana("Quinta");
		tqq1.setHorario("8:00 - 12:00");
						
		Turno tqq2 = new Turno();
		tqq2.setDiaSemana("Quinta");
		tqq2.setHorario("13:00 - 16:00");
						
		Turno tqq3 = new Turno();
		tqq3.setDiaSemana("Quinta");
		tqq3.setHorario("17:00 - 23:00");
		

		//* cria os turnos pra adicionar
		Turno[] turnos4 = new Turno[3];
		turnos4[0] = tqq1;
		turnos4[1] = tqq2;
		turnos4[2] = tqq3;
		
		DiaEscala dia4 = new DiaEscala();
		dia4.setNum(4);
		
		TurnoDia[] arrayTurnoDia4 = new TurnoDia[3];
		
		for(int i = 0; i < turnos4.length; i++){
			
			//* recupera o turno
			Turno turno = turnos4[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia4[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia4.setArrayTurnoDia(arrayTurnoDia4);
				
					
		//* sexta
		Turno tss1 = new Turno();
		tss1.setDiaSemana("Sexta");
		tss1.setHorario("8:00 - 12:00");
						
		Turno tss2 = new Turno();
		tss2.setDiaSemana("Sexta");
		tss2.setHorario("13:00 - 16:00");
						
		Turno tss3 = new Turno();
		tss3.setDiaSemana("Sexta");
		tss3.setHorario("17:00 - 23:00");
		
		//* cria os turnos pra adicionar
		Turno[] turnos5 = new Turno[3];
		turnos5[0] = tss1;
		turnos5[1] = tss2;
		turnos5[2] = tss3;
		
		DiaEscala dia5 = new DiaEscala();
		dia5.setNum(5);
		
		TurnoDia[] arrayTurnoDia5 = new TurnoDia[3];
		
		for(int i = 0; i < turnos5.length; i++){
			
			//* recupera o turno
			Turno turno = turnos5[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia5[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia5.setArrayTurnoDia(arrayTurnoDia5);
				
		
		//* sabado
		Turno tsa1 = new Turno();
		tsa1.setDiaSemana("S‡bado");
		tsa1.setHorario("8:00 - 12:00");
						
		Turno tsa2 = new Turno();
		tsa2.setDiaSemana("S‡bado");
		tsa2.setHorario("13:00 - 16:00");
						
		Turno tsa3 = new Turno();
		tsa3.setDiaSemana("S‡bado");
		tsa3.setHorario("17:00 - 23:00");
		
		//* cria os turnos pra adicionar
		Turno[] turnos6 = new Turno[3];
		turnos6[0] = tsa1;
		turnos6[1] = tsa2;
		turnos6[2] = tsa3;
		
		DiaEscala dia6 = new DiaEscala();
		dia6.setNum(6);
		
		TurnoDia[] arrayTurnoDia6 = new TurnoDia[3];
		
		for(int i = 0; i < turnos6.length; i++){
			
			//* recupera o turno
			Turno turno = turnos6[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia6[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia6.setArrayTurnoDia(arrayTurnoDia6);
				
			
		//* domingo
		Turno td1 = new Turno();
		td1.setDiaSemana("Domingo");
		td1.setHorario("8:00 - 12:00");
						
		Turno td2 = new Turno();
		td2.setDiaSemana("Domingo");
		td2.setHorario("13:00 - 16:00");
						
		Turno td3 = new Turno();
		td3.setDiaSemana("Domingo");
		td3.setHorario("17:00 - 23:00");
		

		//* cria os turnos pra adicionar
		Turno[] turnos7 = new Turno[3];
		turnos7[0] = td1;
		turnos7[1] = td2;
		turnos7[2] = td3;
		
		DiaEscala dia7 = new DiaEscala();
		dia7.setNum(7);
		
		TurnoDia[] arrayTurnoDia7 = new TurnoDia[3];
		
		for(int i = 0; i < turnos7.length; i++){
			
			//* recupera o turno
			Turno turno = turnos7[i];
			
			//* cria um funcionarioTurno com tamanho do numero de funcionarios, porque cada turno
			//* estara relacionado a todos os funcionarios
			FuncionarioTurno[] arrayFuncionarioTurno = new FuncionarioTurno[funcionarios.length];
			
			//* faz um loop em todos os funcionarios e cria o objeto FuncionarioTurno que tera as preferencias,
			//* restricoes e o turno em questao
			for(int x = 0; x < funcionarios.length;  x++){
				
				FuncionarioTurno ft = new FuncionarioTurno();
				ft.setFuncionario(funcionarios[x]);
				ft.setNumPreferencia(3);
				ft.setNumRestricao(1);
				
				arrayFuncionarioTurno[x] = ft;
			}
				
			//* pra cada turno, cria um turno dia que representa aquele turno
			TurnoDia turnoDia = new TurnoDia();
			turnoDia.setTurno(turno);
			turnoDia.setArrayFuncionarioTurno(arrayFuncionarioTurno);
			
			//* atualiza o turno dia no arrayTurnoDia que contera todos os turnos e seus respectivos funcionarios
			//* para o mesmo
			arrayTurnoDia7[i] = turnoDia;
			
		}
		
		//* atualiza a informacao no objeto TurnoEscala
		dia7.setArrayTurnoDia(arrayTurnoDia7);
				
				
		return new DiaEscala[]{dia1,dia2,dia3,dia4,dia5,dia6,dia7};
		
	}
	

	


	
	
}
