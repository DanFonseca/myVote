package com.br.myvote.myvote.business.fixture;

import com.br.myvote.myvote.business.dto.AgendaDTO;
import com.br.myvote.myvote.data.entity.Agenda;

public class AgendaFixture {

    public static Agenda createAgenda(){
        Agenda agenda =  new Agenda();
        agenda.setDescription("My Agenda");
        agenda.setId(1L);
        agenda.setTitle("Title Test");

        return agenda;
    }

    public static AgendaDTO createAgendaDTO (){
        return new AgendaDTO(createAgenda());
    }

}
